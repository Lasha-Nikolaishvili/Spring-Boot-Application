package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.AddCustomerDTO;
import ge.ibsu.demo.dto.RequestObject;
import ge.ibsu.demo.dto.SearchCustomer;
import ge.ibsu.demo.entity.Customer;
import ge.ibsu.demo.service.CustomerService;
import ge.ibsu.demo.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Customer> getAll() {
        return customerService.retrieveAllCustomer();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public Customer addCustomer(@RequestBody AddCustomerDTO rd) throws Exception {
        GeneralUtil.checkRequiredProperties(rd, Arrays.asList("firstName", "lastName", "address"));
        return customerService.addCustomer(rd);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Customer editCustomer(@PathVariable Long id, @RequestBody AddCustomerDTO addCustomerDTO) throws Exception {
        GeneralUtil.checkRequiredProperties(addCustomerDTO, Arrays.asList("firstName", "lastName", "address"));
        return customerService.editCustomer(id, addCustomerDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = {"application/json"})
    public Slice<Customer> searchCustomer(@RequestBody RequestObject<SearchCustomer> rd) {
        return customerService.search(rd.getData(), rd.getPaging());
    }
}
