package com.InvGenius.InvGenius.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.InvGenius.InvGenius.models.producto;

public interface Iproducto extends CrudRepository<producto, String>{
    
}
