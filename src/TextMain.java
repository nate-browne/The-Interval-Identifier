/*
 * Name: Nate Browne
 * Date: 28 July 2017
 * Last Edited: 17 August 2017
 * File: TextMain.java
 * Version: 3.5
 * This is the main method of the program. It uses the
 * IntervalIdentifier class to identify typed input
 */

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/**
 * Main driver for the terminal based edition of the program.
 */
public class TextMain {

  /**
   * Main method. Allows user to select interval type, distance, and starting
   * note.
   *
   * @param args String array of command line arguments passed in
   */
  public static void main(String[] args) {

    System.out.println("\nWelcome to the IntervalIdentifier!!\n");

    // Create the interval identifier object
    IntervalIdentifier id = new IntervalIdentifier();

    // Start the loop
    while(true) {

      try {

        // Grab values needed
        int noteChoice = id.grabNote();
        id.grabOctave();
        int intervalChoice = id.grabInterval();
        String qualityChoice = id.grabQuality();

        // Create the interval
        id.makeInterval(noteChoice, --intervalChoice, qualityChoice, true);

      // Handle user typing a string for a number
      } catch(InputMismatchException e) {

        // Close input going to the scanner
        id.closeScanner();

        // Report error
        System.err.print("Caught InputMismatchException!! ");
        System.err.println("Make sure to use NUMBERS to select note/interval!");

        // Exit the program with error
        System.err.println("Exiting...\n");
        System.exit(1);
      // Handle user typing EOF
      } catch(NoSuchElementException e) {

        // Close input going to the scanner
        id.closeScanner();

        // Exit the program normally
        System.err.println("\nExiting...\n");
        System.exit(0);
      }
    }
  }
}
