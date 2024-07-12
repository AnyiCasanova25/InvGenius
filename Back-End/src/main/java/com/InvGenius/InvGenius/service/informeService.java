package com.InvGenius.InvGenius.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InvGenius.InvGenius.interfaceService.IInformeService;
import com.InvGenius.InvGenius.interfaces.IInforme;
import com.InvGenius.InvGenius.models.informe;

@Service
public class informeService implements IInformeService{
    
    @Autowired
    private IInforme data;

    @Override
    public String save(informe informe){
        data.save(informe);
        return informe.getIdInforme();
    }
    
    @Override
    public List<informe> findAll(){
        List<informe> listaInforme = (List<informe>) data.findAll();
        return listaInforme;
    }

    @Override
    public Optional<informe> findOne(String id){
        Optional<informe> informe = data.findById(id);
        return informe;
    }

    //Filtros de informe
    public List<informe> informeExist(String tipoMovimiento, String nombreCategoria, LocalDate fechaInforme){
        List<informe> listaInforme=data.informeExist(tipoMovimiento, nombreCategoria, fechaInforme);
        return listaInforme;
    }

    @Override
    public int delete(String id){
        data.deleteById(id);
        return 1;
    }
}
