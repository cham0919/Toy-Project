package com.wcp.secure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class RandomSecretKeyProvider implements SecretKeyProvider {

    private final Logger log = LoggerFactory.getLogger(RandomSecretKeyProvider.class);

    @Override
    public SecretKey secretKey() {
        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        }
        return keyGen.generateKey();
    }
}
