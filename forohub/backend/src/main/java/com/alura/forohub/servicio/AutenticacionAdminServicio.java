package com.alura.forohub.servicio;

import com.alura.forohub.modelo.UsuarioModelo;
import com.alura.forohub.servicio.JwtServicio;
import com.alura.forohub.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionAdminServicio {

    @Autowired
    private UsuarioServicio servicioUsuario;

    @Autowired
    private JwtServicio servicioJwt;

    public UsuarioModelo autenticar(String correo, String contrasena) {
        UsuarioModelo usuario = servicioUsuario.buscarPorCorreo(correo);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        if (!usuario.getuContrasena().equals(contrasena)) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        return usuario;
    }
}
