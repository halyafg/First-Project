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
public class ScheduleController extends BaseController {

    private static final String SCHEDULE = "schedule";
    static final String SCHEDULES = "schedules";
    private static final String ALL_SCHEDULES = "allSchedules";
    private static final String ADD_SCHEDULE = "addSchedule";
    private static final String EDIT_SCHEDULE = "editSchedule";
    private static final String REDIRECT_SCHEDULES_ALL = "redirect:/schedules/all";

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    HouseService houseService;
    @Autowired
    CustomerService customerService;
    @Autowired
    PaymentService paymentService;

    @Override
    @RequestMapping(value = "/schedules/all/{houseId}", method = RequestMethod.GET)
    String findAll(@PathVariable int houseId, Model model) {
        model.addAttribute(SCHEDULES, scheduleService.findAllSchedulesInHouse(houseId));
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        return ALL_SCHEDULES;
    }

    @RequestMapping(value = "/schedule/addpage/{houseId}/{customerId}", method = RequestMethod.GET)
    private  String openAddSchedulePage(@PathVariable int houseId,@PathVariable int customerId, Model model){
        model.addAttribute(CustomerController.CUSTOMER, customerService.findById(customerId));
        model.addAttribute(HouseController.HOUSE_ID,houseId);
        model.addAttribute(SCHEDULE, new Schedule());
        return ADD_SCHEDULE;
    }

    @RequestMapping(value = "/schedule/add/{houseId}/{customerId}", method = RequestMethod.POST)
    private String addSchedule(@PathVariable int houseId,
                               @PathVariable int customerId,
                               @ModelAttribute Schedule schedule){
        scheduleService.add(houseId, customerId, schedule);
        return CustomerController.REDIRECT_CUSTOMER_INF + houseId + "/" + customerId;
    }

    @Override
    @RequestMapping(value = "/add/schedule/page/{houseId}", method = RequestMethod.GET)
    String openAddPage(@PathVariable int houseId, Model model) {
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

    @Override
    @RequestMapping(value = "/schedule/editpage/{scheduleId}", method = RequestMethod.GET)
    String openEditPage(@PathVariable int scheduleId, Model model) {
        model.addAttribute(SCHEDULE, scheduleService.findById(scheduleId));
        model.addAttribute("editedSchedule", new Schedule());
        return EDIT_SCHEDULE;
    }

    @RequestMapping(value = "/schedule/edit/{scheduleId}", method = RequestMethod.POST)
    private String editSchedule(@PathVariable int scheduleId,
                                @ModelAttribute Schedule editedSchedule){
        scheduleService.edit(scheduleId, editedSchedule);
        return REDIRECT_SCHEDULES_ALL + "/" + scheduleService.findById(scheduleId).getHouse().getId();
    }

    @Override
    @RequestMapping(value = "/schedule/delete/{houseId}/{id}", method = RequestMethod.GET)
    String delete(@PathVariable int houseId,@PathVariable int id) {
        scheduleService.delete(id);
        return REDIRECT_SCHEDULES_ALL + "/" + houseId;
    }

    @RequestMapping(value = "/schedl_paym/{houseId}/{id}", method = RequestMethod.GET)
    private String schedulesAdnPayments (@PathVariable Integer id, @PathVariable Integer houseId, Model model){
        List<Schedule>scheduleList = scheduleService.findAllCustomerSchedules(customerService.findById(id).getEmail());
        List<Payment>paymentList = paymentService.findPaymentsByCustomerEmail(customerService.findById(id).getEmail());
        model.addAttribute("customerSchedules", scheduleList);
        model.addAttribute("customerPayments", paymentList);

        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));

        double amountUSA = paymentService.getPaymentAmount(id);
        model.addAttribute("amountUSA", amountUSA);

        Customer customer = customerService.findById(id);
        model.addAttribute(CustomerController.CUSTOMER, customer);

        return "schedules_payments";
    }

}
