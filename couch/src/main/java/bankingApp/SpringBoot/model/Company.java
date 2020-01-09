package bankingApp.SpringBoot.model;

import bankingApp.SpringBoot.model.constraints.kvkNumberDoesNotExistConstraint;
import bankingApp.SpringBoot.model.enums.CompanyLegalEntity;
import bankingApp.SpringBoot.model.enums.CompanySector;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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
    private BankUser accountManager =  new BankUser();

    public Company() {
        super();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddres() {
        return address;
    }

    public void setAddres(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getChamberOfCommerceId() {
        return chamberOfCommerceId;
    }

    public void setChamberOfCommerceId(int chamberOfCommerceId) {
        this.chamberOfCommerceId = chamberOfCommerceId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public CompanySector getSector() {
        return sector;
    }

    public void setSector(CompanySector sector) {
        this.sector = sector;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public boolean isHasPin() {
        return hasPin;
    }

    public void setHasPin(boolean hasPin) {
        this.hasPin = hasPin;
    }

    public List<SmeUser> getEmployees() {
        return employees;
    }

    public void setEmployees(List<SmeUser> employees) {
        this.employees = employees;
    }

    public List<BankAccount> getCompanyAccounts() {
        return companyAccounts;
    }

    public void setCompanyAccounts(List<BankAccount> companyAccounts) {
        this.companyAccounts = companyAccounts;
    }

    public void addCompanyEmployee(SmeUser smeUser){
        employees.add(smeUser);
    }

    public void addCompanyAccount(BankAccount bankAccount){
        companyAccounts.add(bankAccount);
    }

    public CompanyLegalEntity getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(CompanyLegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }

    public BankUser getAccountManager() {
        return accountManager;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAccountManager(BankUser accountManager) {
        this.accountManager = accountManager;
    }
}
