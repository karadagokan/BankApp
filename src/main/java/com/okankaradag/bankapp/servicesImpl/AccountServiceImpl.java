package com.okankaradag.bankapp.servicesImpl;

import com.okankaradag.bankapp.models.Account;
import com.okankaradag.bankapp.repositories.IAccountDAO;
import com.okankaradag.bankapp.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDAO iAccountDAO;

    @Override
    public List<Account> getAll() {
        return iAccountDAO.findAll();
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return iAccountDAO.findById(id);
    }

    @Override
    public void save(Account account) {
        iAccountDAO.save(account);
    }

    @Override
    public void delete(Integer id) {
        iAccountDAO.deleteById(id);
    }

    @Override
    public void addRequestCustomer(Integer accountNumber, Integer defaultBalance, Integer customerId) {
        iAccountDAO.addRequestCustomer(accountNumber,defaultBalance,customerId);
    }

}
