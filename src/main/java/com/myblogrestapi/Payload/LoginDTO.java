package com.myblogrestapi.Payload;

import lombok.Data;

@Data
public class LoginDTO {

    private String usernameOrEmail;
    private String password;

}
