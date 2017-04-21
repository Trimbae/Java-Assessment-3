import java.util.*;

public class editStudents{

	public static ArrayList<String> addStudent(ArrayList<String> studentList){

		ArrayList<String> newStudent = new ArrayList<String>(); 
		
		Scanner addStudents = new Scanner(System.in);

		boolean valid = false;

		while(!valid){
			System.out.println("Enter name:");
			String name = addStudents.nextLine();
			if(isName(name)){
				newStudent.add(name);
				valid = true;
			}
			else{
				System.out.println("\n\'" + name + "\' is not a valid input. Name field must be at least 1 character.\n");
			}
		}
		valid = false;
		while(!valid){
			System.out.println("\nEnter student number:");
			String studentNo = addStudents.nextLine();
			if(isStudentNo(studentNo)){
				newStudent.add(studentNo);
				valid = true;
			}
			else{
				System.out.println("\n\'" + studentNo + "\' is not a valid input. Student Number field must be an upper case C followed by 6 digits.\n");
			}
		}
		valid = false;
		while(!valid){
			System.out.println("\nEnter Course Name:");
			String course = addStudents.nextLine();
			if(isName(course)){
				newStudent.add(course);
				valid = true;
			}
			else{
				System.out.println("\n\'" + course + "\' is not a valid input. Course Name field must be at least 1 character.\n");
			}
		}
		valid = false;
		while(!valid){
			System.out.println("\nEnter Course ID:");
			String courseID = addStudents.nextLine();
			if(isCourseID(courseID)){
				newStudent.add(courseID);
				valid = true;
			}
			else{
				System.out.println("\n\'" + courseID + "\' is not a valid input. Course ID field must be 2 upper case letters followed by 4 digits.\n");
			}
		}
		valid = false;
		while(!valid){
			System.out.println("\nEnter House Number:");
			String houseNo = addStudents.nextLine();
			if(isHouseNo(houseNo)){
				newStudent.add(houseNo);
				valid = true;
			}
			else{
				System.out.println("\n\'" + houseNo + "\' is not a valid input. House Number field must be 1 or more digit followed by no more than 1 leter.\n");
			}
		}
		valid = false;
		while(!valid){
			System.out.println("\nEnter Street Name:");
			String streetName = addStudents.nextLine();
			if(isName(streetName)){
				newStudent.add(streetName);
				valid = true;
			}
			else{
				System.out.println("\n\'" + streetName + "\' is not a valid input. Street Name field must be at least 1 character.\n");
			}
		}
		valid = false;
		while(!valid){
			System.out.println("\nEnter Town/City:");
			String town = addStudents.nextLine();
			if(isName(town)){
				newStudent.add(town);
				valid = true;
			}
			else{
				System.out.println("\n\'" + town + "\' is not a valid input. Town/City field must be at least 1 character.\n");
			}
		}
		valid = false;
		while(!valid){
			System.out.println("\nEnter Postcode:");
			String postcode = addStudents.nextLine();
			if(isPostcode(postcode)){
				newStudent.add(postcode);
				valid = true;
			}
			else{
				System.out.println("\n\'" + postcode + "\' is not a valid input. Postcode field must be 2 upper case letters, 1 digit and another 2 upper case letters with no spaces.\n");
			}
		}
		String studentDataLine = "";

		for(String data: newStudent){
			studentDataLine += data + ",";
		}

		studentDataLine = studentDataLine.substring(0,(studentDataLine.length() - 1));
		
		studentList.add(studentDataLine);

		System.out.println("\n" + newStudent.get(0) + " Added!");

		return studentList;

	}

	public static boolean isName(String inputName){

		return inputName.matches("[a-zA-Z\\s]++");
	}

	public static boolean isStudentNo(String inputStudentNo){

		return inputStudentNo.matches("[C]\\d{6}");
	}

	public static boolean isCourseID(String inputCourseID){

		return inputCourseID.matches("[A-Z]{2}\\d{4}");
	}

	public static boolean isHouseNo(String inputHouseNo){

		return inputHouseNo.matches("\\d++[a-zA-Z]?");
	}
	public static boolean isPostcode(String inputPostcode){

		return inputPostcode.matches("[A-Z]{2}\\d[A-Z]{2}");
	}

	public static ArrayList<String> deleteStudent(ArrayList<String> studentList){

		Scanner deleteStudent = new Scanner(System.in);
		System.out.println("");

		for(int i = 0; i < (studentList.size()); i++){
			String[] data = studentList.get(i).split(",");
			System.out.println((i + 1) + ": " + data[0] + ", " + data[1]); 
		}
		System.out.println("\nSelect a student to delete or enter 0 to return to main menu:");

		try{
			int toDelete = deleteStudent.nextInt();
			if(toDelete == 0){
				return studentList;
			}
			else{
				toDelete -= 1;
				String[] deletedStudent = studentList.get(toDelete).split(",");
				
				System.out.println("\nAre you sure you want to delete \'" + deletedStudent[0] + "\'?");
				System.out.println("1: Yes");
				System.out.println("2: No");
				System.out.println("0: Exit to main menu\n");

				int selection = deleteStudent.nextInt();
				
				switch(selection){
					case 1: 
					studentList.remove(toDelete);
					System.out.println("\nStudent \'" + deletedStudent[0] + "\' has been deleted.");
					return studentList;

					case 2:
					deleteStudent(studentList);
					break;
				}
			}
		}
		catch(InputMismatchException error1){
			System.out.println("\nError: Invalid input, must be integer.\n");
			deleteStudent(studentList);
		}
		catch(IndexOutOfBoundsException error2){
			System.out.println("\nError: Student selection was out of range, returning to main menu.");
		}

		return studentList;
	}

}