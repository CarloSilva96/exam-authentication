package com.examauthentication.infrastructure.exception;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException {
    private String msg;
    private final Map<String, String> errors = new HashMap<>();
}
