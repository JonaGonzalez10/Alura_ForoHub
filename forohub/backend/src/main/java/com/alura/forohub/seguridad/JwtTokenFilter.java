package com.alura.forohub.seguridad;

import com.alura.forohub.servicio.JwtServicio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;

public class JwtTokenFilter extends OncePerRequestFilter{
    private JwtServicio jwtServicio;

    public JwtTokenFilter(JwtServicio jwtServicio){
        this.jwtServicio = jwtServicio;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtServicio.obtenerToken(request);
        if (token != null && jwtServicio.validarToken(token)){
            String usuario = jwtServicio.obtenerUsuario(token);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

}
