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
//            // ����ԭʼ����
//            String plaintext = "Hello, World!";
//            byte[] input = plaintext.getBytes();
//
//            // ������Կ
//            String keyHex = "0123456789ABCDEF0123456789ABCDEF";
//            byte[] keyData = Hex.decode(keyHex);
//            SecretKey key = new SecretKeySpec(keyData, "SM4");
//
//            // �����ʼ������IV��
//            String ivHex = "00000000000000000000000000000000";
//            byte[] ivData = Hex.decode(ivHex);
//
//            // ����
//            byte[] encrypted = encrypt(input, key, ivData);
//            String encryptedHex = Hex.toHexString(encrypted);
//            System.out.println("Encrypted: " + encryptedHex);
//
//            // ����
//            byte[] decrypted = decrypt(encrypted, key, ivData);
//            String decryptedText = new String(decrypted);
//            System.out.println("Decrypted: " + decryptedText);
//        } catch (Exception e) {
//            System.err.println("Error: " + e.getMessage());
//        }
        try {
            // ����ԭʼ����
            String perid ="360124200406300017";
            //String plaintext = "Hello, World!";
            byte[] input = perid.getBytes();

            // ������Կ
            String keyHex = "0123456789ABCDEF0123456789ABCDEF";
            byte[] keyData = Hex.decode(keyHex);
            SecretKey key = new SecretKeySpec(keyData, "SM4");

            // �����ʼ������IV��
            String ivHex = "00000000000000000000000000000000";
            byte[] ivData = Hex.decode(ivHex);

            // ����
            SM4 sm4 = new SM4();
            byte[] encrypted = sm4.encrypt(input, key, ivData);
            perid = Hex.toHexString(encrypted);
             //����
            byte[] decrypted = decrypt(encrypted, key, ivData);
            String decryptedText = new String(decrypted);
            System.out.println("Decrypted: " + decryptedText);
        }catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static byte[] encrypt(byte[] input, SecretKey key, byte[] iv) throws Exception {
        // ����SM4�㷨ʵ��
        BlockCipher engine = new SM4Engine();

        // ��SM4�㷨��CBCģʽ��PKCS7������
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));

        // ��ʼ�����ܲ���
        cipher.init(true, new ParametersWithIV(new KeyParameter(key.getEncoded()), iv));

        // ִ�м��ܲ���
        byte[] ciphertext = new byte[cipher.getOutputSize(input.length)];
        int outputLen = cipher.processBytes(input, 0, input.length, ciphertext, 0);
        cipher.doFinal(ciphertext, outputLen);

        return ciphertext;
    }

    public static byte[] decrypt(byte[] input, SecretKey key, byte[] iv) throws Exception {
        // ����SM4�㷨ʵ��
        BlockCipher engine = new SM4Engine();

        // ��SM4�㷨��CBCģʽ��PKCS7������
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));

        // ��ʼ�����ܲ���
        cipher.init(false, new ParametersWithIV(new KeyParameter(key.getEncoded()), iv));

        // ִ�н��ܲ���
        byte[] decrypted = new byte[cipher.getOutputSize(input.length)];
        int outputLen = cipher.processBytes(input, 0, input.length, decrypted, 0);
        cipher.doFinal(decrypted, outputLen);

        return decrypted;
    }
}