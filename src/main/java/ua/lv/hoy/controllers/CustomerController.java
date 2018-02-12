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

    @RequestMapping(value = "/customers/inAllHouses", method = RequestMethod.GET)
    private  String openAllCustomerInAllHousesPage(Model model){
        model.addAttribute(CUSTOMERS, customerService.findAllCustomers());
        return "allCustomersInAllHouses";
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
        return "addCustomer";
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    private String addCustomer(@RequestParam("houseId") String houseId,
                               @RequestParam("custName") String name,
                               @RequestParam("custSurname") String surname,
                               @RequestParam("custLastname") String lastname,
                               @RequestParam("custPhone") String phone,
                               @RequestParam("custEmail") String email,
                               @RequestParam("custPassword") String password,
                               @RequestParam("pasSer") String pasportSerija,
                               @RequestParam("pasNumber") String pasportNumber,
                               @RequestParam("pasVidan") String pasportKimVidan,
                               @RequestParam("pasData") String pasportData){
        customerService.add(name, surname, lastname, phone, email, password,pasportSerija, pasportNumber,pasportKimVidan,pasportData );
        return REDIRECT_CUSTOMER_INF + houseId+"/"+customerService.findCustomerByLogin(email).getId();
    }

    @RequestMapping(value = "/customer/inf/{houseId}/{customerId}")
    private String openCustomerPage(@PathVariable Integer houseId,@PathVariable Integer customerId, Model model){
        model.addAttribute(CUSTOMER, customerService.findById(customerId));
        model.addAttribute(HouseController.HOUSE_ID, houseId);
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
                                @RequestParam("custId") Integer id,
                                @RequestParam("custName") String name,
                                @RequestParam("custSurname") String surname,
                                @RequestParam("custLastname") String lastname,
                                @RequestParam("custPhone") String phone,
                                @RequestParam("custEmail") String email,
                                @RequestParam("custPassword") String password,
                                @RequestParam("pasSer") String pasportSerija,
                                @RequestParam("pasNumber") String pasportNumber,
                                @RequestParam("pasVidan") String pasportKimVidan,
                                @RequestParam("pasData") String pasportData){
        customerService.edit(id,name, surname, lastname, phone, email, password, pasportSerija, pasportNumber,pasportKimVidan,pasportData );
        return REDIRECT_CUSTOMER_INF + houseId+"/"+id;
    }

    @RequestMapping(value = "/customer/delete/{houseId}/{id}")
    private String deleteCustomer(@PathVariable Integer houseId,@PathVariable Integer id){
        customerService.delete(id);
        return REDIRECT_CUSTOMER_ALL_INHOUSE + houseId;
    }
    @RequestMapping(value = "/customers/delete/{id}", method = RequestMethod.GET)
    private String deleteCustomer(@PathVariable Integer id){
        customerService.delete(id);
        return "redirect:/customers/all";
    }
}
