package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lv.hoy.entity.Pantry;
import ua.lv.hoy.entity.Parking;
import ua.lv.hoy.services.CustomerService;
import ua.lv.hoy.services.HouseService;
import ua.lv.hoy.services.PantryService;

import java.util.List;

/**
 * Created by Administrator on 20-Mar-17.
 */
@Controller
public class PantryController extends BaseController  {

    private static final String PANTRY = "pantry";
    private static final String PANTRIES = "pantries";
    private static final String ALL_PANTRIES = "allPantries";
    private static final String ADD_PANTRY = "addPantry";
    private static final String EDIT_PANTRY = "editPantry";
    private static final String BUY_PANTRY = "buyPantry";
    private static final String TAKE_PANTRY = "takePantry";
    static final String FREE_PANTRIES = "freePantries";
    static final String CUSTOMER_S_PANTRIES = "customer_sPantries";
    private static final String REDIRECT_ALL_PANTRIES = "redirect:/pantries/all/";

    @Autowired
    PantryService pantryService;
    @Autowired
    CustomerService customerService;
    @Autowired
    HouseService houseService;


    @RequestMapping(value = "/pantries/all", method = RequestMethod.GET)
    private  String openAllPantriesPage(Model model){
        List<Pantry> pantryList = pantryService.findAllPantries();
        model.addAttribute(PANTRIES, pantryList );
        return ALL_PANTRIES;
    }

    @Override
    @RequestMapping(value = "/pantries/all/{houseId}", method = RequestMethod.GET)
    String findAll(@PathVariable int houseId, Model model) {
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        model.addAttribute(PANTRIES, pantryService.findAllPantriesInHouse(houseId));
        return ALL_PANTRIES;
    }
    @Override
    @RequestMapping(value = "/pantry/add/{houseId}", method = RequestMethod.GET)
    String openAddPage(@PathVariable int houseId, Model model) {
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        model.addAttribute(PANTRY, new Pantry());
        return ADD_PANTRY;
    }

    @RequestMapping(value = "/pantry/add/{houseId}", method = RequestMethod.POST)
    private String addPantry(@PathVariable int houseId,
                             @ModelAttribute Pantry pantry){
        pantryService.add(houseId,pantry);
        return REDIRECT_ALL_PANTRIES + houseId;
    }

    @Override
    @RequestMapping(value = "/pantry/editpage/{pantryId}", method = RequestMethod.GET)
    String openEditPage(@PathVariable int pantryId, Model model) {
        model.addAttribute(PANTRY, pantryService.findById(pantryId));
        model.addAttribute("editedPantry", new Pantry());
        return EDIT_PANTRY;
    }

    @RequestMapping(value = "/pantry/edit/{pantryId}", method = RequestMethod.POST)
    private String editPantry(@PathVariable Integer pantryId,
                              @ModelAttribute Pantry editedPantry){
        pantryService.edit(pantryId,  editedPantry);
        return REDIRECT_ALL_PANTRIES +  pantryService.findById(pantryId).getHouse().getId();
    }

    @Override
    @RequestMapping(value = "/pantry/delete/{houseId}/{pantryId}", method = RequestMethod.GET)
    String delete(@PathVariable int houseId, @PathVariable int pantryId) {
        pantryService.delete(pantryId);
        return REDIRECT_ALL_PANTRIES + houseId;
    }

    @RequestMapping(value = "/pantry/buyPantryPage/{houseId}/{customerId}", method = RequestMethod.GET)
    private String buyPantryPage(@PathVariable Integer houseId,@PathVariable Integer customerId, Model model){
        model.addAttribute(CustomerController.CUSTOMER, customerService.findById(customerId));
        model.addAttribute(FREE_PANTRIES, pantryService.fiindFreePantriesInHouse(houseId));
        model.addAttribute("customer_sPantries", pantryService.findByCustomerId(customerId));
        return BUY_PANTRY;
    }
    @RequestMapping(value = "/pantry/buy", method = RequestMethod.POST)
    private String buyPantry (@RequestParam("customerId") Integer customerId,
                              @RequestParam("houseId") Integer houseId,
                              @RequestParam("pantryId") Integer pantryId){
        pantryService.buy(pantryId, customerId);
        return CustomerController.REDIRECT_CUSTOMER_ALL_INHOUSE + houseId;

    }
    @RequestMapping(value = "/pantry/takePage/{houseId}/{id}", method = RequestMethod.GET)
    private String takeParkingPage(@PathVariable Integer houseId,@PathVariable Integer id, Model model){
        model.addAttribute(CustomerController.CUSTOMER, customerService.findById(id));
        model.addAttribute("customer_sPantries", pantryService.findByCustomerId(id));
        model.addAttribute(HouseController.HOUSE_ID,houseId);
        return TAKE_PANTRY;
    }
    @RequestMapping(value = "/pantry/take", method = RequestMethod.POST)
    private String takeParking(@RequestParam("houseId") Integer houseId,
                               @RequestParam("customerId") Integer customerId,
                               @RequestParam("pantryId") Integer pantryId){
        pantryService.takeOut(pantryId, customerId);
        return CustomerController.REDIRECT_CUSTOMER_ALL_INHOUSE + houseId;
    }
}
