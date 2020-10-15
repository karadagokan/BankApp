package com.okankaradag.bankapp.services;

import com.okankaradag.bankapp.models.Customer;
import com.okankaradag.bankapp.models.UserPrincipal;
import com.okankaradag.bankapp.repositories.ICustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyCustomerDetailsService implements UserDetailsService {
    @Autowired
    ICustomerDAO iCustomerDAO;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer=iCustomerDAO.findByUsername(username);

        if (username==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserPrincipal(customer);
    }
}
