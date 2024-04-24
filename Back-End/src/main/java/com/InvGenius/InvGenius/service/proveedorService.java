package com.InvGenius.InvGenius.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InvGenius.InvGenius.interfaceService.IproveedorService;
import com.InvGenius.InvGenius.interfaces.Iproveedor;
import com.InvGenius.InvGenius.models.proveedor;

@Service
public class proveedorService implements IproveedorService {

    @Autowired
    private Iproveedor data;

    @Override
    public String save(proveedor proveedor){
        data.save(proveedor);
        return proveedor.getIdProveedor();
    }

    @Override
    public List<proveedor> findAll() {
        List<proveedor> listaProveedor=(List<proveedor>) data.findAll();
        return listaProveedor;
    }

    @Override
    public Optional<proveedor> findOne(String id) {
        Optional<proveedor> proveedor = data.findById(id);
        return proveedor;
    }

    @Override
    public int delete(String id) {
        data.deleteById(id);
        return 1;
    }
    
}
