package com.okankaradag.bankapp.services;

import java.util.List;

public interface ICustomerService<Customer> extends IServiceBase<Customer> {

    Customer findByUsernameProfile(String username);

    void accountRequestOne(Integer userId);

    List<Customer> getAccountAllAccountRequestOne();

    List<Customer> getCreditAllCreditRequestOne();

    Customer findByIdFor(Integer id);


}
