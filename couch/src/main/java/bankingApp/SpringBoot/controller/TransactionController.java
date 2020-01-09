package bankingApp.SpringBoot.controller;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.model.Transaction;
import bankingApp.SpringBoot.model.User;
import bankingApp.SpringBoot.model.dao.BankAccountRepository;
import bankingApp.SpringBoot.service.BankAccountService;
import bankingApp.SpringBoot.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class TransactionController implements WebMvcConfigurer {


    @Autowired
    TransactionService transactionService;

    @Autowired
    BankAccountService bankAccountService;


    @RequestMapping(value = "transactionRequest")
    public String pageHandlerGet(@ModelAttribute User user, Model model, Transaction transaction, HttpServletRequest request) {
        HttpSession session = request.getSession (true);
        BankAccount clickedAccount = (BankAccount)session.getAttribute("clickedBankAccount");
        transaction.setBankAccount(clickedAccount);
        model.addAttribute("transaction", transaction);
        model.addAttribute("date", transaction.getTransactionDay());
        model.addAttribute("bankAccount", clickedAccount);
        model.addAttribute("bankAccountType", clickedAccount.getAccountType());
        model.addAttribute("bankAccountTo", transaction.getBankAccountTo());
        model.addAttribute("fullNames", session.getAttribute("fullNames"));
        return "transaction";
    }


    @PostMapping(value="transactionConfirmation")
    public String transactionHandler(@Valid @ModelAttribute(value = "transaction")Transaction transaction,
                                     BindingResult bindingResult, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession (true);
        BankAccount bankAccountFrom = (BankAccount) session.getAttribute("clickedBankAccount");
        model.addAttribute("bankAccountType", bankAccountFrom.getAccountType());
        if(transaction.getBankAccountTo().getIBAN().equals(transaction.getBankAccount().getIBAN())
                || bindingResult.hasErrors()){
            model.addAttribute("date", transaction.getTransactionDay());
            model.addAttribute("bankAccount", bankAccountFrom.getIBAN());
            model.addAttribute("bankAccountTo", transaction.getBankAccountTo().getIBAN());
            model.addAttribute("userName", (String) session.getAttribute("userName"));
            model.addAttribute("balance", bankAccountFrom.getBalance());
            return "transaction";
        } else {
            String feedback = transactionService.TransactionCalculation(transaction, bankAccountFrom);
            model.addAttribute("feedback", feedback);
            model.addAttribute("fullNames", session.getAttribute("fullNames"));
            return "transaction_feedback";
        }
    }
}
