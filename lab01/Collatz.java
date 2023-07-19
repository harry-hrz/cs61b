/** Class that prints the Collatz sequence starting from a given number.
 *  @author Harry Zhang
 */
public class Collatz {

    /** Returns the nextNumber in a Collatz sequence. */
    public static int nextNumber(int n) {
        /**
         * if n is odd, return 3n + 1
         * if n is even, return n/2
         */
        // TODO: Fill in this method.
        int result = 0;
        if (n % 2 == 0) {
            result = n / 2;
        }
        else{
            result = n * 3 + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");

        // Some starter code to test
        while (n != 1) {          
            n = nextNumber(n);          
            System.out.print(n + " ");
        }
        System.out.println();

    }
}

