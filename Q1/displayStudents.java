import java.util.*;

public class displayStudents{

	public static void displayAll(ArrayList<String> studentList){

		if(studentList.size() == 0){
			System.out.println("\nNo students to display.");
			return;
		}
		else{

			for(int i = 0; i < studentList.size(); i++){

				String[] data = studentList.get(i).split(",");

				System.out.println("Student Name: " + data[0]);
				System.out.println("Student Number: " + data[1]);
				System.out.println("Course: " + data[2]);
				System.out.println("Course ID: " + data[3]);
				System.out.println("Address: " + data[4] + " " + data[5]);
				System.out.println("Town/City: " + data[6]);
				System.out.println("Postal Code: " + data[7] + "\n");
			}
		}	
	}

	public static ArrayList<String> getStudentsFromQuery(ArrayList<String> studentList, String dataToSearch, String query){
		
		ArrayList<String> matches = new ArrayList<String>();
		int matchCount = 0;

		for(String student: studentList){

			String[] data = student.toLowerCase().split(",");
			query = query.toLowerCase();

			if(dataToSearch.equals("Address")){

				for(int i = 4; i < 8; i++){
					if(data[i].contains(query) && !matches.contains(student)){

						matches.add(student);
						matchCount += 1;
					}
				}
			}
			else if(dataToSearch.equals("Course")){

				if(data[2].contains(query) && !matches.contains(student)){
					
					matches.add(student);
					matchCount += 1;
				}
			}
		}
		if(matchCount == 0){
			System.out.println("\nNo students found matching " + "\'" + query + "\'");
		}
		else if(matchCount == 1){
			System.out.println("\n1 student found matching " + "\'" + query + "\':\n");
		}
		else{
			System.out.println("\n" + matchCount + " students found matching " + "\'" + query + "\':\n");
		}

		return matches;
	}


	public static ArrayList<String> getStudentsInRange(ArrayList<String> studentList){

		ArrayList<String> studentsInRange = new ArrayList<String>();
		Scanner rangeInput = new Scanner(System.in);


		try{	
			System.out.println("Please enter lower bound:");
			int lowerBound = rangeInput.nextInt();
			System.out.println("\nPlease enter upper bound:");
			int upperBound = rangeInput.nextInt();

			if(lowerBound > upperBound){
				System.out.println("\nError: Please make sure upper bound is greater than or equal to lower bound.");
			}
			else{

				System.out.println("\nShowing students " + lowerBound + "-" + upperBound + ":\n");

				for(int i = (lowerBound -1); i < (upperBound); i++){
					studentsInRange.add(studentList.get(i));
				}
			}
		}
		catch(InputMismatchException error){
			System.out.println("\nError: Incorrect input type. Please enter an integer.\n");
			getStudentsInRange(studentList);
		}
		catch(IndexOutOfBoundsException oobError){
			System.out.println("\nError: Input out of range, please enter a number between 1 and " + studentList.size() + "\n");
			getStudentsInRange(studentList);
		}


		return studentsInRange;
	}
}