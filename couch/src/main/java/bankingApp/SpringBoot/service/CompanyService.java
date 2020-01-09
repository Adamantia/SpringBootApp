package bankingApp.SpringBoot.service;

import bankingApp.SpringBoot.model.Company;
import bankingApp.SpringBoot.model.dao.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;


    public Company findByChamberOfCommerceId(int chamberOfCommerceId){
        return companyRepository.findBychamberOfCommerceId(chamberOfCommerceId);
    }

    public List<Company> findByCompanyName(String companyName){
        return companyRepository.findByCompanyName(companyName);
    }


    public void newCompany(Company company) {
        companyRepository.save(company);
    }


}
