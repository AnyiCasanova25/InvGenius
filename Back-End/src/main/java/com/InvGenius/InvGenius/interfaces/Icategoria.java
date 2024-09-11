package com.InvGenius.InvGenius.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.categoria;

@Repository
public interface Icategoria extends CrudRepository<categoria, String> {

    //Query para los filtros de categoria
    @Query("SELECT c FROM categoria c WHERE c.nombreCategoria LIKE %?1% OR c.estado LIKE %?2% OR c.ubicacion LIKE %?3%")
    List<categoria> categoriaExist(String nombreCategoria, String estado, String ubicacion);
    
}
