package com.github.rodis00.astrosales;

import com.github.rodis00.astrosales.user.UserRepository;
import com.github.rodis00.astrosales.user.entity.Role;
import com.github.rodis00.astrosales.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
public class AstrosalesApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    private static Logger logger = LoggerFactory.getLogger(AstrosalesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AstrosalesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<User> adminAccount = userRepository.findByRole(Role.ADMIN);

        if (adminAccount.isEmpty()) {
            User admin = new User();
            admin.setRole(Role.ADMIN);
            admin.setEmail("admin@gmail.com");
            admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
            userRepository.save(admin);

            logger.info("ADMIN ACCOUNT CREATED");
        }
    }
}
