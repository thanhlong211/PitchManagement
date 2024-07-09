package com.swd.ccp.services_implementors;

import com.swd.ccp.DTO.request_models.LoginRequest;
import com.swd.ccp.DTO.request_models.RegisterRequest;
import com.swd.ccp.DTO.response_models.AccountResponse;
import com.swd.ccp.DTO.response_models.CheckMailExistedResponse;
import com.swd.ccp.DTO.response_models.LoginResponse;
import com.swd.ccp.DTO.response_models.RegisterResponse;
import com.swd.ccp.enums.Role;
import com.swd.ccp.models.entity_models.Account;
import com.swd.ccp.models.entity_models.Customer;
import com.swd.ccp.models.entity_models.Manager;
import com.swd.ccp.models.entity_models.Token;

import com.swd.ccp.repositories.AccountRepo;

import com.swd.ccp.repositories.CustomerRepo;
import com.swd.ccp.repositories.ManagerRepo;
import com.swd.ccp.repositories.TokenRepo;
import com.swd.ccp.services.AuthenticationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {



    private final AccountRepo accountRepo;

    private final CustomerRepo customerRepo;

    private final TokenRepo tokenRepo;

    private final ManagerRepo managerRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (isStringValid(request.getEmail()) && isStringValid(request.getPassword())) {


            // Kiểm tra xem trong cùng một role có username nào trùng không
            if (accountRepo.findByEmailAndRole(request.getEmail(), Role.CUSTOMER).isPresent()) {
                return RegisterResponse.builder()
                        .message("Username already exists in this role")
                        .status(false)
                        .build();
            }

            // Tạo mới account
            Account account = accountRepo.save(
                    Account.builder()
                            .email(request.getEmail())
                            .name(request.getEmail())
                            .password(passwordEncoder.encode(request.getPassword()))
                            .status("active")
                            .role(request.getRole())
                            .build()
            );


            return RegisterResponse.builder()
                    .message("Register successfully")
                    .status(true)
                    .account_id(account.getId())
                    .build();
        }
        return RegisterResponse.builder()
                .message("Invalid email or password")
                .status(false)
                .build();
    }




    @Override
    public LoginResponse login(LoginRequest request) {
        if (isStringValid(request.getEmail()) && isStringValid(request.getPassword())) {
            Account account = accountRepo.findByEmailAndRole(request.getEmail(), request.getRole()).orElse(null);

            if (account == null) {
                return LoginResponse.builder()
                        .message("Email or role not found")
                        .status(false)
                        .build();
            }

            // Check if the password matches
            if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
                return LoginResponse.builder()
                        .message("Incorrect password")
                        .status(false)
                        .build();
            }

            if (!account.getStatus().equals("active")) {
                return LoginResponse.builder()
                        .message("you have been banned!")
                        .status(false)
                        .build();
            }

            Optional<Manager> optionalManager = managerRepo.findByAccount(account);
            Manager manager = optionalManager.orElse(null);

            if (manager == null) {
                return LoginResponse.builder()
                        .message("Login successfully")
                        .status(true)
                        .shopId(0)
                        .account_id(account.getId())
                        .build();
            }

            return LoginResponse.builder()
                    .message("Login successfully")
                    .shopId(manager.getShop().getId())
                    .status(true)
                    .account_id(account.getId())
                    .build();
        }

        return LoginResponse.builder()
                .message("Invalid email or password")
                .status(false)
                .build();
    }

    private Customer checkIfAccountIsCustomer(Account account){
        return customerRepo.findByAccount_Email(account.getEmail()).orElse(null);
    }

    private boolean isStringValid(String string) {
        return string != null && !string.isEmpty();
    }

    private Token getAccessToken(Integer accountId) {
        return tokenRepo.findByAccount_IdAndStatusAndType(accountId, 1, "access").orElse(null);
    }

    private Token getRefreshToken(Integer accountId) {
        return tokenRepo.findByAccount_IdAndStatusAndType(accountId, 1, "refresh").orElse(null);
    }


}
