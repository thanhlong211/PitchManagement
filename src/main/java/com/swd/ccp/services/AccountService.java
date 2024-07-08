package com.swd.ccp.services;

import com.swd.ccp.DTO.response_models.*;
import com.swd.ccp.models.entity_models.Account;

import java.util.List;


public interface AccountService {


    List<AccountDto> getAllAccountDTOs();
    ResponseObject deactivateAccount(Integer accountId);
    ResponseObject activateAccount(Integer accountId);
    List<ProfileResponse> getProfile(Integer id);
}
