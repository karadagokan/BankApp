package com.okankaradag.bankapp.repositories;

import com.okankaradag.bankapp.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IAccountDAO extends JpaRepository<Account, Integer> {

    @Modifying
    @Query(value = "insert into Account (account_number,balance,customer_id) VALUES(:accountNumber,:defaultBalance,:customerId)", nativeQuery = true)
    @Transactional
    void addRequestCustomer(@Param("accountNumber") Integer accountNumber, @Param("defaultBalance") Integer defaultBalance, @Param("customerId") Integer customerId);


}
