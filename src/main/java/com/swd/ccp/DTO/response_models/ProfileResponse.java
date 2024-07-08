package com.swd.ccp.DTO.response_models;

import com.swd.ccp.models.entity_models.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {

    private Integer id;

    private String email;
    private String username;
    private String phone;
    private String gender;

    private Date dob;

}
