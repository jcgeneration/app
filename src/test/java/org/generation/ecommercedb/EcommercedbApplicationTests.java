package org.generation.ecommercedb;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.generation.ecommercedb.model.Producto;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
class EcommercedbApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void pruebaGET() throws Exception {
		this.mockMvc.perform(get("/api/productos/1"))
					.andDo( print() )
					.andExpect( status().isOk() )
					.andExpect( content().string( 
							containsString("borradorB.jpg")
							) );
	}//pruebaGET
	
	@Test
	@Disabled("Deshabilitado porque ya fue probado")
	public void pruebaDELETE () throws Exception{
		this.mockMvc.perform(delete("/api/productos/8"))
		.andDo( print() )
		.andExpect( status().isOk() )
		.andExpect( content().string( 
				containsString("producto_7.jpg")
				) );
	}//pruebaDELETE
	
	@Test
	public void pruebaPOST() throws Exception {
		Producto p = new Producto(); 
		p.setNombre("Cuaderno Profesional Scribe Clásico / Raya / 150 hojas");
		p.setDescripcion("Cuaderno Profesional Scribe Clásico / Raya / 150 hojas");
		p.setImagen("producto_7.jpg");
		p.setPrecio(74.22);
		
		this.mockMvc.perform(post("/api/productos/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(p)  )
				)
		.andDo( print() )
		.andExpect( status().isOk() )
		.andExpect( content().string( 
				containsString("producto_7.jpg")
				) );
	}//pruebaPOST
	
	private static String asJsonString(final Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }//catch
	 } // asJsonString
	
	@Test
	public void pruebaPUT() throws Exception {
		this.mockMvc.perform(put("/api/productos/10")
				.queryParam("imagen", "producto_x.jpg")
				)
		.andDo( print() )
		.andExpect( status().isOk() )
		
		.andExpect( content().string( 
				containsString("producto_x.jpg")
				) );
	}//pruebaPUT
	
	
	
	  
}//class EcommercedbApplicationTest
