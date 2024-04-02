package com.example.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/1/4 10:26
 */
public class AESCipher {

    public static void main(String[] args) {
        byte[] key = "CASB2021lvmc!!".getBytes();
        byte[] iv = "CASB2021lvmc!!".getBytes();
        byte[] plaintext = "10.10.108.17:8015".getBytes();

        // 加密
        try {
            byte[] ciphertext = encrypt(plaintext, key, iv);
            System.out.printf("Plaintext: %s\n", new String(plaintext));
            System.out.printf("Ciphertext: %s\n", bytesToHex(ciphertext));

            // 解密
            byte[] decryptedText = decrypt(ciphertext, key, iv);
            System.out.printf("Decrypted text: %s\n", new String(decryptedText));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static byte[] encrypt(byte[] plaintext, byte[] key, byte[] iv) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
        return cipher.doFinal(plaintext);
    }

    // 使用AES CBC方式解密
    private static byte[] decrypt(byte[] ciphertext, byte[] key, byte[] iv) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

        // 解密

        return cipher.doFinal(ciphertext);
    }

    // 将字节数组转换为十六进制字符串
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }



}
