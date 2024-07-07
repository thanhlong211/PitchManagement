package com.swd.ccp.services;

import com.swd.ccp.DTO.response_models.AccountDto;
import com.swd.ccp.DTO.response_models.AccountResponse;
import com.swd.ccp.DTO.response_models.LogoutResponse;
import com.swd.ccp.DTO.response_models.ResponseObject;
import com.swd.ccp.models.entity_models.Account;

import java.util.List;


public interface AccountService {

    String getAccessToken(Integer accountID);
    String getRefreshToken(Integer accountID);

    Account getCurrentLoggedUser();

    LogoutResponse logout();
    List<AccountDto> getAllActiveAccountDTOs();
    ResponseObject deactivateAccount(Integer accountId);
    ResponseObject activateAccount(Integer accountId);
}
