package com.endava.tmd.BookProject.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.replace("Bearer ", "");

        try{
            String key = "8FlNTBBCwVRlfGBI6h4ZtcHKTwCc2IzTD2DAGl18DHJJP54JXQe9BOto8haZwzqV";
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).build().parseClaimsJws(token);

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
