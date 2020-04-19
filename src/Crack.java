import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Crack implements Cracking {

    @Override
    public void crack(byte[] cipherText, byte[] plaintText, File output) throws NoSuchAlgorithmException, IOException {

        /**random k2**/
        byte[] k2 = new byte[16];
        SecureRandom.getInstanceStrong().nextBytes(k2);

        /**create k1**/
        byte[] k1 = new byte[16];

        /**Copy k2 to k1**/
        for(int i=0; i<k1.length; i++){
            k1[i]=k2[i];
        }
        /**shiftColumn^-1(k1)**/
        GlobalFunction.invertedShiftColumns(k1);

        /**find third key**/
        byte [] k3 = findK3(plaintText, cipherText, k1, k2);

        byte[] keys = new byte[48];

        ByteArrayOutputStream byteArrayOs = new ByteArrayOutputStream();

        byteArrayOs.write(k1);
        byteArrayOs.write(k2);
        byteArrayOs.write(k3);

        keys = byteArrayOs.toByteArray();
        //write the key into output file
        OutputStream os=new FileOutputStream(output);
        os.write(keys);

        os.close();
    }


    public byte[] findK3(byte[] plaintText, byte[] cipherText, byte[]firstKey, byte[]secondKey){

        byte[] merge = new byte[plaintText.length];
        int numOfCellsInBlock=16;
        byte[] tempMatrix  = new byte[16];
        int numOfBlocks = plaintText.length/16;
        int currentBlock=0;
        /**For each block**/
        while(numOfBlocks-1>=currentBlock){
            /**Fill tempMatrix with current block**/
            for(int j=0;j<numOfCellsInBlock ;j++){
                tempMatrix[j]=plaintText[j+16*currentBlock];
            }
                /**shiftColumn^-1(tempMatrix)**/
                GlobalFunction.invertedShiftColumns(tempMatrix);

                /**Insert the encrypt block to merge**/
                for (int j = 0; j < tempMatrix.length; j++) {
                    merge[j + 16 * currentBlock] = tempMatrix[j];
                }

            GlobalFunction.roundKey(merge,cipherText);

            currentBlock++;
        }

//        GlobalFunction.roundKey(merge,cipherText);

        return merge;
    }



}
