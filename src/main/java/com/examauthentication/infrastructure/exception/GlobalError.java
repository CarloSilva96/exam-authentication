package com.examauthentication.infrastructure.exception;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlobalError {
    private String msgError;
    private Map<String, String> errors = new HashMap<>();


}
