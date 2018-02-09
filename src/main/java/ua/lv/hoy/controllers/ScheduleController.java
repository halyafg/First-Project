package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.entity.Payment;
import ua.lv.hoy.entity.Schedule;
import ua.lv.hoy.services.CustomerService;
import ua.lv.hoy.services.HouseService;
import ua.lv.hoy.services.PaymentService;
import ua.lv.hoy.services.ScheduleService;

import java.util.List;

@Controller
public class ScheduleController {

    static final String SCHEDULES = "schedules";

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    HouseService houseService;
    @Autowired
    CustomerService customerService;
    @Autowired
    PaymentService paymentService;

    @RequestMapping(value = "/schedules/all", method = RequestMethod.GET)
    private  String openAllSchedulesPage(Model model){
        List<Schedule> scheduleList = scheduleService.findAllSchedules();
        model.addAttribute(SCHEDULES,scheduleList);
        return "allSchedules";
    }
    @RequestMapping(value = "/schedules/all/{houseId}", method = RequestMethod.GET)
    private  String openAllSchedulesPage(@PathVariable int houseId, Model model){
        model.addAttribute(SCHEDULES,scheduleService.findAllSchedules());
        model.addAttribute(HouseController.HOUSE,houseService.findById(houseId));
        return "allSchedules";
    }
    @RequestMapping(value = "/schedule/addpage/{houseId}/{customer_id}", method = RequestMethod.GET)
    private  String openAddSchedulePage(@PathVariable int houseId,@PathVariable int customerId, Model model){
        model.addAttribute("customer_id", customerId);
        model.addAttribute(HouseController.HOUSE_ID,houseId);
        return "addSchedule";
    }
    @RequestMapping(value = "/schedule/add", method = RequestMethod.POST)
    private String addSchedule(@RequestParam("houseId") String houseId,
                               @RequestParam("data") String data,
                               @RequestParam("amount") Double amount,
                               @RequestParam("customer_id") Integer customerId){
        scheduleService.add(customerId, data, amount);
        return "redirect:/customer/inf/"+houseId+"/"+customerId;
    }
    @RequestMapping(value = "/schedule/editpage/{id}", method = RequestMethod.GET)
    private  String openEditSchedulePage(@PathVariable Integer id, Model model){
        Schedule schedule = scheduleService.findById(id);
        model.addAttribute("schedule", schedule);
        return "editSchedule";
    }
    @RequestMapping(value = "/schedule/edit", method = RequestMethod.POST)
    private String editSchedule(@RequestParam("id") Integer id,
                                @RequestParam("data") String data,
                                @RequestParam("amount") Double amount){
        scheduleService.edit(id, data, amount);
        return "redirect:/schedules/all";
    }
    @RequestMapping(value = "/schedule/delete/{id}", method = RequestMethod.GET)
    private String deleteSchedule(@PathVariable Integer id){
        scheduleService.delete(id);
        return "redirect:/schedules/all";
    }

    @RequestMapping(value = "/schedl_paym/{id}", method = RequestMethod.GET)
    private String schedulesAdnPayments (@PathVariable Integer id, Model model){
        List<Schedule>scheduleList = scheduleService.findAllCustomerSchedules(customerService.findById(id).getEmail());
        List<Payment>paymentList = paymentService.findPaymentsByCustomerEmail(customerService.findById(id).getEmail());
        model.addAttribute("customerSchedules", scheduleList);
        model.addAttribute("customerPayments", paymentList);

        double amountUSA = paymentService.paymentAmount(id);
        model.addAttribute("amountUSA", amountUSA);

        Customer customer = customerService.findById(id);
        model.addAttribute(CustomerController.CUSTOMER, customer);

        return "schedules_payments";
    }



}
