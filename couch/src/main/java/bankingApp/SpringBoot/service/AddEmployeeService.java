package bankingApp.SpringBoot.service;

import bankingApp.SpringBoot.model.Company;
import bankingApp.SpringBoot.model.RetailUser;
import bankingApp.SpringBoot.model.SmeUser;
import bankingApp.SpringBoot.model.dao.CompanyRepository;
import bankingApp.SpringBoot.model.dao.RetailUserRepository;
import bankingApp.SpringBoot.model.dao.SmeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddEmployeeService {

    @Autowired
    CompanyRepository companyDao;

    @Autowired
    RetailUserRepository retailUserDao;

    @Autowired
    SmeUserRepository smeUserDao;

    public AddEmployeeService() {
        super();
    }


    public String addEmployee(int bsn, Company company, String role) {
        // bsn is unique but we have set it up as a list
        String feedback;
        if (smeUserDao.findByBsn(bsn) != null) {
            feedback = "Couch klant niet beschikbaar";
        } else if (retailUserDao.findByBsn(bsn) != null) {
            RetailUser retailsUser = retailUserDao.findByBsn(bsn);
            SmeUser newEmployee = new SmeUser(retailsUser.getUserName(), retailsUser.getUserPassword(), role, company,bsn, retailsUser.getFirstName(), retailsUser.getMiddleName(),
                    retailsUser.getLastName());
            smeUserDao.save(newEmployee);
            companyDao.save(company);
            feedback = "Succes! Medewerker toegevoegd";
        } else {
            feedback = "Couch klant niet gevonden";
        }
        return feedback;
    }
}
