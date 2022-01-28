// Antonio Artini
// R00147013
package Assign2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class RobotMoving {
    Scanner scanner = new Scanner(System.in);

    public static double roundDown(double val, int decimalPlaces) {
        if (decimalPlaces < 0)
            throw new IllegalArgumentException();

        BigDecimal bigD = BigDecimal.valueOf(val);
        bigD = bigD.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bigD.doubleValue();
    }

    public static double moveRobot(int n, double[] cost) {
        try {
            double[][] room = new double[n][n];
            double right = cost[0];
            double down = cost[1];
            double diag = cost[2];
            double totalCost = 0;
            room[0][0] = 0;
            int steps = 0;
            // room[0][1] = right;
            // room[1][0] = down;
            // room[1][1] = diag;

            for (int i = 1; i < n; i++) {
                // Get the value stored to the left and add the cost of a right movement and set
                // the current space to this
                room[0][i] = roundDown(room[0][i - 1] + right, 2);
                for (int j = 1; j <= i; j++) {
                    // Get the value stored to the top and add the cost of a down movement and set
                    // the current space to this
                    room[j][0] = roundDown(room[j - 1][0] + down, 2);
                    for (int k = 1; k < n; k++) {
                        // Compare the value stored at the left plus a right movement and the value
                        // stored at the top plus a down movement and take the smaller of the 2

                        // Compare the value of the top left value plus the cost of a diag movement and
                        // the smaller of the two values from above
                        room[j][k] = roundDown(Math.min(room[j - 1][k - 1] + diag,
                                Math.min(room[j][k - 1] + right, room[j - 1][k] + down)), 2);

                    }
                }
                // This represents the sum total of optimal movement costs thus far
                totalCost = room[i][n - 1];
            }
            // counter keeps track of the spaces we weish to mark as x to show the path
            // taken by the bot
            int counter = 0;

            if (totalCost == roundDown((right * (n - 1)) + (down * (n - 1)), 2)) {
                // If the total cost is equal to the sum total of n-1 right movements and n-1
                // down movements then the steps is set to equal n - 1 right movements plus n -
                // 1 down movements
                steps = 2 * (n - 1);
                for (int i = 0; i < room.length; i++) {
                    // i represents each row
                    for (int j = 0; j < room.length; j++) {
                        // j represents each
                        // if column positon j is equal to our counter position or our counter position
                        // +1 we mark the spot as travelled on
                        if (j == counter || j == counter + 1) {
                            System.out.print(" x |");
                        } else {
                            // else we mark the space as o meaning it is untouched
                            System.out.print(" o |");
                        }
                    }
                    counter++;
                    System.out.println();

                }
            } else {
                steps = n - 1;
                for (int i = 0; i < room.length; i++) {
                    for (int j = 0; j < room.length; j++) {
                        if (j == counter) {
                            System.out.print(" x |");
                        } else {
                            System.out.print(" o |");
                        }
                    }
                    counter++;
                    System.out.println();
                }
            }
            System.out.println();
            for (int i = 0; i < room.length; i++) {
                for (int j = 0; j < room.length; j++) {
                    System.out.print("|" + room[i][j]);
                }
                System.out.println();
            }

            System.out.println("Steps: " + steps);
            return totalCost;
        } catch (Exception e) {
            System.err.println(e);
            return -99;
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double[] cost = new double[] { 1.1, 1.3, 2.5 };
        System.out.println("Please enter a number: ");
        int inp = scan.nextInt();

        System.out.println("Total cost = " + moveRobot(inp, cost));

        scan.close();

    }
}
