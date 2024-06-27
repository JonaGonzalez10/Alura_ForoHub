package com.alura.forohub.repositorio;
import com.alura.forohub.modelo.UsuarioModelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<UsuarioModelo, String> {
    UsuarioModelo findByuCorreo(String uCorreo);
}
