/*
 * Name: Nate Browne
 * Date: 28 July 2017
 * File: IntervalIdentifier.java
 * Version: 2.6
 * This program identifies various types of intervals entered in by the user,
 * calculates them, prints it back, and plays the tones corresponding to it. It
 * plays them starting from middle C and going up.
 */


import java.util.*;
import org.jfugue.*;

/**
 * Main class for the program.
 */
public class IntervalIdentifier {

  /* Declaration of constants and instance variables */

  // Define the distances between notes as constants
  private static final int WHOLE_STEP = 2;
  private static final int HALF_STEP = 1;
  private static final int FIFTH = 7;
  private static final int BASE_NOTE = 5;
  private static final int OCTAVE = 8;
  private static final int OCTAVE_INTERVAL = 12;

  // User input grabbed by the scanner
  private static String qualityChoice;
  private static int noteChoice, intervalChoice;

  // Used to make the note go up the octave if needed
  private static boolean goUp;

  // Used when reporting results back to the user
  private static int originalInterval, octaveCounter, newVal;

  // Create the scanner
  private static Scanner scan = new Scanner(System.in);

  // Create the music player that will play the notes
  private static Player notePlayer = new Player();

  // Array of note names
  private static final String[] NOTES = {"C", "Db", "D", "Eb", "E", "F", "F#",
    "G", "Ab", "A", "Bb", "B"};


  /**
   * Main method. Allows user to select interval type, distance, and starting
   * note.
   */
  public static void main(String[] args) {

    System.out.println("\nWelcome to the IntervalIdentifier!!\n");

    // Start the loop
    while(true) {

      // Reset the boolean to false
      goUp = false;

      try {

        // First, grab the desired starting note
        System.out.println("Enter the starting note in first.");
        for(int i = 0; i < NOTES.length; i++) {

          System.out.println("Note option #" + i + ": " + NOTES[i]);
        }

        System.out.print("Please make a selection: ");

        // Mod by the length of the array to wrap back around
        noteChoice = scan.nextInt() % NOTES.length;

        // Next, grab the interval
        System.out.println("Enter in the desired interval to calculate");
        System.out.print("Intervals are entered with the corresponding number. "
          + "(5th = 5, unison = 1 or 0, 3rd = 3, etc): ");

        // Mod by the number of unique notes in a scale to keep the circular
        // nature of scales intact
        originalInterval = scan.nextInt();
        intervalChoice = originalInterval % OCTAVE;
        octaveCounter = originalInterval / OCTAVE;


        // Last, grab the quality of the interval
        System.out.print("Enter an interval quality: ((a)ugmented, (d)iminished"
          + ", (M)ajor, (m)inor): ");

        qualityChoice = scan.next();

        // Create the interval
        makeInterval(noteChoice, --intervalChoice, qualityChoice);

      // Handle user typing a string for a number
      } catch(InputMismatchException e) {

        // Close input going to the scanner
        scan.close();

        // Report error
        System.err.print("Caught InputMismatchException!!");
        System.err.println("Make sure to use NUMBERS to select note/interval!");

        // Exit the program with error
        System.err.println("Exiting...\n");
        System.exit(1);

      // Handle user typing EOF
      } catch(NoSuchElementException e) {

        // Close input going to the scanner
        scan.close();

        // Exit the program normally
        System.err.println("\nExiting...\n");
        System.exit(0);
      }
    }
  }

  /**
   * This one method delegates to the helper methods to create the desired
   * interval, then changes the quality and prints the results back to the user
   * along with playing the notes in succession.
   * @param note base note of interval grabbed from main.
   * @param interval desired interval to create grabbed from main.
   * @param quality desired quality of created interval
   */
  public static void makeInterval(int note, int interval, String quality) {

    // Used to correctly pitch the interval
    int newOctave = 0;

    // Used to correctly output results
    String created;

    // Figure out which interval to create
    switch(interval) {

      case -1:

        newVal = createUnison(note);
        break;
      case 0:

        newVal = createUnison(note);
        break;
      case 1:

        newVal = createSecond(note);
        break;
      case 2:

        newVal = createThird(note);
        break;
      case 3:

        newVal = createFourth(note);
        break;
      case 4:

        newVal = createFifth(note);
        break;
      case 5:

        newVal = createSixth(note);
        break;
      case 6:

        newVal = createSeventh(note);
        break;
    }

    // Figure out which quality to make the interval
    switch(quality) {

      // Augmented interval
      case "a":

        newVal = (newVal + HALF_STEP) % NOTES.length;

        created = "augmented";
        break;

      // Diminished interval
      case "d":

        newVal = (newVal - HALF_STEP) % NOTES.length;

        // Make sure the correct octave is played and handle the exception
        if(newVal == -1) {

          newVal = NOTES.length - 1;
          goUp = false;
        }

        created = "diminished";
        break;

      // Minor interval
      case "m":

        // Eliminate perfect intervals (intervals without minor variants)
        if(interval != 0 && interval != 3 && interval != 4) {

          newVal = (newVal - HALF_STEP) % NOTES.length;

          // Make sure the correct octave is played and handle the exception
          if(newVal == -1) {

            newVal = NOTES.length - 1;
            goUp = false;
          }
        }

        created = "minor";
        break;

      // Major interval
      case "M":

        created = "major";
        break;

      // Treat everything else as major as well
      default:

        created = "major";
        break;
    }

    // Report result to user
    System.out.println("\nFor the note " + NOTES[note] + ", the interval of an "
      + created + " " + originalInterval + " results in the note " +
      NOTES[newVal] + " " + octaveCounter + " octave(s) above the original " +
      "note.");

    // Play the interval back to the user
    System.out.println("This interval sounds like this: \n");
    notePlayer.play(NOTES[note] + BASE_NOTE);

    if(goUp) {

      newOctave = BASE_NOTE + 1;
    } else {

      newOctave = BASE_NOTE + octaveCounter;
    }

    notePlayer.play(NOTES[newVal] + newOctave);
  }

  /**
   * This method creates an interval of a unison above the given starting note.
   * @param starting note to use as the base
   * @return the base
   */
  public static int createUnison(int starting) {

    return starting;
  }
  /**
   * This method creates an interval of a 2nd above the given starting note. The
   * return value is modded by the size of the array to wrap back around it.
   * @param starting note to use as the base
   * @return the note a second above the base
   */
  public static int createSecond(int starting) {

    if(starting + WHOLE_STEP >= NOTES.length) {

      goUp = true;
    }

    return (starting + WHOLE_STEP) % NOTES.length;
  }

  /**
   * This method creates an interval of a 3rd above the given starting note. The
   * return value is modded by the size of the array to wrap back around it.
   * @param starting note to use as the base
   * @return the note a third above the base
   */
  public static int createThird(int starting) {

    if(starting + (WHOLE_STEP * WHOLE_STEP) >= NOTES.length) {

      goUp = true;
    }

    return (starting + (WHOLE_STEP * WHOLE_STEP)) % NOTES.length;
  }

  /**
   * This method creates an interval of a 4th above the given starting note. The
   * return value is modded by the size of the array to wrap back around it.
   * @param starting note to use as the base
   * @return the note a fourth above the base
   */
  public static int createFourth(int starting) {

    if(starting + (FIFTH - WHOLE_STEP) >= NOTES.length) {

      goUp = true;
    }

    return (starting + (FIFTH - WHOLE_STEP)) % NOTES.length;
  }

  /**
   * This method creates an interval of a 5th above the given starting note. The
   * return value is modded by the size of the array to wrap back around it.
   * @param starting note to use as the base
   * @return the note a fifth above the base
   */
  public static int createFifth(int starting) {

    if(starting + FIFTH >= NOTES.length) {

      goUp = true;
    }

    return (starting + FIFTH) % NOTES.length;
  }

  /**
   * This method creates an interval of a 6th above the given starting note. The
   * return value is modded by the size of the array to wrap back around it.
   * @param starting note to use as the base
   * @return the note a sixth above the base
   */
  public static int createSixth(int starting) {

    if(starting + (FIFTH + WHOLE_STEP) >= NOTES.length) {

      goUp = true;
    }

    return (starting + (FIFTH + WHOLE_STEP)) % NOTES.length;
  }

  /**
   * This method creates an interval of a 7th above the given starting note. The
   * return value is modded by the size of the array to wrap back around it.
   * @param starting note to use as the base
   * @return the note a seventh above the base
   */
  public static int createSeventh(int starting) {

    if(starting + (OCTAVE_INTERVAL - HALF_STEP) >= NOTES.length) {

      goUp = true;
    }

    return (starting + (OCTAVE_INTERVAL - HALF_STEP)) % NOTES.length;
  }
}
