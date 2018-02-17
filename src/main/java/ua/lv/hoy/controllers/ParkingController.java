package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lv.hoy.entity.Parking;
import ua.lv.hoy.services.CustomerService;
import ua.lv.hoy.services.HouseService;
import ua.lv.hoy.services.ParkingService;

/**
 * Created by Administrator on 20-Mar-17.
 */
@Controller
public class ParkingController {
    private static final String PARKING = "parking";
    private static final String PARKINGS = "parkings";
    private static final String REDIRECT_PARKINGS_ALL = "redirect:/parkings/all/";

    @Autowired
    ParkingService parkingService;
    @Autowired
    CustomerService customerService;
    @Autowired
    HouseService houseService;


    @RequestMapping(value = "/parkings/all/{houseId}", method = RequestMethod.GET)
    private  String openAllParkingsPage(@PathVariable int houseId ,Model model){
        model.addAttribute(PARKINGS, parkingService.findAllPantries());
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        return "allParkings";
    }
    @RequestMapping(value = "/parking/addpage/{houseId}", method = RequestMethod.GET)
    private  String openAddParkingPage(@PathVariable int houseId, Model model){
        model.addAttribute(HouseController.HOUSE_ID, houseId);
        model.addAttribute(PARKING, new Parking());
        return "addParking";
    }
    @RequestMapping(value = "/parking/add/{houseId}", method = RequestMethod.POST)
    private String addParking(@ModelAttribute Parking parking,
                              @PathVariable int houseId){
        parkingService.add(parking, houseId);
        return REDIRECT_PARKINGS_ALL + houseId;
    }
    @RequestMapping(value = "/parking/editpage/{parkingId}", method = RequestMethod.GET)
    private  String openEditParkingage( @PathVariable Integer parkingId, Model model){
        model.addAttribute(PARKING, parkingService.findById(parkingId));
        return "editParking";
    }
    @RequestMapping(value = "/parking/edit", method = RequestMethod.POST)
    private String editParking(@RequestParam("id") Integer id,
                               @RequestParam("number") Integer number,
                               @RequestParam("status") String status){
        parkingService.edit(id, number, status);
        return REDIRECT_PARKINGS_ALL + parkingService.findById(id).getHouse().getId();
    }
    @RequestMapping(value = "/parking/delete/{houseId}/{id}", method = RequestMethod.GET)
    private String deleteParking(@PathVariable Integer houseId,
                                 @PathVariable Integer id){
        parkingService.delete(id);
        return REDIRECT_PARKINGS_ALL + houseId;
    }
    @RequestMapping(value = "/parking/buyPage/{houseId}/{customerId}", method = RequestMethod.GET)
    private String buyParkingPage(@PathVariable Integer houseId,
                                  @PathVariable Integer customerId,
                                  Model model){
        model.addAttribute(CustomerController.CUSTOMER, customerService.findById(customerId));
        model.addAttribute("freeParkings", parkingService.findFreeParkings(houseId));
        model.addAttribute("customer_sParkings", parkingService.findAllByCustomerId(customerId));
        return "buyParking";
    }

    @RequestMapping(value = "/parking/buy", method = RequestMethod.POST)
    private String buyParking(@RequestParam("houseId") Integer houseId,
                              @RequestParam("customerId") Integer customerId,
                              @RequestParam("parkingId") Integer parkingId){
        parkingService.buy(parkingId, customerId);
        return CustomerController.REDIRECT_CUSTOMER_ALL_INHOUSE + houseId;
    }

    @RequestMapping(value = "/parking/takePage/{houseId}/{id}", method = RequestMethod.GET)
    private String takeParkingPage(@PathVariable Integer houseId,
                                   @PathVariable Integer id,
                                   Model model){
        model.addAttribute(CustomerController.CUSTOMER, customerService.findById(id));
        model.addAttribute("customer_sParkings", parkingService.findAllByCustomerId(id));
        model.addAttribute(HouseController.HOUSE_ID, houseId);
        return "takeParking";
    }
    @RequestMapping(value = "/parking/take", method = RequestMethod.POST)
    private String takeParking(@RequestParam("houseId") Integer houseId,
                               @RequestParam("customerId") Integer customerId,
                               @RequestParam("parkingId") Integer parkingId){
        parkingService.takeParking(parkingId, customerId);
        return CustomerController.REDIRECT_CUSTOMER_ALL_INHOUSE + houseId;
    }

}
