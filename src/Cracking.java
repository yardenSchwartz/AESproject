import java.io.File;
import java.security.NoSuchAlgorithmException;

public interface Cracking {

    void crack(byte[]cipherText, byte[]plaintText, File output) throws NoSuchAlgorithmException;
    byte[] shiftColumns(byte[] message);
    byte[] roundKey(byte[] state, byte[]key);

}
