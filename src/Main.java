import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

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

    public static void checkCrack() throws IOException, NoSuchAlgorithmException {
        cracking();
        encryption();

        checkEquals("C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\outputCipherLong",
                "C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\cipher_long");

    }
    public static void encryption() throws IOException {

        String[] args=new String[7];

        args[0]="-e";
        args[1]="-k";
        args[2]="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\outputKeys";
        args[3]="-i";
        args[4]="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\message_long";
        args[5]="-o";
        args[6]="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\outputCipherLong";

        EncAES3 encAES3 = new EncAES3();

        File keysFile= new File(args[2]);
        byte[]keys = Files.readAllBytes(keysFile.toPath());

        File inputFile = new File(args[4]);
        byte[] input = Files.readAllBytes(inputFile.toPath());

        File outputFile = new File(args[6]);
        encAES3.EncOrDec(input, outputFile, keys);

    }

    public static void encryptionShort() throws IOException {

        String[] args=new String[7];

        args[0]="-e";
        args[1]="-k";
        args[2]="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\outputKeys";
        args[3]="-i";
        args[4]="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\message_short";
        args[5]="-o";
        args[6]="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\outputCipherShort";

        EncAES3 encAES3 = new EncAES3();

        File keysFile= new File(args[2]);
        byte[]keys = Files.readAllBytes(keysFile.toPath());

        File inputFile = new File(args[4]);
        byte[] input = Files.readAllBytes(inputFile.toPath());

        File outputFile = new File(args[6]);
        encAES3.EncOrDec(input, outputFile, keys);

    }

    public static void cracking() throws IOException, NoSuchAlgorithmException {

        String[] args=new String[7];

        args[0]="-b";
        args[1]="-m";
        args[2]="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\message_long";
        args[3]="-c";
        args[4]="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\cipher_long";
        args[5]="-o";
        args[6]="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\outputKeys";

        Crack crack = new Crack();
        File messageFile= new File(args[2]);
        byte[]message = Files.readAllBytes(messageFile.toPath());
        File cipherText = new File(args[4]);
        byte[] cipher = Files.readAllBytes(cipherText.toPath());
        File outputFile = new File(args[6]);
        crack.crack(cipher,message,outputFile);

    }

    public static void crackingShort() throws IOException, NoSuchAlgorithmException {

        String[] args=new String[7];

        args[0]="-b";
        args[1]="-m";
        args[2]="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\message_short";
        args[3]="-c";
        args[4]="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\cipher_short";
        args[5]="-o";
        args[6]="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\outputKeys";

        Crack crack = new Crack();
        File messageFile= new File(args[2]);
        byte[]message = Files.readAllBytes(messageFile.toPath());
        File cipherText = new File(args[4]);
        byte[] cipher = Files.readAllBytes(cipherText.toPath());
        File outputFile = new File(args[6]);
        crack.crack(cipher,message,outputFile);

    }

    public  static void  checkEquals(String path1,String path2) throws IOException {
        File file1 = new File(path1);
        byte[] first = Files.readAllBytes(file1.toPath());
        File file2 = new File(path2);
        byte[] second = Files.readAllBytes(file2.toPath());
        if(Arrays.equals(first,second))
            System.out.println("kololo");
        else
            System.out.println("bassosh");
    }

    public static void seperatedKeysToFiles() throws IOException {
        String pathToKeysOrMessage1="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\key_short";
        File keysFile1= new File(pathToKeysOrMessage1);
        byte[]keys1 = Files.readAllBytes(keysFile1.toPath());
        int numPfKeys=3;
        int numOfBytesEachKey=16;
        int index=0;
        byte[][]res=new byte[numPfKeys][16];
        for(int i=0; i<numPfKeys;i++){
            for(int j=0; j<numOfBytesEachKey; j++){
                res[i][j]=keys1[index++];
            }
        }
        String output1="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\output1";
        OutputStream os=new FileOutputStream(output1);
        os.write(res[0]);
        os.close();

        String output2="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\output2";
        OutputStream os1=new FileOutputStream(output2);
        os1.write(res[1]);
        os1.close();

        String output3="C:\\Users\\yarden\\Desktop\\Studies\\שנה ג\\סמסטר ב\\אבטחת מחשבים ורשתות\\עבודות\\עבודה 1\\AES3_test_files\\test files\\output3";
        OutputStream os3=new FileOutputStream(output3);
        os3.write(res[2]);
        os3.close();

    }
}
