package com.bank.bankservice.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import com.bank.bankservice.domain.dto.Role;
import com.bank.bankservice.service.contract.JwtService;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    public void doFilterInternal (@NotNull HttpServletRequest request,
                @NotNull HttpServletResponse response,
                @NotNull FilterChain chain)
                throws IOException, ServletException {

        String token = ((HttpServletRequest) request).getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            try {

                JwtParser parser = Jwts.parser()
                        .verifyWith(jwtService.getSigningKey())
                        .build();

                Claims claims = parser.parseSignedClaims(token).getPayload();

                Long userId = claims.get("id", Long.class);
                Role role = claims.get("role", Role.class);

                request.setAttribute("userId", userId);
                request.setAttribute("role", role);

            } catch (Exception e) {
                response.getWriter().write("Invalid token");
                return;
            }

        }

        chain.doFilter(request, response);
    }
}
