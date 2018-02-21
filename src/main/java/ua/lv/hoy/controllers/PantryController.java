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
public class PantryController  {

    static final String PANTRY = "pantry";
    static final String PANTRIES = "pantries";
    static final String ALL_PANTRIES = "allPantries";
    static final String REDIRECT_ALL_PANTRIES = "redirect:/pantries/all/";

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
    @RequestMapping(value = "/pantries/all/{houseId}", method = RequestMethod.GET)
    private  String openAllPantriesPage(@PathVariable Integer houseId,  Model model){
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        model.addAttribute(PANTRIES, pantryService.findAllPantriesInHouse(houseId));
        return ALL_PANTRIES;
    }

    @RequestMapping(value = "/pantry/add/{houseId}", method = RequestMethod.GET)
    private  String openAddPantryPage(@PathVariable Integer houseId, Model model){
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        model.addAttribute(PANTRY, new Pantry());
        return "addPantry";
    }
    @RequestMapping(value = "/pantry/add/{houseId}", method = RequestMethod.POST)
    private String addPantry(@PathVariable int houseId,
                             @ModelAttribute Pantry pantry){
        pantryService.add(houseId,pantry);
        return REDIRECT_ALL_PANTRIES + houseId;
    }
    @RequestMapping(value = "/pantry/editpage/{pantryId}", method = RequestMethod.GET)
    private  String openEditPantryPage(@PathVariable Integer pantryId,
                                       Model model){
        model.addAttribute(PANTRY, pantryService.findById(pantryId));
        model.addAttribute("editedPantry", new Pantry());
        return "editPantry";
    }
    @RequestMapping(value = "/pantry/edit/{pantryId}", method = RequestMethod.POST)
    private String editPantry(@PathVariable Integer pantryId,
                              @ModelAttribute Pantry editedPantry){
        pantryService.edit(pantryId,  editedPantry);
        return REDIRECT_ALL_PANTRIES +  pantryService.findById(pantryId).getHouse().getId();
    }
    @RequestMapping(value = "/pantry/delete/{houseId}/{pantryId}", method = RequestMethod.GET)
    private String deletePantry(@PathVariable Integer houseId, @PathVariable Integer pantryId){
        pantryService.delete(pantryId);
        return REDIRECT_ALL_PANTRIES + houseId;
    }

   @RequestMapping(value = "/pantry/buyPantryPage/{houseId}/{customerId}", method = RequestMethod.GET)
    private String buyPantryPage(@PathVariable Integer houseId,@PathVariable Integer customerId, Model model){
        model.addAttribute(CustomerController.CUSTOMER, customerService.findById(customerId));
        model.addAttribute("freePantries", pantryService.fiindFreePantriesInHouse(houseId));
        model.addAttribute("customer_sPantries", pantryService.findByCustomerId(customerId));
        return "buyPantry";
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
        return "takePantry";
    }
    @RequestMapping(value = "/pantry/take", method = RequestMethod.POST)
    private String takeParking(@RequestParam("houseId") Integer houseId,
                               @RequestParam("customerId") Integer customerId,
                               @RequestParam("pantryId") Integer pantryId){
        pantryService.takeOut(pantryId, customerId);
        return CustomerController.REDIRECT_CUSTOMER_ALL_INHOUSE + houseId;
    }
}
