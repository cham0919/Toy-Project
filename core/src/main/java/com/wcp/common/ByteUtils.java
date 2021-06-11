package com.wcp.common;

public class ByteUtils {

    public static byte[] subArray(byte[] var0, int var1, int var2) {
        byte[] var3 = new byte[var2 - var1];
        System.arraycopy(var0, var1, var3, 0, var2 - var1);
        return var3;
    }
}
