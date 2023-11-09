package com.main.server.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private int id;
    private String name;
    private Date birth;
    private String gender;
    private String email;
    private String phone;
    private String address;
}
