package com.InvGenius.InvGenius.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.marca;

@Repository
public interface Imarca extends CrudRepository<marca , String>{
    

    @Query("SELECT ma FROM marca ma WHERE ma.nombreMarca LIKE %?1%")
    List<marca> marcaExist(String nombreMarca);

}
