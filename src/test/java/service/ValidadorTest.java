package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorTest {

    @Test
    void valida() {
        Validador validador = new Validador();
        boolean ok = validador.valida("ok");
        Assertions.assertEquals(true, ok);

        boolean notOk = validador.valida("");
        Assertions.assertEquals(false, notOk);

    }

    @Test
    void valida_ok(){
        Validador validador = new Validador();
        LocalDateTime now = LocalDateTime.now();
        boolean fechaFormato = validador.fechaFormato(now);
        Assertions.assertEquals(true, fechaFormato);
    }

    @Test
    void valida_list(){
        Validador service = new Validador();

        boolean listaNula = service.listaNoNulaNiVacia(null);
        Assertions.assertFalse(listaNula);

        boolean listaVacia = service.listaNoNulaNiVacia(new ArrayList<>());
        Assertions.assertFalse(listaVacia);

        List<String> d = new ArrayList<>();
        d.add("new documento");

        boolean listaLlena = service.listaNoNulaNiVacia(d);
        Assertions.assertTrue(listaLlena);

    }
}