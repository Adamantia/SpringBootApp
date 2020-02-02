package bankingApp.SpringBoot.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import bankingApp.SpringBoot.model.constraints.UsernameDoesNotExistConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Configuration
@EnableAutoConfiguration
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(name = "userName", unique = true)
    @NotEmpty
    @UsernameDoesNotExistConstraint(message = "Username already taken.")
    private String userName;
    @NotEmpty
    private String userPassword;

    public User(@NotEmpty String userName, @NotEmpty String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }
}

