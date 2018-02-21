package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lv.hoy.entity.Payment;
import ua.lv.hoy.services.CustomerService;
import ua.lv.hoy.services.HouseService;
import ua.lv.hoy.services.PaymentService;

@Controller
public class PaymentController {

    static final String PAYMENT = "payment";
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
        model.addAttribute(PAYMENTS, paymentService.findAllPaymentsInHouse(houseId));
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        return All_PAYMENTS;
    }

    @RequestMapping(value = "/payment/addpage/{houseId}/{customerId}", method = RequestMethod.GET)
    private String openAddPaymentPage(@PathVariable Integer houseId,
                                      @PathVariable Integer customerId,
                                      Model model){
        model.addAttribute(CustomerController.CUSTOMER,  customerService.findById(customerId));
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        model.addAttribute(PAYMENT, new Payment());
        return "addPayment";
    }
    @RequestMapping(value = "/payment/add/{houseId}/{customerId}",method = RequestMethod.POST)
    private String addPayment(@PathVariable Integer houseId,
                              @PathVariable Integer customerId,
                              @ModelAttribute Payment payment){
        paymentService.add(houseId, customerId, payment);
        return REDIRECT_PAYMENTS_ALL + "/" + houseId;
    }


    @RequestMapping(value = "/add/payments/page/{houseId}", method = RequestMethod.GET)
    private String addPayment(@PathVariable int houseId, Model model){
        model.addAttribute(CustomerController.CUSTOMERS, customerService.findAllCustomersInHouse(houseId));
        model.addAttribute(HouseController.HOUSE_ID,houseId);
        return "addPaymentsPage";
    }

    @RequestMapping(value = "/payment/add/{houseId}", method = RequestMethod.POST)
    private String openAddPaymentPage(@PathVariable Integer houseId,
                                      @RequestParam("customerId") int customerId,
                                      @RequestParam ("data") String data,
                                      @RequestParam("amountGRN") double amountGRN) {
        paymentService.add(houseId, customerId, new Payment(data, amountGRN, 0, 0));
        return REDIRECT_PAYMENTS_ALL + "/" + houseId;
    }



    @RequestMapping(value = "/payment/editpage/{id}", method = RequestMethod.GET)
    private String openEditPaymentPage(@PathVariable Integer id, Model model){
        model.addAttribute(PAYMENT, paymentService.findById(id));
        model.addAttribute("editedPayment", new Payment());
        return "editPayment";
    }
    @RequestMapping(value = "/payment/edit/{paymentId}", method = RequestMethod.POST)
    private String editPayment(@PathVariable Integer paymentId,
                               @ModelAttribute Payment editedPayment){
        paymentService.edit(paymentId, editedPayment);
        return REDIRECT_PAYMENTS_ALL + "/" + paymentService.findById(paymentId).getHouse().getId();
    }
    @RequestMapping(value = "/payment/delete/{id}", method = RequestMethod.GET)
    private String deletePayment(@PathVariable Integer id){
        int houseId = paymentService.findById(id).getHouse().getId();
        paymentService.delete(id);
        return REDIRECT_PAYMENTS_ALL + "/" + houseId;
    }
}
