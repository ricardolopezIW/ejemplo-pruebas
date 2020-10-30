package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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


}