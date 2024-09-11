/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Macyn Wilson
 * Last Updated: 09/10/2024
 */
package wilsonmj;

/**
 * Thrown to indicate that the die hasn't been rolled yet
 */
public class DieNotRolledException extends RuntimeException {

    public String getMessage() {
        return "Die hasn't been rolled";
    }

}
