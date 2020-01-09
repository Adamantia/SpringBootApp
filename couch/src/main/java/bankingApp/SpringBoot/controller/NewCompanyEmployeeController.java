package bankingApp.SpringBoot.controller;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.model.Company;
import bankingApp.SpringBoot.model.SmeUser;
import bankingApp.SpringBoot.model.dao.SmeUserRepository;
import bankingApp.SpringBoot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NewCompanyEmployeeController {

    @Autowired
    SmeUserService smeUserService;

    @Autowired
    CompanyService companyService;

    @Autowired
    AddEmployeeService addEmployeeService;

    @Autowired
    AddBankAccountService addBankAccountService;

    @Autowired
    SmeUserRepository smeUserDao;

    private String[] roles = {"CEO", "Medewerker", "Admin"};

    // request for a new employee
    @PostMapping(value = "newEmployeeRequest")
    public String newEmployeeRequestHandler(@RequestParam("newbsn") int newbsn, @RequestParam("newrole") String newrole, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");
        int kvkNr = (int) session.getAttribute("companyKvK");
        Company currentCompany = companyService.findByChamberOfCommerceId(kvkNr);
        SmeUser loggedInUser = smeUserService.findByUserName(userName);
        String feedback = addEmployeeService.addEmployee(newbsn, currentCompany, newrole);
        companyService.newCompany(currentCompany);
        List<BankAccount> bankAccountsList = currentCompany.getCompanyAccounts();
        model.addAttribute("company", loggedInUser.getCompany());
        model.addAttribute("smeUser", loggedInUser);
        model.addAttribute("userName", loggedInUser.getRoleEmployee());
        model.addAttribute("role", loggedInUser.getRoleEmployee());
        model.addAttribute("companyName", loggedInUser.getCompany().getCompanyName());
        model.addAttribute("allBankAccounts", bankAccountsList);
        model.addAttribute("employees", currentCompany.getEmployees());
        model.addAttribute("newbsn", newbsn);
        model.addAttribute("newrole", newrole);
        model.addAttribute("feedback", feedback);
        model.addAttribute("roles", roles);
        return "sme_page";
    }

}
