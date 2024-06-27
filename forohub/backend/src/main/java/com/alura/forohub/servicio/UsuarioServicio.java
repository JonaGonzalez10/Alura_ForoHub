package com.alura.forohub.servicio;
import com.alura.forohub.modelo.UsuarioModelo;
import com.alura.forohub.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    //@Autowired
    //private ContrasenaCodificada contrasenaCodificada;

    public UsuarioModelo guardar(UsuarioModelo usuario) {
        //usuario.setuContrasena(contrasenaCodificada.codificar(usuario.getuContrasena()));
        return usuarioRepositorio.save(usuario);
    }
    public UsuarioModelo buscarPorCorreo(String correo) {
        return usuarioRepositorio.findByuCorreo(correo);
    }
}
