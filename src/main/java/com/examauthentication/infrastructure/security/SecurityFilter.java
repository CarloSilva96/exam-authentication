package com.examauthentication.infrastructure.security;

import com.examauthentication.infrastructure.exception.AuthException;
import com.examauthentication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    /**
     * BATEU UMA REQUISICAO NA API ESSE MÉTODO É CHAMADO ANTES DE QUALQUER OUTRA COISA
     **/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var jwtToken = jwtTokenHeader(request);
        if (Optional.ofNullable(jwtToken).isPresent()) {
            var userLogin = jwtTokenService.getValidateTokenAndGetUserLogin(jwtToken);
            if (Optional.ofNullable(userLogin).isPresent()) {
                var user = userRepository.getUserByLogin(userLogin);
                setAuthenticatingInSpringSecurity(user);
            }
        }

        filterChain.doFilter(request, response);
    }
    private String jwtTokenHeader(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");
        if (Optional.ofNullable(authorization).isPresent())
            return authorization.replace("Bearer ", "");

        return null;
    }

    private void setAuthenticatingInSpringSecurity(UserDetails user) {
        var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
