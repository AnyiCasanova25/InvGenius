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

    // Task para enviar el correo cuando el lote este bajo stock
    @Scheduled(cron = "0 * * * * *") // Se ejecuta cada minuto para pruebas
    public void sendNotificacionLoteBajoStock() {
        var listaLote = data.loteBajoStock();
        System.out.println("Lotes con bajo stock: " + listaLote);

        if (listaLote != null && !listaLote.isEmpty()) {
            email.enviarCorreoBajoStock();
        } else {
            System.out.println("No hay productos con stock bajo.");
        }
    }

    // Task para enviar el correo cuando el lote este vencido
    @Scheduled(cron = "0 * * * * *")
    public void sendNotificacionLoteVencido() {
        var listaLote = data.loteVencido();
        System.out.println("Lotes vencidos: " + listaLote);

        if (listaLote != null && !listaLote.isEmpty()) {
            email.enviarCorreoVencido();
        } else {
            System.out.println("No hay lotes vencidos.");
        }
    }

    // No borrar este task
    // public void sendNotificacionLoteACaducar() {
    // var listaLote = data.loteACaducar();
    // System.out.println("Lotes próximos a caducar: " + listaLote);

    // // Verifica si hay lotes próximos a caducar
    // if (listaLote != null && !listaLote.isEmpty()) {
    // // Obtener el nombre de usuario del contexto de seguridad
    // String username =
    // SecurityContextHolder.getContext().getAuthentication().getName();

    // // Obtener los detalles del usuario (puedes usar tu servicio de usuarios)
    // Optional<user> userOptional = authService.findByUsername(username);

    // if (userOptional.isPresent()) {
    // user usuario = userOptional.get();
    // String destinatario = usuario.getCorreo(); // Obtener el correo del usuario

    // // Envía el correo solo si hay lotes próximos a caducar
    // String resultado = email.enviarCorreoCaducar(destinatario); // Pasar el
    // correo al método
    // System.out.println(resultado); // Muestra el resultado del envío del correo
    // } else {
    // System.out.println("Usuario no encontrado.");
    // }
    // } else {
    // System.out.println("No hay lotes próximos a caducar."); // Mensaje para la
    // consola
    // }
    // }
}
