package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Pantry;
import ua.lv.hoy.services.CustomerService;
import ua.lv.hoy.services.HouseService;
import ua.lv.hoy.services.PantryService;

import java.util.List;

/**
 * Created by Administrator on 20-Mar-17.
 */
@Controller
public class PantryController {
    @Autowired
    PantryService pantryService;
    @Autowired
    CustomerService customerService;
    @Autowired
    HouseService houseService;


    @RequestMapping(value = "/pantries/all", method = RequestMethod.GET)
    private  String openAllPantriesPage(Model model){
        List<Pantry> pantryList = pantryService.findAllPantries();
        model.addAttribute("pantries", pantryList );
        return "allPantries";
    }
    @RequestMapping(value = "/pantries/all/{houseId}", method = RequestMethod.GET)
    private  String openAllPantriesPage(@PathVariable Integer houseId,  Model model){
        model.addAttribute("house", houseService.findById(houseId));
        model.addAttribute("pantries", pantryService.findAllPantriesInHouse(houseId));
        return "allPantries";
    }


    @RequestMapping(value = "/pantry/add/{houseId}", method = RequestMethod.GET)
    private  String openAddPantryPage(@PathVariable Integer houseId, Model model){
        model.addAttribute("houseId", houseId);
        return "addPantry";
    }
    @RequestMapping(value = "/pantry/add", method = RequestMethod.POST)
    private String addPantry(@RequestParam ("houseId") int houseId,
                             @RequestParam("pant_number") int number,
                             @RequestParam("pant_floor") String floor,
                             @RequestParam("p_size") double projectSize,
                             @RequestParam("r_size") double realSize,
                             @RequestParam("pant_description") String description){
        pantryService.add(houseId,number, floor, projectSize, realSize,  description);
        return "redirect:/pantries/all/"+houseId;
    }
    @RequestMapping(value = "/pantry/editpage/{houseId}/{pantryId}", method = RequestMethod.GET)
    private  String openEditPantryPage(@PathVariable Integer houseId, @PathVariable Integer pantryId, Model model){
        Pantry pantry = pantryService.findById(pantryId);
        model.addAttribute("pantry", pantry);
        model.addAttribute("houseId", houseId);
        return "editPantry";
    }
    @RequestMapping(value = "/pantry/edit", method = RequestMethod.POST)
    private String editPantry(@RequestParam("pant_id") Integer id,
                              @RequestParam("houseId") Integer houseId,
                              @RequestParam("pant_number") Integer number,
                              @RequestParam("pant_floor") String floor,
                              @RequestParam("pant_p_size") Double projectSize,
                              @RequestParam("pant_r_size") Double realSize,
                              @RequestParam("pant_status") String status,
                              @RequestParam("pant_description") String description){
        pantryService.edit(id, number, floor, projectSize, realSize, status, description);
        return "redirect:/pantries/all/"+houseId;
    }
    @RequestMapping(value = "/pantry/delete/{houseId}/{pantryId}", method = RequestMethod.GET)
    private String deletePantry(@PathVariable Integer houseId, @PathVariable Integer pantryId){
        pantryService.delete(pantryId);
        return "redirect:/pantries/all/"+houseId;
    }

   @RequestMapping(value = "/pantry/buyPantryPage/{houseId}/{customerId}", method = RequestMethod.GET)
    private String buyPantryPage(@PathVariable Integer houseId,@PathVariable Integer customerId, Model model){
        model.addAttribute("customer", customerService.findById(customerId));
        model.addAttribute("freePantries", pantryService.fiindFreePantriesInHouse(houseId));
        model.addAttribute("customer_sPantries", pantryService.findByCustomerId(customerId));
        return "buyPantry";
    }
    @RequestMapping(value = "/pantry/buy", method = RequestMethod.POST)
    private String buyPantry (@RequestParam("customer_id") Integer customer_id,
                              @RequestParam("houseId") Integer houseId,
                                @RequestParam("pantryId") Integer pantryId){
        pantryService.buy(pantryId, customer_id);
        return "redirect:/customers/all/inhouse/"+houseId;

    }
    @RequestMapping(value = "/pantry/takePage/{houseId}/{id}", method = RequestMethod.GET)
    private String takeParkingPage(@PathVariable Integer houseId,@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.findById(id));
        model.addAttribute("customer_sPantries", pantryService.findByCustomerId(id));
        model.addAttribute("houseId",houseId);
        return "takePantry";
    }
    @RequestMapping(value = "/pantry/take", method = RequestMethod.POST)
    private String takeParking(@RequestParam("houseId") Integer houseId,
                               @RequestParam("customer_id") Integer customer_id,
                               @RequestParam("pantryId") Integer pantryId){
        pantryService.takeOut(pantryId, customer_id);
        return "redirect:/customers/all/inhouse/"+houseId;
    }
}
