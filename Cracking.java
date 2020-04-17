import java.io.File;

public interface Cracking {

    void crack(byte[]cipherText, byte[]plainText, File output);
    void shiftColumns(byte[]array);
    void roundKey(byte[]array, byte[]key);

}
