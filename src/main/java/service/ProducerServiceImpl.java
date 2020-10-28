package service;

import java.time.LocalDateTime;
import java.util.*;

public class ProducerServiceImpl implements ProducerService{

    Validador validador;

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
        return tiposDocumentos;
    }

    @Override
    public String generadorTurno() {
        LocalDateTime now = LocalDateTime.now();
        String turno = "NAY" + now.toString();
        return turno;
    }

    @Override
    public String encuentraTramite(String tramite) {
        Map<String, String> tramites = new HashMap<>();
        tramites.put("ALTA","Alta vehiculo");
        tramites.put("BAJA", "Baja vehiculo");

        String respuesta = tramites.get(tramite);
        return respuesta;
    }

    @Override
    public boolean agregaTramite(String tramite) {
        Set<String> tramites = new HashSet<>();
        boolean agregado = tramites.add(tramite);
        return agregado;
    }

}
