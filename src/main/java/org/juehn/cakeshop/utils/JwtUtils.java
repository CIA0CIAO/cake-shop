package org.juehn.cakeshop.utils;

import io.jsonwebtoken.*;

import java.util.Map;
import java.util.UUID;

public class JwtUtils {

    private static String secretKey = "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz";

    public static String createToken(Map<String, ?> claims) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String token = jwtBuilder.setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setClaims(claims)
                .compact();
        return token;
    }

    public static Claims parseToken(String token) {
        JwtParser parser = Jwts.parser();
        Jws<Claims> claimsJws = parser.setSigningKey(secretKey).parseClaimsJws(token);
        return claimsJws.getBody();
    }

}
