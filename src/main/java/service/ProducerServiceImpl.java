package service;

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
}
