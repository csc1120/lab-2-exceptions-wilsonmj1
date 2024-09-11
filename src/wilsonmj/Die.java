/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Macyn Wilson
 * Last Updated: 09/10/2024
 */
package wilsonmj;

import java.util.Random;

/**
 * represents a die with a certain number of sides
 */
public class Die {

    /**
     * maximum number of sides a die can have
     */
    public static final int MAX_SIDES = 100;
    /**
     * minimum number of sides a die can have
     */
    public static final int MIN_SIDES = 2;

    private final Random rand;
    private final int numSides;
    private int currentValue;

    /**
     * creates a die object with the given number of sides
     * @param numSides number of sides the die will have
     * @throws IllegalArgumentException the given number of sides is outside the allowed range
     */
    public Die(int numSides) throws IllegalArgumentException {
        if (numSides < MIN_SIDES || numSides > MAX_SIDES) {
            throw new IllegalArgumentException();
        }
        this.numSides = numSides;
        rand = new Random();
        currentValue = 0;
    }

    /**
     * rolls the die, randomizing the current number
     */
    public void roll() {
        currentValue = rand.nextInt(numSides) + 1;
    }

    /**
     * gets the value currently on the die and resets it to zero
     * @return value currently on the die
     * @throws DieNotRolledException the die has no value saved to it at the moment
     */
    public int getCurrentValue() throws DieNotRolledException{
        int value = currentValue;
        if (value == 0) {
            throw new DieNotRolledException();
        }
        currentValue = 0;
        return value;
    }

}