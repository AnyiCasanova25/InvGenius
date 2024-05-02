package com.InvGenius.InvGenius.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.InvGenius.InvGenius.models.unidad;

public interface Iunidad extends CrudRepository<unidad, String>{
    
    @Query("SELECT un FROM unidad un WHERE un.nombreUnidad LIKE %?1%")
    List<unidad> unidadExist(String nombreUnidad);
}
