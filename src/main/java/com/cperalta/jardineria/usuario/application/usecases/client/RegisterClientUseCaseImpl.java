package com.cperalta.jardineria.usuario.application.usecases.client;

import com.cperalta.jardineria.usuario.domain.models.Client;
import com.cperalta.jardineria.usuario.domain.ports.input.client.RegisterClientUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.*;
import com.cperalta.jardineria.usuario.domain.records.ClientRecord;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterClientUseCaseImpl implements RegisterClientUseCase {

    private final RegisterClientRepositoryPort registerClientRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final EmailSenderPort emailSender;
    private final TokenGeneratorPort tokenGeneratorPort;
    private final EncryptionPort encryptionPort;

    @Override
    public Client register(ClientRecord clientRecord) {
        String hashedPassword = passwordEncoderPort.encode(clientRecord.password());
        String tokenDeValidacion = tokenGeneratorPort.generateToken();

        ClientRecord clienteRecordEditado = new ClientRecord(
                clientRecord.telephone(),
                clientRecord.name(),
                clientRecord.lastName(),
                clientRecord.email(),
                clientRecord.address(),
                hashedPassword,
                tokenDeValidacion);

        Client clienteNuevo = registerClientRepositoryPort.register(clienteRecordEditado);

        if(clienteNuevo != null){
            //Envio el Correo para la activación
            String id = encryptionPort.encrypt(clientRecord.email());
            String urlValidacion = "http://127.0.0.1:8080/api/registro/activar_cliente?token=" + tokenDeValidacion + "&id=" + id;

            String asunto = "Bienvenido a nuestra plataforma de Jardineria";
            String mensaje = "Hola " + clientRecord.name() + ", tu cuenta ha sido creada con éxito.<br>" +
                    " Para activar su cuenta ingrese en el siguiente enlace: <br> <a href='"+urlValidacion+"'>" + urlValidacion + "</a>";
            emailSender.enviarCorreo(clientRecord.email(), asunto, mensaje);
        }

        return clienteNuevo;
    }

    @Override
    public Boolean activate(String email, String token) {
        String emailDesencriptado = encryptionPort.decrypt(email);
        return registerClientRepositoryPort.activate(emailDesencriptado,token);
    }
}
