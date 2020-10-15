package com.okankaradag.bankapp.repositories;

import com.okankaradag.bankapp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ICustomerDAO extends JpaRepository<Customer, Integer> {

    Customer findByUsername(String username);

    @Query(value = "SELECT c FROM Customer c where c.username=:username")
    Customer findByUsernameProfile(@Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Customer c SET c.accountRequest=1 where c.customerId=:userId")
    public void accountRequestOne(@Param("userId") Integer userId);

    @Query(value = "SELECT c FROM Customer c WHERE c.accountRequest=1")
    List<Customer> getAccountAllAccountRequestOne();

    @Query(value = "SELECT c FROM Customer c WHERE c.creditRequest=1")
    List<Customer> getCreditAllCreditRequestOne();

    @Query(value = "SELECT c FROM Customer c where c.customerId=:customerId")
    Customer findByIdFor(@Param("customerId") Integer id);


}
