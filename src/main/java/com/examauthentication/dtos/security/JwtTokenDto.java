package com.examauthentication.dtos.security;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenDto {
    private String token;
}
