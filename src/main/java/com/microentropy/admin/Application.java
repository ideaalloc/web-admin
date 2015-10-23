package com.microentropy.admin;

import com.microentropy.admin.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-10-23
 */
@SpringBootApplication
@EnableScheduling
public class Application {
    @Value("${admin.mobile}")
    String adminMobile;

    @Value("${admin.email}")
    String adminEmail;

    @Value("${admin.password}")
    String adminPassword;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(final UserService usersService) {

        return arg -> {
            if (!usersService.exists(adminMobile)) {
                usersService.addUser(adminMobile, adminEmail, adminPassword, "ADMIN");
            }
        };

    }

}
