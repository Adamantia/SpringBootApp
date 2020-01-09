package bankingApp.SpringBoot.service;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.model.Transaction;
import bankingApp.SpringBoot.model.dao.BankAccountRepository;
import bankingApp.SpringBoot.model.dao.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionDao;

    @Autowired
    BankAccountRepository bankAccountDao;

    @Autowired
    BankAccountService bankAccountService;

    public TransactionService() {
        super();

    }

    public String TransactionCalculation(Transaction transaction, BankAccount bankAccountFrom) {

        if (transaction.getBankAccountTo().getIBAN().equals(transaction.getBankAccount().getIBAN())) {
            return "Please enter an account number other than your own";
        }
        // check that bank account to exists
        BankAccount bankAccountTo = bankAccountService.findByIban(transaction.getBankAccountTo().getIBAN());
        if (bankAccountTo == null) {
            return "Transaction failed. Iban not found.";
        }
        double oldBalance = bankAccountFrom.getBalance();
        double newBalance = bankAccountFrom.getBalance() - transaction.getAmount();
        System.out.println(newBalance);
        if (newBalance < 0) {
            return "Transaction failed. Your balance is too low.";
        } else {
            // update balance in the send account
            double balance = bankAccountTo.getBalance();
            bankAccountTo.setBalance(balance + transaction.getAmount());
            bankAccountFrom.setBalance(newBalance);
            bankAccountDao.save(bankAccountTo);
            bankAccountDao.save(bankAccountFrom);
            Transaction transaction1 = new Transaction(transaction.getAmount(), transaction.getTransactionDate(),
                    bankAccountFrom, bankAccountTo, transaction.getDescription());
            transactionDao.save(transaction1);
            return "\nThank you!\n<small>Your transaction of " + String.format("%.2f", transaction.getAmount())
                    + " euro was successful. \nYour balance was: " +
                    String.format("%.2f", oldBalance) +
                    "\nYour current balance is " + String.format("%.2f", newBalance) + " euro.</small>";
        }
    }

}
