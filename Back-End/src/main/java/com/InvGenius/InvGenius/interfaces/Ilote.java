package com.InvGenius.InvGenius.interfaces;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.InvGenius.InvGenius.models.lote;

public interface Ilote extends CrudRepository<lote, String> {
    @Query ("SELECT l FROM lote l WHERE l.fehcaingreso = ?1 OR l.fechaVencimiento = ?2")
    List<lote> loteExist(Date fechaIngreso, Date fechaVencimiento);
    
    
}
