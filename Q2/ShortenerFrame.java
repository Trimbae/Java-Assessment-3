/*
 * Name: Matthew Trimby
 * Student number: C1525379
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.io.*;

public class ShortenerFrame extends JFrame implements ActionListener{
    // This class is only a starting point. You may wish to add members and 
    // fields to complete the implementation of this class as per the
    // question on the assignment sheet.
    
    // Constants
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 260;
    
    // Instance variables -- GUI components
    private JPanel panel;
    private JLabel instructionLabel;
    private JButton pasteButton;
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton shortenButton;
    private JButton copyOutputButton;
    
    // Constructor
    public ShortenerFrame() {
        super();
        
        //
        // Set up the frame
        setSize( FRAME_WIDTH, FRAME_HEIGHT );
        setTitle("Text Shortener");
        //
        // Set up the panel and layout manager
        panel = new JPanel();
        GridLayout grid = new GridLayout( 0, 1 );  // a one-column layout
        panel.setLayout( grid );
        
        add( panel );  // add panel to the JFrame

        //
        // Set up and add components
        instructionLabel = new JLabel( "Enter text in the field below and click 'Shorten'" );
        panel.add( instructionLabel );

        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        panel.add( inputArea );

        pasteButton = new JButton("Paste text from clipboad");
        panel.add(pasteButton);
        
        shortenButton = new JButton( "Shorten" );
        panel.add( shortenButton );
        
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        panel.add(outputArea);

        copyOutputButton = new JButton("Copy output text to clipboard");
        panel.add(copyOutputButton);

        shortenButton.addActionListener(this);
        pasteButton.addActionListener(this);
        copyOutputButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent evt){


        if(evt.getSource() == shortenButton){

            String messageInput = inputArea.getText();

            Shortener textShortener = new Shortener();
            outputArea.setText(textShortener.shortenMessage(messageInput));
            
            //if the file cannot be found, Shortener.java returns error message which is then printed to output box
        }
        /*I learned to manipulate the clipboard from the java API and blog
        * https://docs.oracle.com/javase/7/docs/api/java/awt/datatransfer/Clipboard.html
        * https://blogs.oracle.com/JavaFundamentals/entry/transferring_text_through_the_clipboard
        */
        else if(evt.getSource() == copyOutputButton){
            
            StringSelection outputText = new StringSelection(outputArea.getText()); //select string we want to copy 

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //initialise clipboard object using system clipboard

            clipboard.setContents(outputText, null); //set contents of clipboard to the StringSelection

        }
        else if(evt.getSource() == pasteButton){

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //establish clipboard object using system clipboard

            try{
                if(clipboard.getContents(null).isDataFlavorSupported(DataFlavor.stringFlavor)){ //check data is of right format to be transferred across, in this case a string is required
                    String pasteText = (String)(clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor)); //gets contents of clipboard, forcefully convereted to string 
                    inputArea.setText(pasteText); //paste string in input area
                }
            }
            catch(UnsupportedFlavorException error){ //catches error if clipboard data is wrong flavour
                inputArea.setText("Error: Clipboard data is wrong type");
            }
            catch(IOException error2){ //catches error if data from clipboard is not available
                inputArea.setText("Clipboard data not available.");
            }
        }   

    }
}

