package bankingApp.SpringBoot.controller;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.service.BankAccountService;
import bankingApp.SpringBoot.service.TransactionService;
import bankingApp.SpringBoot.model.Transaction;
import bankingApp.SpringBoot.service.CommonAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class BankAccountDetailsController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    CommonAccountService commonAccountService;

    @GetMapping(value = "/bankAccountDetails")
    public String bankAccountDetailsHandler(@RequestParam("id") long bankAccountId, Model model, HttpServletRequest request) {
        // log in session
        HttpSession session = request.getSession(true);
        BankAccount clickedBankAccount = bankAccountService.findByBankAccountId(bankAccountId);
        String retailUserFullNames = commonAccountService.namingAccounts(clickedBankAccount);
//        session.setAttribute("clickedIBAN", clickedBankAccount.getIBAN());
        List <Transaction> transactionList = clickedBankAccount.getTransactions();
        Collections.reverse(transactionList);
        model.addAttribute("fullNames", retailUserFullNames);
        model.addAttribute("iban", clickedBankAccount.getIBAN());
        model.addAttribute("balance", clickedBankAccount.twoDecimalBalance(clickedBankAccount.getBalance()));
        model.addAttribute("transactionsSize", transactionList.size());
        model.addAttribute("allTransactions", transactionList);
        session.setAttribute("clickedBankAccount", clickedBankAccount);
    //    session.setAttribute("bankAccountId", clickedBankAccount.getBankAccountId());
        session.setAttribute("fullNames", retailUserFullNames);
        return "bank_account_details";
    }


}
