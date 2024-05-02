package com.InvGenius.InvGenius.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.InvGenius.InvGenius.models.categoria;

public interface Icategoria extends CrudRepository<categoria, String> {

    //Query para los filtros de categoria
    @Query("SELECT c FROM categoria c " 
            +"JOIN c.ubicacion ubi" 
            +"WHERE c.nombreCategoria LIKE %?1%" 
            +"OR c.estado LIKE %?1%"
            +"OR ubi.bloques LIKE %?1%")
    List<categoria> categoriaExist(String nombreCategoria, String estado, String bloques);
    
}
