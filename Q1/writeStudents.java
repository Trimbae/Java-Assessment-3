import java.util.*;
import java.io.*;

class writeStudents{

	public static void writeToTxt(ArrayList<String> studentList, String filename){
		
		try{
			FileWriter writeStudents = new FileWriter(filename);
			PrintWriter out = new PrintWriter(writeStudents);

			for(String line: studentList){
				out.println(line);
			}
			out.close();
		}
		catch(Exception error){
			System.out.print("Error: " + error);
		}
	}

	public static void writeBinary(ArrayList<String> studentList){
		
		String allLines = "";

		for(String line: studentList){

			allLines += line + ";";
		}

		byte[] binaryLine = allLines.getBytes();


		try{
			FileOutputStream writeStudents = new FileOutputStream("student.dat");
			writeStudents.write(binaryLine, 0, binaryLine.length);
			writeStudents.flush();
			writeStudents.close();
		}
		catch(Exception error){
			System.out.print("Error: " + error);
		}
	}
}