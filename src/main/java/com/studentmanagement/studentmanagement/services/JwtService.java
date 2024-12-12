package com.studentmanagement.studentmanagement.services;
import com.studentmanagement.studentmanagement.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Autowired
    MyUserDetails myUserDetails;

    private String secretKey = null;

    public String generateToken(User user) {
        Map<String , Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        return  Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUsername())
                .issuer("Shantanu")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 120))
                .and()
                .signWith(generateKey())
                .compact();
    }

    public String generateSecurityKey(){
        return secretKey = "ZDDnZumedPK7Rl3Jol+0u+Ak9eKsScc/2Flh9lbquWg=";
    }

    public SecretKey generateKey(){
        byte[] decode = Decoders.BASE64.decode(generateSecurityKey());
        return Keys.hmacShaKeyFor(decode);
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return (String) extractClaim(token, claims -> claims.get("role"));
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
