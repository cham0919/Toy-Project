package com.wcp.common.http;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {

    public static String getClientIp(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if (ip == null) ip = req.getRemoteAddr();
        return ip;
    }

}
