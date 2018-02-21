package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    static final String SCHEDULE = "schedule";
    static final String SCHEDULES = "schedules";
    static final String ALL_SCHEDULES = "allSchedules";
    static final String REDIRECT_SCHEDULES_ALL = "redirect:/schedules/all";

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    HouseService houseService;
    @Autowired
    CustomerService customerService;
    @Autowired
    PaymentService paymentService;


    @RequestMapping(value = "/schedules/all/{houseId}", method = RequestMethod.GET)
    private  String openAllSchedulesPage(@PathVariable int houseId, Model model){
        model.addAttribute(SCHEDULES, scheduleService.findAllSchedulesInHouse(houseId));
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        return ALL_SCHEDULES;
    }

    @RequestMapping(value = "/schedule/addpage/{houseId}/{customerId}", method = RequestMethod.GET)
    private  String openAddSchedulePage(@PathVariable int houseId,@PathVariable int customerId, Model model){
        model.addAttribute(CustomerController.CUSTOMER, customerService.findById(customerId));
        model.addAttribute(HouseController.HOUSE_ID,houseId);
        model.addAttribute(SCHEDULE, new Schedule());
        return "addSchedule";
    }
    @RequestMapping(value = "/schedule/add/{houseId}/{customerId}", method = RequestMethod.POST)
    private String addSchedule(@PathVariable int houseId,
                               @PathVariable int customerId,
                               @ModelAttribute Schedule schedule){
        scheduleService.add(houseId, customerId, schedule);
        return CustomerController.REDIRECT_CUSTOMER_INF + houseId + "/" + customerId;
    }
    @RequestMapping(value = "/add/schedule/page/{houseId}", method = RequestMethod.GET)
    private String addSchedule(@PathVariable int houseId, Model model){
        model.addAttribute(CustomerController.CUSTOMERS, customerService.findAllCustomersInHouse(houseId));
        model.addAttribute(HouseController.HOUSE_ID,houseId);
        model.addAttribute(SCHEDULE, new Schedule());
        return "addSchedulePage";
    }
    @RequestMapping(value = "/schedule/add/{houseId}", method = RequestMethod.POST)
    private String openAddSchedulePage(@PathVariable Integer houseId,
                                      @RequestParam("customerId") int customerId,
                                      @ModelAttribute Schedule schedule) {
        scheduleService.add(houseId, customerId, schedule);
        return REDIRECT_SCHEDULES_ALL + "/" + houseId;
    }
    @RequestMapping(value = "/schedule/editpage/{scheduleId}", method = RequestMethod.GET)
    private  String openEditSchedulePage(@PathVariable int scheduleId,
                                         Model model){
        model.addAttribute(SCHEDULE, scheduleService.findById(scheduleId));
        model.addAttribute("editedSchedule", new Schedule());
        return "editSchedule";
    }
    @RequestMapping(value = "/schedule/edit/{scheduleId}", method = RequestMethod.POST)
    private String editSchedule(@PathVariable int scheduleId,
                                @ModelAttribute Schedule editedSchedule){
        scheduleService.edit(scheduleId, editedSchedule);
        return REDIRECT_SCHEDULES_ALL + "/" + scheduleService.findById(scheduleId).getHouse().getId();
    }
    @RequestMapping(value = "/schedule/delete/{id}", method = RequestMethod.GET)
    private String deleteSchedule(@PathVariable Integer id){
        scheduleService.delete(id);
        return REDIRECT_SCHEDULES_ALL;
    }

    @RequestMapping(value = "/schedl_paym/{houseId}/{id}", method = RequestMethod.GET)
    private String schedulesAdnPayments (@PathVariable Integer id, @PathVariable Integer houseId, Model model){
        List<Schedule>scheduleList = scheduleService.findAllCustomerSchedules(customerService.findById(id).getEmail());
        List<Payment>paymentList = paymentService.findPaymentsByCustomerEmail(customerService.findById(id).getEmail());
        model.addAttribute("customerSchedules", scheduleList);
        model.addAttribute("customerPayments", paymentList);

        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));

        double amountUSA = paymentService.paymentAmount(id);
        model.addAttribute("amountUSA", amountUSA);

        Customer customer = customerService.findById(id);
        model.addAttribute(CustomerController.CUSTOMER, customer);

        return "schedules_payments";
    }

}
