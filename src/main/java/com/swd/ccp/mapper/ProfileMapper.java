package com.swd.ccp.mapper;

import com.swd.ccp.DTO.response_models.AccountDto;
import com.swd.ccp.DTO.response_models.ProfileResponse;
import com.swd.ccp.models.entity_models.Account;
import com.swd.ccp.models.entity_models.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProfileMapper {
    public ProfileResponse toDTO(Customer customer) {
        ProfileResponse dto = new ProfileResponse();
        dto.setId(customer.getId());
        dto.setEmail(customer.getAccount().getEmail());
        dto.setUsername(customer.getAccount().getUsername());
        dto.setPhone(customer.getAccount().getPhone());
        dto.setGender(customer.getGender());
        dto.setDob(customer.getDob());
        return dto;
    }

    public List<ProfileResponse> toDTOList(List<Customer> customers) {
        return customers.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
