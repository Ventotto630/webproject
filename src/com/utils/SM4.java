package com.utils;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.SM4Engine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SM4 {
    public static void main(String[] args) {
//        try {
//            // 定义原始数据
//            String plaintext = "Hello, World!";
//            byte[] input = plaintext.getBytes();
//
//            // 生成密钥
//            String keyHex = "0123456789ABCDEF0123456789ABCDEF";
//            byte[] keyData = Hex.decode(keyHex);
//            SecretKey key = new SecretKeySpec(keyData, "SM4");
//
//            // 定义初始向量（IV）
//            String ivHex = "00000000000000000000000000000000";
//            byte[] ivData = Hex.decode(ivHex);
//
//            // 加密
//            byte[] encrypted = encrypt(input, key, ivData);
//            String encryptedHex = Hex.toHexString(encrypted);
//            System.out.println("Encrypted: " + encryptedHex);
//
//            // 解密
//            byte[] decrypted = decrypt(encrypted, key, ivData);
//            String decryptedText = new String(decrypted);
//            System.out.println("Decrypted: " + decryptedText);
//        } catch (Exception e) {
//            System.err.println("Error: " + e.getMessage());
//        }
        try {
            // 定义原始数据
            String perid ="360124200406300017";
            //String plaintext = "Hello, World!";
            byte[] input = perid.getBytes();

            // 生成密钥
            String keyHex = "0123456789ABCDEF0123456789ABCDEF";
            byte[] keyData = Hex.decode(keyHex);
            SecretKey key = new SecretKeySpec(keyData, "SM4");

            // 定义初始向量（IV）
            String ivHex = "00000000000000000000000000000000";
            byte[] ivData = Hex.decode(ivHex);

            // 加密
            SM4 sm4 = new SM4();
            byte[] encrypted = sm4.encrypt(input, key, ivData);
            perid = Hex.toHexString(encrypted);
             //解密
            byte[] decrypted = decrypt(encrypted, key, ivData);
            String decryptedText = new String(decrypted);
            System.out.println("Decrypted: " + decryptedText);
        }catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static byte[] encrypt(byte[] input, SecretKey key, byte[] iv) throws Exception {
        // 创建SM4算法实例
        BlockCipher engine = new SM4Engine();

        // 将SM4算法与CBC模式和PKCS7填充组合
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));

        // 初始化加密操作
        cipher.init(true, new ParametersWithIV(new KeyParameter(key.getEncoded()), iv));

        // 执行加密操作
        byte[] ciphertext = new byte[cipher.getOutputSize(input.length)];
        int outputLen = cipher.processBytes(input, 0, input.length, ciphertext, 0);
        cipher.doFinal(ciphertext, outputLen);

        return ciphertext;
    }

    public static byte[] decrypt(byte[] input, SecretKey key, byte[] iv) throws Exception {
        // 创建SM4算法实例
        BlockCipher engine = new SM4Engine();

        // 将SM4算法与CBC模式和PKCS7填充组合
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));

        // 初始化解密操作
        cipher.init(false, new ParametersWithIV(new KeyParameter(key.getEncoded()), iv));

        // 执行解密操作
        byte[] decrypted = new byte[cipher.getOutputSize(input.length)];
        int outputLen = cipher.processBytes(input, 0, input.length, decrypted, 0);
        cipher.doFinal(decrypted, outputLen);

        return decrypted;
    }
}