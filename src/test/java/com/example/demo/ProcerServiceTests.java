package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import service.Message;
import service.ProducerService;
import service.ProducerServiceImpl;
import service.Validador;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class ProcerServiceTests {

	Validador validador = Mockito.mock(Validador.class);

	ProducerService ps = new ProducerServiceImpl(validador);

	@Test
	void deliver_true() {

		Message message = getMessage();
		Mockito.when(validador.valida(message.getContenido())).thenReturn(false);

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
		Mockito.when(validador.valida("ALTA")).thenReturn(true);
		boolean agregado = ps.agregaTramite("ALTA");
		Assertions.assertEquals(true, agregado);
	}

	@Test
	void agregarTramite_false() { //tramite repetido
		Mockito.when(validador.valida("ALTA")).thenReturn(false);
		boolean agregado = ps.agregaTramite("ALTA");
		Assertions.assertEquals(false,   agregado);
	}

	@Test
	void agregarTramite_null() {
		Mockito.when(validador.valida(null)).thenReturn(false);
		boolean agregado = ps.agregaTramite(null);
		Assertions.assertEquals(false,   agregado);
	}

	@Test
	void generadorTurno_ok() {

		LocalDateTime now = LocalDateTime.now();
		String turno = "NAY" + now.toString();
		Mockito.when(validador.fechaFormato(now)).thenReturn(true);
		String resultado = ps.generadorTurno(now);
		Assertions.assertEquals(turno, resultado);
	}

	@Test
	void generadorTurno_false() {
		Mockito.when(validador.fechaFormato(null)).thenReturn(false);
		String resultado = ps.generadorTurno(null);
		Assertions.assertEquals(null, resultado);
	}


	@Test
	void encuentraTramite_ok(){
		String resultado = ps.encuentraTramite("ALTA");
		Assertions.assertEquals("Alta vehiculo", resultado);
	}

	@Test
	void encuentraTramite_notOk(){
		String resultado = ps.encuentraTramite("COMPRAVENTA");
		Assertions.assertEquals(null, resultado);
	}



	@Test
	void  tiposDocumentos_ok() {
		List<String> resultado = ps.tiposDocumentos();
		Assertions.assertEquals(true, resultado.contains("comprobante de domicilio"));
	}

	@Test
	void  tiposDocumentos_false() {
		List<String> resultado = ps.tiposDocumentos();

		Assertions.assertEquals(false, resultado.isEmpty());
	}


}
