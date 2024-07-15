package com.alura.forohub.servicio;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServicio {
    @Value("${jwt.secret}")
    private String secreto;

    @Value("${jwt.expiration}")
    private Long expiracion;

    public String generarToken(String correo) {
        return Jwts.builder()
                .setSubject(correo)
                .setExpiration(new Date(System.currentTimeMillis() + expiracion))
                .signWith(SignatureAlgorithm.HS512, secreto)
                .compact();
    }

    public String obtenerCorreoDeToken(String token) {
        return Jwts.parser()
                .setSigningKey(secreto)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public String obtenerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validarToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secreto).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
    public String obtenerUsuario(String token) {
        return obtenerCorreoDeToken(token);
    }

}
