package service;

public class ProducerServiceImpl implements ProducerService{

    Validador validador;

    public ProducerServiceImpl(Validador validador) {
        this.validador = validador;
    }

    @Override
    public boolean deliver(Message message) {

        boolean validaResultado = validador.valida(message.getContenido());

        if (validaResultado && message.getContenido().equals("ok") ){
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
