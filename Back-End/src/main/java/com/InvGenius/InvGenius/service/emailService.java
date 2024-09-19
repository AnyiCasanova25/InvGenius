package com.InvGenius.InvGenius.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;

import com.InvGenius.InvGenius.models.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class emailService {
    
     @Autowired
    private JavaMailSender javaMailSender;

    emailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

     @GetMapping("/enviar-correo-registro")
    public String enviarCorreoRegistro(user user ,String password) {
        try {
            String destinatario = user.getCorreo();
            String asunto = "Registro Plataforma";
            String cuerpo = 
            "<div style='max-width: 600px; margin: 20px auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); overflow: hidden;'>"
            + "  <div style='background-color: #e0e0e0; padding: 20px; text-align: center;'>"
            + "      <img src='https://i.postimg.cc/rpJK95VY/Logo.png' alt='InvGenius Logo' style='max-width: 150px;'>"
            + "      <h1 style='margin: 20px 0 10px; font-size: 24px; color: #333333;'>Hey, bienvenidos</h1>"
            + "      <p style='font-size: 16px; color: #555555;'>Nos alegra que te unas a nuestra plataforma. Con InvGenius, gestionar el inventario de tu minimercado será más fácil y eficiente. Estamos aquí para ayudarte a optimizar tu negocio.</p>"
            + "  </div>"
            + "  <div style='padding: 20px; text-align: center;'>"
            + "      <h2 style='font-size: 22px; color: #333333; margin-bottom: 20px;'>" + user.getNombres() + " " + user.getApellidos() + "</h2>"
            + "      <p style='font-size: 16px; color: #666666; margin-bottom: 20px;'>Es un placer darle la bienvenida a nuestra plataforma. Nos complace informarle que la empresa \"Genius Inventory Company\" le ha registrado con éxito en nuestro sistema. Estamos ansiosos de que use nuestro aplicativo.</p>"
            + "      <div style='background-color: #ffffff; padding: 20px; border-radius: 8px; text-align: left;'>"
            + "          <p style='margin: 10px 0; font-size: 16px; color: #333333;'><strong>Nombre de Usuario:</strong> " + user.getCorreo() + "</p>"
            + "          <p style='margin: 10px 0; font-size: 16px; color: #333333;'><strong>Contraseña:</strong> " + password+ "</p>"
            + "      </div>"
            + "      <p style='font-size: 16px; color: #666666;'>Por favor, inicie sesión en nuestro portal utilizando esta información. Le recomendamos cambiar su contraseña después del primer inicio de sesión por motivos de seguridad.</p>"
            + "  </div>"
            + "  <div style='background-color: #e0e0e0; padding: 10px; text-align: center; font-size: 14px; color: #666666;'>"
            + "      <p>Centro de la industria, la empresa y los servicios</p>"
            + "      <p>invgenius2024@gmail.com - www.Invgenius.com</p>"
            + "  </div>"
            + "</div>";
        

            var retorno = enviarCorreo(destinatario, asunto, cuerpo);
            if (retorno) {
                return "Se envió orrectamente";
            } else {
                return "No se pudo envíar";

            }
        } catch (Exception e) {
            return "Error al Enviar" + e.getMessage();
        }
    }

    // AQUI
    @GetMapping("/enviar-correo-recuperar")
    public String enviarCorreoRecuperar() {
        try {
            String destinatario = "invgenius2024@gmail.com";
            String asunto = "Recuperacion de contraseña";
            String cuerpo = ""
                    + "<h1>Estimado Usuario</h1>"
                    + "<p>Hemos Recibido su solicitud para restablecer la contraseña de su cuenta en \"InvGenius\".Aqui están los pasos para completar el proceso: </p>\r\n"
                    + "      <ol>\r\n"
                    + "          <li>Haga clic en el siguiente enlace para restablecer su contraseña: [invgenius2024@gmail.com]</li>\r\n"
                    + "      </ol>\r\n"
                    + "<img src'https://i.postimg.cc/rpJK95VY/Logo.png' width'100px' heght='100'>"
                    + "      <p>Por motivos de seguridad, este enlace expirará en [Número de Horas] horas. Si no ha solicitado esta acción, le recomendamos que ignore este correo electronico.</p>\r\n"
                    + "      <p>Si experimenta algún problema o tiene alguna pregunta, no dude en comunicarse con nuestro equipo de soporte. Estamos aqui para ayudarle.</p>\r\n"
                    + "      <p>Atentamente,<br>[Yordy Erik Núñez Pineda]<br>[Genius Inventory Company]<br>[invgenius2024@gmail.com]</p>\r\n";

            var retorno = enviarCorreo(destinatario, asunto, cuerpo);
            if (retorno) {
                return "Se envió correctamente";
            } else {
                return "No se pudo envíar";
            }
        } catch (Exception e) {
            return "Error al envíar" + e.getMessage();
        }
    }

    @GetMapping("/enviar-correo-cambio")
    public String enviarCorreoCambio() {
        try {
            String destinatario = "invgenius2024@gmail.com";
            String asunto = "Cambio de contraseña";
            String cuerpo = ""
                    + "<h1>Estimado Usuario</h1>"
                    + "<p>Este correo electrónico es para informarle que la contraseña de su cuenta en \"InvGenius\" ha sido cambiada con éxito. Si usted realizó este cambio, puede ignorar este mensaje. En caso contrario, le recomendamos que se comunique con nuestro equipo de soporte de inmediato.</p>\r\n "
                    + "<img src='https://i.postimg.cc/rpJK95VY/Logo.png' width='100px' heght='100px'>"
                    + "      <p>Si tiene alguna pregunta o necesita asistencia, no dude en ponerse en contacto con nosotros. Estamos aquí para ayudarle. </p>\r\n"
                    + "      <p>Atentamente,<br>[Julian David Fierro Casanova]<br>[Genius Inventory Company]<br>[invgenius2024@gmail.com]</p>\r\n";

            var retorno = enviarCorreo(destinatario, asunto, cuerpo);
            if (retorno) {
                return "Se envió correctamente";
            } else {
                return "No se pudo envíar";
            }
        } catch (Exception e) {
            return "Error al envíar" + e.getMessage();
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

    @GetMapping("/enviar-correo-retiro")
    public String enviarCorreoRetiro() {
        try {
            String destinatario = "invgenius2024@gmail.com";
            String asunto = "Producto Ha Retirar";
            String cuerpo = ""
                    + "<h1>Estimado Usuario</h1>"
                    + "<p>Le informamos que uno de los productos en su inventario registrado en <strong>InvGenius</strong> será retirado del stock. A continuación, se detalla la información del producto:</p>\r\n"
                    + "<ul>\r\n"
                    + "    <li><strong>Razon:</strong> Error de empaquetaje</li>\r\n"
                    + "    <li><strong>Producto:</strong> Leche Entera</li>\r\n"
                    + "    <li><strong>Marca:</strong> Alpina</li>\r\n"
                    + "    <li><strong>Cantidad:</strong> 5 unidades</li>\r\n"
                    + "    <li><strong>Fecha de Caducidad:</strong> 15 de junio de 2024</li>\r\n"
                    + "</ul>\r\n"
                    + "<p>Le recomendamos que se comunique con su proveedor para detalles de la novedad.</p>\r\n"
                    + "<img src='https://example.com/images/leche.png' width='100px' height='100px'>"
                    + "<p>Si tiene alguna pregunta o necesita asistencia adicional, no dude en ponerse en contacto con nuestro equipo.</p>\r\n"
                    + "<p>Atentamente,<br>[Cristian Jeanpool Bahamon Granados]<br>[Genius Inventory Company]<br>[invgenius2024@gmail.com]</p>\r\n";

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

    @GetMapping("/enviar-correo-solicitud")
    public String enviarCorreoSolicitud() {
        try {
            String destinatario = "invgenius2024@gmail.com";
            String asunto = "Solicitud Cambio de Rol";
            String cuerpo = ""
                    + "<h1>Estimado Administrador</h1>"
                    + "<p>Espero que este mensaje le encuentre bien. Me dirijo a usted para solicitar formalmente un cambio de rol dentro de nuestra organización. Actualmente, me desempeño en el rol de <strong>Usuario</strong> y me gustaría ser considerado para el rol de <strong>Administrador</strong>.</p>\r\n"
                    + "<p>Le recomendamos que se comunique con su proveedor para detalles de la novedad.</p>\r\n"
                    + "<img src='https://example.com/images/leche.png' width='100px' height='100px'>"
                    + "<p>Gracias por considerar mi solicitud. Espero su respuesta.</p>\r\n"
                    + "<p>Atentamente,<br>[William Steban Gonzalez Cortes]<br>[Genius Inventory Company]<br>[invgenius2024@gmail.com]</p>\r\n";

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

    @GetMapping("/enviar-correo-novedad")
    public String enviarCorreoNovedad() {
        try {
            String destinatario = "invgenius2024@gmail.com";
            String asunto = "Novedades en el Inventario - Productos con Bajo Stock";
            String cuerpo = ""
                    + "<h1>Estimado Usuario</h1>"
                    + "<p>Le informamos que los siguientes productos están con bajo stock:</p>"
                    + "<ul>"
                    + "<li><strong>Categoría:</strong> Lácteos - <strong>Marca:</strong> Leche Vida</li>"
                    + "<li><strong>Categoría:</strong> Cereales - <strong>Marca:</strong> Granola Plus</li>"
                    + "<li><strong>Categoría:</strong> Bebidas - <strong>Marca:</strong> Jugo Natural</li>"
                    + "</ul>"
                    + "<p>Le recomendamos que se comunique con su proveedor para detalles de la novedad.</p>"
                    + "<img src='https://example.com/images/stock-bajo.png' width='100px' height='100px'>"
                    + "<p>Gracias por su atención.</p>"
                    + "<p>Atentamente,<br>[Julian David Fierro Casanova]<br>[Genius Inventory Company]<br>[invgenius2024@gmail.com]</p>";

            var retorno = enviarCorreo(destinatario, asunto, cuerpo);
            if (retorno) {
                return "Correo enviado correctamente";
            } else {
                return "No se pudo enviar el correo";
            }

        } catch (Exception e) {
            return "Error al enviar: " + e.getMessage();
        }
    }

    public boolean enviarCorreo(String destinatario, String asunto, String cuerpo) throws MessagingException {
        try {
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