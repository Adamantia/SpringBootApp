package bankingApp.SpringBoot.util;

import bankingApp.SpringBoot.model.RetailUser;
import bankingApp.SpringBoot.model.SmeUser;
import bankingApp.SpringBoot.model.User;
import bankingApp.SpringBoot.model.dao.CompanyRepository;
import bankingApp.SpringBoot.model.dao.RetailUserRepository;
import bankingApp.SpringBoot.model.dao.SmeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfUserValidator {

    @Autowired
    SmeUserRepository smeUserDao;

    @Autowired
    CompanyRepository companyDao;

    @Autowired
    RetailUserRepository retailUserDao;

    public TypeOfUserValidator() {
    }

    public boolean validateSMEUser(User formUser) {
        SmeUser user = smeUserDao.findByUserName(formUser.getUserName());
       return (user != null);
    }


    public boolean validateRetailUser(User formUser) {
        List<RetailUser> users = retailUserDao.findByUserName(formUser.getUserName());
        return (users != null && users.size() >= 1);
    }
}
