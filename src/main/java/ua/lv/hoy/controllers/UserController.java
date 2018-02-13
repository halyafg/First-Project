package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lv.hoy.entity.*;
import ua.lv.hoy.services.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Administrator on 19-Mar-17.
 */
@Controller
public class UserController {


    @Autowired
    ScheduleService scheduleService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    FlatService flatService;
    @Autowired
    CustomerService customerService;
    @Autowired
    PantryService pantryService;
    @Autowired
    ParkingService parkingService;

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public  String cabinet(Principal principal, Model model){
        model.addAttribute("principal", principal.getName());

        List<Schedule>scheduleList = scheduleService.findAllCustomerSchedules(principal.getName());
        model.addAttribute(ScheduleController.SCHEDULES, scheduleList);

        List<Payment>paymentList = paymentService.findPaymentsByCustomerEmail(principal.getName());
        model.addAttribute("payments", paymentList);

        double amountUSA = paymentService.paymentAmount(principal.getName());
        model.addAttribute("amountUSA", amountUSA);

        Customer customer = customerService.findCustomerByLogin(principal.getName());
        List<Flat>flatList = flatService.findByCustomerId(customer.getId());
        model.addAttribute("customerFlats", flatList);

        List<Pantry>pantryList =pantryService.findByCustomerId(customer.getId());
        model.addAttribute("customerPantries", pantryList);

        List<Parking>parkingList = parkingService.findAllByCustomerId(customer.getId());
        model.addAttribute("customerParkings", parkingList);

        return "cabinet";
    }

    @RequestMapping(value = "/change/passwordpage", method = RequestMethod.GET)
    public String changePasswordPage(Principal principal, Model model){
        model.addAttribute("userLogin", principal.getName());
        return "changePassword";
    }

    @RequestMapping(value = "/password/change", method = RequestMethod.POST)
    public String changePassword(@RequestParam("customerLogin") String customerLogin,
                                 @RequestParam("password") String password){
        Customer customer = customerService.findCustomerByLogin(customerLogin);
        customer.setPassword(password);
        customerService.edit(customer.getId(),customer);

        return "home";
    }
}
