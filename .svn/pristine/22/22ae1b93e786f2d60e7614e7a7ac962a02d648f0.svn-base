package com.wh.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class CryptAES{

    public void testCrossLanguageEncrypt() throws Exception{
        System.out.println(encrypt("Test String"));
        System.out.println(desEncrypt("2fbwW9+8vPId2/foafZq6Q=="));
    }

    public static String encrypt(String data) throws Exception {
        try {
            //String data = "Test String";
            String key = "1234567812345678";
            String iv = "1234567812345678";
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return new sun.misc.BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String desEncrypt(String data) throws Exception {
        try
        {
           // String data = "2fbwW9+8vPId2/foafZq6Q==";
            String key = "1234567812345678";
            String iv = "1234567812345678";

            byte[] encrypted1 = new sun.misc.BASE64Decoder().decodeBuffer(data);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}