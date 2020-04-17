import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("hello");

        String encOrDecOrCrack = args[0];//-e/-d/-b
        String keysOrPlaintext = args[1];//-k/-m
        String pathToKeysOrMessage = args[2];
        String inputOrCipher = args[3];//-c/-i
        String pathToInputOrCipher = args[4];
        String output = args[5];//-o
        String pathToOutput = args[6];

        //encryption
        if(encOrDecOrCrack.equals("-e")){
            EncAES3 encAES3 = new EncAES3();
            if(keysOrPlaintext.equals("-k")){
                File keysFile= new File(pathToKeysOrMessage);
                byte[]keys = Files.readAllBytes(keysFile.toPath());
                if(inputOrCipher.equals("-i")) {
                    File inputFile = new File(pathToInputOrCipher);
                    byte[] input = Files.readAllBytes(inputFile.toPath());
                    if(output.equals("-o")) {
                        File outputFile = new File(pathToOutput);
                        encAES3.EncOrDec(input, outputFile, keys);
                    }
                }
            }
        }
        else{
            //decryption
            if(encOrDecOrCrack.equals("-d")){
                DecAES3 decAES3 = new DecAES3();
                if(keysOrPlaintext.equals("-k")){
                    File keysFile= new File(pathToKeysOrMessage);
                    byte[]keys = Files.readAllBytes(keysFile.toPath());
                    if(inputOrCipher.equals("-i")) {
                        File inputFile = new File(pathToInputOrCipher);
                        byte[] input = Files.readAllBytes(inputFile.toPath());
                        if(output.equals("-o")) {
                            File outputFile = new File(pathToOutput);
                            decAES3.EncOrDec(input, outputFile, keys);
                        }
                    }
                }
            }
            else{
                //crack
                if(encOrDecOrCrack.equals("-b")){
                    Crack crack = new Crack();
                    if(keysOrPlaintext.equals("-m")){
                        File messageFile= new File(pathToKeysOrMessage);
                        byte[]message = Files.readAllBytes(messageFile.toPath());
                        if(inputOrCipher.equals("-c")) {
                            File cipherText = new File(pathToInputOrCipher);
                            byte[] cipher = Files.readAllBytes(cipherText.toPath());
                            if(output.equals("-o")) {
                                File outputFile = new File(pathToOutput);
                                crack.crack(cipher,message,outputFile);
                            }
                        }
                    }
                }
            }
        }


    }
}
