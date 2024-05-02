package com.InvGenius.InvGenius.interfaces;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.lote;

@Repository
public interface Ilote extends CrudRepository<lote, String> {
    @Query ("SELECT l FROM lote l WHERE l.fechaIngreso = ?1 OR l.fechaVencimiento = ?2")
    List<lote> loteExist(Date fechaIngreso, Date fechaVencimiento);
    
    
}
