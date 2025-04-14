package com.microservice.users.domain.ports.output;

public interface EmailSenderPort {
    void enviarCorreo(String destinatario, String asunto, String mensaje);
}
