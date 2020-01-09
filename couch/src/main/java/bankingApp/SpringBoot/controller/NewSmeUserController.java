package bankingApp.SpringBoot.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class NewSmeUserController {

    @Autowired
    SmeUserService smeUserService;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    CompanyService companyService;

    @Autowired
    SmeUserCompanyValidator validator;

    private List<String> roles = new ArrayList<>();

    @RequestMapping(value = "newSMEUser")
    public String newSMEUserHandler(@Valid @ModelAttribute("smeUser") @RequestBody SmeUser smeUser,
                                    BindingResult bindingResult,
                                    Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Company newCompany = (Company) session.getAttribute("company");
        if (roles.size() == 0) {
            Collections.addAll(roles, "Eigenaar", "Medewerker", "Admin");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roles);
            model.addAttribute("smeUser", smeUser);
            return "new_SMEUser";
        } else {
            //check is user already has a company
            if (validator.userOwnsAnotherCompany(smeUser.getBsn())) {
                return "account_overflow";
            }
//            newCompany.addCompanyEmployee(smeUser);
            smeUser.setCompany(newCompany);
            smeUserService.newSmeUser(smeUser);
            return "new_SMEUser_success";
        }
    }
}
