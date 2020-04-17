import java.io.*;
import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Crack implements Cracking {

    @Override
    public void crack(byte[] cipherText, byte[] plaintText, File output) throws NoSuchAlgorithmException, IOException {

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

//        byte[] keys = new byte[128*3];

        //write the key into output file
        OutputStream os=new FileOutputStream(output);
        os.write(k1);
        os.write(k2);
        os.write(k3);
        os.close();
    }


//    public void insertKey(byte[] key, byte[] keys){
//        for(int i=0;i<keys.length;i++){
//            keys[i]=key[i];
//        }
//    }

    public byte[] findK3(byte[] plaintText, byte[] cipherText, byte[]firstKey, byte[]secondKey){

        byte[] thirdKey = new byte[16];

        byte[] merge = new byte[plaintText.length];
        int numOfCellsInBlock=16;
        byte[] tempMatrix  = new byte[numOfCellsInBlock];
        int numOfBlocks = plaintText.length/numOfCellsInBlock;

        /**For each block**/
        for(int i=0; i<numOfBlocks; i++){

            /**Fill tempMatrix with current block**/
            for(int j=0;j<numOfCellsInBlock ;j++){
                tempMatrix[j]=plaintText[j+numOfCellsInBlock*numOfBlocks];
            }

            /**Two rounds for each block (16 bytes)**/
            for(int j=0; j<2;i++){
                // shiftColumn(temp)
                GlobalFunction.shiftColumns(tempMatrix);
                // shiftColumn(temp) xor k1
                GlobalFunction.roundKey(tempMatrix,firstKey);
                // (shiftColumn(temp) xor k1) xor k2
                GlobalFunction.roundKey(tempMatrix,secondKey);
                // shiftColumn ((shiftColumn(temp) xor k1) xor k2)
                GlobalFunction.shiftColumns(tempMatrix);
                // cipherText xor block
                GlobalFunction.roundKey(tempMatrix,cipherText);
            }

            /**Insert the encrypt block to merge**/
            for(int j=0; j<tempMatrix.length; i++){
                merge[i+numOfCellsInBlock*numOfBlocks]=tempMatrix[i];
            }
        }
        return thirdKey;
    }

}
