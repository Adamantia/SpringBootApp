package bankingApp.SpringBoot.model;

import bankingApp.SpringBoot.model.constraints.kvkNumberDoesNotExistConstraint;
import bankingApp.SpringBoot.model.enums.CompanyLegalEntity;
import bankingApp.SpringBoot.model.enums.CompanySector;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int companyId;
    @Column(name = "kvkNr", unique = true)
    @NotNull
    @Min(value = 10000000, message  = "Chamber of Commerce number must be 8 digits with no spaces")
    @Max(value = 99999999,  message  = "Chamber of Commerce number must be 8 digits with no spaces")
    @kvkNumberDoesNotExistConstraint(message = "Chamber of Commerce number already in use.")
    private int chamberOfCommerceId;
    @NotEmpty
    private String companyName;
    @Enumerated(value = EnumType.STRING)
    private CompanyLegalEntity legalEntity;
    @Enumerated(value = EnumType.STRING)
    private CompanySector sector;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @NotEmpty
    @Size(min= 9, max=11)
    private String phoneNumber;
    @NotEmpty
    @Email
    private String email;
    private int pinCode;
    private boolean hasPin;
    @OneToMany(mappedBy="company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SmeUser> employees;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<BankAccount> companyAccounts = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BankUser accountManager;


    public Company(int chamberOfCommerceId,
                   @NotEmpty String companyName, CompanyLegalEntity legalEntity, CompanySector sector,
                   Address address, String phoneNumber,
                   int pinCode, boolean hasPin, List<SmeUser> employees,
                   List<BankAccount> companyAccounts, BankUser accountManager, String email) {

        super();
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.legalEntity = legalEntity;
        this.sector = sector;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.pinCode = pinCode;
        this.hasPin = hasPin;
        this.employees = employees;
        this.companyAccounts = companyAccounts;
        this.accountManager = accountManager;
        this.email = email;
    }

    public Company(int chamberOfCommerceId, String companyName, CompanyLegalEntity legalEntity, CompanySector sector, Address address,
                   String phoneNumber,
                   String email) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.legalEntity = legalEntity;
        this.sector = sector;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public void addCompanyEmployee(SmeUser smeUser){
        employees.add(smeUser);
    }

    public void addCompanyAccount(BankAccount bankAccount){
        companyAccounts.add(bankAccount);
    }

}
