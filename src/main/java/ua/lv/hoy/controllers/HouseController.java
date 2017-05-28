package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lv.hoy.entity.House;
import ua.lv.hoy.services.FlatService;
import ua.lv.hoy.services.HouseService;
import ua.lv.hoy.services.PantryService;
import ua.lv.hoy.services.ParkingService;

/**
 * Created by Administrator on 07-Apr-17.
 */
@Controller
@Transactional
public class HouseController {

    @Autowired
    private HouseService houseService;
    @Autowired
    private  FlatService flatService;
    @Autowired
    private  PantryService pantryService;
    @Autowired
    private ParkingService parkingService;

    @RequestMapping(value = "/houses/all", method = RequestMethod.GET)
    private String allHouses(){
        return "";
    }

    @RequestMapping(value = "/houses/addpage", method = RequestMethod.GET)
    private String addHousePage(){
        return "addHouse";
    }

    @RequestMapping(value = "/house/add", method = RequestMethod.POST)
    private String addHouse(@RequestParam ("name") String name,
                            @RequestParam("address") String address,
                            @RequestParam("description") String description){

        houseService.add(name, address, description);
        return "redirect:/";
    }

    @RequestMapping(value = "/house/editpage/{houseId}", method = RequestMethod.GET)
    private  String openEditHousePage(@PathVariable Integer houseId, Model model){
        House house = houseService.findById(houseId);
        model.addAttribute("house", house);
        return "editHouse";
    }
    @RequestMapping(value = "/house/edit", method = RequestMethod.POST)
    private String editHouse(@RequestParam("houseId") Integer id,
                            @RequestParam("house_name") String house_name,
                            @RequestParam("house_description") String description,
                            @RequestParam("house_address") String house_address){
        houseService.edit(id, house_name, house_address, description);
        return "redirect:/";
    }

    @RequestMapping(value = "/house/delete/{houseId}", method = RequestMethod.GET)
    private String deleteHouse(@PathVariable Integer houseId){
        houseService.delete(houseId);
        return "redirect:/";
    }

    @RequestMapping(value = "/house/page/{houseId}", method = RequestMethod.GET)
    private String housePage(@PathVariable Integer houseId, Model model){
        model.addAttribute("house", houseService.findById(houseId));
        model.addAttribute("flats1", flatService.findFreeFlatByRoomsNumber(1,houseId));
        model.addAttribute("flats2", flatService.findFreeFlatByRoomsNumber(2,houseId));
        model.addAttribute("flats3", flatService.findFreeFlatByRoomsNumber(3, houseId));
        model.addAttribute("freePantries", pantryService.fiindFreePantriesInHouse(houseId));
        model.addAttribute("freeParkings", parkingService.findFreeParkings(houseId));
        return "housePage";
    }
}
