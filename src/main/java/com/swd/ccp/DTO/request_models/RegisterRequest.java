package com.swd.ccp.DTO.request_models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.swd.ccp.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String email;

    private String password;

    @JsonSerialize
    @JsonInclude
    private String phone;

    private String name;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @JsonSerialize
    @JsonInclude
    private Date dob;

    @JsonSerialize
    @JsonInclude
    private String gender;

    public void setDob(java.util.Date dob) {
        this.dob = new Date(dob.getTime());
    }
}
