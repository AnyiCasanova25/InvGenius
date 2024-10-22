package com.InvGenius.InvGenius.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class preRegisterRequest {
    
    private String nombres;
    private String apellidos;
    private String userName;
    private String documentoIdentidad;
    private String celular;
    private String estado;
    private String genero;
    private String tipoDocumento;

}
