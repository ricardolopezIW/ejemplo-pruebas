package service;

import java.time.LocalDateTime;
import java.util.*;

public class ProducerServiceImpl implements ProducerService{

    Validador validador;
    Set<String> tramites = new HashSet<>();

    public ProducerServiceImpl(Validador validador) {
        this.validador = validador;
    }

    @Override
    public boolean deliver(Message message) {

        boolean newValidation = message.getMore().length() > 0;

        return newValidation && message.getContenido().equals("ok");
    }

    @Override
    public String cambiaMensaje(Message message) {
        String nuevoMensaje = "Mensaje: " + message.getContenido();
        return nuevoMensaje;
    }

    @Override
    public List<String> tiposDocumentos() {
        List<String> tiposDocumentos = new ArrayList<>();
        tiposDocumentos.add("comprobante de domicilio");
        tiposDocumentos.add("identificacion oficial");
        boolean estaVacia = validador.listaNoNulaNiVacia(tiposDocumentos);
        if (!estaVacia) {
            return new ArrayList<>();
        }
        return tiposDocumentos;
    }

    @Override
    public String generadorTurno(LocalDateTime fecha) {
        boolean esFechaValida = validador.fechaFormato(fecha);
        if (esFechaValida) {
            String turno = "NAY" + fecha.toString();
            return turno;
        } else {
            return null;
        }
    }

    @Override
    public String encuentraTramite(String tramite) {
        boolean tramiteValido = validador.valida(tramite);
        if (tramiteValido) {
            Map<String, String> tramites = new HashMap<>();
            tramites.put("ALTA", "Alta vehiculo");
            tramites.put("BAJA", "Baja vehiculo");

            String respuesta = tramites.get(tramite);
            return respuesta;
        } else {
            return null;
        }
    }

    @Override
    public boolean agregaTramite(String tramite) {
        boolean esValidoTramite = validador.valida(tramite);
        if (esValidoTramite) {
            boolean agregado = tramites.add(tramite);
            return agregado;
        } else {
            return false;
        }
    }

}
