package com.InvGenius.InvGenius.Task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.InvGenius.InvGenius.interfaceService.IuserService;
import com.InvGenius.InvGenius.models.user;
import com.InvGenius.InvGenius.service.emailService;

@Component
public class TaskCorreoUsuarioRegistrado {

    @Autowired
    private IuserService authService;

    @Autowired
    private emailService emailService;

    // Task para enviar el correo de registro
    @Scheduled(fixedDelay = 3000)  
    public void sendCorreoRegistro() {
        var listaUser = authService.findAll(); 
        for (user user : listaUser) {
            System.out.println("Enviando correo de bienvenida a: " + user.getUsername());
            String password = "defaultPassword";
            emailService.enviarCorreoRegistro(user, password);
        }
    }
    
    // //task para el correo cuando el usuario se registre
    // @Scheduled(fixedRate = 1000)
    // public void enviarCorreoRegistro(){
    //     List<user> listaUser = data.findAll();
    //     for (user user : listaUser){
    //         System.out.println("Usuario registrado: " + user.getNombres() + " " + user.getApellidos());
    //         email.enviarCorreoRegistro(user, user.getPassword());
    //     }
    // }

    //task para enviar el correo de recuperar contraseña
    


}
