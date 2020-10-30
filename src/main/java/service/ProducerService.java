package service;

import java.time.LocalDateTime;
import java.util.List;

public interface ProducerService {
    boolean deliver(Message message);
    String cambiaMensaje(Message message);
    List<String> tiposDocumentos();//GABRIEL
    String generadorTurno(LocalDateTime fecha);//VERO
    String encuentraTramite(String tramite); //ARMANDO
    boolean agregaTramite(String tramite); //CLAUDIA
}
