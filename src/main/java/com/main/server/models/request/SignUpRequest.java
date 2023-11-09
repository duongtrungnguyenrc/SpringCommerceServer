package com.main.server.models.request;

import lombok.Data;

import java.sql.Date;

@Data
public class SignUpRequest {

    private String name;

    private Date birth;

    private String gender;

    private String phone;

    private String address;

    private String email;

    private String password;

    private String role;
}
