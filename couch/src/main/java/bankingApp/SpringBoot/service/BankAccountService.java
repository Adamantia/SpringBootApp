package bankingApp.SpringBoot.service;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.model.dao.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    IbanGeneratorService ibanGeneratorService;

    public BankAccount findByBankAccountId(long bankAccount) {
        return bankAccountRepository.findByBankAccountId(bankAccount);
    }

    public BankAccount findByIban(String iban) {
        return bankAccountRepository.findByIban(iban);
    }


    public BankAccount newBankAccount(BankAccount bankAccount){
        if (bankAccount.getIBAN() == null) {
            setIban(bankAccount);
        }
        bankAccountRepository.save(bankAccount);
        return bankAccount;
    }

    public void setIban(BankAccount bankAccount) {
        bankAccount.setIBAN(ibanGeneratorService.generateIban());
        if (findByIban(bankAccount.getIBAN()) != null) {
            bankAccount.setIBAN(ibanGeneratorService.generateIban());
        }
    }
}
