package bankingApp.SpringBoot.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Transaction implements Comparable<Transaction> { //implements Serializable {

    @Id
    @Column(name="transaction_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private String description;
    @NotNull
    private double amount;
    private Date transactionDate;
    @ManyToOne
    private BankAccount bankAccount;
    @ManyToOne
    private BankAccount bankAccountTo;

    public Transaction() {

        super();
        this.transactionDate = new Date();
    }


    public Transaction(double amount, Date transactionDate, BankAccount bankAccount,
                       BankAccount bankAccountTo, String description) {
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.bankAccount = bankAccount;
        this.bankAccountTo = bankAccountTo;
        this.description = description;
    }


    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccountTo() {
        return bankAccountTo;
    }

    public void setBankAccountTo(BankAccount bankAccountTo) {
        this.bankAccountTo = bankAccountTo;
    }

    public String getTransactionDateFormatted() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(transactionDate);
    }

    public String getTransactionDay() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//HH:mm:ss
        return dateFormat.format(transactionDate);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "idTransaction=" + transactionId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + this.getTransactionDateFormatted() +
                ", transactionDay=" + this.getTransactionDay() +
                 ", bank account from=" + this.getBankAccount() +
                ", bank account to=" + this.getBankAccountTo() +
                '}';
    }

    @Override
    public int compareTo(Transaction o) {
        return getTransactionDate().compareTo(o.getTransactionDate());
    }
}
