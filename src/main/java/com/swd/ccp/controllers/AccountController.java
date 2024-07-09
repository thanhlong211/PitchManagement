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

    @GetMapping("/{id}/profile")
    public ResponseEntity<List<ProfileResponse>> getCustomerProfile(@PathVariable Integer id) {
        List<ProfileResponse> profileResponses = accountService.getProfile(id);
        if (profileResponses.isEmpty()) {
            return ResponseEntity.notFound().build(); // Or handle as needed
        } else {
            return ResponseEntity.ok(profileResponses);
        }
    }
    @GetMapping("/admin/get_all")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDTOs = accountService.getAllAccountDTOs();
        return ResponseEntity.ok(accountDTOs);
    }

    @PutMapping("/admin/deactivate/{id}")
    public ResponseEntity<ResponseObject> deactivateAccount(@PathVariable Integer id) {
        ResponseObject response = accountService.deactivateAccount(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/admin/activate/{id}")
    public ResponseEntity<ResponseObject> activateAccount(@PathVariable Integer id) {
        ResponseObject response = accountService.activateAccount(id);
        return ResponseEntity.ok(response);
    }
}
