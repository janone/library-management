package org.example.common;

import java.security.MessageDigest;

public final class MD5Util {



    public static String getMD5(String originalString){
        try{
            byte[] originalBytes = originalString.getBytes();

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(originalBytes);

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
