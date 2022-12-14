package com.praxis.praxis_authorization.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.praxis.praxis_authorization.application.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Component
public class AuthSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UsuarioService usuarioService;
    private final int expTime;
    private final String secret;

    public AuthSucessHandler(UsuarioService usuarioService, @Value("${jwt.expiration}") int expTime, @Value("${jwt.secret}") String secret) {
        this.usuarioService = usuarioService;
        this.expTime = expTime;
        this.secret = secret;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String token = JWT.create()
                .withSubject(usuarioService.getUserByUsername(principal.getUsername()).getUsername())
                .withExpiresAt(Instant.ofEpochMilli(ZonedDateTime.now(ZoneId.systemDefault()).toInstant().toEpochMilli() + expTime))
                .sign(Algorithm.HMAC256(secret));
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("Content-Type", "application/json");
        response.getWriter().write("{\"token\": \""+token+"\"}");
    }
}
