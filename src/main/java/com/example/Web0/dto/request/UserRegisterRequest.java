package com.example.Web0.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequest {
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
}
