package service;

import java.time.LocalDateTime;
import java.util.List;

public interface ProducerService {
    boolean deliver(Message message);
    String cambiaMensaje(Message message);
    List<String> tiposDocumentos();
    String generadorTurno(LocalDateTime fecha);
    String encuentraTramite(String tramite);
    boolean agregaTramite(String tramite);
}
