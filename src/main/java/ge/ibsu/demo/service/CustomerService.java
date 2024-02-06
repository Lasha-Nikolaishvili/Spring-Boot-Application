package ge.ibsu.demo.service;

import ge.ibsu.demo.dto.AddCustomerDTO;
import ge.ibsu.demo.dto.Paging;
import ge.ibsu.demo.dto.SearchCustomer;
import ge.ibsu.demo.entity.Address;
import ge.ibsu.demo.entity.Customer;
import ge.ibsu.demo.repository.AddressRepository;
import ge.ibsu.demo.repository.CustomerRepository;
import ge.ibsu.demo.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<Customer> retrieveAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Transactional
    public Customer addCustomer(AddCustomerDTO addCustomerDTO) throws Exception {

        Customer customer = new Customer();
        GeneralUtil.getCopyOf(addCustomerDTO, customer);
        customer.setCreateDate(new Date());
        customer.setActive(1);

        Address address = new Address();
        address.setAddress(addCustomerDTO.getAddress());
        address = addressRepository.save(address);

        customer.setAddress(address);

        return customerRepository.save(customer);
    }

    @Transactional
    public Customer editCustomer(Long id, AddCustomerDTO addCustomerDTO) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new Exception("customer_not_found"));
        GeneralUtil.getCopyOf(addCustomerDTO, customer);
        customer.getAddress().setAddress(addCustomerDTO.getAddress());
        return customerRepository.save(customer);
    }

    public Slice<Customer> search(SearchCustomer searchCustomer, Paging paging) {
        String searchText = null;
        if (searchCustomer.getSearchText() != null && !searchCustomer.getSearchText().equals("")) {
            searchText = "%" + searchCustomer.getSearchText() + "%";
        }
        Pageable pageable = PageRequest.of(paging.getPage() - 1, paging.getSize(), Sort.by("createDate").ascending());
        return customerRepository.search(1, searchText, pageable);
    }

}