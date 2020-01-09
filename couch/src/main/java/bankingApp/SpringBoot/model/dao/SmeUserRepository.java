package bankingApp.SpringBoot.model.dao;

import bankingApp.SpringBoot.model.Company;
import bankingApp.SpringBoot.model.SmeUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SmeUserRepository extends CrudRepository<SmeUser, Integer> {

    public SmeUser findByUserName(String userName);

    public SmeUser findByUserId(int userId);

    public List<SmeUser> findByCompany(Company company);

    public SmeUser findByBsn(int bsn);


}
