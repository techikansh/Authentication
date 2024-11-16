package com.alibou.book.auth;

import com.alibou.book.email.EmailService;
import com.alibou.book.role.RoleRepository;
import com.alibou.book.user.User;
import com.alibou.book.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private final EmailService emailService;

    public void register(RegisterRequest request) throws Exception {

        try {
            var userRole = roleRepo.findByName("USER")
                    .orElseThrow(() -> new Exception("Role: USER wurde nicht initialisiert"));

            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .accountLocked(false)
                    .enabled(true)
                    .roles(List.of(userRole))
                    .build();

            userRepo.save(user);
            String subject = "Willkomen bei der Application!";
            String body = String.format("Hallo %s,\n\nVielen Dank für Ihre Registrierung bei der Webseite.\n\nBeste Grüße,\nDas Team",
                    user.getFirstname());
            emailService.sendEMail(user.getEmail(), subject, body);

        }catch (Exception e) {
            System.out.println(e);
        }

    }
}
