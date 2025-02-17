package com.cperalta.jardineria.usuario.application.usecases.jardinero;

import com.cperalta.jardineria.usuario.domain.models.Jardinero;
import com.cperalta.jardineria.usuario.domain.ports.input.jardinero.RegistrarJardineroUseCase;
import com.cperalta.jardineria.usuario.domain.ports.output.EmailSenderPort;
import com.cperalta.jardineria.usuario.domain.ports.output.PasswordEncoderPort;
import com.cperalta.jardineria.usuario.domain.ports.output.RegistrarJardineroRepositoryPort;
import com.cperalta.jardineria.usuario.domain.ports.output.TokenGeneratorPort;
import com.cperalta.jardineria.usuario.domain.records.JardineroRecord;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class RegistrarJardineroUseCaseImpl implements RegistrarJardineroUseCase {

    private final RegistrarJardineroRepositoryPort registrarJardineroRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final EmailSenderPort emailSender;
    private final TokenGeneratorPort tokenGeneratorPort;

    @Override
    public Jardinero registrar(JardineroRecord jardineroRecord) {
        String hashedPassword = passwordEncoderPort.encode(jardineroRecord.contrasenia());
        String tokenDeValidacion = tokenGeneratorPort.generateToken();

        JardineroRecord jardineroRecordEditado = new JardineroRecord(
                jardineroRecord.telefono(),
                jardineroRecord.nombre(),
                jardineroRecord.apellido(),
                jardineroRecord.email(),
                hashedPassword,
                tokenDeValidacion);

        Jardinero jardineroNuevo = registrarJardineroRepositoryPort.registrar(jardineroRecordEditado);

        //Envio el Correo para la activación
        String id = passwordEncoderPort.encode(jardineroRecord.email());
        String urlValidacion = "http://127.0.0.1:8080/api/confirmar?token=" + tokenDeValidacion + "&id=" + id;

        String asunto = "Bienvenido a nuestra plataforma de Jardineria";
        String mensaje = "Hola " + jardineroRecord.nombre() + ", tu cuenta ha sido creada con éxito.<br>" +
                " Para activar su cuenta ingrese en el siguiente enlace: <br> <a href='"+urlValidacion+"'>" + urlValidacion + "</a>";
        emailSender.enviarCorreo(jardineroRecord.email(), asunto, mensaje);

        return jardineroNuevo;
    }
}
