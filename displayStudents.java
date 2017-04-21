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
				System.out.println("Subject: " + data[2]);
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

			String[] data = student.split(",");

			if(dataToSearch.equals("Address")){

				if(data[3].toLowerCase().contains(query.toLowerCase())){

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


	public static ArrayList<String> getStudentsInRange(ArrayList<String> studentList, int lowerBound, int upperBound){

		ArrayList<String> studentsInRange = new ArrayList<String>();

		for(int i = (lowerBound -1); i < (upperBound); i++){
			studentsInRange.add(studentList.get(i));
		}

		return studentsInRange;
	}
}