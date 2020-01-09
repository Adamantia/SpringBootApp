package bankingApp.SpringBoot.controller;

import bankingApp.SpringBoot.model.RetailUser;
import bankingApp.SpringBoot.service.RetailUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class NewRetailUserController implements WebMvcConfigurer {


    @Autowired
    RetailUserService retailUserService;


   @GetMapping(value = "newRetailUser")
    public String newRetailUserHandler(Model model, RetailUser retailUser){
       model.addAttribute("retailUser", retailUser);
        return "new_retailUser";
    }

    @PostMapping(value ="newRetailUser")
    public String retailUserHandler(@Valid RetailUser retailUser,
                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "new_retailUser";
        } else {
            retailUserService.newRetailUser(retailUser);
        return "new_retailUser_success";
    }
   }

}

