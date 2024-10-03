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

import lombok.Builder;


@Builder
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

    private String confirmarPassword;

    @Column( name="imagen_base", nullable = true, columnDefinition = "MEDIUMBLOB")
	private String  imagen_base;

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

    public user() {
    }

    public user(String idUser, String documentoIdentidad, String nombres, String apellidos, String celular,
            String correo, String password, String confirmarPassword, String imagen_base, String estado,
            boolean cambiarPassword, String tipoDocumento, String genero, com.InvGenius.InvGenius.models.rol rol) {
        this.idUser = idUser;
        this.documentoIdentidad = documentoIdentidad;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.correo = correo;
        this.password = password;
        this.confirmarPassword = confirmarPassword;
        this.imagen_base = "data:image/jpeg;base64,"+ imagen_base;
        this.estado = estado;
        this.cambiarPassword = cambiarPassword;
        this.tipoDocumento = tipoDocumento;
        this.genero = genero;
        this.rol = rol;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmarPassword() {
        return confirmarPassword;
    }

    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }

    public String getImagen_base() {
        return imagen_base;
    }

    public void setImagen_base(String imagen_base) {
        this.imagen_base = "data:image/jpeg;base64,"+ imagen_base;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isCambiarPassword() {
        return cambiarPassword;
    }

    public void setCambiarPassword(boolean cambiarPassword) {
        this.cambiarPassword = cambiarPassword;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public rol getRol() {
        return rol;
    }

    public void setRol(rol rol) {
        this.rol = rol;
    }
    
}
