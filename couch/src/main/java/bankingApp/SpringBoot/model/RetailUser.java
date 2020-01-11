package bankingApp.SpringBoot.model;


import bankingApp.SpringBoot.model.constraints.bsnDoesNotExistConstraint;
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
public class RetailUser extends User {

    @bsnDoesNotExistConstraint
    @NotNull
    @Min(value = 10000000, message = "BSN must be between 8 and 9 characters without spaces.")
    @Max(value = 999999999, message = "BSN must be between 8 and 9 characters without spaces..")
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

