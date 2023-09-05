package org.juehn.cakeshop.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.juehn.cakeshop.entity.User;
import org.juehn.cakeshop.utils.JwtUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JwtUtilsTest {

    String token;

    @Test
    public void createToken() throws JsonProcessingException {
        Map<String, Object> claims = new HashMap<>();
        User user = new User();
        user.setUsername("juehn");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        claims.put("user", userJson);
        token = JwtUtils.createToken(claims);
        System.out.println(token);
    }

    @Test
    public void parseToken() throws JsonProcessingException {
        createToken();
        Claims claims = JwtUtils.parseToken(token);
        String userJson = (String) claims.get("user");
        User user = new ObjectMapper().readerFor(User.class).readValue(userJson);
        System.out.println(user.getUsername());
    }
}
