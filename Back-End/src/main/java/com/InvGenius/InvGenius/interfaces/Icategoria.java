package com.InvGenius.InvGenius.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.InvGenius.InvGenius.models.categoria;

public interface Icategoria extends CrudRepository<categoria, String> {

    //Query para los filtros de categoria
    @Query("SELECT c FROM categoria c WHERE c.nombreCategoria = ?1 OR c.estado = ?2")
    List<categoria> categoriaExist(String nombreCategoria, String estado);
    
}
