package com.InvGenius.InvGenius.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.InvGenius.InvGenius.models.lote;
import com.InvGenius.InvGenius.models.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class emailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private loteService loteService;


    emailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @GetMapping("/enviar-correo-registro")
    public String enviarCorreoRegistro(user user, String password) {
        try {
            String destinatario = user.getCorreo();
            String asunto = "Bienvenido a InvGenius";
            String cuerpo = "<div style='max-width: 600px; margin: 20px auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); font-family: Arial, sans-serif;'>"
                    + "  <div style='background-color: #e0e0e0; padding: 20px; text-align: center; border-top-left-radius: 8px;'>"
                    + "      <h1 style='margin: 20px 0 10px; font-size: 24px; color: #333333;'>¡Bienvenid@ a InvGenius!</h1>"
                    + "      <h3 style='font-size: 22px; color: #333333; margin-bottom: 20px; margin-top: -18px;'>"
                    + user.getNombres() + " " + user.getApellidos() + "</h3>"
                    + "      <img src='https://i.postimg.cc/yNjnwxdQ/Logo.png' alt='InvGenius Logo' style='max-width: 150px;'>"
                    + "      <p style='font-size: 16px; color: #555555;'>Nos alegra que te unas a nuestra plataforma. Con InvGenius, gestionar el inventario de tu minimercado será más fácil y eficiente. Estamos aquí para ayudarte a optimizar tu negocio.</p>"
                    + "  </div>"
                    + "  <div style='padding: 20px; text-align: center;'>"
                    + "      <p style='font-size: 16px; color: #666666; margin-bottom: 20px;'>Nos complace informarle que su registro en <strong>Genius Inventory Company</strong> ha sido completado con éxito. Estamos ansiosos de que empiece a utilizar nuestro aplicativo.</p>"
                    + "      <div style='background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); text-align: left;'>"
                    + "          <p style='margin: 10px 0; font-size: 16px; color: #333333;'><strong>Nombre de Usuario:</strong> "
                    + user.getCorreo() + "</p>"
                    + "          <p style='margin: 10px 0; font-size: 16px; color: #333333;'><strong>Contraseña:</strong> "
                    + password + "</p>"
                    + "      </div>"
                    + "      <p style='font-size: 16px; color: #666666;'>Para acceder a su cuenta, por favor inicie sesión en nuestro portal utilizando la información proporcionada. Le recomendamos cambiar su contraseña después de su primer inicio de sesión por motivos de seguridad.</p>"
                    + "  </div>"
                    + "  <div style='background-color: #e0e0e0; padding: 10px; text-align: center; font-size: 14px; color: #666666; border-bottom-left-radius: 8px; border-bottom-right-radius: 8px;'>"
                    + "      <p>Centro de la Industria, la Empresa y los Servicios</p>"
                    + "      <p>invgenius2024@gmail.com</p>"
                    + "  </div>";

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
    public String enviarCorreoRecuperarContrasena(user user, user cambiarPassword) {
        try {
            String destinatario = user.getCorreo();
            String asunto = "Recuperacion de contraseña";
            String cuerpo = ""
                    + "<h1>Estimado Usuario</h1>"
                    + "<p>Hemos Recibido su solicitud para restablecer la contraseña de su cuenta en \"InvGenius\".Aqui están los pasos para completar el proceso: </p>\r\n"
                    + "      <ol>\r\n"
                    + "          <li>Haga clic en el siguiente enlace para restablecer su contraseña:" +user.getPassword()+"</li>\r\n"
                    + "      </ol>\r\n"
                    + "<img src'https://i.postimg.cc/yNjnwxdQ/Logo.png' width'100px' heght='100'>"
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

    // Este solo debe tener un mensaje de que su contraseña se cambio correctamente
    @GetMapping("/enviar-correo-cambio")
    public String enviarCorreoCambio() {
        try {
            String destinatario = "invgenius2024@gmail.com";
            String asunto = "Cambio de contraseña";
            String cuerpo = ""
                    + "<h1>Estimado Usuario</h1>"
                    + "<p>Este correo electrónico es para informarle que la contraseña de su cuenta en \"InvGenius\" ha sido cambiada con éxito. Si usted realizó este cambio, puede ignorar este mensaje. En caso contrario, le recomendamos que se comunique con nuestro equipo de soporte de inmediato.</p>\r\n "
                    + "<img src='https://i.postimg.cc/yNjnwxdQ/Logo.png' width='100px' heght='100px'>"
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

    // correo para lotes que estan proximos a caducar
    @GetMapping("/loteACaducar")
    public String enviarCorreoCaducar() {
        try {
            List<lote> listaLote = loteService.loteACaducar();
            if (listaLote.isEmpty()) {
                return "No hay productos próximos a caducar.";
            }

            String destinatario = "()";
            String asunto = "Producto Próximo a Caducar";

            StringBuilder cuerpo = new StringBuilder()
                    .append("<h1 style='color: black;'>Estimado Usuario</h1>")
                    .append("<p style='color: black;'>Le informamos que uno de los productos en su inventario registrado en <strong style='color: black;'>InvGenius</strong> está próximo a caducar. A continuación, se detalla la información de los productos:</p>")
                    .append("<ul style='color: black;'>");

            for (lote l : listaLote) {
                cuerpo.append("<li style='color: black;'>")
                        .append("<strong style='color: black;'>Producto:</strong> ")
                        .append(l.getProducto().getNombreProducto())
                        .append(" - <strong style='color: black;'>Fecha de Caducidad:</strong> ")
                        .append(l.getFechaVencimiento())
                        .append("</li>");
            }

            cuerpo.append("</ul>")
                    .append("<p style='color: black;'>Le recomendamos que venda o promocione estos productos antes de la fecha de caducidad indicada para asegurar su frescura y evitar desperdicios.</p>")
                    .append("<p style='color: black;'>Si tiene alguna pregunta o necesita asistencia adicional, no dude en ponerse en contacto con nuestro equipo.</p>")
                    .append("<p style='color: black;'>Atentamente,<br>Anyi Zujey Gomez Casanova<br>Genius Inventory Company<br>invgenius2024@gmail.com</p>");

            var retorno = enviarCorreo(destinatario, asunto, cuerpo.toString());
            if (retorno) {
                return "Se envió correctamente";
            } else {
                return "No se pudo enviar";
            }

        } catch (Exception e) {
            return "Error al enviar: " + e.getMessage();
        }
    }


    // Correo para productos bajos en stock
    @GetMapping("/loteBajoStock/")
    public String enviarCorreoBajoStock() {
        try {
            List<lote> listaLote = loteService.loteBajoStock();
            if (listaLote.isEmpty()) {
                return "No hay productos con bajo stock.";
            }

            String destinatario = "yordierik05@gmail.com";
            String asunto = "Producto con Bajo Stock";

            StringBuilder cuerpo = new StringBuilder()
                    .append("<h1 style='color: black;'>Estimado Usuario</h1>")
                    .append("<p style='color: black;'>Le informamos que uno de los productos en su inventario registrado en <strong style='color: black;'>InvGenius</strong> tiene un stock bajo. A continuación, se detalla la información de los productos:</p>")
                    .append("<ul style='color: black;'>");

            for (lote l : listaLote) {
                cuerpo.append("<li style='color: black;'>")
                        .append("<strong style='color: black;'>Producto:</strong> ")
                        .append(l.getProducto().getNombreProducto())
                        .append(" - <strong style='color: black;'>Número de Lote:</strong> ")
                        .append(l.getNumeroLote())
                        .append(" - <strong style='color: black;'>Stock Actual:</strong> ")
                        .append(l.getProducto().getStock())
                        .append("</li>");
            }

            cuerpo.append("</ul>")
                    .append("<p style='color: black;'>Le recomendamos reabastecer estos productos lo antes posible para evitar faltantes de inventario.</p>")
                    .append("<p style='color: black;'>Si tiene alguna pregunta o necesita asistencia adicional, no dude en ponerse en contacto con nuestro equipo.</p>")
                    .append("<p style='color: black;'>Atentamente,<br>Anyi Zujey Gomez Casanova<br>Genius Inventory Company<br>invgenius2024@gmail.com</p>");

            var retorno = enviarCorreo(destinatario, asunto, cuerpo.toString());
            if (retorno) {
                return "Se envió correctamente";
            } else {
                return "No se pudo enviar";
            }

        } catch (Exception e) {
            return "Error al enviar: " + e.getMessage();
        }
    }

    // correo para lotes vencidos
    @GetMapping("/loteVencido/")
    public String enviarCorreoVencido() {
        try {
            List<lote> listaLote = loteService.loteVencido();
            if (listaLote.isEmpty()) {
                return "No hay productos vencidos.";
            }

            String destinatario = "yordierik05@gmail.com";
            String asunto = "Producto Vencido";

            StringBuilder cuerpo = new StringBuilder()
                    .append("<h1 style='color: black;'>Estimado Usuario</h1>")
                    .append("<p style='color: black;'>Le informamos que uno de los productos en su inventario registrado en <strong style='color: black;'>InvGenius</strong> ha vencido. A continuación, se detalla la información de los productos vencidos:</p>")
                    .append("<ul style='color: black;'>");

            for (lote l : listaLote) {
                cuerpo.append("<li style='color: black;'>")
                        .append("<strong style='color: black;'>Producto:</strong> ")
                        .append(l.getProducto().getNombreProducto())
                        .append(" - <strong style='color: black;'>Categoría:</strong> ")
                        .append(l.getProducto().getCategoria().getNombreCategoria())
                        .append(" - <strong style='color: black;'>Unidad de Medida:</strong> ")
                        .append(l.getProducto().getUnidadMedida())
                        .append(" - <strong style='color: black;'>Número de Lote:</strong> ")
                        .append(l.getNumeroLote())
                        .append(" - <strong style='color: black;'>Fecha de Vencimiento:</strong> ")
                        .append(l.getFechaVencimiento())
                        .append("</li>");
            }

            cuerpo.append("</ul>")
                    .append("<p style='color: black;'>Estos productos ya han pasado su fecha de caducidad. Le recomendamos retirarlos del inventario y seguir los procedimientos apropiados para productos vencidos.</p>")
                    .append("<p style='color: black;'>Si tiene alguna pregunta o necesita asistencia adicional, no dude en ponerse en contacto con nuestro equipo.</p>")
                    .append("<p style='color: black;'>Atentamente,<br>Anyi Zujey Gomez Casanova<br>Genius Inventory Company<br>invgenius2024@gmail.com</p>");

            var retorno = enviarCorreo(destinatario, asunto, cuerpo.toString());
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
