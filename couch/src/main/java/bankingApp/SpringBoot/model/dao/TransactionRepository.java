package bankingApp.SpringBoot.model.dao;

import bankingApp.SpringBoot.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    public Transaction findByTransactionId(int transactiontId);

}
