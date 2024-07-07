package com.swd.ccp.DTO.response_models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private Integer id;

    private String email;

    private String username;

    private String phone;
    private boolean active;

    private String status;

    private String role;
}
