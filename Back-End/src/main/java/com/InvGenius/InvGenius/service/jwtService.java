package com.InvGenius.InvGenius.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class jwtService {
    
    public static final String secret_key="6p85dYRId+jbHB1kM+mxcjBjXVH2s+0k6oCiiXtze4E=";


    public String getToken(UserDetails userData) {
        return getToken(new HashMap<>(),userData);
    }

    private String getToken(HashMap<String,Object> extraClaims,UserDetails userData) {
        var fechageneración=new Date(System.currentTimeMillis());
        var fechaVencimiento=new Date(fechageneración.getTime()+1000*60*60*24);
        var token = Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userData.getUsername())
            .setIssuedAt(fechageneración)
            .setExpiration(fechaVencimiento)//un dia de vigencia 
            .signWith(getKey(),SignatureAlgorithm.HS256)
            .compact();
        return token;
    }

    private Key getKey() {
        byte[]keyBytes=Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public <T> T getClaims(String token, Function<Claims, T >claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaims(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

}