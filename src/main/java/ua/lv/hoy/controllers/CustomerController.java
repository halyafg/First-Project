package ua.lv.hoy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lv.hoy.entity.Customer;
import ua.lv.hoy.services.CustomerService;
import ua.lv.hoy.services.HouseService;


@Controller
public class CustomerController {

    static final String CUSTOMERS = "customers";
    static final String CUSTOMER = "customer";
    static final String REDIRECT_CUSTOMER_INF = "redirect:/customer/inf/";
    static final String REDIRECT_CUSTOMER_ALL_INHOUSE = "redirect:/customers/all/inhouse/";
    static final String ALL_CUSTOMERS = "allCustomers";

    @Autowired
    CustomerService customerService;
    @Autowired
    HouseService houseService;

    @RequestMapping(value = "/customers/all", method = RequestMethod.GET)
    private  String openAllCustomerPage(Model model){
        model.addAttribute(CUSTOMERS, customerService.findAllCustomers());
        return ALL_CUSTOMERS;
    }

    @RequestMapping(value = "/customers/all/inhouse/{houseId}", method = RequestMethod.GET)
    private  String openAllCustomerPage(@PathVariable Integer houseId, Model model){
        model.addAttribute(CUSTOMERS, customerService.findAllCustomersInHouse(houseId));
        model.addAttribute(HouseController.HOUSE, houseService.findById(houseId));

        return ALL_CUSTOMERS;
    }
    @RequestMapping(value = "/customer/add/{houseId}", method = RequestMethod.GET)
    private  String openAddCustomerPage(@PathVariable int houseId, Model model){
        model.addAttribute(HouseController.HOUSE_ID, houseId);
        model.addAttribute(CUSTOMER, new Customer());
        return "addCustomer";
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
        return "customerInf";
    }
    @RequestMapping(value = "/customer/editpage/{houseId}/{id}", method = RequestMethod.GET)
    private String openEditCustomerPage(@PathVariable Integer houseId,@PathVariable Integer id, Model model){
        model.addAttribute(CUSTOMER, customerService.findById(id));
        model.addAttribute(HouseController.HOUSE_ID, houseId);
        return "editCustomer";
    }
    @RequestMapping(value = "/customer/edit", method = RequestMethod.POST)
    private String editCustomer(@RequestParam("houseId") Integer houseId,
                                @RequestParam("custId") Integer customerId,
                                @ModelAttribute Customer customer){

        customerService.edit(customerId, customer);
        return REDIRECT_CUSTOMER_INF + houseId +"/"+customerId;
    }

    @RequestMapping(value = "/customer/delete/{houseId}/{id}")
    private String deleteCustomer(@PathVariable Integer houseId,@PathVariable Integer id){
        customerService.delete(id);
        return REDIRECT_CUSTOMER_ALL_INHOUSE + HouseController.HOUSE_ID;
    }
    @RequestMapping(value = "/customers/delete/{id}", method = RequestMethod.GET)
    private String deleteCustomer(@PathVariable Integer id){
        customerService.delete(id);
        return "redirect:/customers/all";
    }
}
