package com.example.pegasus1.api2parsinglistview;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class Decoding {

    /**
     * Base64에서 문자로 Decoding
     * @param arg
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String Base64ToStringDecoding(String arg) throws UnsupportedEncodingException {

        String base64decoding = null;
        byte[] s_param = arg.getBytes();
        byte[] d_param = Base64.decodeBase64(s_param);
        base64decoding = new String(d_param,"UTF-8");

        return base64decoding;

    }
}
