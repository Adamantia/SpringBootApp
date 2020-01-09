package bankingApp.SpringBoot.model;


import bankingApp.SpringBoot.model.constraints.bsnDoesNotExistConstraint;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RetailUser extends User {


    @bsnDoesNotExistConstraint
    @NotNull
    @Min(value = 10000000, message = "BSN must be between 8 and 9 characters without spaces.")  //AMS: aantal posities mag zijn: 8
    @Max(value = 999999999, message = "BSN must be between 8 and 9 characters without spaces..") //AMS: 8 of 9 posities voldoen
    private int bsn;
    @NotEmpty
    private String firstName;
    private String middleName;
    @NotEmpty
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @NotEmpty
    @Size(min= 9, max=11)
    private String phoneNumber;
    @NotEmpty
    @Pattern(regexp = "^(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01])-(1[9][0-9]{2}|200[0-2])$")
    private String dateOfBirth;
    @NotEmpty
    @Email
    private String email;
    private String role;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts = new ArrayList<BankAccount>();



    public RetailUser() {
        this("","");
        }

    public RetailUser(String userName, String userPassword) {
        super(userName, userPassword);
        this.bankAccounts = new ArrayList<>();
    }

    public RetailUser(String userName, String userPassword, int bsn, String firstName,
                      String middleName, String lastName,
                     Address address, String phoneNumber,
                      String dateOfBirth, String email, String role) {
        super(userName, userPassword);
        this.bsn = bsn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.role = role;

    }


    public RetailUser(String userName, String userPassword, int bsn, String firstName, String middleName,
                      String lastName, Address address, String phoneNumber, String dateOfBirth, String email, String role, List<BankAccount> bankAccounts) {
        super(userName, userPassword);
        this.bsn = bsn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.role = role;
        this.bankAccounts = bankAccounts;
    }

    public int getBsn() { return bsn;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<BankAccount> getBankAccounts() { return bankAccounts; }

    public void setBankAccounts(List<BankAccount> bankAccounts) { this.bankAccounts = bankAccounts; }

    public void addBankAccount(BankAccount bankAccount){
      bankAccounts.add(bankAccount);
    }

    public String getFullName(){
       return firstName + " " + ((middleName != null) ? middleName + " " : "") + lastName;
    }

@Override
    public String toString(){
        return "first name" +firstName + " last name" + lastName +"bsn " + bsn;
    }

}

