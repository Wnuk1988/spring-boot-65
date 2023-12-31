package com.tms.security.filter;

import com.tms.security.CustomUserDetailService;
import com.tms.security.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull
    FilterChain filterChain) throws ServletException, IOException {
        //TODO: 1. Достать токен
        String token = jwtUtils.getTokenFromHttpRequest(request);
        //TODO: 2. Проверить его
        if (token != null && jwtUtils.validateToken(token)) {
            String login = jwtUtils.getLoginFromJwt(token);
            UserDetails userDetails = customUserDetailService.loadUserByUsername(login);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            //TODO: 3. Если все ок, положить пользоателя в SecurityContext
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            log.info("Authentication user with login: " + login);
        }
        filterChain.doFilter(request, response);
    }
}
