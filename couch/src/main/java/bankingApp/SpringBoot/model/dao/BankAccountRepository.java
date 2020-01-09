package bankingApp.SpringBoot.model.dao;

import bankingApp.SpringBoot.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {

    public BankAccount findByBankAccountId(long bankAccountId);

    public BankAccount findByIban(String iban);

    public List<BankAccount> findByAccountType (String accountType);
    

}
