package com.InvGenius.InvGenius.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.novedad;

@Repository
public interface Inovedad extends CrudRepository<novedad, String> {
    
    @Query("SELECT n FROM novedad n WHERE n.asunto LIKE %?1%")
    List<novedad> novedadExist(String asunto);

    @Query("SELECT n FROM novedad n WHERE n.fechaNovedad = ?1")
    List<novedad> fechaDeNovedad(Date fechaNovedad);
}
