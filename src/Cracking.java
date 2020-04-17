import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface Cracking {

    void crack(byte[]cipherText, byte[]plaintText, File output) throws NoSuchAlgorithmException, IOException;
//    byte[] shiftColumns(byte[] message);
//    byte[] roundKey(byte[] state, byte[]key);

}
