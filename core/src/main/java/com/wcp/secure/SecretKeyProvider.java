package com.wcp.secure;

import javax.crypto.SecretKey;

public interface SecretKeyProvider {
	SecretKey secretKey();
}
