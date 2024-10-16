package edu.api.bookflow.Configuration.AuthConfig;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.api.bookflow.Model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenProvider {

    @Value("${api.security.token.secret}")
    private String JWT_SECRET;

    public String generateAccessToken(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                    .withSubject(usuario.getEmail())
                    .withClaim("email", usuario.getEmail())
                    .withExpiresAt(generateAccessExpirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw  new JWTCreationException("Falha ao gerar token.", e);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTCreationException e) {
            throw new JWTVerificationException("Erro ao validar token",e);
        }
    }

    private Instant generateAccessExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
