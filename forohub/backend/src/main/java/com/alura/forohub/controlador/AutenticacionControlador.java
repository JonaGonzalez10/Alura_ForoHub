package com.alura.forohub.controlador;

import com.alura.forohub.servicio.JwtServicio;
import com.alura.forohub.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacion")
public class AutenticacionControlador {
    @Autowired
    private AutenticacionAdmin autenticacionAdmin;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private JwtServicio jwtServicio;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioServicio usuarioServicio) {
     Autenticacion autenticacion = autenticacionAdmin.autenticar(new usuarioServicio.getuCorreo(), usuarioServicio.getuContrasena());
     return ResponseEntity.ok(jwtServicio.crearToken(autenticacion));
}
