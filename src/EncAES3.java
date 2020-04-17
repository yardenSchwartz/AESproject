import java.io.*;

public class EncAES3 implements EncryptionOrDecryption {
    @Override
    public void EncOrDec(byte[] input, File outputFile, byte[] keys) throws IOException {
        int rounds =3;
        int numOfCellsInBlock=16;
        int numOfMassages= input.length/16;
        int currentMassage=0;
        byte [] res=new byte[input.length];
        byte [] tmpRes=new byte[16];
        byte [][] keysArray=getKeys(keys);
        /**For each massage**/
        while(numOfMassages-1!=currentMassage){
            /**Fill tempRes with current massage*/
            for(int i=0;i<numOfCellsInBlock ;i++){
                tmpRes[i]=input[i+numOfCellsInBlock*currentMassage];
            }
            /**Three rounds for each massage (16 bytes)**/
            for(int  i=0; i<rounds;i++){
                encRound(tmpRes,keysArray[0],keysArray[1],keysArray[2]);
            }
            /**Insert the encrypt massage to res**/
            for(int i=0; i<tmpRes.length; i++){
                res[i+numOfCellsInBlock*currentMassage]=tmpRes[i];
            }
            currentMassage++;
        }
        OutputStream os=new FileOutputStream(outputFile);
        os.write(res);
        os.close();

    }

    private byte[][] getKeys(byte[] keys) {
        int numPfKeys=3;
        int numOfBytesEachKey=16;
        int index=0;
        byte[][]res=new byte[numPfKeys][16];
        for(int i=0; i<numPfKeys;i++){
            for(int j=0; j<numOfBytesEachKey; j++){
                res[i][j]=keys[index++];
            }
        }
        return res;
    }

    private void encRound(byte[] input, byte[] key1, byte[] key2, byte[] key3) {
        shiftColumns(input);
        roundKey(input,key1);
        roundKey(input,key2);
        roundKey(input,key3);
    }

    @Override
    public void shiftColumns(byte[] input) {
        GlobalFunction.shiftColumns(input);
    }

    @Override
    public void roundKey(byte[] array, byte[] key) {
        GlobalFunction.roundKey(array,key);
    }
}
