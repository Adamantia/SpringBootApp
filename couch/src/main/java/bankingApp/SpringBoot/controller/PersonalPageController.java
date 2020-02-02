package bankingApp.SpringBoot.controller;


import bankingApp.SpringBoot.model.RetailUser;
import bankingApp.SpringBoot.model.User;
import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.service.BankAccountService;
import bankingApp.SpringBoot.service.RetailUserService;
import bankingApp.SpringBoot.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PersonalPageController<retailUser> {


    @Autowired
    RetailUserService retailUserService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    BankAccountService bankAccountService;


    @GetMapping(value = "overview")
    public String getHome(@ModelAttribute User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");
        RetailUser retailUser = retailUserService.findByUserName(userName);
        List<BankAccount> loggedInBankAccounts  = retailUser.getBankAccounts();
        model.addAttribute("userName", userName);
        model.addAttribute("retailUser", retailUser);
        model.addAttribute("retailUserFullName", retailUser.getFullName());
        model.addAttribute("allBankAccounts", loggedInBankAccounts);

        return "personal_page";
    }

    @RequestMapping(value = "newAccountRequest")
    public String getNewAccount(@ModelAttribute User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");

        if (retailUserService.findByUserName(userName) != null) {
            RetailUser retailUser = retailUserService.findByUserName(userName);
            BankAccount newBankAccount = new BankAccount();
            newBankAccount.setAccountType("Retail");
            retailUser.addBankAccount(newBankAccount);
            bankAccountService.newBankAccount(newBankAccount);

            List<BankAccount> bankAccountsList = retailUser.getBankAccounts();
            model.addAttribute("userName", userName);
            model.addAttribute("retailUserFullName", retailUser.getFullName());
            model.addAttribute("allBankAccounts", bankAccountsList);
        }

        return "personal_page";
    }

}


