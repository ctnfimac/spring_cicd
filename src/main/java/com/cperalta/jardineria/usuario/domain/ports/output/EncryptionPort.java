package com.cperalta.jardineria.usuario.domain.ports.output;

public interface EncryptionPort {
    String encrypt(String data);
    String decrypt(String encryptedData);
}
