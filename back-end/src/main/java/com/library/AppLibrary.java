package com.library;

import com.library.repository.AuthorRepository;
import com.library.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories("com.library.repository")
@EntityScan("com.library.model")
@EnableTransactionManagement
public class AppLibrary implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(AppLibrary.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
