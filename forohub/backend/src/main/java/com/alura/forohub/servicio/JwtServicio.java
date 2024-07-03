package com.alura.forohub.servicio;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

}
