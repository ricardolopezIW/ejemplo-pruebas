package service;

public class ProducerServiceImpl implements ProducerService{
    @Override
    public boolean deliver(Message message) {
        if (message.getContenido().equals("ok")){
            // aqui va todo el proceso de envio de mensaje
            return true;
        }
        return false;
    }

    @Override
    public String cambiaMensaje(Message message) {
        String nuevoMensaje = "Mensaje: " + message.getContenido();
        return nuevoMensaje;
    }
}
