import java.io.File;

public interface EncryptionOrDecryption {

    void EncOrDec(byte[]inputFile, File outputFile, byte[]keys);
    void shiftColumns(byte[]array);
    void roundKey(byte[]array, byte[]key);

}
