package com.InvGenius.InvGenius.service;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InvGenius.InvGenius.interfaceService.ImovimientoService;
import com.InvGenius.InvGenius.interfaces.Imovimientos;
import com.InvGenius.InvGenius.models.movimientos;

@Service
public class movimientosService implements ImovimientoService{
    
    @Autowired
    private Imovimientos data;

    // @SuppressWarnings("null")
    @Override
    public String save(movimientos movimientos) {
        data.save(movimientos);
        return movimientos.getIdMovimiento();
    }

    @Override
    public List<movimientos> findAll(){
        List<movimientos> listaMovimientos = (List<movimientos>) data.findAll();
        return listaMovimientos;
    }

    @Override
    public Optional<movimientos> findOne(String id){
        // @SuppressWarnings("null")
        Optional<movimientos> movimientos = data.findById(id);
        return movimientos;
    }
    

    @Override
    public List<movimientos> movimientosExist(Date FechaMovimiento) {
            List<movimientos> listaMovimientos = data.movimientosExist( FechaMovimiento);
            return listaMovimientos;
    }

    @Override
    public int delete(String id) {
        data.deleteById(id);
        return 1;
    
}
}
