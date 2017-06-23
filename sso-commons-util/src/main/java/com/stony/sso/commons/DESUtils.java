package com.stony.sso.commons;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * <p>Java-SSO
 * <p>com.stony.sso.commons
 *
 * @author stony
 * @version 上午10:21
 * @since 2017/6/22
 */
public abstract class DESUtils {

    private static final String KEY_ALGORITHM = "DES";

    /**
     * 生成加密key
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        DESKeySpec des = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(des);
        return secretKey;
    }



    /**
     * 加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom random = new SecureRandom();
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k,random);
        return cipher.doFinal(data);
    }


    /**
     * 解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom random = new SecureRandom();
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k ,random);
        return cipher.doFinal(data);
    }



    /**
     * @param args
     * @return
     */
    public static String generateTicket(String...args){
        StringBuffer buffer = new StringBuffer();
        for(String arg : args){
            buffer.append(arg);
        }
        try {
            buffer.append(UUID.randomUUID());
            return MD5Util.md5UpperCase(buffer.toString());
        } catch (Exception e) {
            return buffer.toString();
        }
    }

    public static String generateAppKey(){
        String key = "AK47" + RandomsUtil.generateCode() + "_" + System.currentTimeMillis();
        byte[] keyByte = key.getBytes();
        return UUID.nameUUIDFromBytes(keyByte).toString();
    }
    public static String generateAppSecret(){
        String key = "M16" + RandomsUtil.generateCode() + "_" + System.currentTimeMillis();
        byte[] keyByte = key.getBytes();
        return UUID.nameUUIDFromBytes(keyByte).toString();
    }

    public static String token2Phone(String token) {
        return null;
    }
}
