package com.swd.ccp.services_implementors;

import com.swd.ccp.models.entity_models.Customer;
import com.swd.ccp.repositories.CustomerRepo;
import com.swd.ccp.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepository;

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByAccount_Email(email).orElse(null);
    }
}
