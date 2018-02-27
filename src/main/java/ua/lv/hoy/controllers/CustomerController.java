package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.services.CustomerService;
import ua.lv.hoy.services.HouseService;


@Controller
public class CustomerController extends BaseController{

    static final String CUSTOMER = "customer";
    static final String CUSTOMERS = "customers";
    private static final String CUSTOMER_INF = "customerInf";
    private static final String EDIT_CUSTOMER = "editCustomer";
    private static final String ADD_CUSTOMER = "addCustomer";
    static final String REDIRECT_CUSTOMER_INF = "redirect:/customer/inf/";
    static final String REDIRECT_CUSTOMER_ALL_INHOUSE = "redirect:/customers/all/inhouse/";
    private static final String ALL_CUSTOMERS = "allCustomers";

    @Autowired
    CustomerService customerService;
    @Autowired
    HouseService houseService;

   /* @RequestMapping(value = "/customers/all", method = RequestMethod.GET)
    private  String openAllCustomerPage(Model model){
        model.addAttribute(CUSTOMERS, customerService.findAllCustomers());
        return ALL_CUSTOMERS;
    }*/

    @Override
    @RequestMapping(value = "/customers/all/inhouse/{houseId}", method = RequestMethod.GET)
    String findAll(@PathVariable int houseId, Model model) {
        model.addAttribute(CUSTOMERS, customerService.findAllCustomersInHouse(houseId));
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        return ALL_CUSTOMERS;
    }

    @Override
    @RequestMapping(value = "/customer/add/{houseId}", method = RequestMethod.GET)
    String openAddPage(@PathVariable int houseId, Model model) {
        model.addAttribute(HouseController.HOUSE_ID, houseId);
        model.addAttribute(CUSTOMER, new Customer());
        return ADD_CUSTOMER;
    }

    @RequestMapping(value = "/customer/add/{houseId}", method = RequestMethod.POST)
    private String addCustomer(@PathVariable int houseId,
                               @ModelAttribute Customer customer){
        customerService.add(customer);
        return REDIRECT_CUSTOMER_INF + houseId+"/"+customerService.findCustomerByLogin(customer.getEmail()).getId();
    }

    @RequestMapping(value = "/customer/inf/{houseId}/{customerId}")
    private String openCustomerPage(@PathVariable Integer houseId,@PathVariable Integer customerId, Model model){
        model.addAttribute(CUSTOMER, customerService.findById(customerId));
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));
        return CUSTOMER_INF;
    }

    @Override
    String openEditPage(int id, Model model) {
        return null;
    }

    @RequestMapping(value = "/customer/editpage/{houseId}/{id}", method = RequestMethod.GET)
    String openEditPage(@PathVariable int houseId, @PathVariable int id, Model model) {
        model.addAttribute(CUSTOMER, customerService.findById(id));
        model.addAttribute(HouseController.HOUSE_ID, houseId);
        return EDIT_CUSTOMER;
    }

    @RequestMapping(value = "/customer/edit", method = RequestMethod.POST)
    private String editCustomer(@RequestParam("houseId") Integer houseId,
                                @RequestParam("custId") Integer customerId,
                                @ModelAttribute Customer customer){
        customerService.edit(customerId, customer);
        return REDIRECT_CUSTOMER_INF + houseId +"/"+customerId;
    }

    @Override
    @RequestMapping(value = "/customer/delete/{houseId}/{id}")
    String delete( @PathVariable int houseId, @PathVariable int id) {
        customerService.delete(id);
        return REDIRECT_CUSTOMER_ALL_INHOUSE + HouseController.HOUSE_ID;
    }

}
