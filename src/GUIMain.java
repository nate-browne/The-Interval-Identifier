/*
 * Author: Nate Browne
 * Version: 1.0
 * Date: 30 Jun 2018
 * Last Edited: 30 Jun 2018
 * File: GUIMain.java
 * This file sets up the GUI for the Interval Identifier program
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

//@SuppressWarnings("serial")
public class GUIMain extends JFrame {

  /* Declaration of instance variables and constants */
  private static final long serialVersionUID = 1L;
  private static final String TEXT1 = "First, enter a starting note as a number"
    + " (C = 1 through B = 12)";
  private static final String TEXT2 = "Next, enter in a desired starting octave"
    + ". Middle C is \"5\".";
  private static final String TEXT3 = "Third, enter the desired interval to " +
    "calculate. (unison = 1, 5th = 5, etc)";
  private static final String TEXT4 = "Last, enter an interval quality. " +
    "((a)ugmented, (d)iminished, (M)ajor, (m)inor.";

  private IntervalIdentifier ID; // Used throughout the program
  private JTextField step1, step2, step3, step4;
  private JLabel text1, text2, text3, text4, titleText;
  private JPanel top1, top2, mid1, mid2, bot, mainTop, mainMid, mainBot;
  private JButton but1, but2, but3, but4, exitButton;

  /**
   * Constructor for the GUI. Sets up the fields of the GUI
   */
  public GUIMain() {

    // First, create the IntervalIdentifier
    this.ID = new IntervalIdentifier();

    // Next, create all of the panels
    this.top1 = new JPanel(new FlowLayout());
    this.mid1 = new JPanel(new FlowLayout());
    this.mid2 = new JPanel(new FlowLayout());
    this.top2 = new JPanel(new FlowLayout());
    this.bot = new JPanel(new FlowLayout());
    this.mainTop = new JPanel(new BorderLayout());
    this.mainMid = new JPanel(new BorderLayout());
    this.mainBot = new JPanel(new BorderLayout());

    // Now, create all of the labels
    this.text1 = new JLabel(TEXT1, JLabel.CENTER);
    this.text2 = new JLabel(TEXT2, JLabel.CENTER);
    this.text3 = new JLabel(TEXT3, JLabel.CENTER);
    this.text4 = new JLabel(TEXT4, JLabel.CENTER);
    this.titleText = new JLabel("Interval Identifier", JLabel.CENTER);

    // Next up, the buttons
    this.but1 = new JButton("Go!");
    this.but2 = new JButton("Go!");
    this.but3 = new JButton("Go!");
    this.but4 = new JButton("Go!");
    this.exitButton = new JButton("Exit");

    // Disable buttons 2 and 3 for now
    this.but2.setEnabled(false);
    this.but3.setEnabled(false);
    this.but4.setEnabled(false);

    // Add action listeners for the buttons
    this.but1.addActionListener(new firstSubmitButtonHandler());
    this.but2.addActionListener(new secondSubmitButtonHandler());
    this.but3.addActionListener(new thirdSubmitButtonHandler());
    this.but4.addActionListener(new fourthSubmitButtonHandler());
    this.exitButton.addActionListener(new exitButtonHandler());

    // Lastly, the TextFields
    this.step1 = new JTextField(20);
    this.step2 = new JTextField(20);
    this.step3 = new JTextField(20);
    this.step4 = new JTextField(20);

    // First, the top panel
    this.top1.add(this.titleText);
    this.top2.add(this.text1);
    this.top2.add(this.step1);
    this.top2.add(this.but1);
    this.mainTop.add(this.top1, BorderLayout.NORTH);
    this.mainTop.add(this.top2, BorderLayout.SOUTH);

    // Next up, the middle panel
    this.mid1.add(this.text2);
    this.mid1.add(this.step2);
    this.mid1.add(this.but2);
    this.mid2.add(this.text3);
    this.mid2.add(this.step3);
    this.mid2.add(this.but3);
    this.mainMid.add(this.mid1, BorderLayout.NORTH);
    this.mainMid.add(this.mid2, BorderLayout.SOUTH);

    // Bottom panel
    this.bot.add(this.text4);
    this.bot.add(this.step4);
    this.bot.add(this.but4);
    this.mainBot.add(this.bot, BorderLayout.NORTH);
    this.mainBot.add(this.exitButton, BorderLayout.SOUTH);

    // Add the panels to the canvas
    this.add(this.mainTop, BorderLayout.NORTH);
    this.add(this.mainMid, BorderLayout.CENTER);
    this.add(this.mainBot, BorderLayout.SOUTH);
  }

  private class firstSubmitButtonHandler implements ActionListener {

    public void actionPerformed(ActionEvent evt) {}
  }

  private class secondSubmitButtonHandler implements ActionListener {

    public void actionPerformed(ActionEvent evt) {}
  }

  private class thirdSubmitButtonHandler implements ActionListener {

    public void actionPerformed(ActionEvent evt) {}
  }

  private class fourthSubmitButtonHandler implements ActionListener {

    public void actionPerformed(ActionEvent evt) {}
  }

  /**
   * This inner class handles all actions associated with the "exit" button
   */
  private class exitButtonHandler implements ActionListener {

    public void actionPerformed(ActionEvent evt) {
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
    gg.setSize(1200, 1000);
    gg.setVisible(true);
  }                                                                  
}