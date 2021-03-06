package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Validador {
    public boolean valida(String contenido) {
        return contenido.length() > 0;
    }

    public boolean fechaFormato(LocalDateTime fechaYHora){
        if (fechaYHora == null) {
            return false;
        }
        try {
            String format = fechaYHora.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean listaNoNulaNiVacia(List<String> lista){
        if (lista == null) {
            return false;
        } else if (lista != null && lista.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
