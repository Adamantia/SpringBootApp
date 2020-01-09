package bankingApp.SpringBoot.service;

import bankingApp.SpringBoot.model.User;
import bankingApp.SpringBoot.model.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PasswordValidator {

    @Autowired
    UserRepository userDao;


    public PasswordValidator() {
        super();
    }

    public boolean validateMemberPassword(User formUser) {
        boolean loginOk;
        // user name is unique
        User dbUser = userDao.findByUserName(formUser.getUserName());
        if (dbUser != null) {
            loginOk = dbUser.getUserPassword().equals(formUser.getUserPassword());
        } else {
            loginOk = false;
        }
        return loginOk;
    }

}