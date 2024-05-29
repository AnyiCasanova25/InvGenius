package com.InvGenius.InvGenius.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.proveedor;

@Repository
public interface Iproveedor extends CrudRepository<proveedor, String> {

    @Query ("SELECT prov FROM proveedor prov WHERE prov.estadoProveedor = ?1")
    List<proveedor> proveedorExist(String estadoProveedor);
    
}
