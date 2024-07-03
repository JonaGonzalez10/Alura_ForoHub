package com.alura.forohub.controlador;

import com.alura.forohub.modelo.AutenticacionModelo;
import com.alura.forohub.dto.LoginDto;
import com.alura.forohub.modelo.UsuarioModelo;
import com.alura.forohub.servicio.AutenticacionAdminServicio;
import com.alura.forohub.servicio.JwtServicio;
import com.alura.forohub.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/autenticacion")
public class AutenticacionControlador {
    @Autowired
    private AutenticacionAdminServicio autenticacionAdmin;


    @Autowired
    private JwtServicio jwtServicio;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        UsuarioModelo usuarioModelo = autenticacionAdmin.autenticar(loginDto.getCorreo(), loginDto.getContrasena());
        return ResponseEntity.ok(jwtServicio.generarToken(String.valueOf(usuarioModelo)));
    }
}