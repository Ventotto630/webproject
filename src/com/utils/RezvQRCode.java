package com.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;

public class RezvQRCode {

    public static String generateQRCode(String data, String path, String fileType, int size, int onColor, int offColor) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1); // ���ñ߾࣬Ĭ����5

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, size, size, hints);

        MatrixToImageConfig config = new MatrixToImageConfig(onColor, offColor);
        Path filePath = new File(path).toPath(); // ȷ��·������ȷ��

        MatrixToImageWriter.writeToPath(matrix, fileType, filePath, config);
        MatrixToImageWriter.writeToStream(matrix, fileType, outputStream, config);

        String base64ImageData = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        String dataUri = "data:image/png;base64," + base64ImageData;
        return dataUri;
    }

    public static void main(String[] args) {
        try {
            String qrCodeData = "https://www.example.com";
            String filePath = "X:\\code\\java\\demo1\\webproject4\\web\\QRCode\\CustomColorQRCode.png";
            int size = 250; // ��ά��ĳߴ�
            int onColor = 0xFF2E7D32; // ��ά����ɫ����ɫ��
            int offColor = 0xFFFFAB91; // ������ɫ����ɫ��
            generateQRCode(qrCodeData, filePath, "PNG", size, onColor, offColor);
            System.out.println("��ά���ѳɹ�����: " + filePath);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}