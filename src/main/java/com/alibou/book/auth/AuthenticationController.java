package com.alibou.book.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("register")
    public ResponseEntity<?> register (@RequestBody @Valid RegisterRequest request) {
        try{
            authService.register(request);
            return new ResponseEntity<>("Registierung erfolgreich",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Ein fehler aufgetreten",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
