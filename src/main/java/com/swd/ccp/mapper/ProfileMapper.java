package com.swd.ccp.mapper;

import com.swd.ccp.DTO.response_models.ProfileResponse;
import com.swd.ccp.models.entity_models.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProfileMapper {
    public ProfileResponse toDTO(Account customer) {
        ProfileResponse dto = new ProfileResponse();
        dto.setId(customer.getId());
        dto.setEmail(customer.getEmail());
        dto.setUsername(customer.getUsername());
        return dto;
    }

    public List<ProfileResponse> toDTOList(List<Account> customers) {
        return customers.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
