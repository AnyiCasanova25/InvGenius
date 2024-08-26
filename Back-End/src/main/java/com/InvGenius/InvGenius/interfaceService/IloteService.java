package com.InvGenius.InvGenius.interfaceService;

import java.util.Optional;
import java.util.List;
import java.util.Date;

import com.InvGenius.InvGenius.models.lote;

public interface IloteService {
    
    public String save(lote lote);
    public List<lote> findAll();

    //filtro de lote para buscar por el codigo
    public List<lote> loteExist(String codigoLote);

    //Filtro para llamar al lote proximo a caducar
    public List<lote> loteACaducar(String nombreProducto, Date fechaVencimiento);

    //Filtro para llamar al lote bajo en stock
    public List<lote> loteBajoStock(String nombreProducto, String stock);

    //Filtro para llamar al lote vencido
    public List<lote> loteVencido(String nombreProdcuto, Date fechaVencimiento);

    public Optional<lote> findOne (String id);

    public int delete(String id);
    
}
