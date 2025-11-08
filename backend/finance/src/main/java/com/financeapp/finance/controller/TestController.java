package com.financeapp.finance.controller;

import java.time.Instant;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @GetMapping("/public/hello")
    public String publicEndpoint(){
        return "this is public no auth";
    }
    
    @GetMapping("/protected/hello")
    public String protectedEndpoint(){
        return "this is protected need auth";
    }

    @GetMapping("/public/token")
    public Map<String, String> getTestToken(){
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(3600))
            .subject("test-user")
            .claim("username", "testuser")
            .build();
        String token = jwtEncoder.encode(org.springframework.security.oauth2.jwt.JwtEncoderParameters.from(claims)).getTokenValue();
        return Map.of("token", token);
    }

}
