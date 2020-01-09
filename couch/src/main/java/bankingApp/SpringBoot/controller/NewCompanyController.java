package bankingApp.SpringBoot.controller;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.model.Company;
import bankingApp.SpringBoot.model.SmeUser;
import bankingApp.SpringBoot.service.BankAccountService;
import bankingApp.SpringBoot.service.CompanyService;
import bankingApp.SpringBoot.service.SmeUserCompanyValidator;
import bankingApp.SpringBoot.service.SmeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class NewCompanyController implements WebMvcConfigurer {


    @Autowired
    SmeUserService smeUserService;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    CompanyService companyService;

    @Autowired
    SmeUserCompanyValidator validator;

    private List<String> companyForm = new ArrayList<>();
    private List<String> roles = new ArrayList<>();
    private List<SmeUser> employees = new ArrayList<>();

//
    @GetMapping(value = "couch-zakelijk")
    public String newCompanyHandler(Model model, Company company) {
//        if (companyForm.size() == 0 ) {
//            Collections.addAll(companyForm, "B.V.", "Eenmanszaak", "Vereniging of Stichting", "V.O.F", "Andere ondernemingsvorm");
////        }
//        model.addAttribute("companyForm", companyForm);
//        model.addAttribute("sectors", sectors);
        return "new_company";
    }

    // create a new company
    @PostMapping(value = "couch-zakelijk")
    public String newSMEUserHandler(@Valid @ModelAttribute("company") @RequestBody Company company,
                                    BindingResult bindingResult, SmeUser smeUser, Model model,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession(true);
//        if (roles.size() == 0) {
//            Collections.addAll(roles, "Eigenaar", "Medewerker", "Admin");
//        }
        if (bindingResult.hasErrors()) {
//            model.addAttribute("companyForm", companyForm);
//            model.addAttribute("company", company);
            return "new_company";
        } else {
            BankAccount bankAccount = new BankAccount();
            bankAccount.setAccountType("Zakelijk");
            bankAccountService.newBankAccount(bankAccount);
//            company.setEmployees(employees);
            companyService.newCompany(company);
            // saving company in session
            session.setAttribute("company", company);
            model.addAttribute("company", company);
//            model.addAttribute("roles", roles);
            model.addAttribute("smeUser", smeUser);
            return "new_smeUser";
        }
    }

}


