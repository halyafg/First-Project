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
import ua.lv.hoy.services.CustomerService;
import ua.lv.hoy.services.HouseService;
import ua.lv.hoy.services.PaymentService;

import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    CustomerService customerService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    HouseService houseService;

    @RequestMapping(value = "/payments/all", method = RequestMethod.GET)
    private  String openAllPaymentsPage(Model model){
        List<Payment> paymentList=paymentService.findAllPayments();
        model.addAttribute("payments",paymentList);
        return "allPayments";
    }
    @RequestMapping(value = "/payments/all/{houseId}", method = RequestMethod.GET)
    private  String openAllPaymentsPage(@PathVariable int houseId, Model model){
        List<Payment>paymentList=paymentService.findAllPayments();
        model.addAttribute("payments",paymentList);
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        return "allPayments";
    }
    @RequestMapping(value = "/payment/addpage/{houseId}/{id}", method = RequestMethod.GET)
    private String openAddPaymentPage(@PathVariable Integer houseId,
                                      @PathVariable Integer id,
                                      Model model){
        model.addAttribute(CustomerController.CUSTOMER,  customerService.findById(id));
        model.addAttribute(HouseController.HOUSE_ID,houseId);
        return "addPayment";
    }
    @RequestMapping(value = "/payment/addpage/{houseId}", method = RequestMethod.GET)
    private String openAddPaymentPage(@PathVariable Integer houseId, Model model){
        List<Customer>customerList=customerService.findAllCustomersInHouse(houseId);
        model.addAttribute(CustomerController.CUSTOMERS, customerList);
        model.addAttribute(HouseController.HOUSE_ID,houseId);
        return "addPaymentsPage";
    }

    @RequestMapping(value = "/payment/add",method = RequestMethod.POST)
    private String addPayment(@RequestParam("houseId") Integer houseId,
                              @RequestParam("customer_id") Integer customerId,
                              @RequestParam("data") String data,
                              @RequestParam("amount") Double amountGRN){

        paymentService.add(customerId, data, amountGRN);
        return "redirect:/payments/all/"+houseId;
    }

    @RequestMapping(value = "/add/payments/page/{houseId}", method = RequestMethod.GET)
    private String addPayments_Page(@PathVariable int houseId, Model model){
        List<Customer>customerList = customerService.findAllCustomers();
        model.addAttribute(CustomerController.CUSTOMERS, customerList);
        model.addAttribute(HouseController.HOUSE_ID,houseId);
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
