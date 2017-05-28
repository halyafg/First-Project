package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.House;
import ua.lv.hoy.entity.Parking;
import ua.lv.hoy.services.CustomerService;
import ua.lv.hoy.services.HouseService;
import ua.lv.hoy.services.ParkingService;

import java.util.List;

/**
 * Created by Administrator on 20-Mar-17.
 */
@Controller
public class ParkingController {


    @Autowired
    ParkingService parkingService;
    @Autowired
    CustomerService customerService;
    @Autowired
    HouseService houseService;


    @RequestMapping(value = "/parkings/all/{houseId}", method = RequestMethod.GET)
    private  String openAllParkingsPage(@PathVariable int houseId ,Model model){

        List<Parking> parkingList = parkingService.findAllPantries();
        model.addAttribute("parkings",parkingList);
        model.addAttribute("house", houseService.findById(houseId));
        return "allParkings";
    }
    @RequestMapping(value = "/parking/addpage/{houseId}", method = RequestMethod.GET)
    private  String openAddParkingPage(@PathVariable int houseId, Model model){
        model.addAttribute("houseId", houseId);
        return "addParking";
    }
    @RequestMapping(value = "/parking/add", method = RequestMethod.POST)
    private String addParking(@RequestParam("number") int number, @RequestParam("houseId") int houseId){
        parkingService.add(number, houseId);
        return "redirect:/parkings/all/"+houseId;
    }
    @RequestMapping(value = "/parking/editpage/{parkingId}", method = RequestMethod.GET)
    private  String openEditParkingage( @PathVariable Integer parkingId, Model model){
        Parking parking = parkingService.findById(parkingId);
        model.addAttribute("parking", parking);
        return "editParking";
    }
    @RequestMapping(value = "/parking/edit", method = RequestMethod.POST)
    private String editParking(/*@RequestParam("houseId") Integer houseId,*/
                               @RequestParam("id") Integer id,
                               @RequestParam("number") Integer number,
                               @RequestParam("status") String status){
        parkingService.edit(id, number, status);
        return "redirect:/parkings/all/"+parkingService.findById(id).getHouse().getId();
    }
    @RequestMapping(value = "/parking/delete/{houseId}/{id}", method = RequestMethod.GET)
    private String deleteParking(@PathVariable Integer houseId, @PathVariable Integer id){
        parkingService.delete(id);
        return "redirect:/parkings/all/"+houseId;
    }
    @RequestMapping(value = "/parking/buyPage/{houseId}/{customerId}", method = RequestMethod.GET)
    private String buyParkingPage(@PathVariable Integer houseId, @PathVariable Integer customerId, Model model){
        model.addAttribute("customer", customerService.findById(customerId));
        model.addAttribute("freeParkings", parkingService.findFreeParkings(houseId));
        model.addAttribute("customer_sParkings", parkingService.findAllByCustomerId(customerId));
        return "buyParking";
    }

    @RequestMapping(value = "/parking/buy", method = RequestMethod.POST)
    private String buyParking(@RequestParam("houseId") Integer houseId,
                              @RequestParam("customer_id") Integer customer_id,
                              @RequestParam("parkingId") Integer parkingId){

        parkingService.buy(parkingId, customer_id);

        return "redirect:/customers/all/inhouse/"+houseId;
    }

    @RequestMapping(value = "/parking/takePage/{houseId}/{id}", method = RequestMethod.GET)
    private String takeParkingPage(@PathVariable Integer houseId, @PathVariable Integer id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        List<Parking>parkingList1 = parkingService.findAllByCustomerId(id);
        model.addAttribute("customer_sParkings", parkingList1);
        model.addAttribute("houseId", houseId);
        return "takeParking";
    }
    @RequestMapping(value = "/parking/take", method = RequestMethod.POST)
    private String takeParking(@RequestParam("houseId") Integer houseId,
                               @RequestParam("customer_id") Integer customer_id,
                              @RequestParam("parkingId") Integer parkingId){
        parkingService.takeParking(parkingId, customer_id);

        return "redirect:/customers/all/inhouse/"+houseId;
    }

}
