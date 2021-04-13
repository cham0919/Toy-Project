package com.wcp.judge;

import java.util.Base64;

public interface Base64Utils {

    default String encodeBase64(String input){
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    default String decodeBase64(String input){
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        return new String(decodedBytes);
    }
}
