public class GlobalFunction {
    /***
     * get an array in size 16 and make shift columns
     * @param input
     */
    public static void shiftColumns(byte[] input) {
        byte temp1=input[4];
        input[4]=input[5];
        input[5]=input[6];
        input[6]=input[7];
        input[7]=temp1;

        temp1=input[8];
        byte temp2=input[9];
        input[8]=input[10];
        input[9]=input[11];
        input[10]=temp1;
        input[11]=temp2;

        temp1=input[12];
        temp2=input[13];
        byte temp3=input[14];
        input[12]=input[15];
        input[13]=temp1;
        input[14]=temp2;
        input[15]=temp3;
    }

    public static void invertedShiftColumns(byte[] input) {
        byte temp1=input[7];
        input[7]=input[6];
        input[6]=input[5];
        input[5]=input[4];
        input[4]=temp1;

        temp1=input[11];
        byte temp2=input[10];
        input[11]=input[9];
        input[10]=input[8];
        input[9]=temp1;
        input[8]=temp2;

        temp1=input[15];
        temp2=input[14];
        byte temp3=input[13];
        input[15]=input[12];
        input[12]=temp3;
        input[13]=temp2;
        input[14]=temp1;
    }

    /**
     * get an array in size 16 and make round key
     * @param array
     * @param key
     */
    public static void roundKey(byte[] array, byte[] key) {
        for(int i=0; i<array.length; i++){
            array[i]=((byte)(0xff &((int)array[i])^((int)key[i])));
        }
    }

    /**
     * get long array of keys (3*16 length) and split it to 3 keys
     * @param keys
     * @return
     */
    public static byte[][] getKeys(byte[] keys) {
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

}
