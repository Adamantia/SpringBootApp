package bankingApp.SpringBoot.model.dao;

import bankingApp.SpringBoot.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByUserName(String userName); // username must be unique
    public List<User> findByUserPassword(String userPassword);


}
