package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lv.hoy.entity.Flat;
import ua.lv.hoy.services.CustomerService;
import ua.lv.hoy.services.FlatService;
import ua.lv.hoy.services.HouseService;

/**
 * Created by Administrator on 22-Mar-17.
 */
@Controller
public class FlatController extends BaseController {

    private static final String FLAT = "flat";
    private static final String FLATS = "flats";
    private static final String FLAT_INF = "flatInf";
    private static final String ALL_FLATS = "allFlats";
    private static final String ADD_FLAT = "addFlat";
    private static final String EDIT_FLAT = "editFlat";
    private static final String BUY_FLAT = "buyFlat";
    private static final String TAKE_FLAT = "takeFlat";
    static final String CUSTOMER_S_FLATS = "customer_sFlats";
    private static final String REDIRECT_FLATS_ALL = "redirect:/flats/all/";

    @Autowired
    FlatService flatService;
    @Autowired
    CustomerService customerService;
    @Autowired
    HouseService houseService;

    @RequestMapping(value = "/flat/inf/{flatId}", method = RequestMethod.GET)
    private String openFlatImage(@PathVariable Integer flatId,
                                 Model model){
        model.addAttribute(FLAT, flatService.findById(flatId));
        return FLAT_INF;
    }

    @Override
    @RequestMapping(value = "/flats/all/{houseId}", method = RequestMethod.GET)
    String findAll(@PathVariable int houseId, Model model) {
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        model.addAttribute(FLATS, flatService.findAllFlatsInHouse(houseId));
        return ALL_FLATS;
    }

    @Override
    @RequestMapping(value = "/flat/add/{houseId}", method = RequestMethod.GET)
    String openAddPage(@PathVariable int houseId, Model model) {
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        model.addAttribute(FLAT, new Flat());
        return ADD_FLAT;
    }

    @RequestMapping(value = "/flat/add/{houseId}", method = RequestMethod.POST)
    private String addFlat(@PathVariable int houseId,
                           @ModelAttribute Flat flat){
        flatService.addFlatToHouse(houseId, flat);
        return REDIRECT_FLATS_ALL + houseId;
    }

    @Override
    @RequestMapping(value = "/flat/editpage/{flatId}", method = RequestMethod.GET)
    String openEditPage(@PathVariable int flatId,
                        Model model) {
        model.addAttribute(FLAT, flatService.findById(flatId));
        return EDIT_FLAT;
    }

    @RequestMapping(value = "/flat/edit", method = RequestMethod.POST)
    private String editFlat(@RequestParam("flId") Integer flatId,
                            @ModelAttribute Flat flat){
        flatService.edit(flatId, flat);
        return REDIRECT_FLATS_ALL + flat.getHouse().getId();
    }

    @Override
    @RequestMapping(value = "/flat/delete/{houseId},{flatId}", method = RequestMethod.GET)
    String delete(@PathVariable int houseId, @PathVariable int flatId) {
        flatService.delete(flatId);
        return REDIRECT_FLATS_ALL + houseId;
    }

    @RequestMapping(value = "/flat/buypage/{houseId}/{customerId}", method = RequestMethod.GET)
    private String openBuyFlatPage(@PathVariable Integer houseId, @PathVariable Integer customerId, Model model){
        model.addAttribute("freeFlats", flatService.findFreeFlatsInHouse(houseId));
        model.addAttribute("customer_sFlats", flatService.findByCustomerId(customerId));
        model.addAttribute(CustomerController.CUSTOMER, customerService.findById(customerId));
        model.addAttribute(HouseController.HOUSE_ID, houseId);
        return BUY_FLAT;
    }

    @RequestMapping(value = "/flat/buy", method = RequestMethod.POST)
    private String buyFlat(@RequestParam("houseId") Integer houseId,
                           @RequestParam("customerId") Integer customerId,
                           @RequestParam("flatId") Integer flatId){
        flatService.buy(flatId, customerId);
        return CustomerController.REDIRECT_CUSTOMER_ALL_INHOUSE + houseId;
    }

    @RequestMapping(value = "/flat/takepage/{houseId}/{id}", method = RequestMethod.GET)
    private String takeFlatPage(@PathVariable Integer houseId, @PathVariable Integer id, Model model){
        model.addAttribute("customer_sFlats", flatService.findByCustomerId(id));
        model.addAttribute(CustomerController.CUSTOMER, customerService.findById(id));
        model.addAttribute(HouseController.HOUSE_ID, houseId);
        return TAKE_FLAT;
    }

    @RequestMapping(value = "/flat/take", method = RequestMethod.POST)
    private String takeFlat(@RequestParam("houseId") Integer houseId,
                            @RequestParam("customerId") Integer customerId,
                            @RequestParam("flatId") Integer flatId){
        flatService.takeAway(flatId, customerId);
        return CustomerController.REDIRECT_CUSTOMER_ALL_INHOUSE + houseId;
    }


}
