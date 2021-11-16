package com.sample.group.springproductmanagementproject.security.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;
    private String password;
}
