package bankingApp.SpringBoot.model.dao;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.model.RetailUser;

import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface RetailUserRepository extends CrudRepository<RetailUser, Integer> {

    public List<RetailUser> findAllByBsn(int bsn); // bsn should be unique

    public RetailUser findByBsn(int bsn);



    public List<RetailUser> findByUserName(String userName);

    public List<BankAccount> findBankAccountsByUserName(String userName);

    public List<RetailUser> findRetailUsersByBankAccounts(List<BankAccount> bankAccounts);

}
