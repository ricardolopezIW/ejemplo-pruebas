package service;

public interface ProducerService {
    boolean deliver(Message message);
    String cambiaMensaje(Message message);
}
