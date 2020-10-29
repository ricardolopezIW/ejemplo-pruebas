package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.springframework.boot.test.context.SpringBootTest;
import service.Message;
import service.ProducerService;
import service.ProducerServiceImpl;
import service.Validador;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class ProcerServiceTests {

	Validador validador = Mockito.mock(Validador.class);

	ProducerService ps = new ProducerServiceImpl(validador);

	@Test
	void deliver_true() {

		Message message = getMessage();
		Mockito.when(validador.valida(message.getContenido())).thenReturn(true);

		boolean resultado = ps.deliver(message);
		Assertions.assertEquals(true, resultado);


		message.setMore("");
		resultado = ps.deliver(message);
		Assertions.assertEquals(false, resultado);

	}

	private Message getMessage() {
		Message message = new Message();
		message.setContenido("ok");
		message.setMore("<html></html>");
		return message;
	}

	@Test
	void deliver_false() {
		Message message = getMessage();
		Mockito.when(validador.valida(message.getContenido())).thenReturn(false);
		boolean resultado = ps.deliver(message);
		Assertions.assertEquals(true, resultado, "Fallo la prueba debido a que esperaba false");

		message.setMore("");
		message.setContenido("not ok");
		resultado = ps.deliver(message);
		Assertions.assertEquals(false, resultado, "Fallo la prueba debido a que esperaba false");

		message.setMore("");
		message.setContenido("ok");
		resultado = ps.deliver(message);
		Assertions.assertEquals(false, resultado, "Fallo la prueba debido a que esperaba false");


		message.setContenido("not ok");
		resultado = ps.deliver(message);
		Assertions.assertEquals(false, resultado, "Fallo la prueba debido a que esperaba false");
	}

	@Test
	void cambiaMensaje_ok() {
		Message message = new Message();
		message.setContenido(" nuevo");
		String resultado = ps.cambiaMensaje(message);
		Assertions.assertEquals("Mensaje: "+" nuevo", resultado);
	}


	@Test
	void cambiaMensaje_false() {
		Message message = new Message();
		message.setContenido(null);
		String resultado = ps.cambiaMensaje(message);
		Assertions.assertEquals("Mensaje: null", resultado);
	}

	@Test
	void agregarTramite_ok() {


		boolean agregado = ps.agregaTramite("ALTA");
		Assertions.assertEquals(true, agregado);
	}

	@Test
	void agregarTramite_false() {
		boolean agregado = ps.agregaTramite("ALTA");
		        agregado = ps.agregaTramite("ALTA");

		Assertions.assertEquals(false,   agregado);

	}

	@Test
	void agregarTramite_null() {
		boolean agregado = ps.agregaTramite(null);
		agregado = ps.agregaTramite(null);
		Assertions.assertEquals(false,   agregado);

	}
}
