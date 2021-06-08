package com.wcp.secure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class DefaultSecretKeyProvider implements SecretKeyProvider {

	private final Logger log = LoggerFactory.getLogger(DefaultSecretKeyProvider.class);

	private static final String key = "wcp";

	@Override
	public SecretKey secretKey() {
		SecretKey secretKey = null;
		try{
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("UTF-8");
			int len = b.length;
			if (len > keyBytes.length) {
				len = keyBytes.length;
			}
			System.arraycopy(b, 0, keyBytes, 0, len);
			secretKey = new SecretKeySpec(keyBytes, "AES");
		}catch (Throwable t){
			log.error(t.getMessage(), t);
		}
		return secretKey;
	}
}
