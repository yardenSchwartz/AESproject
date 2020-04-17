import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface EncryptionOrDecryption {

    void EncOrDec(byte[]inputFile, File outputFile, byte[]keys) throws IOException;
    void shiftColumns(byte[]array);
    void roundKey(byte[]array, byte[]key);

}
