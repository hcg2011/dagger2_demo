package com.prize.dagger2_demo.Base64;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @ClassName :    com.example.zwq.mrrdemo.util
 * @Describe :     3DES加密解密工具类
 * @Author :       ZWQ
 * @Time :       2017/4/5 17:38
 */

public class DES3Utils {
    // 定义加密算法，DESede即3DES 加密算法,可用 DES,DESede,Blowfish
    private static final String Algorithm = "算法";
    // 加密密钥
    private static final String PASSWORD_CRYPT_KEY = "加密密钥";
	
	hm.put("username", new BASE64Encoder().encode(DES3Utils.encryptMode(userName.getBytes())));
	hm.put("password", new BASE64Encoder().encode(DES3Utils.encryptMode(password.getBytes())));

    /**
     * 加密方法
     *
     * @param src 源数据的字节数组
     * @return
     */
    public static byte[] encryptMode(byte[] src) {
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(
                    build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);
            // 实例化Cipher
            Cipher cipher = Cipher.getInstance(Algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            return cipher.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 解密函数
     *
     * @param src 密文的字节数组
     * @return
     */
    public static byte[] decryptMode(byte[] src) {
        try {
            SecretKey deskey = new SecretKeySpec(
                    build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 根据字符串生成密钥24位的字节数组
     *
     * @param keyStr
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] build3DesKey(String keyStr)
            throws UnsupportedEncodingException {
        byte[] key = new byte[24];
        byte[] temp = keyStr.getBytes("UTF-8");

        if (key.length > temp.length) {
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }
}
