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

    static final String REDIRECT_HOME_PAGE = "redirect:/";
    private static final String HOME = "home";
    private static final String LOGIN = "login";
    private static final String CABINET = "cabinet";
    private static final String PRINCIPAL = "principal";

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
    @Autowired
    private HouseService houseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private  String  openHomePage(Model model){
        model.addAttribute("houses", houseService.findAll());
        return HOME;
    }

    @RequestMapping(value = "/loginpage", method = RequestMethod.GET)
    private String openLoginPage(){
        return LOGIN;
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public  String opeCabinet(Principal principal, Model model){
        model.addAttribute(PRINCIPAL, principal.getName());

        List<Schedule>scheduleList = scheduleService.findAllCustomerSchedules(principal.getName());
        model.addAttribute(ScheduleController.SCHEDULES, scheduleList);

        List<Payment>paymentList = paymentService.findPaymentsByCustomerEmail(principal.getName());
        model.addAttribute(PaymentController.PAYMENTS, paymentList);

        double amountUSA = paymentService.getPaymentAmount(principal.getName());
        model.addAttribute("amountUSA", amountUSA);

        Customer customer = customerService.findCustomerByLogin(principal.getName());
        List<Flat>flatList = flatService.findByCustomerId(customer.getId());
        model.addAttribute(FlatController.CUSTOMER_S_FLATS, flatList);

        List<Pantry>pantryList =pantryService.findByCustomerId(customer.getId());
        model.addAttribute(PantryController.CUSTOMER_S_PANTRIES, pantryList);

        List<Parking>parkingList = parkingService.findAllByCustomerId(customer.getId());
        model.addAttribute(ParkingController.CUSTOMER_S_PARKING, parkingList);

        return CABINET;
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

        return HOME;
    }
}
