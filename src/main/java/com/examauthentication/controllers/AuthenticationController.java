package com.examauthentication.controllers;

import com.examauthentication.dtos.security.JwtTokenDto;
import com.examauthentication.dtos.security.UserAuthenticationDto;
import com.examauthentication.infrastructure.security.JwtTokenService;
import com.examauthentication.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    @PostMapping
    public ResponseEntity<JwtTokenDto> authenticate(@RequestBody @Valid UserAuthenticationDto userAuthenticationDto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userAuthenticationDto.getLogin(), userAuthenticationDto.getPassword());
        var authenticatedUser = authenticationManager.authenticate(authenticationToken);
        var user = (User) authenticatedUser.getPrincipal();
        var jwtToken = jwtTokenService.generationJwtToken(user);
        return ResponseEntity.ok(
                JwtTokenDto.builder()
                        .token(jwtToken)
                        .build()
        );
    }
}
