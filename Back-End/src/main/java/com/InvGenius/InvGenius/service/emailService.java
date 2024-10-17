package com.InvGenius.InvGenius.service;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;

import com.InvGenius.InvGenius.models.lote;
import com.InvGenius.InvGenius.models.movimientos;
//import com.InvGenius.InvGenius.models.novedad;
import com.InvGenius.InvGenius.models.rol;
import com.InvGenius.InvGenius.models.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class emailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private loteService loteService;

    @Autowired
    private authService authService;

    // @Autowired
    // private novedadService novedadService;


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
    public String enviarCorreoRecuperarContrasena(user user) {
        try {
            String destinatario = user.getCorreo();
            String asunto = "Recuperacion de contraseña";
            String cuerpo = ""
                    + "<h1>Estimado Usuario</h1>"
                    + "<p>Hemos Recibido su solicitud para restablecer la contraseña de su cuenta en \"InvGenius\".Aqui tiene su nueva contraseña autogenerada por</p>\r\n"
                    + "<p>cuestiones de seguridad, le recomendamos apenas inicie sesión, cambiarla a una que le sea más facil recordar:</p>\r\n"
                    + "      <ol>\r\n"
                    + "          <li>Su nueva contraseña:" +user.getPassword()+"</li>\r\n"
                    + "      </ol>\r\n"
                    + "<img src'https://i.postimg.cc/yNjnwxdQ/Logo.png' width'100px' heght='100'>"
                    + "      <p>Por motivos de seguridad, este enlace expirará en [Número de Horas] horas. Si no ha solicitado esta acción, le recomendamos que se comunicque con nosotros.</p>\r\n"
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
    public String enviarCorreoCambio(user user) {
        try {
            String destinatario = user.getCorreo();
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

            // Obtén la lista de administradores desde authService
            List<user> administradores = authService.buscarRol(Enum.valueOf(rol.class, "Admin"));

            String asunto = "Producto Próximo a Caducar";

            // Construir el cuerpo del correo
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

            // Enviar el correo a cada administrador
            for (user admin : administradores) {
                String destinatario = admin.getCorreo(); // Asumiendo que el modelo user tiene un campo email
                var retorno = enviarCorreo(destinatario, asunto, cuerpo.toString());
                if (!retorno) {
                    return "No se pudo enviar el correo a " + destinatario;
                }
            }

            return "Correos enviados correctamente a los administradores.";

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

            // Obtén la lista de administradores desde authService
            List<user> administradores = authService.buscarRol(Enum.valueOf(rol.class, "Admin"));

            String asunto = "Producto con Bajo Stock";

            // Construir el cuerpo del correo
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

            // Enviar el correo a cada administrador
            for (user admin : administradores) {
                String destinatario = admin.getCorreo(); // Asumiendo que el modelo user tiene un campo email
                var retorno = enviarCorreo(destinatario, asunto, cuerpo.toString());
                if (!retorno) {
                    return "No se pudo enviar el correo a " + destinatario;
                }
            }

            return "Correos enviados correctamente a los administradores.";

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

            // Obtén la lista de administradores desde authService
            List<user> administradores = authService.buscarRol(Enum.valueOf(rol.class, "Admin"));

            String asunto = "Producto Vencido";

            // Construir el cuerpo del correo
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

            // Enviar el correo a cada administrador
            for (user admin : administradores) {
                String destinatario = admin.getCorreo(); // Asumiendo que el modelo user tiene un campo email
                var retorno = enviarCorreo(destinatario, asunto, cuerpo.toString());
                if (!retorno) {
                    return "No se pudo enviar el correo a " + destinatario;
                }
            }

            return "Correos enviados correctamente a los administradores.";

        } catch (Exception e) {
            return "Error al enviar: " + e.getMessage();
        }
    }


    @GetMapping("/enviar-correo-retiro")
    public String enviarCorreoRetiro(List<movimientos> movimientosRetiro) {
        try {

            // Obtén la lista de administradores desde authService
            List<user> administradores = authService.buscarRol(Enum.valueOf(rol.class, "Admin"));
            if (administradores.isEmpty()) {
                return "No hay administradores para enviar el correo.";
            }

            String asunto = "Productos Retirados del Inventario";

            StringBuilder cuerpo = new StringBuilder()
                    .append("<h1 style='color: black;'>Estimado Usuario</h1>")
                    .append("<p style='color: black;'>Le informamos que se han retirado los siguientes productos de su inventario registrado en <strong>InvGenius</strong>. A continuación, se detalla la información de los productos:</p>")
                    .append("<table style='border-collapse: collapse; width: 100%; color: black;'>")
                    .append("<thead>")
                    .append("<tr>")
                    .append("<th style='border: 1px solid black; padding: 8px;'>Categoría</th>")
                    .append("<th style='border: 1px solid black; padding: 8px;'>Producto</th>")
                    .append("<th style='border: 1px solid black; padding: 8px;'>Cantidad</th>")
                    .append("<th style='border: 1px solid black; padding: 8px;'>Descripción del Movimiento</th>")
                    .append("<th style='border: 1px solid black; padding: 8px;'>Fecha de Movimiento</th>")
                    .append("</tr>")
                    .append("</thead>")
                    .append("<tbody>");

            for (movimientos m : movimientosRetiro) {
                cuerpo.append("<tr>")
                        .append("<td style='border: 1px solid black; padding: 8px;'>")
                        .append(m.getProducto().getCategoria().getNombreCategoria()) // Categoría del producto
                        .append("</td>")
                        .append("<td style='border: 1px solid black; padding: 8px;'>")
                        .append(m.getProducto().getNombreProducto()) // Nombre del producto
                        .append("</td>")
                        .append("<td style='border: 1px solid black; padding: 8px;'>")
                        .append(m.getCantidadProducto()) // Cantidad del producto
                        .append("</td>")
                        .append("<td style='border: 1px solid black; padding: 8px;'>")
                        .append(m.getDescripcionMovimiento()) // Descripción del movimiento
                        .append("</td>")
                        .append("<td style='border: 1px solid black; padding: 8px;'>")
                        .append(m.getFechaMovimiento()) // Fecha del movimiento
                        .append("</td>")
                        .append("</tr>");
            }

            cuerpo.append("</tbody>")
                    .append("</table>")
                    .append("<p style='color: black;'>Si tiene alguna pregunta o necesita asistencia adicional, no dude en ponerse en contacto con nuestro equipo.</p>")
                    .append("<p style='color: black;'>Atentamente,<br>[Cristian Jeanpool Bahamon Granados]<br>[Genius Inventory Company]<br>[invgenius2024@gmail.com]</p>");

            // Enviar el correo
            for (user admin : administradores) {
                var retorno = enviarCorreo(admin.getCorreo(), asunto, cuerpo.toString());
                if (!retorno) {
                    return "No se pudo enviar el correo a: " + admin.getCorreo();
                }
            }

            return "Se enviaron los correos correctamente a los administradores.";

        } catch (Exception e) {
            return "Error al enviar: " + e.getMessage();
        }
    }


    @GetMapping("/enviar-correo-solicitud")
    public String enviarCorreoSolicitud(user user) {
        try {

            // Obtén la lista de administradores desde authService
            List<user> administradores = authService.buscarRol(Enum.valueOf(rol.class, "Admin"));
            if (administradores.isEmpty()) {
                return "No hay administradores disponibles para enviar el correo.";
            }

            String asunto = "Solicitud Formal de Cambio de Rol";

            // Construir el cuerpo del correo de solicitud
            String cuerpo = "<h1 style='color: black;'>Estimado Administrador,</h1>"
                    + "<p style='color: black;'>Espero que este mensaje le encuentre bien. Mi nombre es <strong style='color: black;'>"
                    + user.getNombres()
                    + "</strong> y actualmente me desempeño en el rol de <strong style='color: black;'>Usuario</strong> en la plataforma <strong style='color: black;'>InvGenius</strong>.</p>"
                    + "<p style='color: black;'>Me gustaría solicitar formalmente un cambio de rol a <strong style='color: black;'>Administrador</strong>. Considero que mi experiencia y dedicación me permitirán desempeñar con éxito las responsabilidades adicionales asociadas a este rol.</p>"
                    + "<p style='color: black;'>Le agradecería que considerara mi solicitud. Si necesita información adicional o desea discutir esta solicitud con más detalle, estaré a su disposición.</p>"
                    + "<p style='color: black;'>Gracias por su atención y espero su pronta respuesta.</p>"
                    + "<p style='color: black;'>Atentamente,<br>"
                    + user.getNombres() + " " + user.getApellidos()
                    + "<br><strong style='color: black;'>Genius Inventory Company</strong><br><strong style='color: black;'>invgenius2024@gmail.com</strong></p>";

            // Enviar el correo a cada administrador
            for (user admin : administradores) {
                var retorno = enviarCorreo(admin.getCorreo(), asunto, cuerpo);
                if (!retorno) {
                    return "No se pudo enviar el correo a: " + admin.getCorreo();
                }
            }

            return "Se enviaron correctamente las solicitudes a los administradores.";

        } catch (Exception e) {
            return "Error al enviar: " + e.getMessage();
        }
    }
    

    // @GetMapping("/enviar-correo-novedad/{idNovedad}")
    // public String enviarCorreoNovedad(@PathVariable String idNovedad) {
    //     try {
    //         // Obtener la novedad por idNovedad
    //         Optional<novedad> novedadOpt = novedadService.findOne(idNovedad);

    //         if (novedadOpt.isPresent()) {
    //             novedad novedad = novedadOpt.get();

    //             // Obtener correos de administradores
    //             List<user> administradores = authService.buscarRol(Enum.valueOf(rol.class, "Admin"));
    //             if (administradores.isEmpty()) {
    //                 return "No hay administradores para enviar el correo.";
    //             }

    //             // Asunto del correo
    //             String asunto = "Nueva Novedad: " + novedad.getAsunto();

    //             // Construir el cuerpo del correo con formato similar al ejemplo
    //             StringBuilder cuerpo = new StringBuilder()
    //                     .append("<h1 style='color: black;'>Estimado Administrador</h1>")
    //                     .append("<p style='color: black;'><strong>De:</strong> ").append(novedad.getDe()).append("</p>")
    //                     .append("<p style='color: black;'><strong>Asunto:</strong> ").append(novedad.getAsunto())
    //                     .append("</p>")
    //                     .append("<p style='color: black;'><strong>Mensaje:</strong> ").append(novedad.getCuerpo())
    //                     .append("</p>")
    //                     .append("<p style='color: black;'>Por favor, revise la evidencia adjunta si es necesario.</p>");

    //             // Incluir imagen si existe
    //             if (novedad.getImagenNovedad() != null && !novedad.getImagenNovedad().isEmpty()) {
    //                 cuerpo.append("<p><img src='").append(novedad.getImagenNovedad())
    //                         .append("' width='100px' height='100px'></p>");
    //             }

    //             cuerpo.append("<p style='color: black;'><strong>Fecha de novedad:</strong> ")
    //                     .append(novedad.getFechaNovedad()).append("</p>")
    //                     .append("<p style='color: black;'>Gracias por su atención.</p>")
    //                     .append("<p style='color: black;'>Atentamente,<br>[InvGenius Team]</p>");

    //             // Enviar el correo a cada administrador
    //             for (user admin : administradores) {
    //                 var retorno = enviarCorreo(admin.getCorreo(), asunto, cuerpo.toString());
    //                 if (!retorno) {
    //                     return "No se pudo enviar el correo a: " + admin.getCorreo();
    //                 }
    //             }

    //             return "Correo de novedad enviado correctamente";
    //         } else {
    //             return "No se encontró la novedad";
    //         }

    //     } catch (Exception e) {
    //         return "Error al enviar el correo de novedad: " + e.getMessage();
    //     }
    // }
    
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
