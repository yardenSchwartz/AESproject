import java.io.*;

public class DecAES3  implements EncryptionOrDecryption{

    @Override
    public void EncOrDec(byte[] input, File outputFile, byte[] keys) throws IOException {
        int rounds=3;
        int numOfCellsInBlock=16;
        int currentMassage=0;
        int numOfMassages= input.length/16;
        byte [] res=new byte[input.length];
        byte [] tmpRes=new byte[16];
        byte [][] keysArray=GlobalFunction.getKeys(keys);
        /**For each massage**/
        while(numOfMassages-1>=currentMassage){
            /**Fill tempRes with current massage*/
            for(int i=0;i<numOfCellsInBlock ;i++){
                tmpRes[i]=input[i+numOfCellsInBlock*currentMassage];
            }
            /**Three rounds for each massage (16 bytes)**/
            for(int  i=rounds-1; i>=0;i++){
                decRound(tmpRes,keysArray[i]);
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

    private void decRound(byte[] tmpRes, byte[] key) {
        roundKey(tmpRes,key);
        shiftColumns(tmpRes);

    }

    /**
     * implement reverse shift column
     * @param input
     */
    @Override
    public void shiftColumns(byte[] input) {
        byte temp1=input[7];
        input[7]=input[6];
        input[6]=input[5];
        input[5]=input[4];
        input[4]=temp1;

        temp1=input[11];
        byte temp2=input[10];
        input[11]=input[9];
        input[10]=input[8];
        input[9]=temp2;
        input[8]=temp1;

        temp1=input[15];
        temp2=input[14];
        byte temp3=input[13];
        input[15]=input[12];
        input[12]=temp3;
        input[13]=temp2;
        input[14]=temp1;
    }

    @Override
    public void roundKey(byte[] array, byte[] key) {

    }
}
