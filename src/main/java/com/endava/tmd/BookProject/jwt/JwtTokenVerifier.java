package com.endava.tmd.BookProject.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    public JwtTokenVerifier(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())){
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");

        try{
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(jwtConfig.getSecretKeyForSigning()).build().parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    Set.of()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch(JwtException e){
            throw new IllegalStateException(String.format("Token %s is not a valid one!",token));
        }

        filterChain.doFilter(request, response);
    }
}
