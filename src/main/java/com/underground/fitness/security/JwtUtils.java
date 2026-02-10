package com.underground.fitness.security;

import com.underground.fitness.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;


@Component
public class JwtUtils {

    private String jwtSecret = "5f9f4ecc6862a1c7fc6cf85de8023cb4a4ec7680da29ad229fa311a19f3babdf5d8db66cf866b8825a5e6c1b8d914303b0b3a087e30898c837cc82fc08995626";
    private int jwtExpirationMs = 172800000;

    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String generateTokenFromUserId(String userId,String role) {

        return Jwts.builder()
                .subject(userId)
                .claim("roles", List.of(new SimpleGrantedAuthority(role)))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();

    }

    public boolean validateJwtToken(String jwtToken) {
        try{
            Jwts.parser().verifyWith((SecretKey) key()).build()
                    .parseSignedClaims(jwtToken);
        }
        catch(Exception e){
                e.printStackTrace();
        }
        return true;
    }

    public Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserIdFromToken(String jwtToken) {
        return Jwts.parser().verifyWith((SecretKey) key()).build()
                .parseClaimsJws(jwtToken).getPayload().getSubject();
    }

    public Claims getClaimsFromToken(String jwtToken) {
        return  Jwts.parser().verifyWith((SecretKey) key()).build()
                .parseSignedClaims(jwtToken).getPayload();
    }


}
