package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lv.hoy.services.CustomerService;
import ua.lv.hoy.services.HouseService;
import ua.lv.hoy.services.PaymentService;

@Controller
public class PaymentController {

    static final String PAYMENTS = "payments";
    static final String All_PAYMENTS = "allPayments";
    static final String REDIRECT_PAYMENTS_ALL = "redirect:/payments/all";

    @Autowired
    CustomerService customerService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    HouseService houseService;

    @RequestMapping(value = "/payments/all", method = RequestMethod.GET)
    private  String openAllPaymentsPage(Model model){
        model.addAttribute(PAYMENTS, paymentService.findAllPayments());
        return All_PAYMENTS;
    }
    @RequestMapping(value = "/payments/all/{houseId}", method = RequestMethod.GET)
    private  String openAllPaymentsPage(@PathVariable int houseId, Model model){
        model.addAttribute(PAYMENTS, paymentService.findAllPayments());
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        return All_PAYMENTS;
    }
    @RequestMapping(value = "/payment/addpage/{houseId}/{id}", method = RequestMethod.GET)
    private String openAddPaymentPage(@PathVariable Integer houseId,
                                      @PathVariable Integer id,
                                      Model model){
        model.addAttribute(CustomerController.CUSTOMER,  customerService.findById(id));
        model.addAttribute(HouseController.HOUSE_ID, houseId);
        return "addPayment";
    }
    @RequestMapping(value = "/payment/addpage/{houseId}", method = RequestMethod.GET)
    private String openAddPaymentPage(@PathVariable Integer houseId, Model model){
        model.addAttribute(CustomerController.CUSTOMERS, customerService.findAllCustomersInHouse(houseId));
        model.addAttribute(HouseController.HOUSE_ID,houseId);
        return "addPaymentsPage";
    }

    @RequestMapping(value = "/payment/add",method = RequestMethod.POST)
    private String addPayment(@RequestParam("houseId") Integer houseId,
                              @RequestParam("customerId") Integer customerId,
                              @RequestParam("data") String data,
                              @RequestParam("amount") Double amountGRN){
        paymentService.add(customerId, data, amountGRN);
        return REDIRECT_PAYMENTS_ALL + "/" + houseId;
    }

    @RequestMapping(value = "/add/payments/page/{houseId}", method = RequestMethod.GET)
    private String addPayments_Page(@PathVariable int houseId, Model model){
        model.addAttribute(CustomerController.CUSTOMERS, customerService.findAllCustomers());
        model.addAttribute(HouseController.HOUSE_ID,houseId);
        return "addPaymentsPage";
    }

    @RequestMapping(value = "/payment/editpage/{id}", method = RequestMethod.GET)
    private String openEditPaymentPage(@PathVariable Integer id, Model model){
        model.addAttribute("payment", paymentService.findById(id));
        return "editPayment";
    }
    @RequestMapping(value = "/payment/edit", method = RequestMethod.POST)
    private String editPayment(@RequestParam("id") Integer id,
                               @RequestParam("data") String data,
                               @RequestParam("amount") Double amountGRN,
                               @RequestParam("quote") Double quote){
        double amountUSA = amountGRN/quote;
        paymentService.edit(id, data, amountGRN, quote, amountUSA);
        return REDIRECT_PAYMENTS_ALL;
    }
    @RequestMapping(value = "/payment/delete/{id}", method = RequestMethod.GET)
    private String deletePayment(@PathVariable Integer id){
        paymentService.delete(id);
        return REDIRECT_PAYMENTS_ALL;
    }
}
