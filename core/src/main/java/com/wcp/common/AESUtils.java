package com.wcp.common;

import com.wcp.common.ByteUtils;
import com.wcp.secure.DefaultSecretKeyProvider;
import com.wcp.secure.SecretKeyProvider;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    private static final Logger log = LoggerFactory.getLogger(AESUtils.class);

    private static SecretKey secretKey = null;
    private static SecretKeySpec keySpec = null;
    private static IvParameterSpec paramSpec = null;

    private static boolean initiated = false;
    private final static String ALGORITHM = "AES/CBC/PKCS5Padding";

    public static String encrypt(String plain) {
        checkIfInitiated();
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
            final byte[] bytes = cipher.doFinal(plain.getBytes("UTF-8"));
            return Base64.encodeBase64URLSafeString(bytes);
        } catch (Throwable t) {
            log.error(t.getMessage(),t);
        }
        return null;
    }

    public static String decrypt(String encrypted) {
        checkIfInitiated();
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
            final byte[] bytes = cipher.doFinal(Base64.decodeBase64(encrypted));
            return new String(bytes, "UTF-8");
        } catch (Throwable t) {
            log.error(t.getMessage(),t);
        }
        return null;
    }

    private static void checkIfInitiated() {
        if (!initiated) {
            SecretKeyProvider provider = null;
            try {
                provider = new DefaultSecretKeyProvider();
            } catch (Throwable t) {
                log.error(t.getMessage(), t);
            }
            secretKey = provider.secretKey();
            final byte[] bytes = ByteUtils.subArray(secretKey.getEncoded(), 0, 16);
            keySpec = new SecretKeySpec(bytes, "AES");
            paramSpec = new IvParameterSpec(bytes);
            initiated = true;
        }
    }
}
