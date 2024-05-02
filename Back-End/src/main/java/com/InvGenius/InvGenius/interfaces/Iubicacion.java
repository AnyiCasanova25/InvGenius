package com.InvGenius.InvGenius.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.ubicacion;

@Repository
public interface Iubicacion extends CrudRepository<ubicacion, String> {

    
}
