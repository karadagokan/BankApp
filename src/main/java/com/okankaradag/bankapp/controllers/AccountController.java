package com.okankaradag.bankapp.controllers;

import com.okankaradag.bankapp.models.Account;
import com.okankaradag.bankapp.repositories.IAccountDAO;
import com.okankaradag.bankapp.services.IAccountService;
import com.okankaradag.bankapp.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AccountController implements IControllerBase<Account> {

    private String prefixPage = "redirect:/accounts";

    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IAccountDAO iAccountDAO;

    @GetMapping("/accounts")
    @Override
    public String getAll(Model model) {
        model.addAttribute("accounts", iAccountService.getAll());
        return "account";
    }


    @PostMapping("accounts/addNew")
    public String addNew(Account account) {
        iAccountService.save(account);
        return prefixPage;
    }


    @RequestMapping(value = "accounts/update", method = {RequestMethod.PUT, RequestMethod.GET})
    @Override
    public String update(Account account) {
        iAccountService.save(account);
        return prefixPage;
    }

    @RequestMapping(value = "accounts/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    @Override
    public String delete(Integer id) {
        iAccountService.delete(id);
        return prefixPage;
    }

    @RequestMapping("accounts/findById")
    @ResponseBody
    @Override
    public Optional<Account> findById(int id) {
        return iAccountService.findById(id);
    }

}
