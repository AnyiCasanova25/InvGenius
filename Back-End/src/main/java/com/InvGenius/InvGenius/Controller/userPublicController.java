package com.InvGenius.InvGenius.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.models.authResponse;
import com.InvGenius.InvGenius.models.loginRequest;
import com.InvGenius.InvGenius.models.preRegisterRequest;
import com.InvGenius.InvGenius.models.registerRequest;
// import com.InvGenius.InvGenius.models.registerRequest;
import com.InvGenius.InvGenius.service.authService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/public/user")
@CrossOrigin
@RequiredArgsConstructor
public class userPublicController {

    private final authService authService;

    @PostMapping("/login")
    public ResponseEntity<authResponse> login(@RequestBody loginRequest request) {
        authResponse response = authService.login(request);
        return new ResponseEntity<authResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/pre-register/")
    public ResponseEntity<authResponse> preRegister(@RequestBody preRegisterRequest request) {
        // Llamar al servicio de preRegistro
        authResponse response = authService.preRegister(request);
        // Devolver la respuesta con el token
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
