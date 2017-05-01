import java.util.*;
import java.io.*;
import java.nio.*;

public class Students{

	public static ArrayList<String> studentList = new ArrayList<String>();

	public static void main(String[] args){

		Scanner in = new Scanner(System.in);

		System.out.println(" \nEnter File to be read from, if file does not exist a new file will be created: \n");
		String filenameInput = in.nextLine();

		File studentFile = new File(filenameInput);

		if(studentFile.exists()){

			System.out.println("\nFile found, reading data...");

			if(filenameInput.matches(".*\\.txt$")){

				try{

					BufferedReader reader = new BufferedReader(new FileReader(studentFile));

					String line;
					while((line = reader.readLine()) != null){
						studentList.add(line);

					}

					System.out.println("File loaded.");

				}catch(FileNotFoundException error){
					
					System.out.println("Error: " + error);

				}catch(IOException error){

					System.out.println("Error: " + error);

				}
			}
			else{
				int length = (int) studentFile.length();

				byte[] data = new byte[length];
				try{
					FileInputStream readStudents = new FileInputStream(studentFile);
					readStudents.read(data, 0, data.length);
					readStudents.close();

					String allStudents = new String(data);

					String[] students = allStudents.split(";");

					for(int i = 0; i < students.length; i++){
						studentList.add(students[i]);
					}

					System.out.println("File loaded.");
				}
				catch(Exception e){
					System.out.println("Error: " + e);
				}
			}
		}else{

			System.out.println("File not found, creating new file...");

			try{
				studentFile.createNewFile();

				System.out.println("New file " + studentFile + " created.");

			}catch(IOException error){
				System.out.println("Error creating new file. \n" + error);
			}
		}

		int selection = -1;

		do{
			System.out.println("\n++++++++++ Student Data Application +++++++++++ \n");

			System.out.println("Please select an option:");
			System.out.println("1: Display all students");
			System.out.println("2: Add a student");
			System.out.println("3: Delete a student");
			System.out.println("4: Find students by course name");
			System.out.println("5: Find students by address");
			System.out.println("6: Get subset of students");
			System.out.println("0: Save changes to file and exit\n");

			try{
				selection = in.nextInt();
			}
			catch(InputMismatchException error){
				System.out.println("\nError: input invalid, \'" + in.next() + "\' is not a menu item.");
			}

			switch(selection){
				case 1:
				System.out.println("\nAll students:\n");
				displayStudents.displayAll(studentList);
				break;

				case 2:
				System.out.println("\nAdd a student to directory:\n");
				studentList = editStudents.addStudent(studentList);
				break;

				case 3:
				System.out.println("\nDelete Student");
				studentList = editStudents.deleteStudent(studentList);
				break;

				case 4:
				Scanner searchCourse = new Scanner(System.in);
				System.out.println("\nEnter Course to search for: ");
				String courseQuery = searchCourse.nextLine();
				displayStudents.displayAll(displayStudents.getStudentsFromQuery(studentList, "Course", courseQuery));
				break;

				case 5:
				Scanner input = new Scanner(System.in);
				System.out.println("\nEnter address to search for: ");
				String addressQuery = input.nextLine();
				displayStudents.displayAll(displayStudents.getStudentsFromQuery(studentList, "Address", addressQuery));
				break;

				case 6:
				System.out.println("\nShow students in a range\nThere are " + studentList.size() +" students in total.\n");
				displayStudents.displayAll(displayStudents.getStudentsInRange(studentList));
				break;

				case 0:
				if(filenameInput.matches(".*\\.txt$")){
					writeStudents.writeToTxt(studentList, filenameInput);
				}
				else{
					writeStudents.writeBinary(studentList);
				}
				System.out.println("\nChanges to students saved.");
				System.out.println("Exiting Program.");
				break;

				default:
				System.out.println("Please enter an integer from the menu.");
				break;
			}

		}while(selection != 0);









	}
}
