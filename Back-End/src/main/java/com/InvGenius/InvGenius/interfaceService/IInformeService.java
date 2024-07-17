package com.InvGenius.InvGenius.interfaceService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.InvGenius.InvGenius.models.informe;

public interface IInformeService {

    public String save(informe informe);

    public List<informe> findAll();

    //Para los filtros
    public List<informe> informeExist(String tipomovimiento, String nombreCategoria, LocalDate fechaInforme);
    
    public Optional<informe> findOne (String id);

    public int delete(String id);
}
