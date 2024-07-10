package com.InvGenius.InvGenius.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InvGenius.InvGenius.models.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class emailController {
    
    @Autowired
    private JavaMailSender javaMailSender;

    emailController(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    @GetMapping("/enviar-correo-registro")
    public String enviarCorreoRegistro(user user){
        try {
            String destinatario = user.getCorreo();
            String asunto = "Registro Plataforma";
            String cuerpo=""
            +"<p>Estimado Usuario "+user.getPrimerNombre()+ " " +user.getPrimerApellido()+",</p>\r\n"
            +"      <p>Es un placer darle la bienvenida a nuestra plataforma. Nos complace informarle que la empresa \"Genius Inventory Company\",le ha registrado con exito en nuestro sistema.Estamos ansiosos de que use nuestro aplicativo</p>\r\n"
            +"      <p>A continuacion, encontrará sus credenciales de inicio de sesión: </p>\r\n"
            +"      <ul>\r\n"
            +"          <li><strong>Nombre de Usuario: </strong> "+user.getCorreo()+"</li>\r\n"
            +"          <li><strong>Contraseña: "+user.getPassword()+"</strong> </li>\r\n"
            +"      </ul>\r\n"
            +"<img src='https://i.postimg.cc/rpJK95VY/Logo.png' width='100px' heght='100px'>"
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
            String destinatario = "invgenius2024@gmail.com";
            String asunto="Recuperacion de contraseña";
            String cuerpo=""
            +"<h1>Estimado Usuario</h1>"
            +"<p>Hemos Recibido su solicitud para restablecer la contraseña de su cuenta en \"InvGenius\".Aqui están los pasos para completar el proceso: </p>\r\n"
            +"      <ol>\r\n"
            +"          <li>Haga clic en el siguiente enlace para restablecer su contraseña: [invgenius2024@gmail.com]</li>\r\n"
            +"      </ol>\r\n"
            +"<img src'https://i.postimg.cc/rpJK95VY/Logo.png' width'100px' heght='100'>"
            +"      <p>Por motivos de seguridad, este enlace expirará en [Número de Horas] horas. Si no ha solicitado esta acción, le recomendamos que ignore este correo electronico.</p>\r\n"
            +"      <p>Si experimenta algún problema o tiene alguna pregunta, no dude en comunicarse con nuestro equipo de soporte. Estamos aqui para ayudarle.</p>\r\n"
            +"      <p>Atentamente,<br>[Yordy Erik Núñez Pineda]<br>[Genius Inventory Company]<br>[invgenius2024@gmail.com]</p>\r\n";

            var retorno=enviarCorreo(destinatario,asunto,cuerpo);
            if (retorno) {
                return "Se envió correctamente";
            }else{
                return "No se pudo envíar";
            }
        }catch (Exception e) {
            return "Error al envíar" +e.getMessage();
        }
    }

    @GetMapping("/enviar-correo-cambio")
    public String enviarCorreoCambio() {
        try{
            String destinatario="invgenius2024@gmail.com";
            String asunto ="Cambio de contraseña";
            String cuerpo=""
                    +"<p>Este correo electrónico es para informarle que la contraseña de su cuenta en \"InvGenius\" ha sido cambiada con éxito. Si usted realizó este cambio, puede ignorar este mensaje. En caso contrario, le recomendamos que se comunique con nuestro equipo de soporte de inmediato.</p>\r\n "
                    +"<img src='https://i.postimg.cc/rpJK95VY/Logo.png' width='100px' heght='100px'>"
                    +"      <p>Si tiene alguna pregunta o necesita asistencia, no dude en ponerse en contacto con nosotros. Estamos aquí para ayudarle. </p>\r\n"
                    +"      <p>Atentamente,<br>[Julian David Fierro Casanova]<br>[Genius Inventory Company]<br>[invgenius2024@gmail.com]</p>\r\n";

                    var retorno= enviarCorreo(destinatario,asunto,cuerpo);
                    if (retorno) {
                        return "Se envió correctamente";
                    }else{
                        return "No se pudo envíar";
                    }
        }catch (Exception e){
            return "Error al envíar"+e.getMessage();
        }
    }


    @GetMapping("/enviar-correo-caducar")
    public String enviarCorreoCaducar() {
        try {
            String destinatario = "invgenius2024@gmail.com";
            String asunto = "Producto Próximo a Caducar";
            String cuerpo = ""
                    + "<h1>Estimado Usuario</h1>"
                    + "<p>Le informamos que uno de los productos en su inventario registrado en <strong>InvGenius</strong> está próximo a caducar. A continuación, se detalla la información del producto:</p>\r\n"
                    + "<ul>\r\n"
                    + "    <li><strong>Producto:</strong> Leche Entera</li>\r\n"
                    + "    <li><strong>Marca:</strong> Alpina</li>\r\n"
                    + "    <li><strong>Cantidad:</strong> 5 unidades</li>\r\n"
                    + "    <li><strong>Fecha de Caducidad:</strong> 15 de junio de 2024</li>\r\n"
                    + "</ul>\r\n"
                    + "<p>Le recomendamos que venda o promocione este producto antes de la fecha de caducidad indicada para asegurar su frescura y evitar desperdicios.</p>\r\n"
                    + "<img src='https://example.com/images/leche.png' width='100px' height='100px'>"
                    + "<p>Si tiene alguna pregunta o necesita asistencia adicional, no dude en ponerse en contacto con nuestro equipo.</p>\r\n"
                    + "<p>Atentamente,<br>[Anyi Zujey Gomez Casanova]<br>[Genius Inventory Company]<br>[invgenius2024@gmail.com]</p>\r\n";
    
            var retorno = enviarCorreo(destinatario, asunto, cuerpo);
            if (retorno) {
                return "Se envió correctamente";
            } else {
                return "No se pudo enviar";
            }
    
        } catch (Exception e) {
            return "Error al enviar: " + e.getMessage();
        }
    }

    public boolean enviarCorreo(String destinatario, String asunto, String cuerpo) throws MessagingException {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(cuerpo, true);

            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
