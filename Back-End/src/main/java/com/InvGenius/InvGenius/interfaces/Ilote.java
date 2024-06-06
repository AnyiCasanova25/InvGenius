package com.InvGenius.InvGenius.interfaces;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.lote;

@Repository
public interface Ilote extends CrudRepository<lote, String> {

    @Query ("SELECT l FROM lote l WHERE l.codigoLote = ?1")
    List<lote> loteExist(String codigoLote);
    
    
}
