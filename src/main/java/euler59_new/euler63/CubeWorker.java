package euler59_new.euler63;

import java.util.concurrent.Callable;

public class CubeWorker implements Callable<Integer> {
    private int startNumber;
    private int[] digits;


    public CubeWorker(int startNumber) {
        this.startNumber = startNumber;
    }

    @Override
    public Integer call() throws Exception {
        int cube = (int) Math.pow(startNumber, 3);
        int[] digits = getDigits(cube);
        
        return null;
    }

    public int numberOfCubes(int[]digits){
        int noCubes = 0;

        return noCubes;
    }
    
    private int[] getDigits(int number){
        String strNumber = Integer.toString(number);
        int[] digits = new int[strNumber.length()];

        for(int i = 0; i < strNumber.length(); i++){
            digits[i] = strNumber.charAt(i) - '0';
        }

        this.digits = digits;
        return digits;
    }

    public static void main(String[] args) throws Exception {
        CubeWorker worker = new CubeWorker(300);
        worker.call();
        for(int i : worker.digits){
            System.out.printf(i + " , ");
        }
    }
}
