package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import service.Message;
import service.ProducerService;
import service.ProducerServiceImpl;

@SpringBootTest
class ProcerServiceTests {



	ProducerService ps = new ProducerServiceImpl();

	@Test
	void deliver_true() {
		Message message = new Message();
		message.setContenido("ok");
		boolean resultado = ps.deliver(message);
		Assertions.assertEquals(true, resultado);
	}

	@Test
	void deliver_false() {
		Message message = new Message();
		message.setContenido("not ok");
		boolean resultado = ps.deliver(message);
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
}
