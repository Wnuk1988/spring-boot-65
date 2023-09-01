package com.tms.security.controller;

import com.tms.security.domain.AuthRequest;
import com.tms.security.domain.AuthResponse;
import com.tms.security.domain.RegistrationDTO;
import com.tms.security.service.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    private final SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody RegistrationDTO registrationDTO){
        securityService.registration(registrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest authRequest){
        //1. Генерация JWT если все хоршо
        //2. или возврощать 401 если все плохо
        String token = securityService.generateToken(authRequest);
        if (token.isBlank()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }
}
