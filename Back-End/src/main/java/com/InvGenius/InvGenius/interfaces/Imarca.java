package com.InvGenius.InvGenius.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.InvGenius.InvGenius.models.marca;

public interface Imarca extends CrudRepository<marca , String>{
    

    @Query("SELECT ma FROM marca ma WHERE ma.nombreMarca =")
    List<marca> userExist(String nombreMarca);

    List<marca> marcaExist(String nombreMarca);
}
