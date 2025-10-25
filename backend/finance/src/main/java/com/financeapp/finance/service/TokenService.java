package com.financeapp.finance.service;

import java.time.Instant;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.financeapp.finance.model.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public String generatJwt(User user){
        Instant now = Instant.now();
        Instant expiration = Instant.now().plusSeconds(60 * 5);
        String scope = user.getUserId().toString();
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(expiration)
            .subject(user.getEmail())
            .claim("user", scope)
            .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        }

    public boolean validateToken(String token){
        try{
            jwtDecoder.decode(token);
            return true;
        } catch (JwtException e){
            return false;
        }

    }

    public Long getUserIdFromToken(String token){
        try{
            Jwt jwt = jwtDecoder.decode(token);
            String userId = jwt.getClaim("user");
            return Long.valueOf(userId);
        } catch (JwtException e){
            throw new RuntimeException("Invalid token or unable to retrieve userID", e);
        } catch (NumberFormatException e){
            throw new RuntimeException("Invalid userID format in token", e);
        }
    }

    public String getEmailFromToken(String token){
        try{
            Jwt jwt = jwtDecoder.decode(token);
            return jwt.getSubject();
        } catch (JwtException e){
            throw new RuntimeException("Invalid token or unable to extract email");
        }
    }
}
