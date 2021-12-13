package it.unibo.oop.lab.mvcio2;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

import java.awt.BorderLayout;
/*
 * TODO: Starting from the application in mvcio:
 * 
 * 1) Add a JTextField and a button "Browse..." on the upper part of the
 * graphical interface.
 * Suggestion: use a second JPanel with a second BorderLayout, put the panel
 * in the North of the main panel, put the text field in the center of the
 * new panel and put the button in the line_end of the new panel.
 * 
 * 2) The JTextField should be non modifiable. And, should display the
 * current selected file.
 * 
 * 3) On press, the button should open a JFileChooser. The program should
 * use the method showSaveDialog() to display the file chooser, and if the
 * result is equal to JFileChooser.APPROVE_OPTION the program should set as
 * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
 * then the program should do nothing. Otherwise, a message dialog should be
 * shown telling the user that an error has occurred (use
 * JOptionPane.showMessageDialog()).
 * 
 * 4) When in the controller a new File is set, also the graphical interface
 * must reflect such change. Suggestion: do not force the controller to
 * update the UI: in this example the UI knows when should be updated, so
 * try to keep things separated.
 */
/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private static final String TITLE = "Simple GUI with file choser";
    private static final int PROPORTION = 2;
    private final  JFrame frame = new JFrame(TITLE);
    /**
     * @param controller
     */
    public SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel panelIn = new JPanel();
        panelIn.setLayout(new BorderLayout());
        final JPanel panelOut = new JPanel();
        panelOut.setLayout(new BorderLayout());
        panelOut.add(panelIn, BorderLayout.NORTH);
        final JTextField tf = new JTextField(controller.getCurrentPath());
        tf.setEditable(false);
        final JButton browse = new JButton("Browse...");
        panelIn.add(tf, BorderLayout.CENTER);
        panelIn.add(browse, BorderLayout.LINE_END);
        frame.setContentPane(panelOut);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /**
         * Handlers
         */
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
              final JFileChooser fc = new JFileChooser("choose where to save");
              fc.setSelectedFile(controller.getFile());
              final int  result = fc.showSaveDialog(frame);
              switch (result) {
              case JFileChooser.APPROVE_OPTION:
                  final File newDest = fc.getSelectedFile();
                  controller.setFile(newDest);
                  tf.setText(newDest.getPath());
                  break;
              case JFileChooser.CANCEL_OPTION:
                  break;
              default:
                  JOptionPane.showMessageDialog(frame, result, "Meh!", JOptionPane.ERROR_MESSAGE);
                  break;
              }
            }
        });
    }
    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    /**
     * @param args ignored
     */
    public static void main(final String... args) {
       new SimpleGUIWithFileChooser(new Controller()).display();
    }
}
