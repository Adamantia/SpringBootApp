package bankingApp.SpringBoot.model;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class BankAccount {
    @Id
    @Column(name="bankAccount_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bankAccountId;
    @Column(name ="iban")
    private String iban;
    private double balance;
    @Pattern(regexp="^(0|[1-9][0-9]*)$")
    @Size(min=5, max=5)
    private String koppelcode;
    @OneToMany(mappedBy = "bankAccount")
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "bankAccountTo")
    private List<Transaction> transactionsTo;
    @ManyToMany(mappedBy = "bankAccounts")
    private List<RetailUser> retailUsers = new ArrayList<RetailUser>();
    @ManyToMany(mappedBy = "companyAccounts")
    private List<Company> companies = new ArrayList<>();
    private String accountType;

    public BankAccount () {
        this(50);
    }

    public BankAccount(double balance){
        this.iban = iban;
        this.balance = balance;
    }

    public BankAccount( String iBAN, double balance) {
        super();
        this.iban = iBAN;
        this.balance = balance;
    ;
    }

    public BankAccount( String iBAN, double balance, String accountType) {
        this.iban = iBAN;
        this.balance = balance;
        this.accountType = accountType;

    }

    public BankAccount(String iban, double balance, List<Transaction> transactions, List<Transaction> transactionsTo,
                       List<RetailUser> retailUsers, List<Company> companies, String accountType) {
        this.iban = iban;
        this.balance = balance;
        this.transactions = transactions;
        this.transactionsTo = transactionsTo;
        this.retailUsers = retailUsers;
        this.companies = companies;
        this.accountType = accountType;
    }

    //getters
    public String getIBAN() { return iban; }
    public double getBalance() {return balance; }
    public long getBankAccountId() { return bankAccountId; }
    public List<Transaction> getTransactions() { return transactions; }
    public List<Transaction> getTransactionsTo() { return transactionsTo; }
    public List<RetailUser> getRetailUsers() { return retailUsers; }
    public String getKoppelcode() {
        return koppelcode;
    }

    public void setBankAccountId(long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }


    public void setRetailUsers(List<RetailUser> retailUsers) {
        this.retailUsers = retailUsers;
    }

    public void setTransactionsTo(List<Transaction> transactionsTo) {
        this.transactionsTo = transactionsTo;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public void setBalance(double balance) { this.balance = balance; }
    public void setKoppelcode(String koppelcode) {
        this.koppelcode = koppelcode;
    }
    public void setIBAN(String iban) {
        this.iban = iban;
    }

    public void addRetailUser(RetailUser retailUser){
        retailUsers.add(retailUser);
    }


    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void addTransaction(Transaction transaction){ transactions.add(transaction); }
    public void addTransactionTo(Transaction transaction){ transactionsTo.add(transaction); }


    public String twoDecimalBalance(double balance){ return String.format("%.2f", balance); }

    @Override
    public String toString() {
        return iban;
    }
}
