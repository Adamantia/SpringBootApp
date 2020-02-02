package bankingApp.SpringBoot.service;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.model.dao.BankAccountRepository;
import bankingApp.SpringBoot.util.IbanGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    IbanGenerator ibanGenerator;

    private final double INITIAL_BALANCE = 5.00;


    public BankAccount findByBankAccountId(long bankAccount) {
        return bankAccountRepository.findByBankAccountId(bankAccount);
    }

    public BankAccount findByIban(String iban) {
        return bankAccountRepository.findByIban(iban);
    }


    public BankAccount newBankAccount(BankAccount bankAccount){
        if (bankAccount.getIban() == null) {
            setIban(bankAccount);

            // give initial balance
            bankAccount.setBalance(INITIAL_BALANCE);
            bankAccountRepository.save(bankAccount);
        }

        return bankAccount;
    }

    public void setIban(BankAccount bankAccount) {
        String iban = ibanGenerator.generateIban();
        if (findByIban(iban) != null) {
            iban = ibanGenerator.generateIban();
            // TODO add while loop
        }

        bankAccount.setIban(iban);
    }
}
