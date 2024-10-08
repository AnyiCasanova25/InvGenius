package com.InvGenius.InvGenius.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InvGenius.InvGenius.interfaceService.IloteService;
import com.InvGenius.InvGenius.interfaces.Ilote;
import com.InvGenius.InvGenius.models.lote;

@Service
public class loteService implements IloteService {

    @Autowired
    private Ilote data;

    @Override
    public String save(lote lote){
        data.save(lote);
        return lote.getIdLote();
    }

    @Override
    public List<lote> findAll() {
        List<lote> listaLote =  (List<lote>) data.findAll();
        return listaLote;
    }
    
    @Override
    public Optional<lote> findOne (String id) {
        Optional<lote> lote = data.findById(id);
        return lote;
    } 

    //Filtro de lote
    @Override
    public List<lote> loteExist(String codigoLote){
        List<lote> listaLote = data.loteExist(codigoLote);
        return listaLote;
    }

    //Filtro para llamar al lote proximo a caducar
    @Override
    public List<lote> loteACaducar(){
        List<lote> listaLote = data.loteACaducar();
        return listaLote;
    }

    //Filtro para llamar al lote bajo en stock
    @Override
    public List<lote> loteBajoStock(){
        List<lote> listaLote = data.loteBajoStock();
        return listaLote;
    }

    //Filtro para llamar al lote vencido
    @Override
    public List<lote> loteVencido(){
        List<lote> listaLote = data.loteVencido();
        return listaLote;
    }


    @Override
    public int delete(String id) {
        data.deleteById(id);
        return 1;
    }
}
