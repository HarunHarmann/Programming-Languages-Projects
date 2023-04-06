import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;







public class Command {

	public void command() {
		BufferedReader commands;
		ArrayList<String> rows;
		ArrayList<ArrayList<String>> gameGrid = new ArrayList<>();
		TreeMap<Integer, String> users = new TreeMap<>();
		int totalScore = 0;
		
		try {
			
			FileWriter file1 = new FileWriter("monitoring.txt", true);
			PrintWriter pw1 = new PrintWriter(file1);
			pw1.println("Game grid:");
			pw1.println();
			
			BufferedReader matrixLine = new BufferedReader(new FileReader("gameGrid.txt"));
			int totalLine = 0;
			while (matrixLine.readLine() != null)
				totalLine++;
			matrixLine.close();
			
			BufferedReader matrix = new BufferedReader(new FileReader("gameGrid.txt"));  // Read gameGrid.txt
			for (int i = 1; i <= totalLine; i++) {
				String currentLine = matrix.readLine();
				pw1.println(currentLine);
				List<String> row =  Arrays.asList(currentLine.split(" "));
				rows = new ArrayList<>(row);
				gameGrid.add(rows);
			}
			pw1.println();
			matrix.close();
			
			
			BufferedReader leaderBoard = new BufferedReader(new FileReader("leaderboard.txt")); // Read leaderboard.txt and create a TreeMap.
			String line;
			while ((line = leaderBoard.readLine()) != null) {
				String[] userInfo = line.split(" ");
				users.put(Integer.parseInt(userInfo[1]),userInfo[0]);
			}
			
			FileWriter file = new FileWriter("leaderboard.txt", true);
			PrintWriter pw = new PrintWriter(file);
			
		
					
			commands = new BufferedReader(new FileReader("command.txt"));
			int totalLines = 0;
			while (commands.readLine() != null)
				totalLines++;
			commands.close();
			
			BufferedReader command = new BufferedReader(new FileReader("command.txt"));  // Read commands.txt
			try {
			 for (int i = 1; i <totalLines; i++) 
			 {
				
				String currentLine = command.readLine();		
				String[] commandContent =currentLine.split(" ");
				
				pw1.println("Select coordinate or enter E to end the game: " + currentLine);
				pw1.println();

				if (commandContent.length == 2) {
					int row = Integer.parseInt(commandContent[0]);
					int col = Integer.parseInt(commandContent[1]);
					
					
					if (row >= gameGrid.size() || row <0 || col >= gameGrid.size() || col < 0 || gameGrid.get(row).get(col).equals(" ")) {
						pw1.println("Please enter a valid coordinate");
						pw1.println();
					}else {
					
					String jewelString = gameGrid.get(row).get(col);	
						
					int oldScore = totalScore;
					
					Jewels diamond = new Diamond();
					diamond.findKind(row, col, gameGrid,jewelString);
					totalScore+= diamond.getPoint();
					
					Jewels wildcard = new Wildcard();
					wildcard.findKind(row, col, gameGrid,jewelString);
					totalScore+= wildcard.getPoint();
					
					Jewels square = new Square();
					square.findKind(row, col, gameGrid, jewelString);
					totalScore+= square.getPoint();
					
					Jewels triangle = new Triangle();
					triangle.findKind(row, col, gameGrid, jewelString);
					totalScore+= triangle.getPoint();
					
					Jewels leftSlash = new LeftSlash();			
					leftSlash.findKind(row, col, gameGrid, jewelString);
					totalScore+= leftSlash.getPoint();
					
					Jewels rightSlash = new RightSlash();
					rightSlash.findKind(row, col, gameGrid, jewelString);
					totalScore+= rightSlash.getPoint();
					
					Jewels plus = new Plus();
					plus.findKind(row, col, gameGrid, jewelString);
					totalScore+= plus.getPoint();
					
					Jewels minus = new Minus();
					minus.findKind(row, col, gameGrid, jewelString);
					totalScore+= minus.getPoint();
					
					Jewels stick = new Stick();
					stick.findKind(row, col, gameGrid, jewelString);
					totalScore+= stick.getPoint();
					
					for (ArrayList<String> arrayList : gameGrid) {
						String formattedString = arrayList.toString()
							    .replace(",", "")  //remove the commas
							    .replace("[", "")  //remove the right bracket
							    .replace("]", "")  //remove the left bracket
							    ; 
						pw1.println(formattedString);
						
					}
					pw1.println();
					pw1.println("Score: "+String.valueOf(totalScore-oldScore)+ " points");
					pw1.println();
					
					
				}
				}
				
				else if (commandContent[0].equals("E")) {
					String name = command.readLine();
					users.put(totalScore, name);
					pw.write("\n"+name +" "+ totalScore);
					pw.flush();
					pw1.println("Total score: " + totalScore + " points");
					pw1.println();
					pw1.println("Enter name: " + name);
					pw1.println();
					if (users.lastKey() == totalScore) {
						pw1.println("Your rank is "+ "1/"+ String.valueOf(users.size())+ ", your score is " + String.valueOf(totalScore - users.lowerKey(totalScore)+" points higher than " +users.get(users.lowerKey(totalScore))));
						pw1.println();
					}
					else if (users.firstKey() == totalScore) {
						pw1.println("Your rank is "+ String.valueOf(users.size())+"/"+String.valueOf(users.size())+ ", your score is " + String.valueOf(users.higherKey(totalScore) - totalScore)+" points lower than " +users.get(users.higherKey(totalScore)));
						pw1.println();
					}
					else {
						Set<Integer> results = users.keySet();
						ArrayList<Integer> resultsList = new ArrayList<Integer>(results);
						for (Integer integer : results) {
							if (integer == totalScore) {
								pw1.println("Your rank is "+ String.valueOf(results.size() -(resultsList.indexOf(integer)))+"/"+String.valueOf(users.size())+ ", your score is " + String.valueOf(users.higherKey(totalScore) - totalScore)+" points lower than " +users.get(users.higherKey(totalScore))
											+ " and " +String.valueOf(totalScore - users.lowerKey(totalScore)+" points higher than " + users.get(users.lowerKey(totalScore))));
								pw1.println();
							}
						}
						
					}
				 }				
				
				}
			  
			}
			finally {				
				pw1.println("Good bye!");
				pw1.flush();
			}
				 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
}