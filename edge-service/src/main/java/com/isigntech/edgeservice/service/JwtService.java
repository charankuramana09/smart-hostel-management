package com.isigntech.edgeservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtService {

    private static final String JWT_SECRET = "2v/++UPDcxJ8awevhoGybYEdWlvmwt1GRr1W/SbvgBVqNXzfdtAh8mtOtCucY5TSneWKF6K8XhKTOC3xwrPpUw==";
    private final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

    @Autowired
    private HttpServletRequest request;

    public String generateToken(UserDetails userDetails) {
        String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("authorities", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 654000000))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Claims getClaimsFromToken(String token) {
    	JwtParser parser = Jwts.parser().setSigningKey(key).build(); // Updated parser creation
        return parser.parseClaimsJws(token).getBody();
    }

    public Collection<? extends GrantedAuthority> getAuthoritiesFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        String authoritiesString = claims.get("authorities", String.class);
        if (authoritiesString == null) {
            return Collections.emptyList();
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String authority : authoritiesString.split(",")) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractEmailname(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractEmailname(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
    	 JwtParser parser = Jwts.parser()
    	            .setSigningKey(key)
    	            .build();
    	    return parser.parseClaimsJws(token).getBody(); 

    }
}
