package com.InvGenius.InvGenius.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.InvGenius.InvGenius.interfaceService.IuserService;
import com.InvGenius.InvGenius.interfaces.Iuser;
import com.InvGenius.InvGenius.models.authResponse;
import com.InvGenius.InvGenius.models.loginRequest;
import com.InvGenius.InvGenius.models.registerRequest;
import com.InvGenius.InvGenius.models.rol;
import com.InvGenius.InvGenius.models.user;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class authService implements IuserService {

    @Autowired
    private JavaMailSender javaMailSender;

    private final Iuser data;
    private final jwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // traer el método que genere la contraseña
    private static int numeroAleatorioEnRango(int minimo, int maximo) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos
        // 1
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

    private String codigoAleatorio() {
        int longitud = 25;
        // El banco de caracteres
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        // La cadena en donde iremos agregando un carácter aleatorio
        String cadena = "";
        for (int x = 0; x < longitud; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }

    // user.setPassword(codigoAleatorio());

    @Override
    public authResponse register(registerRequest request) {
        String password = codigoAleatorio();// llamar al método encargado de generar la contraseña aleatoria
        user userData = user.builder()
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .rol(rol.User)
                .correo(request.getUserName())
                .documentoIdentidad(request.getDocumentoIdentidad())
                .celular(request.getCelular())
                .estado(request.getEstado())
                .tipoDocumento(request.getTipoDocumento())
                .genero(request.getGenero())
                .password(passwordEncoder.encode(password))
                .cambiarPassword(true)
                // .password(passwordEncoder.encode(request.getPassword())) //cuando se solicita
                // la contraseña al usuario
                .build();

        data.save(userData);
        emailService email = new emailService(javaMailSender);
        email.enviarCorreoRegistro(userData, password);
        // envíar correo electronico que confirme el registro con la contraseña
        // recomendado hacerlo con un scheuler para que no se demore en el registro
        return authResponse.builder()
                .token(jwtService.getToken(userData))
                .build();
    }

    public authResponse login(loginRequest request) {
        // Autenticación del usuario
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()));

        // Encontrar el usuario autenticado por su nombre de usuario
        UserDetails user = findByUsername(request.getUserName()).orElseThrow();

        // Obtener el token JWT
        String token = jwtService.getToken(user);

        // Obtener el rol del usuario (esto depende de cómo hayas implementado los roles
        // en tu sistema)
        String rol = user.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("User"); // Si no tiene rol, por defecto le asigna "USER"

        // Devolver la respuesta con el token y el rol
        return authResponse.builder()
                .token(token)
                .rol(rol) // Incluir el rol en la respuesta
                .build();
    }

    @Override
    public String save(user user) {
        data.save(user);
        return user.getIdUser();
    }

    @Override
    public List<user> findAll() {
        List<user> listaUser = (List<user>) data.findAll();
        return listaUser;
    }

    @Override
    public List<user> userExist(String correo, String documentoIdentidad) {
        List<user> listaUser = data.userExist(correo, documentoIdentidad);
        return listaUser;
    }

    // Filtro para traer a todos los administradores
    public List<user> buscarRol(rol rol) {
        List<user> listaUser = data.buscarRol(rol);
        return listaUser;
    }

    @Override
    public Optional<user> findOne(String id) {
        Optional<user> user = data.findById(id);
        return user;
    }

    @Override
    public int delete(String id) {
        data.deleteById(id);
        return 1;
    }

    @Override
    public Optional<user> findByUsername(String userName) {
        return data.findByUsername(userName);
    }
}
