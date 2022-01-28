// Antonio Artini
// R00147013
package Assign2;

import java.util.Scanner;
import java.util.ArrayList;

public class PaperRollCuttingBottomUp {

    public static double cutRoll(int n, double[] price) {
        try {
            // The table of values containing the most profitable revenues for lengths of
            // roll
            double[] val = new double[n + 1];
            // An array containing the most profitable cut combinations for our
            // rolls.
            ArrayList[] rolesCut = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                rolesCut[i] = new ArrayList<Integer>();
            }
            // For the length of our paper
            for (int i = 1; i <= n; i++) {
                // We set a maxValue variable which keeps track of the most profitable value
                // found in the loop when making a cut of size j
                // The max value is initialised to the lowest possible value as a starting point
                // for the second for loop
                double maxVal = Double.MIN_VALUE;
                // For the total possible combination of cuts j in
                for (int j = 1; j <= Math.min(i, price.length - 1); j++) {
                    // We compare the maxValue vairiable against the sum total of
                    // a known price at j in the prices array and a solved price of cuts in the val
                    // table at i-j
                    if (maxVal < price[j] + val[i - j]) {
                        // If the ArrayList at i is empty we populate it with the cut, j
                        // This is to show the first cut that can be made
                        if (rolesCut[i].isEmpty()) {
                            rolesCut[i].add(j);
                        } // If the ArrayList isn't empty, we set the value at 0, as in the first cut we
                          // tested against, to the new largest cut, j
                        else {
                            rolesCut[i].set(0, j);
                        }
                    }
                    // maxVal is then set to be equal to either maxVal or the sum total of price at
                    // j and val at i - j, whichever is greater
                    // This is to make sure that we accept maxVal as only the most profitable cut or
                    // cuts at a particular lenght or paper
                    maxVal = Math.max(maxVal, price[j] + val[i - j]);

                }
                // We then update the val table at the end of the first for loop to store the
                // optimum revenue for paper of length i
                val[i] = maxVal;
                // To keep track of the cuts that can be made to max revenue for paper of length
                // n, we store the value at rolesCut[i - rolesCut[i]]
                // If we use the example of i = 9
                // rolesCut[9] = an Arraylist containing the value 3
                // We add to the ArrayList at rolesCut[9] the values stored at rolesCut[9 -
                // rolesCut[9].get(0)]
                // ==> [9 - 3]
                // This then gets us the value stored at rolesCut[6] ==> ArrayList containig the
                // values [3,3], and adds the cuts to the ArrayList at rolesCut[9]
                rolesCut[i].addAll(rolesCut[i - (int) rolesCut[i].get(0)]);
            }
            System.out.println(rolesCut[n]);
            return val[n];
        } catch (Exception e) {
            System.err.println(e);
            return -99;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a number :");
        int input = scanner.nextInt();

        double[] prices = new double[] { 0, 1.20, 3, 5.80, 0, 10.10 };
        System.out.println(cutRoll(input, prices));
        scanner.close();

    }
}