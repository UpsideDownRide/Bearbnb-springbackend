package pl.upside.bearbnbbackend.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.gen.OctetSequenceKeyGenerator;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j

public class JwtTokenService {

    private final Algorithm hmac512;
    private final JWTVerifier verifier;
//    private final DirectEncrypter encrypter;
//    private final OctetSequenceKey key;
    private static final long JWT_TOKEN_VALIDITY = 1 * 60 * 60 * 1000; // 1 hour

    public JwtTokenService(@Value("${jwt.secret}") final String secret) {
        this.hmac512 = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(this.hmac512).build();
//        try {
//            OctetSequenceKey key = new OctetSequenceKeyGenerator(512).generate();
//            this.key = key;
//            this.encrypter = new DirectEncrypter(key);
//        } catch (JOSEException e) {
//            throw new RuntimeException(e);
//        }
    }

//    public String generateToken(final UserDetails user){
//        var now = new Date();
//        var header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A256CBC_HS512);
//        var claims = new JWTClaimsSet.Builder()
//                .subject(user.getUsername())
//                .audience("bearbnb")
//                .expirationTime(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
//                .notBeforeTime(now)
//                .issueTime(now)
//                .jwtID(UUID.randomUUID().toString())
//                .build();
//        var encrypted = new EncryptedJWT(header, claims);
//        try {
//            encrypted.encrypt(encrypter);
//        } catch (JOSEException e) {
//            throw new RuntimeException(e);
//        }
//        return encrypted.serialize();
//    }

    public String generateToken(final UserDetails user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .withIssuer("domirar.com")
                .sign(this.hmac512);
    }

    public Optional<String> validateTokenAndGetUsername(final String token) {
        try {
            return Optional.ofNullable(verifier.verify(token).getSubject());
        } catch (final JWTVerificationException verificationEx) {
            log.warn("token invalid: {}", verificationEx.getMessage());
            return Optional.empty();
        }
    }

}