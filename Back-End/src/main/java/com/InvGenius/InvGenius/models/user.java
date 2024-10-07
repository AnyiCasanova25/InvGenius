package com.InvGenius.InvGenius.models;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class user implements UserDetails {

    /*
     * id
     * nombres
     * apellidos
     * documentos
     * celular
     * correo
     * contraseña
     * confirmar contraseña
     * rol
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idUser", nullable = false, length = 36)
    private String idUser;

    @Column(name = "documentoIdentidad", nullable = true, length = 36)
    private String documentoIdentidad;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "celular", nullable = true, length = 13)
    private String celular;

    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    // private String confirmarPassword;

    // @Column( name="imagen_base", nullable = true, columnDefinition = "MEDIUMBLOB")
	// private String  imagen_base;

    @Column(name = "estado", nullable = true, length = 10)
    private String estado;

    @Column(name = "cambiarPassword", nullable = false)
    private boolean cambiarPassword;

    @Column(name = "tipoDocumento", nullable = false, length = 10)
    private String tipoDocumento;

    @Column(name = "genero", nullable = false, length =  100)
    private String genero;

    @Enumerated(EnumType.STRING)
    private rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.rol.name()));
    }

    @Override
    public String getUsername() {
        return this.correo;
    }
}
