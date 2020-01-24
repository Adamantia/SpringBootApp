package bankingApp.SpringBoot.controller;

import bankingApp.SpringBoot.model.*;
import bankingApp.SpringBoot.model.dao.SmeUserRepository;
import bankingApp.SpringBoot.service.*;
import bankingApp.SpringBoot.util.PasswordValidator;
import bankingApp.SpringBoot.util.TypeOfUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class CompanyPageController {

    @Autowired
    PasswordValidator validator;

    @Autowired
    TypeOfUserValidator validator2;

    @Autowired
    SmeUserService smeUserService;

    @Autowired
    CompanyService companyService;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    AddBankAccountService addBankAccountService;

    @Autowired
    SmeUserRepository smeUserDao;

    private String[] roles = {"CEO", "Medewerker", "Admin"};


    // zakelijk klant login & validation
    @PostMapping(value = "zakelijk-klant")
    public String smeLoginHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        // validate user is an SME User
        if (!validator2.validateSMEUser(user) || !validator.validateMemberPassword(user)) {
            String message = "Invalid username and/or password. Please try again";
            SmeUser smeUser = new SmeUser();
            model.addAttribute("user", user);
            model.addAttribute("smeUser", smeUser);
            model.addAttribute("message", message);
            return "company_login";
        }
        SmeUser loggedInUser = smeUserDao.findByUserName(user.getUserName());
        Company company = loggedInUser.getCompany();
        HttpSession session = request.getSession(true);
        String companyData = company.getCompanyName() + " kvkNr: " + company.getChamberOfCommerceId() +
                " gebruiker  : " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName();
        session.setAttribute("companyKvK", loggedInUser.getCompany().getChamberOfCommerceId());
        session.setAttribute("userName", loggedInUser.getUserName());
        session.setAttribute("fullNames", companyData);
        model.addAttribute("smeUser", loggedInUser);
        model.addAttribute("userName", loggedInUser.getUserName());
        model.addAttribute("role", loggedInUser.getRoleEmployee());
        model.addAttribute("company", company);
        model.addAttribute("companyLegalEntity", company.getLegalEntity().getDisplayEntity());
        model.addAttribute("employees", company.getEmployees());
        model.addAttribute("allBankAccounts", loggedInUser.getCompany().getCompanyAccounts());
        model.addAttribute("newbsn", 0);
        model.addAttribute("newrole", "");
        model.addAttribute("roles", roles);
        return "sme_page";
    }

    // user goes back to page
    @GetMapping(value = "zakelijk")
    public String smeHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        int kvkNr = (int) session.getAttribute("companyKvK");
        Company currentCompany = companyService.findByChamberOfCommerceId(kvkNr);
        String userName = (String) session.getAttribute("userName");
        SmeUser smeUser = smeUserService.findByUserName(userName);
        model.addAttribute("smeUser", smeUser);
        model.addAttribute("userName", userName);
        model.addAttribute("role", smeUser.getRoleEmployee());
        model.addAttribute("company", currentCompany);
        model.addAttribute("allBankAccounts", currentCompany.getCompanyAccounts());
        model.addAttribute("employees", currentCompany.getEmployees());
        model.addAttribute("newbsn", 0);
        model.addAttribute("newrole", "");
        model.addAttribute("roles", roles);
        return "sme_page";
    }

    // request for a new company account
    @RequestMapping(value = "newCompanyAccountRequest")
    public String newCompanyAccountRequestHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        // log in session
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");
        int kvkNr = (int) session.getAttribute("companyKvK");
        Company currentCompany = companyService.findByChamberOfCommerceId(kvkNr);
        SmeUser loggedInUser = smeUserService.findByUserName(userName);
        String message = addBankAccountService.addBankAccount(currentCompany);
        model.addAttribute("company", loggedInUser.getCompany());
        model.addAttribute("smeUser", loggedInUser);
        model.addAttribute("userName", loggedInUser.getRoleEmployee());
        model.addAttribute("role", loggedInUser.getRoleEmployee());
        model.addAttribute("companyName", loggedInUser.getCompany().getCompanyName());
        model.addAttribute("allBankAccounts", currentCompany.getCompanyAccounts());
        model.addAttribute("employees", currentCompany.getEmployees());
        model.addAttribute("newbsn", 0);
        model.addAttribute("newrole", "");
        model.addAttribute("roles", roles);
        model.addAttribute("message", message);
        return "sme_page";
    }

    // company details per clicked bank account
    @GetMapping(value = "companyAccountDetails")
    public String companyAccountDetailsHandler(@RequestParam("id") long bankAccountId,
                                               Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        BankAccount clickedBankAccount = bankAccountService.findByBankAccountId(bankAccountId);
        session.setAttribute("clickedBankAccount", clickedBankAccount);
        List<Transaction> transactionList = clickedBankAccount.getTransactions();
        List<Transaction> transactionToList = clickedBankAccount.getTransactionsTo();
        transactionList.addAll(transactionToList);
        Collections.sort(transactionList);
        Collections.reverse(transactionList);
        model.addAttribute("iban", clickedBankAccount.getIban());
        model.addAttribute("balance", clickedBankAccount.twoDecimalBalance(clickedBankAccount.getBalance()));
        model.addAttribute("allTransactions",  transactionList);
        model.addAttribute("fullNames", session.getAttribute("fullNames"));
        session.setAttribute("bankAccountId", clickedBankAccount.getBankAccountId());
        model.addAttribute("roles", roles);
        return "company_account_details";
    }
}
