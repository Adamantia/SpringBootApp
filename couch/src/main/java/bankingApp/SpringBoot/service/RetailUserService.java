package bankingApp.SpringBoot.service;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.model.RetailUser;
import bankingApp.SpringBoot.model.dao.RetailUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetailUserService {

    @Autowired
    RetailUserRepository retailUserRepository;

    @Autowired
    BankAccountService bankAccountService;

    public RetailUser findByUserName(String userName) {
        return retailUserRepository.findByUserName(userName);
    }

    public void newRetailUser(RetailUser retailUser){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountType("Private");
        retailUser.addBankAccount(bankAccount);
        bankAccountService.newBankAccount(bankAccount);
        retailUser.setRole("Retail");
        retailUserRepository.save(retailUser);
    }
}
