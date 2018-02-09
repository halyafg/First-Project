package ua.lv.hoy.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.lv.hoy.services.*;


/**
 * Created by Administrator on 02-Mar-17.
 */
@Controller
public class BaseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private  String  homePage(Model model){

        model.addAttribute("houses", houseService.findAll());
        return "home";
    }

    @RequestMapping(value = "/loginpage", method = RequestMethod.GET)
    private String openLoginPage(){
        return "login";
    }

}
