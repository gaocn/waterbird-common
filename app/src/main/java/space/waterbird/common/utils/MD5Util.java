package space.waterbird.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import space.waterbird.android.log.Log;

/**
 * MD5
 */
public class MD5Util {
    private static final String TAG                  = MD5Util.class.getSimpleName();
    private static final int    STREAM_BUFFER_LENGTH = 1024;

    public static MessageDigest getDigest(final String algorithm) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(algorithm);
    }

    public static byte[] md5(String txt) {
        return md5(txt.getBytes());
    }

    public static byte[] md5(byte[] bytes) {
        try {
            MessageDigest digest = getDigest("MD5");
            digest.update(bytes);
            return digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] md5(InputStream is) throws NoSuchAlgorithmException, IOException {
        return updateDigest(getDigest("MD5"), is).digest();
    }

    public static MessageDigest updateDigest(final MessageDigest digest, final InputStream data) throws IOException {
        final byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
        int read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);

        while (read > -1) {
            digest.update(buffer, 0, read);
            read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
        }

        return digest;
    }
    /**
     * 获取文件的MD5值
     * @param filePath
     * @return
     */
    public static String getFileMD5(String filePath) {
        try {
            File file = new File(filePath);
            MessageDigest digest = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(file);

            byte[] buffer = new byte[1024];
            int len = -1;
            while((len = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            byte[] result = digest.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b : result) {
                int number = b & 0xFF;
                String numberStr = Integer.toHexString(number);
                if(numberStr.length() == 1)
                    sb.append("0");
                sb.append(numberStr);
            }
            Log.d(TAG, "getFileMD5: 文件的MD5值为 " + sb.toString());
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }




}
