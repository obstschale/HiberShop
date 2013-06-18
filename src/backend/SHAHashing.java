package backend;

import java.security.MessageDigest;
 
public class SHAHashing 
{
    public static String createSHA1(String file) throws Exception
    {
 
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(file.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
    }
}
