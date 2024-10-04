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

    //En este archivo task ira el loteProximoCaducar, loteBajoStock, loteVencido

    //Task para enviar el correo cuando un lote esta proximo a caducar
    @Scheduled(cron = "* * 23 * * *")
    public void sendNotificacionLoteACaducar(){
        var listaLote = data.loteACaducar();
        for (lote lote : listaLote){
            System.out.println("Lotes proximos a caducar: "+
            lote.getProducto());
            email.enviarCorreoCaducar();
        }
    }

    //Task para enviar el correo cuando el lote este bajo stock




    //Task para enviar el correo cuando el lote este vencido
}
