package com.alura.forohub.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioModelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String uNombre;

    private String uCorreo;
    @Getter
    private String uContrasena;
    private int uPerfil;

    public String getuContrasena() {
        return this.uContrasena;
    }
}
