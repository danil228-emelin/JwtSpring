package itmo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//todo add database, there must be authorities,products ONE TO MANY MANY TO ONE
//todo how authorities works
//todo how filter chain works
//todo what is  WebSecurityCustomizer
//todo sensitive information remove from application.properties
@SpringBootApplication
public class SpringSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }
}
