package com.okankaradag.bankapp.servicesImpl;

import com.okankaradag.bankapp.models.Customer;
import com.okankaradag.bankapp.repositories.ICustomerDAO;
import com.okankaradag.bankapp.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService<Customer> {

    @Autowired
    private ICustomerDAO iCustomerDAO;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public List<Customer> getAll() {
        return iCustomerDAO.findAll();
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return iCustomerDAO.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customer.setPassword(encoder.encode(customer.getPassword()));
        iCustomerDAO.save(customer);
    }

    @Override
    public void delete(Integer id) {
        iCustomerDAO.deleteById(id);
    }

    @Override
    public Customer findByUsernameProfile(String username) {
        return iCustomerDAO.findByUsernameProfile(username);
    }

    @Override
    public void accountRequestOne(Integer userId) {
        iCustomerDAO.accountRequestOne(userId);
    }

    @Override
    public List<Customer> getAccountAllAccountRequestOne() {
        return iCustomerDAO.getAccountAllAccountRequestOne();
    }

    @Override
    public List<Customer> getCreditAllCreditRequestOne() {
        return iCustomerDAO.getCreditAllCreditRequestOne();
    }

    @Override
    public Customer findByIdFor(Integer id) {
        return iCustomerDAO.findByIdFor(id);
    }


}
