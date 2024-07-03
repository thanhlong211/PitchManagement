package com.swd.ccp.services;


import com.swd.ccp.DTO.request_models.LoginRequest;
import com.swd.ccp.DTO.request_models.RegisterRequest;
import com.swd.ccp.DTO.response_models.CheckMailExistedResponse;
import com.swd.ccp.DTO.response_models.LoginResponse;
import com.swd.ccp.DTO.response_models.RegisterResponse;

public interface AuthenticationService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    CheckMailExistedResponse checkUserIsExisted(String email);
}
