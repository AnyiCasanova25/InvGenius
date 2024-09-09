package com.InvGenius.InvGenius;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.InvGenius.InvGenius.models.user;
import com.InvGenius.InvGenius.service.userService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class InvGeniusApplicationTests {

    @Autowired
    private MockMvc mockMvc;

	@Autowired
	private userService uService;

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
		user.setRol("Admin");
		user.setImagenUser("imagenUser");

		
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
		user.setRol("Admin");
		user.setImagenUser("imagenUser");

		
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
		user.setRol("Admin");
		user.setImagenUser("imagenUser");

		
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

	

}
