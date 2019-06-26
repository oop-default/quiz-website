package cracker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordGenerator {
    public static String generate(String password){
        return hexToString(generateHex(password));
    }
    private static String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i=0; i<bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;  // remove higher bits, sign
            if (val<16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }
    private static byte [] generateHex(String password){
        byte [] bytes =password.getBytes();
        MessageDigest messageDigest;
        byte[] result = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA");
            result =messageDigest.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
