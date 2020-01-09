package bankingApp.SpringBoot.service;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.model.Company;
import bankingApp.SpringBoot.model.dao.CompanyRepository;
import bankingApp.SpringBoot.model.dao.SmeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddBankAccountService {
    @Autowired
    CompanyRepository companyDao;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    SmeUserRepository smeUserDao;

    private final static int MAX_ACCOUNTS = 5;

    public AddBankAccountService() {
    }

    public String addBankAccount(Company currentCompany) {
        String message;
        if (currentCompany.getCompanyAccounts().size() >= MAX_ACCOUNTS) {
            message = "You cannot have more than "+ MAX_ACCOUNTS + " bank accounts. ";
        } else {
            BankAccount newBankAccount = new BankAccount();
            newBankAccount.setAccountType("Zakelijk");
            currentCompany.addCompanyAccount(newBankAccount);
            bankAccountService.newBankAccount(newBankAccount);
            companyDao.save(currentCompany);
            message = "Account added";
        }
        return message;
    }
}
