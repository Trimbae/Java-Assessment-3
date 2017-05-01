/*
 * Name: Matthew Trimby
 * Student number: c1525379
 */

/*
 * A command-line application that shortens a message.
 */
public class ShortenerUtility {
    public static void main(String[] args){

		if(args.length < 1){
			System.out.println("Error: please enter a message as an argument.");
		}
		else{
    		Shortener shorter = new Shortener();
    		System.out.println(shorter.shortenMessage(args[0]));
    	}
    }
}