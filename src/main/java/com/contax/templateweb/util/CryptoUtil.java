/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contax.templateweb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;

/**
 *
 * @author 584455
 */
public class CryptoUtil {

    private static final char[] toHex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void main(String[] args) {
        String s = "21,22,27,28";

        System.out.println(toAdler32Hex(s));
    }

    public static String toAdler32Hex(String str) {
        byte bytes[] = str.getBytes();

        Adler32 checksum = new Adler32();
        checksum.update(bytes);

        int lngChecksum = (int) checksum.getValue();

        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        StringBuilder hexBuilder = new StringBuilder(8);
        hexBuilder.setLength(8);
        for (int i = 8 - 1; i >= 0; --i) {
            int j = lngChecksum & 0x0F;
            hexBuilder.setCharAt(i, hexDigits[j]);
            lngChecksum >>= 4;
        }

        return hexBuilder.toString();
    }

    public static String toHexString(byte b[]) {
        int pos = 0;
        char[] c = new char[b.length * 2];
        for (int i = 0; i < b.length; i++) {
            c[pos++] = toHex[(b[i] >> 4) & 0x0F];
            c[pos++] = toHex[b[i] & 0x0f];
        }
        return new String(c);
    }

    public static String H(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            return toHexString(digest.digest(data.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            // shouldn't happen
            throw new RuntimeException("Failed to instantiate an MD5 algorithm", ex);
        }
    }

    public static String KD(String secret, String data) {
        return H(secret + ":" + data);
    }
}
