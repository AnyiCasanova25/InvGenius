package com.InvGenius.InvGenius.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.unidad;

@Repository
public interface Iunidad extends CrudRepository<unidad, String>{
    
    @Query("SELECT un FROM unidad un WHERE un.nombreUnidad LIKE %?1%")
    List<unidad> unidadExist(String nombreUnidad);
}
