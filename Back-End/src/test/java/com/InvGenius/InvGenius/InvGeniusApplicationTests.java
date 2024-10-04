package com.InvGenius.InvGenius;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.InvGenius.InvGenius.models.rol;
import com.InvGenius.InvGenius.models.user;
import com.InvGenius.InvGenius.service.authService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class InvGeniusApplicationTests {

    @Autowired
    private MockMvc mockMvc;

	@Autowired
	private authService uService;

	@Test
	void contextLoads() {
	}

	@Test
	void testRegistroCorrecto() {
		user user=new user();
		user.setDocumentoIdentidad("1984884343");
		user.setNombres("Julian David");
		user.setApellidos("Fierro Casanova");
		user.setCelular("3123332332");
		user.setCorreo("julifierrocasanova@gmail.com");
		user.setPassword("jajaja");
		user.setConfirmarPassword("jajaja");
		user.setRol(rol.User);
		user.setImagen_base("imagen_base");

		
		var ID = uService.save(user);
		assertNotNull(ID);
	}

	@Test
	void testRegistroCorrectoConDocumentoYCorreoRepetido() {
		user user=new user();
		user.setDocumentoIdentidad("1984884343");
		user.setNombres("Julian David");
		user.setApellidos("Fierro Casanova");
		user.setCelular("3123332332");
		user.setCorreo("julifierrocasanova@gmail.com");
		user.setPassword("jajaja");
		user.setConfirmarPassword("jajaja");
		user.setRol(rol.User);
		user.setImagen_base("imagen_base");

		
		var ID = uService.save(user);
		assertNotNull(ID);
	}

	@Test
	void testRegistroIncorrecto() {
		user user=new user();
		user.setDocumentoIdentidad("1984884343");
		user.setNombres("Julian David");
		// user.setApellidos("Fierro Casanova");
		user.setCelular("3123332332");
		user.setCorreo("julifierrocasanova@gmail.com");
		user.setPassword("jajaja");
		user.setConfirmarPassword("jajaja");
		user.setRol(rol.User);
		user.setImagen_base("imagen_base");

		
		var ID = uService.save(user);
		assertNotNull(ID);
	}

	@Test
    public void testConsultaListaUsuario() throws Exception { //correcto cuando pido usuario e incorrecto cuando pido empresa
        var listaUser = uService.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String listaUserJson = objectMapper.writeValueAsString(listaUser);
        mockMvc.perform(get("/api/v1/user/"))
                .andExpect(status().isOk()) //oisforbidden
                .andExpect(content().string(listaUserJson));
    }

	@Test
    public void testConsultaListaUsuarioErroneo() throws Exception { //correcto cuando pido usuario e incorrecto cuando pido empresa
        var listaUser = uService.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String listaUserJson = objectMapper.writeValueAsString(listaUser);
        mockMvc.perform(get("/api/v1/login/"))
                .andExpect(status().isOk()) //oisforbidden
                .andExpect(content().string(listaUserJson));
    }
    @Test
    void registroUsuarioCorrectoController() throws Exception { //test correcto con todos los datos e incorrecto solo con un dato
        // se crea el usuario
        var user = new user();
        // asignan los datos
		user.setDocumentoIdentidad("1984884343");
		user.setNombres("Mariana David");
		user.setApellidos("Fierro Casanova");
		user.setCelular("3123332332");
		user.setCorreo("julifierrocasanova@gmail.com");
		user.setPassword("jajaja");
		user.setConfirmarPassword("jajaja");
		user.setRol(rol.User);
		user.setImagen_base("imagen_base");
        // el resto de los datos

        // Convertir el objeto Item a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        // se realiza la petición
        mockMvc.perform(post("/api/v1/user/")
                // se define el tipo datos
                .contentType(MediaType.APPLICATION_JSON)
                // enviamos los datos
                .content(userJson))
                .andExpect(jsonPath("$.idUser").isNotEmpty())
                .andExpect(status().isOk());
    }
    @Test
    void registroUsuarioIncorrectoController() throws Exception { //test correcto con todos los datos e incorrecto solo con un dato
        // se crea el usuario
        var user = new user();
        // asignan los datos
		user.setDocumentoIdentidad("1984884343");
		// user.setNombres("Mariana David");
		user.setApellidos("Fierro Casanova");
		user.setCelular("3123332332");
		user.setCorreo("julifierrocasanova@gmail.com");
		user.setPassword("jajaja");
		user.setConfirmarPassword("jajaja");
		user.setRol(rol.User);
		user.setImagen_base("imagen_base");
        // el resto de los datos

        // Convertir el objeto Item a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        // se realiza la petición
        mockMvc.perform(post("/api/v1/user/")
                // se define el tipo datos
                .contentType(MediaType.APPLICATION_JSON)
                // enviamos los datos
                .content(userJson))
                .andExpect(jsonPath("$.idUser").isNotEmpty())
                .andExpect(status().isOk());
    }
    @Test
    void testRegistroCorrectoConDocumentoYCorreoRepetidoController() throws Exception { //test correcto con todos los datos e incorrecto solo con un dato
        // se crea el usuario
        var user = new user();
        // asignan los datos
		user.setDocumentoIdentidad("1984884343");
		user.setNombres("Mariana David");
		user.setApellidos("Fierro Casanova");
		user.setCelular("3123332332");
		user.setCorreo("julifierrocasanova@gmail.com");
		user.setPassword("jajaja");
		user.setConfirmarPassword("jajaja");
		user.setRol(rol.User);
		user.setImagen_base("imagen_base");
        // el resto de los datos

        // Convertir el objeto Item a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        // se realiza la petición
        mockMvc.perform(post("/api/v1/user/")
                // se define el tipo datos
                .contentType(MediaType.APPLICATION_JSON)
                // enviamos los datos
                .content(userJson))
                .andExpect(jsonPath("$.idUser").isNotEmpty())
                .andExpect(status().isOk());
    }
}
