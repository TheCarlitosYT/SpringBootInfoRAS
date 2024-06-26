//package example.security.jwt;
//
//import example.security.services.UsuarioDetailsImpl;
//import io.jsonwebtoken.*;
//import java.security.Key;
//
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.function.Function;
//
//@Service
//public class JwtUtils {
//    // private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
//
//    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
//
////    private Key key = Jwts.SIG.HS512.key();
//    private int jwtExpirationMs = 86400000;
//
//    public String generateJwtToken(Authentication authentication) {
//        System.out.println("teting 987");
//        return generateJwtToken(new HashMap<>(), authentication);
//    }
//    public String generateJwtToken(HashMap<String,Object> extraClaims, Authentication authentication) {
//
//        System.out.println("testing 123, testing");
//        UsuarioDetailsImpl userPrincipal = (UsuarioDetailsImpl) authentication.getPrincipal();
//
//        String token = Jwts.builder()
//                .setClaims(extraClaims)
//                .setSubject((userPrincipal.getUsername()))
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date((new Date(System.currentTimeMillis())).getTime() + jwtExpirationMs))
//                .signWith(getKey(), SignatureAlgorithm.HS512)
//                .compact();
//        System.out.println("Token: " + token);
//        return token;
//    }
//
//    private Key getKey() {
//        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
//        System.out.println(keyBytes.length + " ");
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String getUsernameFromToken(String token) {
//        return getClaim(token, Claims::getSubject);
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String username=getUsernameFromToken(token);
//        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
//    }
//
//    private Claims getAllClaims(String token)
//    {
//        System.out.println(token);
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
//    {
//        final Claims claims=getAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Date getExpiration(String token)
//    {
//        return getClaim(token, Claims::getExpiration);
//    }
//
//    private boolean isTokenExpired(String token)
//    {
//        return getExpiration(token).before(new Date());
//    }
//
//    /*public boolean validateJwtToken(String authToken) {
//        try {
//            Jwts.parser().verifyWith(key).build().parseSignedClaims(authToken);
//
//            Jwts.parserBuilder().setSigningKey(key).parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException e) {
//            logger.error("Invalid JWT signature: {}", e.getMessage());
//        } catch (MalformedJwtException e) {
//            logger.error("Invalid JWT token: {}", e.getMessage());
//        } catch (ExpiredJwtException e) {
//            logger.error("JWT token is expired: {}", e.getMessage());
//        } catch (UnsupportedJwtException e) {
//            logger.error("JWT token is unsupported: {}", e.getMessage());
//        } catch (IllegalArgumentException e) {
//            logger.error("JWT claims string is empty: {}", e.getMessage());
//        }
//
//        return false;
//    }*/
//}

package example.security.jwt;

import example.security.services.UsuarioDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Component
public class JwtUtils {
    @Value("mqucqhudegg273462384634698347982742gbvjwbwhbcgda9863")
    private String jwtSecret;

    @Value("86400000")
    private int jwtExpirationMs;

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(Authentication authentication) {

        UsuarioDetailsImpl accountPrincipal = (UsuarioDetailsImpl) authentication.getPrincipal();

        return Jwts
                .builder()
                .setSubject((accountPrincipal.getUsername()))
                .setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException | IllegalArgumentException | UnsupportedJwtException | ExpiredJwtException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
