package com.InvGenius.InvGenius.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.InvGenius.InvGenius.models.lote;
import com.InvGenius.InvGenius.service.emailService;
import com.InvGenius.InvGenius.service.loteService;

@Component
public class TaskCorreoCaducar {

    @Autowired
    private loteService data;

    @Autowired
    private emailService email;
    

    @Scheduled(cron = "* * 23 * * *")
    public void sendNotificacionLoteACaducar(){
        var listaLote = data.loteACaducar(null);
        for (lote lote : listaLote){
            System.out.println("Lotes proximos a caducar: "+
            lote.getProducto());
            email.enviarCorreoCaducar();
        }
    }
}
