package com.swd.ccp.mapper;

import com.swd.ccp.DTO.response_models.AccountDto;
import com.swd.ccp.DTO.response_models.AccountResponse;
import com.swd.ccp.models.entity_models.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public AccountDto toDTO(Account account) {
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setEmail(account.getEmail());
        dto.setUsername(account.getName());
        dto.setRole(account.getRole().toString());
        dto.setStatus(account.getStatus());
        dto.setPhone(account.getPhone());
        dto.setActive(account.isActive());
        return dto;
    }

    public List<AccountDto> toDTOList(List<Account> accounts) {
        return accounts.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
