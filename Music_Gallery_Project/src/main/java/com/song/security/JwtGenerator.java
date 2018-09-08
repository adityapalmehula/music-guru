package com.song.security;

import org.springframework.stereotype.Component;

import com.register.Register;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {


    public String generate(Register jwtUser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getEmailId());
        claims.put("password", String.valueOf(jwtUser.getPassword()));
        claims.put("role", jwtUser.getRole());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "adity11musicguru")
                .compact();
    }
}
