package bankingApp.SpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmeUser extends User {

    private String roleEmployee;
    @ManyToOne
    private Company company;
    @NotNull
    @Min(value = 10000000)
    @Max(value = 999999999)
    private int bsn;
    private String firstName;
    private String middleName;
    private String lastName;


    public SmeUser(String userName, String password, String roleEmployee, int bsn, String firstName,
                   String middleName, String lastName) {
        super(userName, password);
        this.roleEmployee = roleEmployee;
        this.bsn = bsn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public SmeUser(String userName, String password, String roleEmployee, Company company, int bsn, String firstName,
                   String middleName, String lastName) {
        super(userName, password);
        this.roleEmployee = roleEmployee;
        this.bsn = bsn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.company = company;
    }

    public String getUserName() {
        return super.getUserName();
    }

    @Override
    public String toString(){
        return "first name" +firstName + " last name" + lastName +"bsn " + bsn;
    }
}

