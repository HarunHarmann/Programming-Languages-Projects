
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Food {
	public ArrayList<String> listAllFoods() {
		ArrayList<String> listOfFoods = new ArrayList<>();
		
		
		try {
			BufferedReader food1 =new BufferedReader(new FileReader("C:\\Users\\ACER\\Desktop\\Assignments-Java\\Assignment1\\food.txt"));
			int totalLines = 0;
			while (food1.readLine() != null)
				totalLines++;
			food1.close();
			
			BufferedReader food2 =new BufferedReader(new FileReader("C:\\Users\\ACER\\Desktop\\Assignments-Java\\Assignment1\\food.txt"));
			
			for (int i = 0; i < totalLines; i++) {
				String currentLine = food2.readLine();
				
				String[] addThisTo =currentLine.split("\t");
				for (String j:addThisTo) {
					listOfFoods.add(j);
				}
				
				
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listOfFoods;
	}
}