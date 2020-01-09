package bankingApp.SpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmeUserCompanyValidator {

    @Autowired
    SmeUserService smeUserService;


    public boolean userOwnsAnotherCompany(int bsn) {
        return (smeUserService.findByBsn(bsn) != null) ;
    }
}

