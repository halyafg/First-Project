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
public class ParkingController extends BaseController  {
    private static final String PARKING = "parking";
    private static final String PARKINGS = "parkings";
    private static final String ALL_PARKINGS = "allParkings";
    private static final String ADD_PARKING = "addParking";
    private static final String EDIT_PARKING = "editParking";
    private static final String BUY_PARKING = "buyParking";
    private static final String TAKE_PARKING = "takeParking";
    static final String FREE_PARKINGS = "freeParkings";
    static final String CUSTOMER_S_PARKING = "customer_sParkings";
    private static final String REDIRECT_PARKINGS_ALL = "redirect:/parkings/all/";

    @Autowired
    ParkingService parkingService;
    @Autowired
    CustomerService customerService;
    @Autowired
    HouseService houseService;

    @Override
    @RequestMapping(value = "/parkings/all/{houseId}", method = RequestMethod.GET)
    String findAll(@PathVariable int houseId, Model model) {
        model.addAttribute(PARKINGS, parkingService.findAllParkingsInHouse(houseId));
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        return ALL_PARKINGS;
    }

    @Override
    @RequestMapping(value = "/parking/addpage/{houseId}", method = RequestMethod.GET)
    String openAddPage(@PathVariable int houseId, Model model) {
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        model.addAttribute(PARKING, new Parking());
        return ADD_PARKING;
    }

    @RequestMapping(value = "/parking/add/{houseId}", method = RequestMethod.POST)
    private String addParking(@ModelAttribute Parking parking,
                              @PathVariable int houseId){
        parkingService.add(parking, houseId);
        return REDIRECT_PARKINGS_ALL + houseId;
    }

    @Override
    @RequestMapping(value = "/parking/editpage/{parkingId}", method = RequestMethod.GET)
    String openEditPage(@PathVariable int parkingId, Model model) {
        model.addAttribute(PARKING, parkingService.findById(parkingId));
        model.addAttribute("editedParking", new Parking());
        return EDIT_PARKING;
    }

    @RequestMapping(value = "/parking/edit/{parkingId}", method = RequestMethod.POST)
    private String editParking(@PathVariable Integer parkingId,
                               @ModelAttribute Parking editedParking){
        parkingService.edit(parkingId, editedParking);
        return REDIRECT_PARKINGS_ALL + parkingService.findById(parkingId).getHouse().getId();
    }

    @Override
    @RequestMapping(value = "/parking/delete/{houseId}/{id}", method = RequestMethod.GET)
    String delete(@PathVariable int houseId, @PathVariable int id) {
        parkingService.delete(id);
        return REDIRECT_PARKINGS_ALL + houseId;
    }

    @RequestMapping(value = "/parking/buyPage/{houseId}/{customerId}", method = RequestMethod.GET)
    private String buyParkingPage(@PathVariable Integer houseId,
                                  @PathVariable Integer customerId,
                                  Model model){
        model.addAttribute(CustomerController.CUSTOMER, customerService.findById(customerId));
        model.addAttribute(FREE_PARKINGS, parkingService.findFreeParkings(houseId));
        model.addAttribute(CUSTOMER_S_PARKING, parkingService.findAllByCustomerId(customerId));
        return BUY_PARKING;
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
        model.addAttribute(CUSTOMER_S_PARKING, parkingService.findAllByCustomerId(id));
        model.addAttribute(HouseController.HOUSE_ID, houseId);
        return TAKE_PARKING;
    }
    @RequestMapping(value = "/parking/take", method = RequestMethod.POST)
    private String takeParking(@RequestParam("houseId") Integer houseId,
                               @RequestParam("customerId") Integer customerId,
                               @RequestParam("parkingId") Integer parkingId){
        parkingService.takeParking(parkingId, customerId);
        return CustomerController.REDIRECT_CUSTOMER_ALL_INHOUSE + houseId;
    }

}
