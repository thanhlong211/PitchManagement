package com.swd.ccp.services_implementors;

import com.swd.ccp.DTO.response_models.*;
import com.swd.ccp.mapper.AccountMapper;
import com.swd.ccp.mapper.ProfileMapper;
import com.swd.ccp.models.entity_models.Account;

import com.swd.ccp.repositories.AccountRepo;
import com.swd.ccp.services.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;

    private final AccountMapper accountMapper;
    private final ProfileMapper profileMapper;



    @Override
    public List<AccountDto> getAllAccountDTOs() {
        List<Account> accounts = accountRepo.findAll();
        return accountMapper.toDTOList(accounts);
    }
    @Override
    public List<ProfileResponse> getProfile(Integer id) {
        Optional<Account> customerOptional = accountRepo.findById(id);
        if (customerOptional.isPresent()) {
            Account customer = customerOptional.get();
            ProfileResponse profileResponse = profileMapper.toDTO(customer);
            return Collections.singletonList(profileResponse); // Đảm bảo trả về danh sách đơn
        } else {
            return Collections.emptyList(); // Hoặc xử lý trường hợp không tìm thấy customer
        }
    }




    @Override
    public ResponseObject deactivateAccount(Integer accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));

        if (account.getStatus().equals("active")) {
            account.setStatus("deactive");
            accountRepo.save(account);
            return ResponseObject.<Account>builder()
                    .data(null)
                    .message("Account deactivated successfully")
                    .statusCode(200)
                    .build();
        }

        return ResponseObject.<Account>builder()
                .message("Account is already deactivated")
                .statusCode(400)
                .build();
    }

    @Override
    public ResponseObject activateAccount(Integer accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));

        if (account.getStatus().equals("deactive")) {
            account.setStatus("active");
            accountRepo.save(account);
            return ResponseObject.<Account>builder()
                    .data(null)
                    .message("Account activated successfully")
                    .build();
        }

        return ResponseObject.<Account>builder()
                .message("Account is already active")
                .build();
    }


}
