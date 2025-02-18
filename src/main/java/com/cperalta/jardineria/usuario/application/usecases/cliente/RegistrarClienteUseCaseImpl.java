package com.cperalta.jardineria.usuario.application.usecases.cliente;

import com.cperalta.jardineria.usuario.domain.models.Cliente;
import com.cperalta.jardineria.usuario.domain.ports.input.cliente.RegistrarClienteUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.*;
import com.cperalta.jardineria.usuario.domain.records.ClienteRecord;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegistrarClienteUseCaseImpl implements RegistrarClienteUseCase {

    private final RegistrarClienteRepositoryPort registrarClienteRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final EmailSenderPort emailSender;
    private final TokenGeneratorPort tokenGeneratorPort;
    private final EncryptionPort encryptionPort;

    @Override
    public Cliente registrar(ClienteRecord clienteRecord) {
        String hashedPassword = passwordEncoderPort.encode(clienteRecord.contrasenia());
        String tokenDeValidacion = tokenGeneratorPort.generateToken();

        ClienteRecord clienteRecordEditado = new ClienteRecord(
                clienteRecord.telefono(),
                clienteRecord.nombre(),
                clienteRecord.apellido(),
                clienteRecord.email(),
                clienteRecord.direccion(),
                hashedPassword,
                tokenDeValidacion);

        Cliente clienteNuevo = registrarClienteRepositoryPort.registrar(clienteRecordEditado);

        if(clienteNuevo != null){
            //Envio el Correo para la activación
            String id = encryptionPort.encrypt(clienteRecord.email());
            String urlValidacion = "http://127.0.0.1:8080/api/registro/activar_cliente?token=" + tokenDeValidacion + "&id=" + id;

            String asunto = "Bienvenido a nuestra plataforma de Jardineria";
            String mensaje = "Hola " + clienteRecord.nombre() + ", tu cuenta ha sido creada con éxito.<br>" +
                    " Para activar su cuenta ingrese en el siguiente enlace: <br> <a href='"+urlValidacion+"'>" + urlValidacion + "</a>";
            emailSender.enviarCorreo(clienteRecord.email(), asunto, mensaje);
        }

        return clienteNuevo;
    }

    @Override
    public Boolean activar(String email, String token) {
        String emailDesencriptado = encryptionPort.decrypt(email);
        return registrarClienteRepositoryPort.activar(emailDesencriptado,token);
    }
}
