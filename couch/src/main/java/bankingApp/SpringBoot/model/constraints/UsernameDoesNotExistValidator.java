package bankingApp.SpringBoot.model.constraints;

import bankingApp.SpringBoot.model.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UsernameDoesNotExistValidator implements ConstraintValidator<UsernameDoesNotExistConstraint, String> {

    @Autowired
    UserRepository userDao;

    @Override
    public void initialize(UsernameDoesNotExistConstraint constraint){}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(userDao == null){
            return true;
        }
        return userDao.findByUserName(value) == null;
    }
}
