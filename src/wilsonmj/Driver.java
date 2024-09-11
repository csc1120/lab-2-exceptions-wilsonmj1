/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Macyn Wilson
 * Last Updated: 09/11/2024
 */
package wilsonmj;

import java.util.Scanner;

/**
 * simulates rolling a number of dice with a certain number of sides and
 * provides data on the results
 */
public class Driver {

    public static void main(String[] args) {
        int numDice;
        int numSides;
        int numRolls;
        Die[] dice;
        int[] results;
        Scanner scanner = new Scanner(System.in);

        boolean isValid = false;
        while (!isValid) {
            //get user inputs
            int[] userInput = getInput(scanner);
            numDice = userInput[0];
            numSides = userInput[1];
            numRolls = userInput[2];

            //create and roll dice, then report results
            try {
                dice = createDice(numDice, numSides);
                results = rollDice(dice, numRolls, numSides);
                report(results, numDice);
                isValid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Bad die creation: Illegal number of sides: " + numSides);
            }
        }
    }

    /**
     * asks user to input three integers in a specific format
     *
     * @param scanner scanner object to use for input
     * @return array containing the three values entered
     */
    public static int[] getInput(Scanner scanner) {
        String intro =
                """
                        Please enter the number of dice to roll, how many sides the dice have,
                        and how many rolls to complete, separating the values by a space.
                        Example: "2 6 1000"
                        
                        Enter configuration:""";
        int[] output = new int[3];

        boolean isValid = false;
        while (!isValid) {
            try {
                //print intro message and get response
                System.out.println(intro);
                String input = scanner.nextLine();

                //split response and parse to ints
                String[] tokens = input.split(" ");
                for (int i = 0; i < output.length; i++) {
                    output[i] = Integer.parseInt(tokens[i]);
                }
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: All values must be whole numbers.");
            } catch (ArrayIndexOutOfBoundsException e) {
                //count the number of inputs that were received
                int inputs = 0;
                for (int i : output) {
                    if (i != 0) {
                        inputs++;
                    }
                }
                System.out.println("Invalid input: Expected 3 values but only received " + inputs);
            }
        }
        return output;
    }

    /**
     * creates a specified number of dice with the same number of sides
     * @param numDice number of dice to be created
     * @param numSides how many sides each die will have
     * @return array containing the specified number of dice
     */
    public static Die[] createDice(int numDice, int numSides) {
        Die[] dice = new Die[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }

    /**
     * rolls an array of dice a specified number of times and records the results
     * @param dice array of Die objects to be rolled
     * @param numRolls how many times to roll the dice
     * @param numSides how many sides the dice have
     * @return array containing the number of times each result was rolled
     */
    public static int[] rollDice(Die[] dice, int numRolls, int numSides) {
        int[] output = new int[dice.length * numSides];

        for (int i = 0; i < numRolls; i++) {
            int sum = 0;
            for (Die d : dice) {
                d.roll();
                sum += d.getCurrentValue();
            }
            output[sum-1]++;
        }
        return output;
    }

    /**
     * prints the given results with the correct formatting
     * @param results data to be formatted
     * @param numDice number of dice that were rolled
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static void report(int[] results, int numDice) {
        int centerOffset = 3;
        int maxValue = findMax(results);
        int highRoll = results.length;
        int digits1 = countDigits(highRoll);
        int digits2 = countDigits(maxValue);

        //format for the output
        String formatting = "%1$-" + digits1 + "d:%2$-" + (digits2 + centerOffset) + "d%3$-1s";

        for (int i = numDice-1; i < results.length; i++) {
            int numStars = Math.round(10 * ((float)results[i] / maxValue));
            String output = String.format(formatting, i+1, results[i], "*".repeat(numStars));
            System.out.println(output);
        }
    }

    /**
     * count the number of digits in an integer
     * @param num the number to have digits counted
     * @return number of digits
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static int countDigits(int num) {
        int count = 0;
        while (num != 0) {
            num /= 10;
            count++;
        }
        return count;
    }

    /**
     * find the highest value in an integer array
     * @param data array to be searched
     * @return highest value within the array;
     */
    public static int findMax(int[] data) {
        int max = Integer.MIN_VALUE;
        for (int i : data) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}