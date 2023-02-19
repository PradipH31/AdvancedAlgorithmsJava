package org.pradip.algorithm.euclid;

/*
*
*Euclid Algorithm
* Calculates GCD(A,B)
*
*/

public class Euclid {
    /**
     * Implementation using recursion
     *      22/6 = 3 remainder 4
     *      6/4 = 1 remainder 2
     *      4/1 = 1 remainder 0
     * @param number
     * @param divisor
     * @return
     */
    public int gcd(int number, int divisor){
        int remaining = (number % divisor);

        if(remaining != 0) {
            return gcd(divisor,remaining);
        } else {
            return divisor;
        }
    }

    /**
     * Implementation without recursion
     *      22/6 = 3 remainder 4
     *      6/4 = 1 remainder 2
     *      4/1 = 1 remainder 0
     *
     *      number/temp = result rest of divisor
     *
     * @param number
     * @param divisor
     * @return
     */
    public int gcdNoRecursion(int number, int divisor){
        while(divisor != 0){
            int temp = divisor;
            divisor = number % divisor;
            number = temp;
        }
        return number;
    }
}
