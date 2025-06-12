package com.bank.bankservice.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
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
                String role = claims.get("role", String.class);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userId,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + role)) // Преобразуем роль в Authority
                );

                // Устанавливаем аутентификацию в SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authToken);

                request.setAttribute("userId", userId);
                request.setAttribute("role", role);

            } catch (Exception e) {
                response.getWriter().write(e.getMessage());
                return;
            }

        }

        chain.doFilter(request, response);
    }
}
