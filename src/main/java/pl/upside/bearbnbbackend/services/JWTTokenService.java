package pl.upside.bearbnbbackend.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class JWTTokenService implements Clock, TokenService {
    String issuer;
    String secretKey;
    private static final long EXPIRE_DURATION = 1 * 60 * 60 * 1000;

    public JWTTokenService() {
        super();
        this.issuer = "bearbnb";
        this.secretKey = TextCodec.BASE64.encode("supersecretkey");
    }

    public String newToken(Map<String, String> attributes) {
        final Date now = new Date(System.currentTimeMillis());
        final Claims claims = Jwts.claims().setIssuer(issuer).setIssuedAt(now);
        claims.putAll(attributes);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION));
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    @Override
    public Map<String, String> verify(String token) {
        final JwtParser parser = Jwts.parser().requireIssuer(issuer).setClock(this).setSigningKey(secretKey);
        return parseClaims(() -> parser.parseClaimsJws(token).getBody());
    }

    private static Map<String, String> parseClaims(Supplier<Claims> toClaims) {
        try {
            final Claims claims = toClaims.get();
            final Map<String, String> result = new HashMap<>();
            claims.forEach((key, value) -> result.put(key, String.valueOf(value)));
            return result;

        } catch (IllegalArgumentException | JwtException e) {
            return Map.of();
        }
    }

    @Override
    public Date now() {
        final ZonedDateTime now = ZonedDateTime.now();
        return Date.from(now.toInstant());
    }
}
