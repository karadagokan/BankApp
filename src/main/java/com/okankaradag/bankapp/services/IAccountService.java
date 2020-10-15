package com.okankaradag.bankapp.services;

import com.okankaradag.bankapp.models.Account;

public interface IAccountService extends IServiceBase<Account> {

    void addRequestCustomer(Integer accountNumber, Integer defaultBalance, Integer customerId);
}
