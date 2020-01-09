package bankingApp.SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("bankingApp.SpringBoot")
@EntityScan("bankingApp.SpringBoot")
@SpringBootApplication
public class CouchApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouchApplication.class, args);

    }
}