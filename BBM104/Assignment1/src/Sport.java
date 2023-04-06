
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Sport {
	public ArrayList<String> listAllSports() {
		ArrayList<String> listOfSports = new ArrayList<>();
		
		
		try {
			BufferedReader sport1 =new BufferedReader(new FileReader("C:\\Users\\ACER\\Desktop\\Assignments-Java\\Assignment1\\sport.txt"));
			int totalLines = 0;
			while (sport1.readLine() != null)
				totalLines++;
			sport1.close();
			
			BufferedReader sport2 =new BufferedReader(new FileReader("C:\\Users\\ACER\\Desktop\\Assignments-Java\\Assignment1\\sport.txt"));
			
			for (int i = 0; i < totalLines; i++) {
				String currentLine = sport2.readLine();
				
				String[] addThisTo =currentLine.split("\t");
				for (String j:addThisTo) {
					listOfSports.add(j);
				}
				
				
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listOfSports;
	}
}