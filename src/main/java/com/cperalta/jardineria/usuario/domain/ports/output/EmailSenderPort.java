package com.cperalta.jardineria.usuario.domain.ports.output;

public interface EmailSenderPort {
    void enviarCorreo(String destinatario, String asunto, String mensaje);
}
