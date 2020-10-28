package service;

import java.util.List;

public interface ProducerService {
    boolean deliver(Message message);
    String cambiaMensaje(Message message);
    List<String> tiposDocumentos();
    String generadorTurno();
    String encuentraTramite(String tramite);
    boolean agregaTramite(String tramite);
}
