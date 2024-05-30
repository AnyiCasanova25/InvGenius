package com.InvGenius.InvGenius.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.ubicacion;


@Repository
public interface Iubicacion extends CrudRepository<ubicacion, String> {
     @Query("SELECT ubi FROM ubicacion ubi WHERE ubi.bloques = ?1")
    List<ubicacion> ubicacionExist(String bloques);
    
}
