package com.swd.ccp.services;

import com.swd.ccp.models.entity_models.Customer;

public interface CustomerService {
    Customer findCustomerByEmail(String email);
}
