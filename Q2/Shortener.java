/*
 * Name: Matthew Trimby
 * Student number: C1525379
 */
import java.util.*;
import java.io.*;

public class Shortener {
    // This class is only a starting point. You should complete all members
    // below, but you may also need to add other fields and methods to
    // finish the implementation as per the question on the assignment sheet.

    private File textFile; //initialise File variable to store user file
    private ArrayList<String> abbreviations = new ArrayList<String>(); //initialise ArrayList to store abbreviations 

    /*
     * Default constructor that will load a default abbreviations text file.
     */
    public Shortener() {
        this.textFile = new File("abbreviations.txt");
    }
    
    /*
     * Constructor that will load the abbreviations file represented by the
     * File parameter.
     */
    public Shortener( File inAbbreviationsFile ) {
        this.textFile = inAbbreviationsFile;
    }
    
    /*
     * Constructor that will load the abbreviations file that the String 
     * parameter is a file path for.
     */
    public Shortener( String inAbbreviationsFilePath ) {
        this.textFile = new File(inAbbreviationsFilePath);
    }
    
    /*
     * This method attempts to shorten a word by finding its abbreviation. If 
     * no abbreviation exists for this word, then this method will return the 
     * original (i.e., unshortened) word.
     * 
     * You may assume that words are always lower case.
     *
     * `inWord` should be a single word (no spaces). It may optionally be
     * followed by one of the five following punctuation characters:
     *   ,
     *   ?
     *   .
     *   !
     *   ;
     * If one of these characters is at the end of the word this method will
     * shorten the word (ignoring the punctuation) and return the shortened
     * word with the punctuation character at the end.
     * For example,
     *     shortenerObject.shortenWord( "hello?" )
     * should return
     *     "lo?"
     *
     * You may assume that words are always lower case.
     */
    public String shortenWord( String inWord ) {
        
        inWord = inWord.trim(); //remove any spaces either side of the word
        String lastChar = inWord.substring(inWord.length() - 1); //get last character, later we will check if this is punctuation
        boolean hasPunc = false; //initialise boolean 
        String[] punctuation = {",", "?", ".", "!", ";"}; //initialise list of punctuation to check for


        if(Arrays.asList(punctuation).contains(lastChar)){ //check last character againts punctuation list

            inWord = inWord.substring(0, inWord.length() - 1); //remove last char if it is puntuation
            hasPunc = true; //set boolean to true

        }

        for(String word: abbreviations){

            String[] splitWord = word.split(","); //seperate words and their abbreviations

            if(splitWord[0].toLowerCase().equals(inWord.toLowerCase())){ //check if input word matches the current word in list
                String shortWord = splitWord[1]; 
                if(hasPunc){
                    return shortWord + lastChar; //if there was puctuation, add it back on when returning
                }
                else{
                    return shortWord; //otherwise just return the abbreviated word
                }
            }
        }
        if(hasPunc){ //return statement reached if input word doesnt match anything in abbreviation list
            return inWord + lastChar;
        }
        else{
            return inWord;
        }
    }
    public String shortenPhrase(String inMessage){ //function to find phrases to shorten within the message 

        for(String wordOrPhrase: abbreviations){

            String[] splitWordOrPhrase = wordOrPhrase.split(","); //splits word/phrase from it's abbreviation

            if(splitWordOrPhrase[0].split(" ").length > 1){ //checks if word/phrase from list contains a space, if it does then it's a phrase and should be checked for

                if(inMessage.toLowerCase().indexOf(splitWordOrPhrase[0].toLowerCase()) > 0){ //checks if the phrase from list is present in the input message string

                    inMessage = inMessage.replace(splitWordOrPhrase[0].toLowerCase(), splitWordOrPhrase[1]); //if phrase is present, replace it with its shortened version

                }
            }
        }
        return inMessage; //return message 
    }
    /*
     * Attempts to shorten a message by replacing words with their 
     * abbreviations. 
     *
     * You may assume that messages are always lower case.
     *
     * Punctuation characters (,?.!;) should be retained after shortening. See
     * `shortenWord( String inWord )` for more information.
     */
    public String shortenMessage( String inMessage ) {
        
        String finalMessage = ""; //initialise string to contain final message

        try{

            BufferedReader reader = new BufferedReader(new FileReader(textFile)); //initialise reader on file

            String line;

            while((line = reader.readLine()) != null){ //read through lines of file
                abbreviations.add(line); //store abbreviations in ArrayList

            }

        }catch(FileNotFoundException error){ //catch exceptions when loading
            
            return "Error: Abbreviations file could not be loaded, file not found.";

        }catch(IOException error){

            return "Error: abbreviations file could not be loaded.";
        }

        inMessage = inMessage.toLowerCase(); //even though we're assuming input is lower case, just make sure 

        inMessage = shortenPhrase(inMessage); //shorten any phrases within the message first

        String[] message = inMessage.split("\\s+"); //split the message into individual words


        for(int i = 0; i < message.length; i++){
            String finalWord = shortenWord(message[i]); //shorten each word in the message
            finalMessage += finalWord + " "; //add words back into final message with spaces afterwards
        }

        return finalMessage.substring(0, finalMessage.length() -1); //return message -1 char to remove the space added to the end
    }
}