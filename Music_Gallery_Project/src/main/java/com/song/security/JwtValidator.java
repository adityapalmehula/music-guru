package com.song.security;



import org.springframework.stereotype.Component;

import com.register.Register;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
    

    private String secret = "adity11musicguru";

    public Register validate(String token) {

    	Register jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new Register();

            jwtUser.setEmailId(body.getSubject());
            jwtUser.setPassword((String) body.get("password"));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
	
}
