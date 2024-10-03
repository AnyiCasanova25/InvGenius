package com.InvGenius.InvGenius.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// @Getter
// @Setter
// @Builder
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
@Entity (name = "categoria")
public class categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idCategoria", nullable = false, length = 36)
    private String idCategoria;

    @Column(name = "nombreCategoria", nullable = false, length = 40)
    private String nombreCategoria;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "ubicacion", nullable = false, length = 20)
    private String ubicacion;

    @Column( name="imagen_base", nullable = true, columnDefinition = "MEDIUMBLOB")
	private String  imagen_base;

    //maximo 10megas
    //rechazar la peticion si es muy larga la imagen 
    

    public categoria() {
    }

    public categoria(String idCategoria, String nombreCategoria, String estado, String ubicacion, String imagen_base) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.estado = estado;
        this.ubicacion = ubicacion;
		this.imagen_base = "data:image/jpeg;base64,"+ imagen_base;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getImagen_base() {
        return imagen_base;
    }

    public void setImagen_base(String imagen_base) {
        this.imagen_base = "data:image/jpeg;base64,"+ imagen_base;
    }
}
