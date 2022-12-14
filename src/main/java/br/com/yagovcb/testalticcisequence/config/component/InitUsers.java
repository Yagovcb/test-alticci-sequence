package com.praxis.praxis_users.config.component;

import com.praxis.praxis_users.domain.enums.Role;
import com.praxis.praxis_users.domain.model.User;
import com.praxis.praxis_users.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitUsers implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User user = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .email("admin@admin.com")
                    .active(true)
                    .mobile(false)
                    .registrationDate(LocalDate.now())
                    .numberMobile("11 911112222")
                    .fullName("Administrador do Sistema")
                    .roles(Set.of(Role.ROLE_ADMIN, Role.ROLE_USER))
                    .build();
            userRepository.save(user);
        }
    }
}
