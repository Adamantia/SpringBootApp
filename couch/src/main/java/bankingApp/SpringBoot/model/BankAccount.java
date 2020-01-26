package bankingApp.SpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Id
    @Column(name="bankAccount_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bankAccountId;
    @Column(name ="iban")
    private String iban;
    private double balance;
    @OneToMany(mappedBy = "bankAccount")
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "bankAccountTo")
    private List<Transaction> transactionsTo;
    @ManyToMany(mappedBy = "bankAccounts")
    private List<RetailUser> retailUsers = new ArrayList<RetailUser>();
    @ManyToMany(mappedBy = "companyAccounts")
    private List<Company> companies = new ArrayList<>();
    private String accountType;

    public BankAccount(String iban, double balance, String accountType) {
        this.iban = iban;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String twoDecimalBalance(double balance){ return String.format("%.2f", balance); }

    @Override
    public String toString() {
        return iban;
    }
}
