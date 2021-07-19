package br.com.zup.mercadolivre.security;

import br.com.zup.mercadolivre.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenService {

    @Value("${ml.jwt.expiration}")
    private String expiration;

    @Value("${ml.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = Date.from(LocalDateTime.now().
                plusHours(1).
                atZone(ZoneId.of("America/Sao_Paulo")).
                toInstant());
        return Jwts.builder()
                .setIssuer("Api Mercado Livre")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValid(String token) {
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Integer getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.getSubject());
    }
}
