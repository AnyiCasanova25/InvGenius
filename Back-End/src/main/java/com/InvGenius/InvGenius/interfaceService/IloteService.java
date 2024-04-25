package com.InvGenius.InvGenius.interfaceService;

import java.util.Optional;
import java.sql.Date;
import java.util.List;

import com.InvGenius.InvGenius.models.lote;

public interface IloteService {
    
    public String save(lote lote);
    public List<lote> findAll();

    //Para los filtros de lote
    public List<lote> loteExist(Date fechaIngreso, Date fechaVencimiento);

    public Optional<lote> findOne (String id);
    public int delete(String id);
    
}
