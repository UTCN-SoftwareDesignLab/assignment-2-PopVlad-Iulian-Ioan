package com.lab4.library;

import com.lab4.library.book.BookRepository;
import com.lab4.library.security.AuthService;
import com.lab4.library.security.dto.SignupRequest;
import com.lab4.library.user.RoleRepository;
import com.lab4.library.user.UserRepository;
import com.lab4.library.user.model.ERole;
import com.lab4.library.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final BookRepository bookRepository;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            bookRepository.deleteAll ();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("vlad@email.com")
                    .username("vlad")
                    .password("WooHoo1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("vlad1@email.com")
                    .username("vlad1")
                    .password("WooHoo1!")
                    .roles(Set.of("REGULAR"))
                    .build());
        }
    }
}
