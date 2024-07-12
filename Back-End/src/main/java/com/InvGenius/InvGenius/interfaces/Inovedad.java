package com.InvGenius.InvGenius.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.novedad;

@Repository
public interface Inovedad extends CrudRepository<novedad, String>{
    
    @Query("SELECT n FROM novedad n WHERE n.asunto %?1%")
    List<novedad> asuntoNovedad(String asunto);

    @Query("SELECT n FROM novedad n WHERE n.numeroNovedad = ?2")
    List<novedad> numNovedad(int numeroNovedad);

    @Query("SELECT n FROM novedad n WHERE n.fechaNovedad = ?3")
    List<novedad> fechaNovedad(Date fechaNovedad);
}
