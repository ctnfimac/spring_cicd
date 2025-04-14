package com.microservice.users.domain.ports.output;

public interface EncryptionPort {
    String encrypt(String data);
    String decrypt(String encryptedData);
}
