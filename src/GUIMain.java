/*
 * Author: Nate Browne
 * Version: 1.0
 * Date: 30 Jun 2018
 * Last Edited: 1 Jul 2018
 * File: GUIMain.java
 * This file sets up the GUI for the Interval Identifier program
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 * This class is the main driver for the GUI version of the program
 */
//@SuppressWarnings("serial")
public class GUIMain extends JFrame {

  /* Declaration of instance variables and constants */
  private static final long serialVersionUID = 1L;
  private static final String TEXT1 = "First, enter a starting note as a number"
    + " (C = 0 through B = 11)";
  private static final String TEXT2 = "Next, enter in a desired starting octave"
    + ". Middle C is \"5\".";
  private static final String TEXT3 = "Third, enter the desired interval to " +
    "calculate. (unison = 1, 5th = 5, etc)";
  private static final String TEXT4 = "Last, enter an interval quality. " +
    "((a)ugmented, (d)iminished, (M)ajor, (m)inor.";
  private static final String ERR_STR = "Your input was invalid. Re-enter?";
  private static final int NUM_FIELDS = 4; // Number of JTextFields created
  private static final Color EMERALD = new Color(0, 89, 11);

  private IntervalIdentifier id; // to create intervals
  private JTextField step1, step2, step3, step4; // For user input
  private JLabel text1, text2, text3, text4, titleText; // To display info
  private JTextField[] fields = new JTextField[NUM_FIELDS];
  private JPanel top1, top2, mid1, mid2, bot, mainMid, mainBot;
  private JButton but1, but2, but3, but4, exitButton; // For user interaction
  private int noteChoice, intervalChoice; // Entered by the user
  private String qualityChoice;

  /**
   * Constructor for the GUI. Sets up the components used.
   */
  public GUIMain() {

    // First, create the IntervalIdentifier
    this.id = new IntervalIdentifier();

    // Next, create all of the panels and set background colors
    // to black
    this.top1 = new JPanel(new FlowLayout());
    this.mid1 = new JPanel(new FlowLayout());
    this.mid2 = new JPanel(new FlowLayout());
    this.top2 = new JPanel(new FlowLayout());
    this.bot = new JPanel(new FlowLayout());
    this.mainMid = new JPanel(new BorderLayout());
    this.mainBot = new JPanel(new BorderLayout());
    this.top1.setBackground(Color.BLACK);
    this.top2.setBackground(Color.BLACK);
    this.mid1.setBackground(Color.BLACK);
    this.mid2.setBackground(Color.BLACK);
    this.bot.setBackground(Color.BLACK);
    this.mainMid.setBackground(Color.BLACK);
    this.mainBot.setBackground(Color.BLACK);

    // Now, create all of the labels and set the text colors
    this.text1 = new JLabel(TEXT1, JLabel.CENTER);
    this.text2 = new JLabel(TEXT2, JLabel.CENTER);
    this.text3 = new JLabel(TEXT3, JLabel.CENTER);
    this.text4 = new JLabel(TEXT4, JLabel.CENTER);
    this.titleText = new JLabel("Interval Identifier", JLabel.CENTER);
    this.titleText.setForeground(EMERALD);
    this.text1.setForeground(Color.white);
    this.text2.setForeground(Color.white);
    this.text3.setForeground(Color.white);
    this.text4.setForeground(Color.white);

    // Next up, the buttons
    this.but1 = new JButton("Next Step");
    this.but2 = new JButton("Next Step");
    this.but3 = new JButton("Next Step");
    this.but4 = new JButton("Go!");
    this.exitButton = new JButton("Exit");

    // Disable buttons 2, 3, and 4 for now
    this.but2.setEnabled(false);
    this.but3.setEnabled(false);
    this.but4.setEnabled(false);

    // Next, the TextFields
    this.step1 = new JTextField(20);
    this.step2 = new JTextField(20);
    this.step3 = new JTextField(20);
    this.step4 = new JTextField(20);

    /*
     * Add ActionListeners to the corresponding components
     * The ActionListeners are defined below using a functional programming
     * style
     */
    this.but1.addActionListener(al1);
    this.step1.addActionListener(al1);
    this.but2.addActionListener(al2);
    this.step2.addActionListener(al2);
    this.but3.addActionListener(al3);
    this.step3.addActionListener(al3);
    this.but4.addActionListener(al4);
    this.step4.addActionListener(al4);
    this.exitButton.addActionListener(exitB);

    // initialize the fields array
    fields[0] = this.step1;
    fields[1] = this.step2;
    fields[2] = this.step3;
    fields[3] = this.step4;

    // First, the top panel
    this.top1.add(this.titleText);

    // Next up, the middle panel
    this.top2.add(this.text1);
    this.top2.add(this.step1);
    this.top2.add(this.but1);
    this.mid1.add(this.text2);
    this.mid1.add(this.step2);
    this.mid1.add(this.but2);
    this.mid2.add(this.text3);
    this.mid2.add(this.step3);
    this.mid2.add(this.but3);
    this.mainMid.add(this.top2, BorderLayout.NORTH);
    this.mainMid.add(this.mid1, BorderLayout.CENTER);
    this.mainMid.add(this.mid2, BorderLayout.SOUTH);

    // Bottom panel
    this.bot.add(this.text4);
    this.bot.add(this.step4);
    this.bot.add(this.but4);
    this.mainBot.add(this.bot, BorderLayout.NORTH);
    this.mainBot.add(this.exitButton, BorderLayout.CENTER);

    // Add the panels to the canvas
    this.add(this.top1, BorderLayout.NORTH);
    this.add(this.mainMid, BorderLayout.CENTER);
    this.add(this.mainBot, BorderLayout.SOUTH);
  }

  /**
   * This first ActionListener is for the first button/JTextField.
   * It handles the user pressing the button/hitting <Enter> in the field.
   */
  ActionListener al1 = ae -> {
    boolean option = true;
    try {

      // Set note choice based on the passed in string
      noteChoice = id.grabNote(step1.getText());
    } catch (NumberFormatException e) {
      // Report the error then allow user to decide
      int cont = JOptionPane.showConfirmDialog(null, ERR_STR);
      option = false;

      switch(cont) {
        case JOptionPane.YES_OPTION:
          step1.setText("");
          break;
        default:
          endProg(true);
      }
    }

    // Enable next set of buttons
    if(option) {

      but1.setEnabled(false);
      but2.setEnabled(true);
      step2.requestFocus();
    }
  };

  /**
   * This ActionListener handles the second button/JTextField.
   * It handles the user pressing the button/hitting <Enter> in the field
   */
  ActionListener al2 = ae -> {

    boolean option = true;
    try {

      id.grabOctave(step2.getText());
    } catch (NumberFormatException e) {
      // Report the error then allow user to decide
      int cont = JOptionPane.showConfirmDialog(null, ERR_STR);
      option = false;

      switch(cont) {
        case JOptionPane.YES_OPTION:
          step2.setText("");
          break;
        default:
          endProg(true);
      }
    }

    // Enable next set of buttons
    if(option) {

      but2.setEnabled(false);
      but3.setEnabled(true);
      step3.requestFocus();
    }
  };

  /**
   * This ActionListener handles the third button/JTextField.
   * It handles the user pressing the button/hitting <Enter> in the field
   */
  ActionListener al3 = ae -> {

    boolean option = true;
    try {

      intervalChoice = id.grabInterval(step3.getText());
    } catch (NumberFormatException e) {
      // Report the error then allow user to decide
      int cont = JOptionPane.showConfirmDialog(null, ERR_STR);
      option = false;

      switch(cont) {
        case JOptionPane.YES_OPTION:
          step3.setText("");
          break;
        default:
          endProg(true);
      }
    }

    // Enable next set of buttons
    if(option) {

      but3.setEnabled(false);
      but4.setEnabled(true);
      step4.requestFocus();
    }
  };

  /**
   * This ActionListener handles the fourth button/JTextField.
   * It handles the user pressing the button/hitting <Enter> in the field
   */
  ActionListener al4 = ae -> {

    String toPrint;

    // Get user's choice of interval quality
    qualityChoice = step4.getText();

    // Create message to display
    toPrint = id.makeInterval(noteChoice, --intervalChoice, qualityChoice, false);

    // Display the message
    JOptionPane.showMessageDialog(null, toPrint);

    // Reset the GUI
    but4.setEnabled(false);
    but1.setEnabled(true);
    clearFields();
    step1.requestFocus();
  };

  /**
   * This small ActionListener handles the exit button being pressed.
   */
  ActionListener exitB = ae -> {
    endProg(false);
  };

  /**
   * This helper method loops through the JTextFields array and sets all of
   * the fields to the empty string, effectively clearing them
   */
  private void clearFields() {
    for(JTextField field : fields) {
      field.setText("");
    }
  }

  /**
   * This function abstracts the end program tasks away so that other methods
   * can call it if needed. It closes the open scanner before exiting.
   *
   * @param error boolean for if the program should exit with code 1 or code 0
   */
  private void endProg(boolean error) {
    id.closeScanner();
    if(error) {
      System.exit(1);
    } else {
      System.exit(0);
    }
  }

  /**
   * Main entry point into the program.
   *
   * @param args String array of command line arguments
   */
  public static void main(String[] args) {
    GUIMain gg = new GUIMain();
    gg.setLocation(30, 50);
    gg.setSize(850, 800);
    gg.setVisible(true);
  }
}
