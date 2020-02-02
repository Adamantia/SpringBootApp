package bankingApp.SpringBoot.controller;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.model.Transaction;
import bankingApp.SpringBoot.model.User;
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
    public String pageHandlerGet(@RequestParam("id") long bankAccountId, @ModelAttribute User user, Model model, Transaction transaction,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession (true);
        BankAccount clickedBankAccount = bankAccountService.findByBankAccountId(bankAccountId);
        session.setAttribute("clickedBankAccount", clickedBankAccount);
        //BankAccount clickedBankAccount = (BankAccount)session.getAttribute("clickedBankAccount");
        transaction.setBankAccount(clickedBankAccount);
        // add validation
        System.out.println("balance" + clickedBankAccount.getBalance());
        System.out.println("iban is: " + clickedBankAccount.getIban());
        User loggedinuser = (User) session.getAttribute("user");
        model.addAttribute("user", loggedinuser);
        model.addAttribute("transaction", transaction);
        model.addAttribute("userName", loggedinuser.getUserName());
        model.addAttribute("transaction", transaction);
        model.addAttribute("date", transaction.getTransactionDay());
        model.addAttribute("bankAccount", clickedBankAccount);
        model.addAttribute("bankAccountId", clickedBankAccount.getBankAccountId());
        model.addAttribute("iban", session.getAttribute("iban"));
       // model.addAttribute("bankAccountType", clickedAccount.getAccountType());
        model.addAttribute("bankAccountTo", transaction.getBankAccountTo());
        model.addAttribute("fullNames", session.getAttribute("fullNames"));
        return "transaction";
    }


    @PostMapping(value="transactionConfirmation")
    public String transactionHandler(@Valid @ModelAttribute(value = "transaction")Transaction transaction,
                                     @RequestParam("id") long bankAccountId, BindingResult bindingResult, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession (true);
        BankAccount bankAccountFrom = (BankAccount) session.getAttribute("clickedBankAccount");
        model.addAttribute("bankAccountType", bankAccountFrom.getAccountType());

        if(transaction.getBankAccountTo().getIban().equals(transaction.getBankAccount().getIban())
                || bindingResult.hasErrors()){
            model.addAttribute("user",session.getAttribute("user"));
            model.addAttribute("date", transaction.getTransactionDay());
            model.addAttribute("bankAccount", bankAccountFrom.getIban());
            model.addAttribute("bankAccountTo", transaction.getBankAccountTo().getIban());
            model.addAttribute("userName", session.getAttribute("userName"));
            model.addAttribute("balance", bankAccountFrom.getBalance());

            return "transaction";
        }

        model.addAttribute("user",session.getAttribute("user"));
        String feedback = transactionService.TransactionCalculation(transaction, bankAccountFrom);
        model.addAttribute("feedback", feedback);
        model.addAttribute("bankAccountId", bankAccountId);
        model.addAttribute("fullNames", session.getAttribute("fullNames"));

        return "transaction_feedback";
    }
}
