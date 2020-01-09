package bankingApp.SpringBoot.model.dao;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.model.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    public Company findBychamberOfCommerceId(int chamberOfCommerceId);

    public List<Company> findByCompanyName(String companyName);

    public List<Company> findByCompanyAccounts(List<BankAccount> list);


}
