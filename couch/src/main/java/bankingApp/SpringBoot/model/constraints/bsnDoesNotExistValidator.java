package bankingApp.SpringBoot.model.constraints;

import bankingApp.SpringBoot.model.dao.RetailUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class bsnDoesNotExistValidator implements ConstraintValidator<bsnDoesNotExistConstraint, Integer> {

    @Autowired
    RetailUserRepository retailUserDao;

    @Override
    public void initialize(bsnDoesNotExistConstraint constraint){}

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context){
        if(retailUserDao == null){
            return true;
        }
        return retailUserDao.findByBsn(value)==null;
    }

}
