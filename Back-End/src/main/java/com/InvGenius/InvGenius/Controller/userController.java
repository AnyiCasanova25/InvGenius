package com.InvGenius.InvGenius.Controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

// import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.InvGenius.InvGenius.interfaceService.IuserService;
import com.InvGenius.InvGenius.models.changePasswordRequest;
import com.InvGenius.InvGenius.models.registerRequest;
import com.InvGenius.InvGenius.models.response;
import com.InvGenius.InvGenius.models.respuestaImagen;
import com.InvGenius.InvGenius.models.rol;
import com.InvGenius.InvGenius.models.user;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class userController {

    @Autowired
    private IuserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    // @Autowired
    // private JavaMailSender javaMailSender;

    // private static int numeroAleatorioEnRango(int minimo, int maximo) {
    // // nextInt regresa en rango pero con límite superior exclusivo, por eso
    // // sumamos
    // // 1
    // return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    // }

    // private String codigoAleatorio() {
    // int longitud = 6;
    // // El banco de caracteres
    // String banco =
    // "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    // // La cadena en donde iremos agregando un carácter aleatorio
    // String cadena = "";
    // for (int x = 0; x < longitud; x++) {
    // int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
    // char caracterAleatorio = banco.charAt(indiceAleatorio);
    // cadena += caracterAleatorio;
    // }
    // return cadena;
    // }

    @PostMapping("/register/")
    public ResponseEntity<Object> register(@RequestBody registerRequest user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        user userToken = (user) auth.getPrincipal();
        if (!userToken.getRol().name().equals(rol.Admin.name())) {
            response respuesta = response.builder()
                    .message("No tiene permiso contactese con su administrador")
                    .build();
            // Retornar el objeto como JSON
            return new ResponseEntity<>(respuesta, HttpStatus.FORBIDDEN);
        }

        // Verificar que no exista numero de telefono
        var listaUser = userService.userExist(user.getUserName(), user.getDocumentoIdentidad());

        // if (listaUser.size() != 0) {
        // return new ResponseEntity<>("Este usuario ya existe",
        // HttpStatus.BAD_REQUEST);
        // }

        if (listaUser.size() != 0) {
            // Construir una respuesta con el mensaje y el estado
            response respuesta = response.builder()
                    .message("Este usuario ya existe")
                    .build();
            // Retornar el objeto como JSON
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        // Verificar que el campo de de documento de identidad sea diferente a vacio
        // Añadir campos obligatorios

        if (user.getDocumentoIdentidad().equals("")) {

            return new ResponseEntity<>("El documento de identidad es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (user.getNombres().equals("")) {

            return new ResponseEntity<>("El primer nombre es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (user.getApellidos().equals("")) {

            return new ResponseEntity<>("El primer apellido es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (user.getCelular().equals("")) {

            return new ResponseEntity<>("El numero de telefono es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (user.getUserName().equals("")) {

            return new ResponseEntity<>("El correo es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        // user.setPassword(codigoAleatorio());

        // todo bien-
        // user.setRol(rol.User);
        userService.register(user);
        // emailController email = new emailController(javaMailSender);
        // email.enviarCorreoRegistro(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Método para consultar categorías con manejo de imágenes (ruta cambiada)
@GetMapping("/consultar-imagenes")
public ResponseEntity<Object> consultarcategoriaJson() {
    List<user> listaUser = userService.consultaruser();
    listaUser.forEach(c -> c.setImagen_base("")); // Aquí puedes ajustar cómo se manejan las imágenes
    return new ResponseEntity<>(listaUser, HttpStatus.OK);
}

    // Método para guardar imagen asociada a una categoría
    @PostMapping("/imagen")  // Cambio de endpoint para evitar conflicto
    public ResponseEntity<Object> guardarImagenJson(
            user user, 
            @RequestParam("file") MultipartFile file) throws IOException {

        try {
            // Guardar el archivo y generar la URL
            // String fileName = gestionArchivoService.storeFile(file);
            // categoria.setImagen_url("http://localhost:8080/api/downloadFile/" + fileName);
            user.setImagen_base(Base64.getEncoder().encodeToString(file.getBytes()));

            int resultado = userService.guardarimagenJson(user);
            if (resultado == 0) {
                return new ResponseEntity<>(new respuestaImagen("ok", "Se guardó correctamente"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new respuestaImagen("error", "Error al guardar"), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (IOException e) {
            return new ResponseEntity<>("Error al guardar la imagen: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("profile/")
    public ResponseEntity<user> getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        user user = (user) auth.getPrincipal();
        return new ResponseEntity<user>(user, HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody changePasswordRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        user user = (user) auth.getPrincipal();
        // 1 paso preguntar si la contraseña actual es igual a la base datos
        // 2 paso preguntar si la nueva contraseña es distinta a la actual
        // 3 paso preguntar si la nueva y la confirmación son iguales ok
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getCorreo(),
                        request.getActualPassword()));
        if (request.getNewPassword().equals(request.getActualPassword())) {
            return new ResponseEntity<>("Error, la contraseña actual y la contraseña nueva son iguales",
                    HttpStatus.BAD_REQUEST);
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return new ResponseEntity<>("Error las contraseñas no coinciden", HttpStatus.BAD_REQUEST);

        }
        // // Actualizar la contraseña
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setCambiarPassword(false); // Cambiar el estado de la contraseña a no
        // temporal
        userService.save(user);
        return new ResponseEntity<>("Contraseña cambiada exitosamente", HttpStatus.OK);
    }

    // @PostMapping("/register/")
    // public ResponseEntity<String> register(@RequestBody String entity) {
    // return new ResponseEntity<>("end-point Publico register",HttpStatus.OK);
    // }

    // @GetMapping("/admin/findAll/")
    // public ResponseEntity<String> findAll() {
    // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // var user = (user) auth.getPrincipal();
    // if (user.getRol() != rol.Admin)
    // return new ResponseEntity<String>("No tiene permiso", HttpStatus.FORBIDDEN);
    // return new ResponseEntity<String>("Metodo admin", HttpStatus.OK);
    // }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var listaUser = userService.findAll();
        return new ResponseEntity<>(listaUser, HttpStatus.OK);
    }

    @GetMapping("/busquedaFiltros/{filtro}")
    public ResponseEntity<Object> findFiltro(@PathVariable String filtro) {
        var listaUser = userService.userExist(filtro, filtro);
        return new ResponseEntity<>(listaUser, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var user = userService.findOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        var optionaluser = userService.findOne(id);

        if (optionaluser.isPresent()) {
            var user = optionaluser.get();

            if ("Activo".equals(user.getEstado())) {
                user.setEstado("Inactivo");
                userService.save(user);
                return new ResponseEntity<>("Se ha desactivado correctamente", HttpStatus.OK);
            } else {
                user.setEstado("Activo");
                userService.save(user);
                return new ResponseEntity<>("Se ha activado correctamente", HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("No se ha encontrado el registro", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody user userUpdate) {
        var user = userService.findOne(id).get();

        if (user != null) {

            user.setDocumentoIdentidad(userUpdate.getDocumentoIdentidad());
            user.setNombres(userUpdate.getNombres());
            user.setApellidos(userUpdate.getApellidos());
            user.setCelular(userUpdate.getCelular());
            user.setCorreo(userUpdate.getCorreo());
            user.setPassword(userUpdate.getPassword());
            user.setConfirmarPassword(userUpdate.getConfirmarPassword());
            user.setRol(userUpdate.getRol());

            userService.save(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error usuario NO encontrado", HttpStatus.BAD_REQUEST);
        }
    }

}
