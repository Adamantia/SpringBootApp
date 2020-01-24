package bankingApp.SpringBoot;


import bankingApp.SpringBoot.model.*;
import bankingApp.SpringBoot.model.dao.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.*;


@Component
@Slf4j
public class HibernateLab implements CommandLineRunner {


    @Autowired
    UserRepository userDao;

    @Autowired
    RetailUserRepository retailUserDao;

    @Autowired
    BankAccountRepository bankAccountDao;

    @Autowired
    TransactionRepository transactionDao;

    @Autowired
    BankUserRepository bankUserDao;

    @Autowired
    SmeUserRepository smeUserDao;

    @Autowired
    CompanyRepository companyDao;


    public HibernateLab()  {
        super();
    }

    public void dbinit() {
        System.out.println("ok ...");


        // to ensure no data duplication
//        // user info for checking if DB is empty
        if (userDao.findByUserPassword("1").size() == 0) {
            User johnDoe = new User("John Doe", "1");
            userDao.save(johnDoe);
//
//            // Creating Bank account data
//            BankAccount account1 = new BankAccount("NL10CCH0123456790", 80.00, "Prive");
//            BankAccount account2 = new BankAccount("NL10CCH0223456791", 10.00, "Prive");
//            BankAccount account3 = new BankAccount("NL10CCH0323456792", 50.00, "Prive");
//            BankAccount account4 = new BankAccount("NL10CCH0423456793", 80.00, "Prive");
//            BankAccount account5 = new BankAccount("NL10CCH0523456794", 100.00, "Prive");
//            BankAccount account6 = new BankAccount("NL10CCH0523456795", 13000, "Prive");
//
//            Address address = new Address("Kalverstraat", 25, "B", "1011AB", "Amsterdam", "NL");
//
//            // Creating Retail user data
//            RetailUser bart = new RetailUser("Bart", "1234", 987654975, "Bart",
//                    "", "Simpson", address, "690000000", "10-10-1900", "bart@hva.nl", "Retail");
//            RetailUser charlotte = new RetailUser("Charlotte", "1234", 987654322,
//                    "Charlotte", "de", "Witte", address, "690000001", "10-10-1999", "cdv@gmail.com", "Retail");
//            RetailUser karin = new RetailUser("Karin", "1234", 987654325,
//                    "Karin", "van", "Dijk", address, "690000801", "10-10-1990", "kvd@gmail.com", "Retail");
//            RetailUser jan = new RetailUser("Jan", "1234", 987654326,
//                    "Jan", "", "Bakken", address, "6901230801", "10-10-1989", "jbakker@gmail.com", "Retail");
//            RetailUser felix = new RetailUser("Felix", "1234", 987654321, "Boudewijn",
//                    "", "Simpson", address, "690000000", "10-10-1900", "bart@hva.nl", "Retail");
//
//            // confirmation that DB is running
//            System.out.println("Creating schema");
//            charlotte.addBankAccount(account2);
//            bart.addBankAccount(account1);
//            jan.addBankAccount(account5);
//            felix.addBankAccount(account6);
//
//            bankAccountDao.save(account1);
//            bankAccountDao.save(account2);
//            bankAccountDao.save(account3);
//            bankAccountDao.save(account4);
//            bankAccountDao.save(account5);
//            bankAccountDao.save(account6);
//
//
//
//            // saving to the db
//            retailUserDao.save(bart);
//            retailUserDao.save(karin);
//            retailUserDao.save(charlotte);
//            retailUserDao.save(jan);
//            retailUserDao.save(felix);
//
//            //  ----  Data for Company & employees ----
//            BankAccount accountTest = new BankAccount("NL10COUC0523455661", 150000);
//            accountTest.setAccountType("Zakelijk");
            List<Company> companies = new ArrayList<>();
            BankUser accountManagerTest = new BankUser("b1", "1234", "Account Manager", "Jan Zucchini", companies);
            bankUserDao.save(accountManagerTest);
//            List<SmeUser> employees = new ArrayList<>();
//            SmeUser smeUserTest = new SmeUser("sme1", "1234", "CEO", 20000000, "Fran", " ", "Tomato");
//            SmeUser smeUserTest2 = new SmeUser("sme2", "1234", "Medewerker", 20000001, "Frank", "van", "Zucchini");
//            SmeUser smeUserTest3 = new SmeUser("sme3", "1234", "Admin", 20000002, "John", "de", "Artichoke");
//            SmeUser existingRetailUser = new SmeUser("sme4", "1234", "Medewerker", 987654322, "Charlotte", "de", "Witte");
//            employees.add(smeUserTest);
//            employees.add(smeUserTest2);
//            employees.add(smeUserTest3);
//            employees.add(existingRetailUser);
//
//            bankAccountDao.save(accountTest);
//
//            List<BankAccount> bankAccountListTest = new ArrayList<>();
//            bankAccountListTest.add(accountTest);
//
////            Company companyTest = new Company(98763198, "Amaphon", "B.V", "ICT", address, "6904134321", 1234, true, employees, bankAccountListTest,
////                    accountManagerTest, "amaphon@nn.nl");
////
////            companyDao.save(companyTest);
////            smeUserTest.setCompany(companyTest);
////            smeUserTest2.setCompany(companyTest);
////            smeUserTest3.setCompany(companyTest);
////            existingRetailUser.setCompany(companyTest);
////            smeUserDao.save(smeUserTest);
////            smeUserDao.save(smeUserTest2);
////            smeUserDao.save(smeUserTest3);
////            smeUserDao.save(existingRetailUser);
//
//
//            //  testData.makeRetailUserList();                          //AMS: haalt retail data op uit CSV file
//            //  testData.retailUserListSplitAddBankaccountAndSave();    //AMS: verwerken testdata
//
//            //bankmedewerkers voor HoofdMKB en HoofdParticulieren
//
//            BankUser bankUser1 = new BankUser("piet", "pietgeheim", "Hoofd MKB");
//            BankUser bankUser2 = new BankUser("marie", "mariegeheim", "Hoofd Particulieren");
//
//            bankUserDao.save(bankUser1);
//            bankUserDao.save(bankUser2);
//
//            System.out.println("dbinit klaar.");
        }

//    }
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("database filled in");

    }
}
