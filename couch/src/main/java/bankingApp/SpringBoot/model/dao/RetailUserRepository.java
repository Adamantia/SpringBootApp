package bankingApp.SpringBoot.model.dao;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.model.RetailUser;

import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface RetailUserRepository extends CrudRepository<RetailUser, Integer> {

    public RetailUser findByBsn(int bsn);

    public RetailUser findByUserName(String userName);

}
