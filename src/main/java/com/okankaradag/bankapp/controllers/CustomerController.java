package com.okankaradag.bankapp.controllers;

import com.okankaradag.bankapp.models.Account;
import com.okankaradag.bankapp.models.Credit;
import com.okankaradag.bankapp.models.Customer;
import com.okankaradag.bankapp.models.Role;
import com.okankaradag.bankapp.services.IAccountService;
import com.okankaradag.bankapp.services.ICreditService;
import com.okankaradag.bankapp.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Controller
public class CustomerController implements IControllerBase<Customer> {

    private String prefixPage = "redirect:/customers";

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private ICreditService iCreditService;

    @RequestMapping(value = "/profile")
    public String getAllMyProfile(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("customersProfile", iCustomerService.findByUsernameProfile(username));
        return "profile";
    }

    @GetMapping("/customers")
    @Override
    public String getAll(Model model) {
        model.addAttribute("customers", iCustomerService.getAll());
        return "customer";
    }


    @GetMapping("/requestCreditCustomer")
    public String getRequestCreditCustomer(Model model) {
        model.addAttribute("customersCreditRequest", iCustomerService.getCreditAllCreditRequestOne());
        return "requestCreditCustomer";
    }

    @GetMapping("/requestAccountCustomer")
    public String getRequestAccountCustomer(Model model) {
        model.addAttribute("customers", iCustomerService.getAccountAllAccountRequestOne());
        return "requestAccountCustomer";
    }

    @RequestMapping(value = "/customers/update", method = {RequestMethod.PUT, RequestMethod.GET})
    @Override
    public String update(Customer customer) {
        Customer inComingCustomer = (Customer) iCustomerService.findByIdFor(customer.getCustomerId());

        inComingCustomer.setCustomerFirstName(customer.getCustomerFirstName());
        inComingCustomer.setCustomerLastName(customer.getCustomerLastName());
        inComingCustomer.setSalary(customer.getSalary());
        iCustomerService.save(inComingCustomer);

        if (inComingCustomer.getRole().getName().equalsIgnoreCase("USER")) {
            return "redirect:/profile";

        } else {

            return prefixPage;
        }


    }

    @RequestMapping("customers/findById")
    @ResponseBody // if you dont write this , not get information
    @Override
    public Optional<Customer> findById(int id) {
        return iCustomerService.findById(id);
    }

    @RequestMapping(value = "/customers/creditRequest", method = {RequestMethod.PUT, RequestMethod.GET})
    public String creditRequestUpdate(Integer customerId, Double sum) {
        Customer customer = (Customer) iCustomerService.findByIdFor(customerId);
        customer.setCreditRequest(1);
        customer.setCreditRequestAmount(sum);
        iCustomerService.save(customer);


        return "redirect:/profile";
    }

    @RequestMapping(value = "/customers/payCredit", method = {RequestMethod.PUT, RequestMethod.GET})
    public String payCredit(Integer customerId, Double paid) {
        Customer customer = (Customer) iCustomerService.findByIdFor(customerId);
        if (customer.getCredit() != null) {
            Double sum = customer.getCredit().getSum();
            Double pay = customer.getCredit().getPaid();
            Double pay2 = pay;
            Integer creditId = customer.getCredit().getCreditId();

            if (sum >= (paid + pay)) {
                pay += paid;
                customer.getCredit().setPaid(pay);

                if (sum == (paid + pay2)) {
                    customer.setCreditRequest(0);

                    customer.setCredit(null);

                    iCreditService.delete(creditId);

                }

                iCustomerService.save(customer);

            } else {
                iCustomerService.save(customer);
            }
        } else {
            return "redirect:/profile";
        }
        return "redirect:/profile";
    }

    // Modified method to Add a new user User
    @PostMapping(value = "customers/addNew")
    public RedirectView addNew(Customer customer, RedirectAttributes redir) {

        Role role = new Role();
        role.setName("USER");

        customer.setRole(role);

        iCustomerService.save(customer);

        RedirectView redirectView = new RedirectView("/login", true);

        redir.addFlashAttribute("message", "You successfully registered! You can now login");

        return redirectView;
    }


    @RequestMapping(value = "/customers/accountRequestUpdate", method = {RequestMethod.PUT, RequestMethod.GET})
    public String accountRequestUpdate(Integer id) {
        iCustomerService.accountRequestOne(id);
        return "redirect:/profile";
    }

    @RequestMapping(value = "customers/noRequestCredit", method = {RequestMethod.PUT, RequestMethod.GET})
    public String noRequestCredit(Integer id) {
        Customer customer = (Customer) iCustomerService.findByIdFor(id);
        customer.setCreditRequest(0);
        iCustomerService.save(customer);
        return "redirect:/requestCreditCustomer";
    }


    @RequestMapping(value = "customers/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    @Override
    public String delete(Integer id) {
        iCustomerService.delete(id);
        return prefixPage;
    }

    @RequestMapping(value = "customers/newCreditRequest", method = {RequestMethod.PUT, RequestMethod.GET})
    public String newCreditRequest(Integer id) {


        Customer customer = (Customer) iCustomerService.findByIdFor(id);
        customer.setCreditRequest(2);

        Double requestCredit = customer.getCreditRequestAmount();
        Double salary = customer.getSalary();

        Credit credit = new Credit();

        if (requestCredit > 5000) {
            credit.setSum(requestCredit);
            credit.setMountlyPayment((float) (requestCredit / 24));
            credit.setStartDate(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 2);
            credit.setEndDate(calendar.getTime());
            credit.setEndDate(new Date());
            customer.setCredit(credit);
        } else {
            credit.setSum(requestCredit);
            credit.setMountlyPayment((float) (requestCredit / 12));
            credit.setStartDate(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
            credit.setEndDate(calendar.getTime());
            customer.setCredit(credit);
        }
        customer.setCreditRequestAmount(0.0);
        iCreditService.save(credit);
        iCustomerService.save(customer);

        return "redirect:/requestCreditCustomer";
    }

    @GetMapping("/credit")
    public String getCredit(Model model) {
        model.addAttribute("credits", iCreditService.getAll());
        return "credit";
    }

    @RequestMapping(value = "/customers/noRequestAccount")
    public String noRequestAccount(Integer id) {
        Customer customer = (Customer) iCustomerService.findByIdFor(id);
        customer.setAccountRequest(0);
        iCustomerService.save(customer);
        return "redirect:/requestAccountCustomer";
    }

    @RequestMapping(value = "/customers/withdrawAmount", method = {RequestMethod.PUT, RequestMethod.GET})
    public String withdrawAmount(Double withdrawAmount, Integer customerId) {

        Customer customer = (Customer) iCustomerService.findByIdFor(customerId);
        Double nowBalance = customer.getAccount().getBalance();
        if (nowBalance >= withdrawAmount) {
            nowBalance -= withdrawAmount;
        }
        customer.getAccount().setBalance(nowBalance);
        iCustomerService.save(customer);
        return "redirect:/profile";
    }

    @RequestMapping(value = "/customers/depositMoney", method = {RequestMethod.PUT, RequestMethod.GET})
    public String depositMoney(Double withdrawAmount, Integer customerId) {

        Customer customer = (Customer) iCustomerService.findByIdFor(customerId);
        Double nowBalance = customer.getAccount().getBalance();

        nowBalance += withdrawAmount;

        customer.getAccount().setBalance(nowBalance);
        iCustomerService.save(customer);
        return "redirect:/profile";
    }

    @RequestMapping(value = "/customers/sendMoney", method = {RequestMethod.PUT, RequestMethod.GET})
    public String sendMoney(Double withdrawAmount, Integer customerId) {

        Customer customer = (Customer) iCustomerService.findByIdFor(customerId);
        Double nowBalance = customer.getAccount().getBalance();
        if (nowBalance >= withdrawAmount) {
            nowBalance -= withdrawAmount;
        }
        customer.getAccount().setBalance(nowBalance);
        iCustomerService.save(customer);
        return "redirect:/profile";
    }

    @RequestMapping(value = "/customers/addRandomNew", method = {RequestMethod.PUT, RequestMethod.GET})
    public String addRandomNew(Integer id) {

        Random random = new Random();
        int number = (int) Math.round(random.nextFloat() * Math.pow(10, 7));


        Customer customer = (Customer) iCustomerService.findByIdFor(id);
        customer.setAccountRequest(2);

        Account account = new Account(number, 100.0, customer);

        iAccountService.save(account);

        return "redirect:/requestAccountCustomer";
    }


}
