package bankingApp.SpringBoot.util;

import bankingApp.SpringBoot.service.SmeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyOwnerValidator {

    @Autowired
    SmeUserService smeUserService;


    public boolean userOwnsAnotherCompany(int bsn) {
        return (smeUserService.findByBsn(bsn) != null) ;
    }
}

