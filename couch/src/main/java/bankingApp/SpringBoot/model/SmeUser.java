package bankingApp.SpringBoot.model;

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


    public SmeUser(String userName, String Password, String roleEmployee, Company company, int bsn, String firstName, String middleName, String lastName) {
        super();
        this.roleEmployee = roleEmployee;
        this.company = company;
        this.bsn = bsn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }


    @Override
    public String toString(){
        return "first name" +firstName + " last name" + lastName +"bsn " + bsn;
    }
}

