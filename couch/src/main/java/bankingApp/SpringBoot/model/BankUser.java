package bankingApp.SpringBoot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BankUser extends User {
    private String role;
    private String fullName;
    @OneToMany(mappedBy = "accountManager", cascade = CascadeType.ALL)
    // bank user is responsible for many companies
    private List<Company> companies = new ArrayList<>();

    public BankUser(String userName, String password, String role, String fullName,List<Company> companies) {
        this.setUserName(userName);
        this.setUserPassword(password) ;
        this.role = role;
        this.fullName = fullName;
        this.companies = companies;
    }

    public void addCompany(Company company){
        companies.add(company);
    }
}
