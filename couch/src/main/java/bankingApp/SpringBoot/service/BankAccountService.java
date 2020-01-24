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


    public BankAccount findByBankAccountId(long bankAccount) {
        return bankAccountRepository.findByBankAccountId(bankAccount);
    }

    public BankAccount findByIban(String iban) {
        return bankAccountRepository.findByIban(iban);
    }


    public BankAccount newBankAccount(BankAccount bankAccount){
        if (bankAccount.getIban() == null) {
            setIban(bankAccount);
        }
        bankAccountRepository.save(bankAccount);
        return bankAccount;
    }

    public void setIban(BankAccount bankAccount) {
        bankAccount.setIban(ibanGenerator.generateIban());
        if (findByIban(bankAccount.getIban()) != null) {
            bankAccount.setIban(ibanGenerator.generateIban());
        }
    }


}
