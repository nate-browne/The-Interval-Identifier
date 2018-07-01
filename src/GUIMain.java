/*
 * Author: Nate Browne
 * Version: 1.0
 * Date: 30 Jun 2018
 * Last Edited: 30 Jun 2018
 * File: GUIMainUI.java
 * This file sets up the GUI for the Interval Identifier program
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

//@SuppressWarnings("serial")
public class GUIMain extends JFrame implements ActionListener, KeyListener {

  /* Declaration of instance variables and constants */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for the GUI. Sets up the fields of the GUI
   */
  public GUIMain() {}

  /**
   * Main entry point into the program.
   * 
   * @param args String array of command line arguments
   */
  public static void main(String[] args) {
    GUIMain gg = new GUIMain();
    gg.setVisible(true);
  }                                                                  
}