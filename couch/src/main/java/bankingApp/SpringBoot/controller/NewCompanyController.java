package bankingApp.SpringBoot.controller;

import bankingApp.SpringBoot.model.BankAccount;
import bankingApp.SpringBoot.model.Company;
import bankingApp.SpringBoot.model.SmeUser;
import bankingApp.SpringBoot.service.BankAccountService;
import bankingApp.SpringBoot.service.CompanyService;
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
    BankAccountService bankAccountService;

    @Autowired
    CompanyService companyService;

    private List<String> roles = new ArrayList<>();

    @GetMapping(value = "couch-zakelijk")
    public String newCompanyHandler(Model model, Company company) {
        return "new_company";
    }

    // create a new company
    @PostMapping(value = "couch-zakelijk")
    public String newSMEUserHandler(@Valid @ModelAttribute("company") @RequestBody Company company,
                                    BindingResult bindingResult, SmeUser smeUser, Model model,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (roles.size() == 0) {
            Collections.addAll(roles, "Eigenaar", "Medewerker", "Admin");
        }
        if (bindingResult.hasErrors()) {
            return "new_company";
        }

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountType("Zakelijk");
        bankAccountService.newBankAccount(bankAccount);
        companyService.newCompany(company);

        // saving company in session
        session.setAttribute("company", company);
        model.addAttribute("company", company);
        model.addAttribute("roles", roles);
        model.addAttribute("smeUser", smeUser);

        return "new_smeUser";
    }
}


