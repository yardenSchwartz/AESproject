import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Crack implements Cracking {

    @Override
    public void crack(byte[] cipherText, byte[] plaintText, File output) throws NoSuchAlgorithmException {

        //random k1
        byte[] k1 = new byte[16];
        SecureRandom.getInstanceStrong().nextBytes(k1);
        //random k2 - while k1!=k2
        byte[] k2 = new byte[16];
        SecureRandom.getInstanceStrong().nextBytes(k2);
        //check if not equals
        while (Arrays.equals(k1, k2)) {
            SecureRandom.getInstanceStrong().nextBytes(k2);
        }
        byte [] k3 = findK3(plaintText, cipherText, k1, k2);
        //func findk3() - check if all the keys -> from plaintext to ciphertext

        //write the key into output file

    }

    public byte[] findK3(byte[] plaintText, byte[] cipherText, byte[]firstKey, byte[]secondKey){
        byte[] thirdKey = new byte[16];
        //first iteration

        //second iteration

        return thirdKey;
    }

    public byte[] splitMatrix(int startIndex, int endIndex, byte[] message,int roundNum){
        byte[] splitedArray = new byte[16];

        for(int i=0; i<16; i++){
            splitedArray[i] = message[16*roundNum+i];
        }
        return splitedArray;
    }

    @Override
    public byte[] shiftColumns(byte[] message) {
        byte[] a = new byte[message.length];

        int numOfBlocks = message.length/16;
        int indexOfCell = 0;
        int round=0;
        for(int i=0; i<numOfBlocks; i++){

            for(int j=indexOfCell; j<indexOfCell+16; j++){
                byte[] tempMatrix = splitMatrix(j,j+16,message,round);




                //update current index of cell
                indexOfCell = j;
            }


        }

        return a;
    }

    @Override
    public byte[] roundKey(byte[] state, byte[] key) {
        byte[] a = new byte[16];

        return a;
    }
}
