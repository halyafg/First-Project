package ua.lv.hoy.controllers;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lv.hoy.dao.HouseDao;
import ua.lv.hoy.entity.*;
import ua.lv.hoy.services.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Administrator on 02-Mar-17.
 */
@Controller
@Transactional
public class BaseController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private PaymentService paymentService;

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

    @RequestMapping(value = "/customers/all", method = RequestMethod.GET)
    private  String openAllCustomerPage(Model model){
        List<Customer> customerList = customerService.findAllCustomers();
        model.addAttribute("customers", customerList);
        return "allCustomers";
    }

    @RequestMapping(value = "/customers/inAllHouses", method = RequestMethod.GET)
    private  String openAllCustomerInAllHousesPage(Model model){
        List<Customer> customerList = customerService.findAllCustomers();
        model.addAttribute("customers", customerList);
        return "allCustomersInAllHouses";
    }

    @RequestMapping(value = "/customers/all/inhouse/{houseId}", method = RequestMethod.GET)
    private  String openAllCustomerPage(@PathVariable Integer houseId, Model model){
        model.addAttribute("customers", customerService.findAllCustomersInHouse(houseId));
        model.addAttribute("house", houseService.findById(houseId));
        return "allCustomers";
    }
    @RequestMapping(value = "/customer/add/{houseId}", method = RequestMethod.GET)
    private  String openAddCustomerPage(@PathVariable int houseId, Model model){
        model.addAttribute("houseId", houseId);
        return "addCustomer";
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    private String addCustomer(@RequestParam("houseId") String houseId,
                               @RequestParam("cust_name") String name,
                               @RequestParam("cust_surname") String surname,
                               @RequestParam("cust_lastname") String lastname,
                               @RequestParam("cust_phone") String phone,
                               @RequestParam("cust_email") String email,
                               @RequestParam("cust_password") String password,
                               @RequestParam("pas_ser") String pasportSerija,
                               @RequestParam("pas_number") String pasportNumber,
                               @RequestParam("pas_vidan") String pasportKimVidan,
                               @RequestParam("pas_data") String pasportData){
        customerService.add(name, surname, lastname, phone, email, password,pasportSerija, pasportNumber,pasportKimVidan,pasportData );
        return "redirect:/customer/inf/"+houseId+"/"+customerService.findCustomerByLogin(email).getId();
    }

    @RequestMapping(value = "/customer/inf/{houseId}/{customerId}")
    private String openCustomerPage(@PathVariable Integer houseId,@PathVariable Integer customerId, Model model){
        model.addAttribute("customer", customerService.findById(customerId));
        model.addAttribute("houseId", houseId);
        return "customerInf";
    }
    @RequestMapping(value = "/customer/editpage/{houseId}/{id}", method = RequestMethod.GET)
    private String openEditCustomerPage(@PathVariable Integer houseId,@PathVariable Integer id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("cust", customer);
        model.addAttribute("houseId", houseId);
        return "editCustomer";
    }
    @RequestMapping(value = "/customer/edit", method = RequestMethod.POST)
    private String editCustomer(@RequestParam("houseId") Integer houseId,
                                @RequestParam("cust_id") Integer id,
                                @RequestParam("cust_name") String name,
                                @RequestParam("cust_surname") String surname,
                                @RequestParam("cust_lastname") String lastname,
                                @RequestParam("cust_phone") String phone,
                                @RequestParam("cust_email") String email,
                                @RequestParam("cust_password") String password,
                                @RequestParam("pas_ser") String pasportSerija,
                                @RequestParam("pas_number") String pasportNumber,
                                @RequestParam("pas_vidan") String pasportKimVidan,
                                @RequestParam("pas_data") String pasportData){
        customerService.edit(id,name, surname, lastname, phone, email, password, pasportSerija, pasportNumber,pasportKimVidan,pasportData );
        return "redirect:/customer/inf/"+houseId+"/"+id;
    }

    @RequestMapping(value = "/customer/delete/{houseId}/{id}")
    private String deleteCustomer(@PathVariable Integer houseId,@PathVariable Integer id){
        customerService.delete(id);
        return "redirect:/customers/all/inhouse/"+houseId;
    }
    @RequestMapping(value = "/customers/delete/{id}", method = RequestMethod.GET)
    private String deleteCustomer(@PathVariable Integer id){
        customerService.delete(id);
        return "redirect:/customers/all";
    }





    @RequestMapping(value = "/schedules/all", method = RequestMethod.GET)
    private  String openAllSchedulesPage(Model model){
        List<Schedule> scheduleList = scheduleService.findAllSchedules();
        model.addAttribute("schedules",scheduleList);
        return "allSchedules";
    }
    @RequestMapping(value = "/schedules/all/{houseId}", method = RequestMethod.GET)
    private  String openAllSchedulesPage(@PathVariable int houseId, Model model){
        model.addAttribute("schedules",scheduleService.findAllSchedules());
        model.addAttribute("house",houseService.findById(houseId));
        return "allSchedules";
    }
    @RequestMapping(value = "/schedule/addpage/{houseId}/{customer_id}", method = RequestMethod.GET)
    private  String openAddSchedulePage(@PathVariable int houseId,@PathVariable int customer_id, Model model){
        model.addAttribute("customer_id", customer_id);
        model.addAttribute("houseId",houseId);
        return "addSchedule";
    }
    @RequestMapping(value = "/schedule/add", method = RequestMethod.POST)
    private String addSchedule(@RequestParam("houseId") String houseId,
                               @RequestParam("data") String data,
                               @RequestParam("amount") Double amount,
                               @RequestParam("customer_id") Integer customer_id){
        scheduleService.add(customer_id, data, amount);
        return "redirect:/customer/inf/"+houseId+"/"+customer_id;
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
        model.addAttribute("customer", customer);

        return "schedules_payments";
    }


    @RequestMapping(value = "/payments/all", method = RequestMethod.GET)
    private  String openAllPaymentsPage(Model model){
        List<Payment>paymentList=paymentService.findAllPayments();
        model.addAttribute("payments",paymentList);
        return "allPayments";
    }
    @RequestMapping(value = "/payments/all/{houseId}", method = RequestMethod.GET)
    private  String openAllPaymentsPage(@PathVariable int houseId,  Model model){
        List<Payment>paymentList=paymentService.findAllPayments();
        model.addAttribute("payments",paymentList);
        model.addAttribute("house", houseService.findById(houseId));
        return "allPayments";
    }
    @RequestMapping(value = "/payment/addpage/{houseId}/{id}", method = RequestMethod.GET)
    private String openAddPaymentPage(@PathVariable Integer houseId,@PathVariable Integer id, Model model){
        model.addAttribute("customer",  customerService.findById(id));
        model.addAttribute("houseId",houseId);
        return "addPayment";
    }
    @RequestMapping(value = "/payment/addpage/{houseId}", method = RequestMethod.GET)
    private String openAddPaymentPage(@PathVariable Integer houseId, Model model){
        List<Customer>customerList=customerService.findAllCustomersInHouse(houseId);
        model.addAttribute("customers", customerList);
        model.addAttribute("houseId",houseId);
        return "addPaymentsPage";
    }

    @RequestMapping(value = "/payment/add",method = RequestMethod.POST)
    private String addPayment(@RequestParam("houseId") Integer houseId,
                              @RequestParam("customer_id") Integer customer_id,
                              @RequestParam("data") String data,
                              @RequestParam("amount") Double amountGRN){

        paymentService.add(customer_id, data, amountGRN);
        return "redirect:/payments/all/"+houseId;
    }



    @RequestMapping(value = "/add/payments/page/{houseId}", method = RequestMethod.GET)
    private String addPayments_Page(@PathVariable int houseId, Model model){
        List<Customer>customerList = customerService.findAllCustomers();
        model.addAttribute("customers", customerList);
        model.addAttribute("houseId",houseId);
        return "addPaymentsPage";
    }




    @RequestMapping(value = "/payment/editpage/{id}", method = RequestMethod.GET)
    private String openEditPaymentPage(@PathVariable Integer id, Model model){
        Payment payment = paymentService.findById(id);
        model.addAttribute("payment", payment);
        return "editPayment";
    }
    @RequestMapping(value = "/payment/edit", method = RequestMethod.POST)
    private String editPayment(@RequestParam("id") Integer id,
                               @RequestParam("data") String data,
                               @RequestParam("amount") Double amountGRN,
                               @RequestParam("quote") Double quote
                               /*@RequestParam("amount_$") Double amount_$*/){
        double amount_$ = amountGRN/quote;
        paymentService.edit(id, data, amountGRN, quote, amount_$);
        return "redirect:/payments/all";
    }
    @RequestMapping(value = "/payment/delete/{id}", method = RequestMethod.GET)
    private String deletePayment(@PathVariable Integer id){
        paymentService.delete(id);
        return "redirect:/payments/all";
    }


}
