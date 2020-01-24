package bankingApp.SpringBoot.controller;

import bankingApp.SpringBoot.HibernateLab;
import bankingApp.SpringBoot.model.*;
import bankingApp.SpringBoot.service.*;
import bankingApp.SpringBoot.util.PasswordValidator;
import bankingApp.SpringBoot.util.TypeOfUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    PasswordValidator validator;

    @Autowired
    TypeOfUserValidator validatorType;

    @Autowired
    HibernateLab lab;

    @Autowired
    RetailUserService retailUserService;

    User user = new User();


    @RequestMapping({"", "/", "/index"})
    public String indexHandler(Model model, HttpSession session) {
        // destroy session
        if (session != null) {
            session.invalidate();
        }
        // initiate database
        lab.dbinit();
        model.addAttribute("user", user);
        return "index";
    }

    // user log in & user validation and direction to personal page
    @PostMapping(value = "overview")
    public String loginHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        // check if user is a Retail User & validate password
        if (!(validatorType.validateRetailUser(user)) || !validator.validateMemberPassword(user) ) {
            String message = "Invalid username and/or password. Please try again";
            model.addAttribute("user", user);
            model.addAttribute("message", message);
            return "index";
        }
        HttpSession session = request.getSession(true);
        String userName = user.getUserName();
        List<RetailUser> loggedInRetailUsers = retailUserService.findByUserName(userName);
        RetailUser loggedInRetailUser = loggedInRetailUsers.get(0);
        List<BankAccount> loggedInBankAccounts = loggedInRetailUser.getBankAccounts();
        // -- for login session ---
        session.setAttribute("userName", userName);
        session.setAttribute("retailUser", loggedInRetailUser);
        session.setAttribute("userId", user.getUserId());
        model.addAttribute("userName", loggedInRetailUser.getUserName());
        model.addAttribute("retailUserFullName", loggedInRetailUser.getFullName());
        model.addAttribute("allBankAccounts", loggedInBankAccounts);
        return "personal_page";

    }

    @GetMapping(value = "retail-login")
    public String newCompanyHandler(@ModelAttribute User user, Model model) {
        SmeUser smeUser = new SmeUser();
        model.addAttribute("user", user);
        model.addAttribute("smeUser", smeUser);
        return "company_login";
    }

    @GetMapping(value = "newUser")
    public String newUserHandler() {
        return "new_user_select_type";
    }
}
