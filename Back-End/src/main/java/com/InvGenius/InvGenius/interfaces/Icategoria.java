package com.InvGenius.InvGenius.interfaces;

import org.springframework.data.repository.CrudRepository;
import com.InvGenius.InvGenius.models.categoria;

public interface Icategoria extends CrudRepository<categoria, String> {
    
}
