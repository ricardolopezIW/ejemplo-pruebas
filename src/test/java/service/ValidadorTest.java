package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}