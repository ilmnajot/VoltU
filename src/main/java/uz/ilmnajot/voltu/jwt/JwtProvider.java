package uz.ilmnajot.voltu.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long TOKEN_EXPIRATION;

    // Generate the access token for the user
    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, TOKEN_EXPIRATION);
    }

    // Generate the JWT token
    private String generateToken(UserDetails userDetails, long TOKEN_EXPIRATION) {
        return Jwts
                .builder()
                .subject(userDetails.getUsername()) // Set the username as the subject
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION)) // Set expiration
                .signWith(getSignKey()) // Sign the token with the secret key
                .compact(); // Return the generated token
    }

    // Extract username from the JWT token
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // Helper method to extract claims
    private <T> T extractClaims(String token, Function<Claims, T> resolver) {
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token) // Parse the JWT token
                .getBody(); // Get the body (claims) of the token
    }

    // Get the signing key from the secret key
    private Key getSignKey() {
        byte[] decoded = Decoders.BASE64.decode(SECRET_KEY); // Decode the secret key
        return Keys.hmacShaKeyFor(decoded); // Return the key for signing
    }

    // Validate if the token is valid
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String extractedUsername = extractUsername(token); // Extract username from the token
        return (extractedUsername.equals(userDetails.getUsername()) && !isTokenExpired(token)); // Check validity
    }

    // Check if the token is expired
    public boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date()); // Check expiration date
    }

    // Extract the expiration date from the token
    private Date getExpiration(String token) {
        return extractClaims(token, Claims::getExpiration); // Extract expiration claim
    }
}
