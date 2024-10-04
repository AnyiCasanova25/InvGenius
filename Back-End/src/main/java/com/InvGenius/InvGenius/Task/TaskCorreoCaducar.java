package com.InvGenius.InvGenius.Task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.InvGenius.InvGenius.service.emailService;
import com.InvGenius.InvGenius.service.loteService;

@Component
public class TaskCorreoCaducar {

    @Autowired
    private loteService data;

    @Autowired
    private emailService email;

    // En este archivo task ira el loteProximoCaducar, loteBajoStock, loteVencido

    // Task para enviar el correo cuando un lote esta proximo a caducar
    @Scheduled(cron = "0 * * * * *") 
    public void sendNotificacionLoteACaducar() {
        var listaLote = data.loteACaducar();
        System.out.println("Lotes próximos a caducar: " + listaLote);

        if (listaLote != null && !listaLote.isEmpty()) {
            
            email.enviarCorreoCaducar();
        } else {
            System.out.println("No hay lotes próximos a caducar.");
        }
    }

    // @Scheduled(cron = "0 * * * * *")
    // public void sendNotificacionLoteACaducar() {
    // var listaLote = data.loteACaducar();
    // if (listaLote != null && !listaLote.isEmpty()) {
    // for (lote lote : listaLote) {
    // System.out.println("Lotes próximos a caducar: " + lote.getProducto());
    // email.enviarCorreoCaducar();
    // }
    // } else {
    // System.out.println("No hay lotes próximos a caducar.");
    // }
    // }

    // Task para enviar el correo cuando el lote este bajo stock

    // Task para enviar el correo cuando el lote este vencido
}
