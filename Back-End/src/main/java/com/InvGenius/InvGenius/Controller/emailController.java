package com.InvGenius.InvGenius.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class emailController {
    
    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/enviar-correo-registro")
    public String enviarCorreoRegistro(){
        try {
            String destinatario = "invgenius2024@gmail.com";
            String asunto = "Registro Plataforma";
            String cuerpo=""
            +"<p>Estimado Usuario,</p>\r\n"
            +"      <p>Es un placer darle la bienvenida a nuestra plataforma. Nos complace informarle que la empresa \"Genius Inventory Company\", l@ ha registrado con exito en nuestro sistema. Estamos emocionados de tenerl@ a bordo.</p>\r\n"
            +"      <p>A continuacion, encontrará sus credenciales de inicio de sesión: </p>\r\n"
            +"      <ul>\r\n"
            +"          <li><strong>Nombre de Usuario: </strong> [Nombre de Usuario Elegido]</li>\r\n"
            +"          <li><strong>Contraseña: </strong> [Contraseña Temporal]</li>\r\n"
            +"      </ul>\r\n"
            +"<img src='' width='100px' heght='100px'>"
            +"      <p>Por favor, inicie sesión en nuestro portal utilizando esta informacion. Le recomendamos cambiar su contraseña despues del primer inicio de sesión por motivos de seguridad.</p>\r\n"
            +"      <p>Si tiene alguna pregunta o necesita asistencia, no dude en ponerse en contacto con nuestro equipo de soporte. Estamos aquí para ayudarle. </p>\r\n"
            +"      <p>Gracias por unirse a nosotros.</p>\r\n"
            +"      <p>Atentamente, <br>[Laura Valentina Ariza Alejo]<br>[Genius Inventory Company]<br>[invgenius2024@gmail.com]</p>\r\n";

            var retorno=enviarCorreo(destinatario,asunto,cuerpo);
            if (retorno) {
                return "Se envió orrectamente";
            }else{
                return "No se pudo envíar";

            }
        }catch(Exception e) {
            return "Error al Enviar"+e.getMessage();
        }
    }

    //AQUI
    @GetMapping("/enviar-correo-recuperar")
    public String enviarCorreoRecuperar() {
        try {
            String destinatario = "dc8987835@gmail.com";
        }
    }
}
