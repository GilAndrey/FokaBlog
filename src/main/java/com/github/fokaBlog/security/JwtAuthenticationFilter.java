package com.github.fokaBlog.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
* Vai verificar se existe token JWT na requisição
* Ira validar o Token
* Se for válido, ira logar o usuario automanticamente no Spring Security
* */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JWTUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Gets recover header Authorization of requisition
        final String authHeader = request.getHeader("Authorization");

        // Check if exists a token and if starts with Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extrai o token, remove o bearer e pega o email do token
        final String token = authHeader.substring(7);
        final String username = jwtUtils.extractUsername(token);

        // Se o usuario nao estiver autenticado, ira tentar autenticar nesse code
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userDetailsService.loadUserByUsername(username);

            // Cria autenticação e passa um contexto de segurança
            if (jwtUtils.isTokenValid(token, userDetails.getUsername())) {
                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            }

        }
        // Continuar a cadeia de filtros!!
        filterChain.doFilter(request, response);

    }
}
