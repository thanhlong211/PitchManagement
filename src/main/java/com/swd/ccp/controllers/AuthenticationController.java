package com.swd.ccp.controllers;


import com.swd.ccp.DTO.request_models.CheckMailExistedRequest;
import com.swd.ccp.DTO.request_models.LoginRequest;
import com.swd.ccp.DTO.request_models.RegisterRequest;
import com.swd.ccp.DTO.response_models.CheckMailExistedResponse;
import com.swd.ccp.DTO.response_models.LoginResponse;
import com.swd.ccp.DTO.response_models.RegisterResponse;
import com.swd.ccp.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok().body(authenticationService.login(request));

    }

    @PostMapping("/check-email")
    public ResponseEntity<CheckMailExistedResponse> checkUserIsExisted(@RequestBody CheckMailExistedRequest request){
        return ResponseEntity.ok().body(authenticationService.checkUserIsExisted(request.getEmail()));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok().body(authenticationService.register(request));
    }

}
