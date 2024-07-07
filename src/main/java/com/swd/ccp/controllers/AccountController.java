package com.swd.ccp.controllers;


import com.swd.ccp.DTO.request_models.RegisterRequest;
import com.swd.ccp.DTO.response_models.*;
import com.swd.ccp.models.entity_models.Account;
import com.swd.ccp.services.AccountService;
import com.swd.ccp.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AuthenticationService authenticationService;

    @GetMapping("/active")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<AccountDto>> getAllActiveAccounts() {
        List<AccountDto> accountDTOs = accountService.getAllActiveAccountDTOs();
        return ResponseEntity.ok(accountDTOs);
    }

    @PutMapping("/deactivate/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<ResponseObject> deactivateAccount(@PathVariable Integer id) {
        ResponseObject response = accountService.deactivateAccount(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/activate/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<ResponseObject> activateAccount(@PathVariable Integer id) {
        ResponseObject response = accountService.activateAccount(id);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/createOwner")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<RegisterResponse> CreateOwner(@RequestBody RegisterRequest request){
        return ResponseEntity.ok().body(authenticationService.CreateOwner(request));
    }
}
