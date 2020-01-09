package bankingApp.SpringBoot.model.constraints;

import bankingApp.SpringBoot.model.dao.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class kvkNumberDoesNotExistValidator implements ConstraintValidator<kvkNumberDoesNotExistConstraint, Object> {

    @Autowired
    CompanyRepository companyDao;

    @Override
    public void initialize(kvkNumberDoesNotExistConstraint constraint){}

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(companyDao == null){
            return true;
        }
        return companyDao.findBychamberOfCommerceId((int)value) == null;
    }


}
