/*
 * Name: Nate Browne
 * Date: 30 June 2017
 * File: IntervalIdentifier.java
 * Version: 3.5
 * This program identifies various types of intervals entered in by the user,
 * calculates them, prints it back, and plays the tones corresponding to it. It
 * plays them starting from middle C and going up.
 */

import java.util.*;
import org.jfugue.*;

/**
 * This class is used in the Main method for the Driver.
 * It contains the functionality to parse user input, calculate the correct
 * notes, report to the user, and play the notes.  
 */
public class IntervalIdentifier {

  /* Declaration of constants and instance variables */

  // Define the distances between notes as constants
  private static final int WHOLE_STEP = 2;
  private static final int HALF_STEP = 1;
  private static final int FIFTH = 7;
  private static final int OCTAVE = 8;
  private static final int OCTAVE_INTERVAL = 12;
  private static final String MSG = "\nFor the note %s, the interval of a %s %d"
    + " results in the note %s %d octave(s) above the original note.\n";

  // music player that will play the notes
  private Player notePlayer; 

  // Scanner used to read in input
  private Scanner scan;

  // Used to make the note go up the octave if needed
  private boolean goUp;

  // Used when reporting results back to the user
  private int originalInterval, octaveCounter, newVal, baseNote;

  // Array of note names
  private static final String[] NOTES = {"C", "Db", "D", "Eb", "E", "F", "F#",
    "G", "Ab", "A", "Bb", "B"};

  /**
   * This constructor creates the note player instance used. It instantiates
   * a notePlayer object as well as a scanner
   */
  public IntervalIdentifier() {

    this.notePlayer = new Player();
    this.scan = new Scanner(System.in);
  }

  /**
   * This method grabs the user input for the desired note to use as a base
   * 
   * @throws NoSuchElementException when program is supposed to terminate (EOF)
   * @throws InputMismatchException when a mismatching type is given
   * @return the index in the array corresponding to the chosen note
   */
  public int grabNote() throws NoSuchElementException, InputMismatchException {

    // First, grab the desired starting note
    System.out.println("Enter the starting note in first.");
    for(int i = 0; i < NOTES.length; i++) {

      System.out.println("Note option #" + i + ": " + NOTES[i]);
    }

    System.out.print("Please make a selection: ");

    // Mod by the length of the array to wrap back around
    return scan.nextInt() % NOTES.length;
  }

  /**
   * This method grabs the octave that the user wants to put their base in
   * 
   * @throws NoSuchElementException when program is supposed to terminate (EOF)
   * @throws InputMismatchException when a mismatching type is given
   */
  public void grabOctave() throws NoSuchElementException, InputMismatchException {

    System.out.println("Please enter in a desired starting octave.");
    System.out.print("Middle C is \"5\": ");
    this.baseNote = scan.nextInt();
  }


  /**
   * This method grabs the interval the user wants to hear
   * 
   * @throws NoSuchElementException when program is supposed to terminate (EOF)
   * @throws InputMismatchException when a mismatching type is given
   * @return the chosen interval
   */
  public int grabInterval() throws NoSuchElementException, InputMismatchException {

    // Next, grab the interval
    System.out.println("Enter in the desired interval to calculate.");
    System.out.print("Intervals are entered with the corresponding number. "
      + "(5th = 5, unison = 1, 3rd = 3, etc): ");

    // Mod by the number of unique notes in a scale to keep the circular
    // nature of scales intact
    this.originalInterval = scan.nextInt();
    int intervalChoice = this.originalInterval % (OCTAVE - 1);

    // Used to make sure user that enters in a 7th gets a 7th
    if(intervalChoice == 0) {

      intervalChoice = 7;
    }

    // Used later to report back the octave of the created note in the
    // interval
    this.octaveCounter = this.originalInterval / OCTAVE;
    return intervalChoice;
  }

  /**
   * This method grabs the desired interval quality from the four choices
   * 
   * @throws NoSuchElementException when program is supposed to terminate (EOF)
   * @return a string corresponding to the desired quality
   */
  public String grabQuality() throws NoSuchElementException {

    // Last, grab the quality of the interval
    System.out.print("Enter an interval quality: ((a)ugmented, (d)iminished"
      + ", (M)ajor, (m)inor): ");

    return scan.next();
  }

  /**
   * This simple method closes the scanner. Called from main
   */
  public void closeScanner() {
    this.scan.close();
  }

  /**
   * This helper method parses the quality and returns the corresponding string
   * 
   * @param interval the passed in interval to use
   * @param quality the passed in string for quality
   * @return the corrected string to use
   */
  public String parseQuality(int note, int interval, String quality) {

    String created;

    // Figure out which quality to make the interval
    switch(quality) {

      // Augmented interval
      case "a":

        newVal = (newVal + HALF_STEP) % NOTES.length;

        // Special case of user entering augmented 7 (which is really an octave)
        if(newVal == note) {

          octaveCounter++;
        }

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
        if(interval != 0 && interval != 3 && interval != 4 && interval != -1) {

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

    return created;
  }

  /**
   * This small helper method parses the given interval int passed in
   * 
   * @param interval the interval to parse
   */
  public void parseInterval(int note, int interval) {

    // Figure out which interval to create
    switch(interval) {

      case -1:
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
  }

  /**
   * This one method delegates to the helper methods to create the desired
   * interval, then changes the quality and prints the results back to the user
   * along with playing the notes in succession.
   * 
   * @param note base note of interval grabbed from main.
   * @param interval desired interval to create grabbed from main.
   * @param quality desired quality of created interval
   */
  public void makeInterval(int note, int interval, String quality) {

    // Reset the boolean each time through this loop
    this.goUp = false;

    // Used to correctly pitch the interval
    int newOctave = 0;

    // Used to correctly output results
    this.parseInterval(note, interval);
    String created = this.parseQuality(note, interval, quality);

    // Report result to user
    System.out.printf(MSG, NOTES[note], created, originalInterval, NOTES[newVal]
      , octaveCounter);

    // Play the interval back to the user
    System.out.println("This interval sounds like this: \n");
    notePlayer.play(NOTES[note] + baseNote);

    // Set the value of newOctave, if needed
    newOctave = (goUp) ? baseNote + 1 : baseNote + octaveCounter;
    notePlayer.play(NOTES[newVal] + newOctave);
  }

  /**
   * This method creates an interval of a unison above the given starting note.
   * 
   * @param starting note to use as the base
   * @return the base
   */
  public int createUnison(int starting) {

    return starting;
  }
  /**
   * This method creates an interval of a 2nd above the given starting note. The
   * return value is modded by the size of the array to wrap back around it.
   * 
   * @param starting note to use as the base
   * @return the note a second above the base
   */
  public int createSecond(int starting) {

    if(starting + WHOLE_STEP >= NOTES.length) {

      goUp = true;
    }

    return (starting + WHOLE_STEP) % NOTES.length;
  }

  /**
   * This method creates an interval of a 3rd above the given starting note. The
   * return value is modded by the size of the array to wrap back around it.
   * 
   * @param starting note to use as the base
   * @return the note a third above the base
   */
  public int createThird(int starting) {

    if(starting + (WHOLE_STEP * WHOLE_STEP) >= NOTES.length) {

      goUp = true;
    }

    return (starting + (WHOLE_STEP * WHOLE_STEP)) % NOTES.length;
  }

  /**
   * This method creates an interval of a 4th above the given starting note. The
   * return value is modded by the size of the array to wrap back around it.
   * 
   * @param starting note to use as the base
   * @return the note a fourth above the base
   */
  public int createFourth(int starting) {

    if(starting + (FIFTH - WHOLE_STEP) >= NOTES.length) {

      goUp = true;
    }

    return (starting + (FIFTH - WHOLE_STEP)) % NOTES.length;
  }

  /**
   * This method creates an interval of a 5th above the given starting note. The
   * return value is modded by the size of the array to wrap back around it.
   * 
   * @param starting note to use as the base
   * @return the note a fifth above the base
   */
  public int createFifth(int starting) {

    if(starting + FIFTH >= NOTES.length) {

      goUp = true;
    }

    return (starting + FIFTH) % NOTES.length;
  }

  /**
   * This method creates an interval of a 6th above the given starting note. The
   * return value is modded by the size of the array to wrap back around it.
   * 
   * @param starting note to use as the base
   * @return the note a sixth above the base
   */
  public int createSixth(int starting) {

    if(starting + (FIFTH + WHOLE_STEP) >= NOTES.length) {

      goUp = true;
    }

    return (starting + (FIFTH + WHOLE_STEP)) % NOTES.length;
  }

  /**
   * This method creates an interval of a 7th above the given starting note. The
   * return value is modded by the size of the array to wrap back around it.
   * 
   * @param starting note to use as the base
   * @return the note a seventh above the base
   */
  public int createSeventh(int starting) {

    if(starting + (OCTAVE_INTERVAL - HALF_STEP) >= NOTES.length) {

      goUp = true;
    }

    return (starting + (OCTAVE_INTERVAL - HALF_STEP)) % NOTES.length;
  }
}