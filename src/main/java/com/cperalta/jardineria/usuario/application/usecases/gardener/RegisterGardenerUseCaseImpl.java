package com.cperalta.jardineria.usuario.application.usecases.gardener;

import com.cperalta.jardineria.usuario.domain.models.Gardener;
import com.cperalta.jardineria.usuario.domain.ports.input.gardener.RegisterGardenerUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.*;
import com.cperalta.jardineria.usuario.domain.records.GardenerRecord;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class RegisterGardenerUseCaseImpl implements RegisterGardenerUseCase {

    private final RegisterGardenerRepositoryPort registerGardenerRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final EmailSenderPort emailSender;
    private final TokenGeneratorPort tokenGeneratorPort;
    private final EncryptionPort encryptionPort;


    @Override
    public Gardener register(GardenerRecord gardenerRecord) {
        String hashedPassword = passwordEncoderPort.encode(gardenerRecord.password());
        String tokenDeValidacion = tokenGeneratorPort.generateToken();

        GardenerRecord jardineroRecordEditado = new GardenerRecord(
                gardenerRecord.telephone(),
                gardenerRecord.name(),
                gardenerRecord.lastName(),
                gardenerRecord.email(),
                hashedPassword,
                tokenDeValidacion);

        Gardener gardenerNew = registerGardenerRepositoryPort.register(jardineroRecordEditado);

        if(gardenerNew != null){
            //Envio el Correo para la activación
            //String id = passwordEncoderPort.encode(gardenerRecord.email());
            String id = encryptionPort.encrypt(gardenerRecord.email());
            String urlValidacion = "http://127.0.0.1:8080/api/registro/activar_jardinero?token=" + tokenDeValidacion + "&id=" + id;

            String asunto = "Bienvenido a nuestra plataforma de Jardineria";
            String mensaje = "Hola " + gardenerRecord.name() + ", tu cuenta ha sido creada con éxito.<br>" +
                    " Para activar su cuenta ingrese en el siguiente enlace: <br> <a href='"+urlValidacion+"'>" + urlValidacion + "</a>";
            emailSender.enviarCorreo(gardenerRecord.email(), asunto, mensaje);
        }

        return gardenerNew;
    }

    @Override
    public Boolean activate(String email, String token) {
        String emailDecrypted = encryptionPort.decrypt(email);
        return registerGardenerRepositoryPort.activate(emailDecrypted,token);
    }
}
