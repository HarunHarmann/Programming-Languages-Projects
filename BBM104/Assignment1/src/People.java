
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;





public class People {
	public ArrayList<String> listAllPerson() {
		ArrayList<String> listOfPeople = new ArrayList<>();
		
		
		try {
		BufferedReader people1 = new BufferedReader(new FileReader("C:\\Users\\ACER\\Desktop\\Assignments-Java\\Assignment1\\people.txt"));
			int totalLines = 0;
			while (people1.readLine() != null)
				totalLines++;
			people1.close();
			
			BufferedReader people2 = new BufferedReader(new FileReader("C:\\Users\\ACER\\Desktop\\Assignments-Java\\Assignment1\\people.txt"));
			
			
			for (int i = 0; i < totalLines; i++) {
				String currentLine = people2.readLine();
				
				String[] addThisTo =currentLine.split("\t");
				for (String j:addThisTo) {
					listOfPeople.add(j);
				}				
			}			
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listOfPeople;
	}
	
	public double dailyCaloriNeed() {
		for(int i=2;i< listAllPerson().size();i+=6) {
			int weight = Integer.parseInt(listAllPerson().get(i+1));
			int height = Integer.parseInt(listAllPerson().get(i+2));
			int age = 2022-Integer.parseInt(listAllPerson().get(i+3));
			
			
			if (listAllPerson().get(i) == "male") {
				double forMale = 66+(13.75*weight) + (5*height) - (6.8*age); 			
			}
			else if (listAllPerson().get(i) == "female") {
				double forFemale = 665 + (9.6*weight) + (1.7*height) - (4.7*age); 
				
		}

// Calculation a tekrar bak tüm listeden mi bakacak yoksa commandda okunan kiþiye mi?
		}
		return 0;
		
	}
}