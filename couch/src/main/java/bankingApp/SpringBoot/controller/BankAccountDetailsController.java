package bankingApp.SpringBoot.controller;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.service.BankAccountService;
import bankingApp.SpringBoot.model.Transaction;
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
    BankAccountService bankAccountService;


    @GetMapping(value = "/bankAccountDetails")
    public String bankAccountDetailsHandler(@RequestParam("id") long bankAccountId, Model model,
                                            HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        BankAccount clickedBankAccount = bankAccountService.findByBankAccountId(bankAccountId);
        String retailUserFullNames = clickedBankAccount.getRetailUsers().get(0).getFullName();

        // getting all transactions of selected bank account
        // TODO: add validation check
        List <Transaction> transactionList = clickedBankAccount.getTransactions();
        Collections.reverse(transactionList);
        model.addAttribute("fullNames", retailUserFullNames);
        model.addAttribute("bankAccount", clickedBankAccount);
        model.addAttribute("userName", (String) session.getAttribute("userName"));
        model.addAttribute("iban", clickedBankAccount.getIban());
        model.addAttribute("balance", clickedBankAccount.twoDecimalBalance(clickedBankAccount.getBalance()));
        model.addAttribute("transactionsSize", transactionList.size());
        model.addAttribute("allTransactions", transactionList);
        session.setAttribute("clickedBankAccount", clickedBankAccount);
        session.setAttribute("fullNames", retailUserFullNames);

        return "bank_account_details";
    }
}
