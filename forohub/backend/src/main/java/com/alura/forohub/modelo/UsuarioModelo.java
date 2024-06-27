package com.alura.forohub.modelo;

import jakarta.persistence.*;
import lombok.Data;

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
    private String uContrasena;
    private int uPerfil;
}
