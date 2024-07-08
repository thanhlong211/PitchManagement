package com.swd.ccp.controllers;


import com.swd.ccp.DTO.request_models.CheckMailExistedRequest;
import com.swd.ccp.DTO.request_models.LoginRequest;
import com.swd.ccp.DTO.request_models.RegisterRequest;
import com.swd.ccp.DTO.response_models.CheckMailExistedResponse;
import com.swd.ccp.DTO.response_models.LoginResponse;
import com.swd.ccp.DTO.response_models.LogoutResponse;
import com.swd.ccp.DTO.response_models.RegisterResponse;
import com.swd.ccp.enums.Role;
import com.swd.ccp.services.AccountService;
import com.swd.ccp.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/customer/login")
    public ResponseEntity<LoginResponse> loginCustomer(@RequestBody LoginRequest request) {
        if (request.getRole() != Role.CUSTOMER) {
            return ResponseEntity.badRequest().body(
                    LoginResponse.builder()
                            .message("Role is not CUSTOMER")
                            .status(false)
                            .build()
            );
        }

        // Call the login method from the service
        LoginResponse response = authenticationService.login(request);

        // Return the response
        if (response.isStatus()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/admin/login")
    public ResponseEntity<LoginResponse> loginAdmin(@RequestBody LoginRequest request) {
        // Check if role is CUSTOMER
        if (request.getRole() != Role.ADMIN) {
            return ResponseEntity.badRequest().body(
                    LoginResponse.builder()
                            .message("Role is not Admin")
                            .status(false)
                            .build()
            );
        }

        // Call the login method from the service
        LoginResponse response = authenticationService.login(request);

        // Return the response
        if (response.isStatus()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PostMapping("/owner/login")
    public ResponseEntity<LoginResponse> loginOwner(@RequestBody LoginRequest request) {
        if (request.getRole() != Role.OWNER) {
            return ResponseEntity.badRequest().body(
                    LoginResponse.builder()
                            .message("Role is not Owner")
                            .status(false)
                            .build()
            );
        }

        // Call the login method from the service
        LoginResponse response = authenticationService.login(request);

        // Return the response
        if (response.isStatus()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }


    @PostMapping("/customer/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok().body(authenticationService.register(request));
    }

}
