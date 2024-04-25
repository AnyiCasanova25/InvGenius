package com.InvGenius.InvGenius.interfaceService;

import java.util.Optional;
import java.util.List;

import com.InvGenius.InvGenius.models.proveedor;

public interface IproveedorService {

    public String save(proveedor proveedor);
    public List<proveedor> findAll ();

    //Filtro de proveedor
    public List<proveedor> proveedorExist(String marcasProveedor);
    
    public Optional<proveedor> findOne (String id);
    public int delete(String id);
    
}
