package bankingApp.SpringBoot.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class SmeUser extends User {

    private String roleEmployee;
    // one employee belongs to one company
    @ManyToOne
//    @JoinColumn(name="userId")
    private Company company;
    @NotNull
    @Min(value = 10000000)
    @Max(value = 999999999)
    private int bsn;
    private String firstName;
    private String middleName;
    private String lastName;


    public SmeUser() {
        this("", "");
    }

    public SmeUser(String userName, String password, Company company) {
        super(userName, password);
        this.company = company;
    }

    public SmeUser(String userName, String userPassword) {
        super(userName, userPassword);
    }


    public SmeUser(String userName, String userPassword, String roleEmployee) {
        super(userName, userPassword);
        this.roleEmployee = roleEmployee;
    }

    public SmeUser(String userName, String userPassword, String roleEmployee, int bsn, String firstName, String middleName, String lastName) {
        super(userName, userPassword);
        this.roleEmployee = roleEmployee;
        this.bsn = bsn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

    }

    public SmeUser(String userName, String userPassword, String roleEmployee, Company company) {
        super(userName, userPassword);
        this.roleEmployee = roleEmployee;
        this.company = company;
    }

    public SmeUser(@NotEmpty String userName, @NotEmpty String userPassword, String roleEmployee, Company company, @NotNull @Min(value = 10000000) @Max(value = 999999999) int bsn) {
        super(userName, userPassword);
        this.roleEmployee = roleEmployee;
        this.company = company;
        this.bsn = bsn;
    }

    public SmeUser(String roleEmployee, Company company, @NotNull @Min(value = 10000000) @Max(value = 999999999) int bsn, String firstName, String middleName, String lastName) {
        this.roleEmployee = roleEmployee;
        this.company = company;
        this.bsn = bsn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public int getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    public String getRoleEmployee() {
        return roleEmployee;
    }

    public void setRoleEmployee(String roleEmployee) {
        this.roleEmployee = roleEmployee;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString(){
        return "first name" +firstName + " last name" + lastName +"bsn " + bsn;
    }
}

