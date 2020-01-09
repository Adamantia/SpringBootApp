package bankingApp.SpringBoot.model.dao;

import bankingApp.SpringBoot.model.BankUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankUserRepository extends CrudRepository<BankUser,Integer> {

//    public BankUser findByUserName(String userName);

    public List<BankUser> findByUserName(String userName);

    public List<BankUser> findAllByRole(String role);

}
