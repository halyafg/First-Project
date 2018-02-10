package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lv.hoy.entity.Flat;
import ua.lv.hoy.services.CustomerService;
import ua.lv.hoy.services.FlatService;
import ua.lv.hoy.services.HouseService;

/**
 * Created by Administrator on 22-Mar-17.
 */
@Controller
public class FlatController {

    static final String FLAT = "flat";
    static final String FLATS = "flats";
    static final String REDIRECT_FLATS_ALL = "redirect:/flats/all/";

    @Autowired
    FlatService flatService;
    @Autowired
    CustomerService customerService;
    @Autowired
    HouseService houseService;

    @RequestMapping(value = "/flat/inf/{flatId}", method = RequestMethod.GET)
    private String openFlatImage(@PathVariable Integer flatId, Model model){
        Flat flat = flatService.findById(flatId);
        model.addAttribute(FLAT, flat);
        return "flatInf";
    }

    @RequestMapping(value = "/flats/all/{houseId}", method = RequestMethod.GET)
    private  String openAllFlatsPage(@PathVariable Integer houseId, Model model){
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        model.addAttribute(FLATS, flatService.findAllFlatsInHouse(houseId));
        return "allFlats";
    }

    @RequestMapping(value = "/flat/add/{id}", method = RequestMethod.GET)
    private  String openAddFlatPage(@PathVariable Integer id, Model model){
        model.addAttribute(HouseController.HOUSE, houseService.findById(id));
        return "addFlat";
    }

    @RequestMapping(value = "/flat/add", method = RequestMethod.POST)
    private String addFlat(@RequestParam("houseId") int houseId,
                            @RequestParam("flName") int number,
                           @RequestParam("flFloor") int floor,
                           @RequestParam("flRooms") int rooms,
                           @RequestParam("flPrSize") double projectSize,
                           @RequestParam("flRealSize") double realSize,
                           @RequestParam("flDescription") String description){
        houseService.addFlatToHouse(houseId, number, floor, rooms, projectSize, realSize, description);
        return REDIRECT_FLATS_ALL + houseId;
    }
    @RequestMapping(value = "/flat/editpage/{houseId},{flatId}", method = RequestMethod.GET)
    private  String openEditFlatPage(@PathVariable Integer houseId,@PathVariable Integer flatId, Model model){
        model.addAttribute(FLAT, flatService.findById(flatId));
        model.addAttribute(HouseController.HOUSE_ID,houseId);
        return "editFlat";
    }
    @RequestMapping(value = "/flat/edit", method = RequestMethod.POST)
    private String editFlat(@RequestParam("flId") Integer id,
                            @RequestParam("houseId") Integer houseId,
                            @RequestParam("flName") Integer number,
                            @RequestParam("flFloor") Integer floor,
                            @RequestParam("flRooms") Integer rooms,
                            @RequestParam("flPrSize") Double projectSize,
                            @RequestParam("flRealSize") Double realSize,
                            @RequestParam("flStatus") String status,
                            @RequestParam("flDescription") String description){
        flatService.edit(id, number, floor, rooms, projectSize, realSize, status, description);
        return REDIRECT_FLATS_ALL + houseId;
    }
    @RequestMapping(value = "/flat/delete/{houseId},{flatId}", method = RequestMethod.GET)
    private String deleteFlat(@PathVariable Integer houseId,
                              @PathVariable Integer flatId){
        flatService.delete(flatId);
        return REDIRECT_FLATS_ALL + houseId;
    }

    @RequestMapping(value = "/flat/buypage/{houseId}/{customerId}", method = RequestMethod.GET)
    private String buyFlatPage(@PathVariable Integer houseId, @PathVariable Integer customerId, Model model){
        model.addAttribute("freeFlats", flatService.findFreeFlatsInHouse(houseId));
        model.addAttribute("customer_sFlats", flatService.findByCustomerId(customerId));
        model.addAttribute(CustomerController.CUSTOMER, customerService.findById(customerId));
        model.addAttribute(HouseController.HOUSE_ID, houseId);
        return "buyFlat";
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
        return "takeFlat";
    }

    @RequestMapping(value = "/flat/take", method = RequestMethod.POST)
    private String takeFlat(@RequestParam("houseId") Integer houseId,
                            @RequestParam("customerId") Integer customerId,
                            @RequestParam("flatId") Integer flatId){
        flatService.takeAway(flatId, customerId);
        return CustomerController.REDIRECT_CUSTOMER_ALL_INHOUSE + houseId;
    }


}
