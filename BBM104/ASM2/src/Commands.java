import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Commands {

	private int count1=0;
	private int count2=0;
	private int count3=0;
	private int count4 =0;
	
	Player player1 = new Player();
	Player player2 = new Player();
	Banker banker = new Banker();
   public void command() {
	try {
		
		BufferedReader commands = new BufferedReader(new FileReader("command.txt"));
		int totalLines = 0;
		while (commands.readLine() != null)
			totalLines++;
		commands.close();
		
		BufferedReader command = new BufferedReader(new FileReader("command.txt"));
		
		FileWriter file = new FileWriter("output.txt", true);
		PrintWriter pw = new PrintWriter(file);
		
		
		for (int i = 1; i <=totalLines; i++) 
		{
			String currentLine = command.readLine();		
			String[] commandContent =currentLine.split(";");
			String whichPlayer = commandContent[0];
			
			
			if (whichPlayer.equals("Player 1")){ 					// This if will consider only player 1 and its actions.
				int movement = Integer.parseInt(commandContent[1]);	
				player1.setName("Player 1");
				player1.getPosition(movement);
				String communityChest = player1.communityChest(player2,banker,movement);
				String chance = player1.chance(banker,movement,player1,player2);
				String land = player1.land(banker, player2);
				String railRoad = player1.railRoad(banker, player2);
				String company = player1.company(banker,player2, movement);
				String go = player1.GO(banker,movement);	
				String tax = player1.payTax(banker);
				int freePark = player1.position;
				String goToJail = player1.goToJail();
				int jail = player1.position;
				
				
				
//				
				if (!communityChest.equals("")) {
					pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1"+communityChest);
					pw.println();
				}
				else if (!chance.equals("")) {
					pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1"+chance);
					pw.println();
				}
				else if (freePark == 21) {
					count3+=1;
					
					if (count3 <=2) {
						pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1 is in Free Parking");
						pw.println();
					}
					if (count3 ==3) {
						count3 =0;
					}
					
				}
				else if (!land.equals("")) {
					if (!land.equals(" goes bankrupt")) {
						pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1"+land);
						pw.println();
					}
					else {
						pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1"+land);
						pw.println();
						show(pw);
						break;
					}
					
				}		
				else if (!railRoad.equals("")) {
					
					if (!railRoad.equals(" goes bankrupt")) {
						pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1"+railRoad);
						pw.println();
					}
					else {
						pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1"+railRoad);
						pw.println();
						show(pw);
						break;
					}
				}
				else if (!company.equals("")) {
					if (!company.equals(" goes bankrupt")) {
						pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1"+company);
						pw.println();
					}
					else {
						pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1"+company);
						pw.println();
						show(pw);
						break;
					}
				}
				else if (!go.equals("")) {
					pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1"+go);
					pw.println();
				}
				else if (!tax.equals("")) {
					pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1"+tax);
					pw.println();
				}
				
				else if (jail == 11) {
					count1+= 1;
					if (count1 ==1) {
						pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1"+" went to jail");
						pw.println();
					}
					else if (count1 <= 4) {
						pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1"+" in jail "+"(count="+String.valueOf(count1-1)+")");
						pw.println();
					}
					if (count1 == 4) {
						count1 =0;
					}
					
					
				}
				
				
				
				
				
						
			}
			else if (whichPlayer.equals("Player 2")) {					// This if will consider only player 2 and its actions.
				int movement = Integer.parseInt(commandContent[1]);
				player2.setName("Player 2");
				player2.getPosition(movement);
				String communityChest = player2.communityChest(player1,banker,movement);
				String chance = player2.chance(banker,movement,player2,player1);
				String land = player2.land(banker,player1);			
				String railRoad = player2.railRoad(banker, player1);
				String company = player2.company(banker, player1, movement);
				String go = player2.GO(banker,movement);
				String tax = player2.payTax(banker);
				int freePark = player2.position;
				String goToJail = player2.goToJail();
				int jail = player2.position;
				
				
				
				
				
				
				if (!communityChest.equals("")) {
					pw.print("Player 2"+"\t"+movement+"\t"+player2.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 2"+communityChest);
					pw.println();
				}
				else if (!chance.equals("")) {
					pw.print("Player 2"+"\t"+movement+"\t"+player2.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 2"+chance);
					pw.println();
				}
				else if (freePark == 21) {
					count4+=1;
					
					if (count4 <=2) {
						pw.print("Player 1"+"\t"+movement+"\t"+player1.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 1 is in Free Parking");
						pw.println();
					}
					if (count4 ==3) {
						count4 =0;
					}
					
				}
				else if (!land.equals("")) {
					if (!land.equals(" goes bankrupt")) {
						pw.print("Player 2"+"\t"+movement+"\t"+player2.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 2"+land);
						pw.println();
					}
					else {
						pw.print("Player 2"+"\t"+movement+"\t"+player2.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 2"+land);
						pw.println();
						show(pw);
						break;
					}
					
				}
				else if (!railRoad.equals("")) {
					
					if (!railRoad.equals(" goes bankrupt")) {
						pw.print("Player 2"+"\t"+movement+"\t"+player2.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 2"+railRoad);
						pw.println();
					}
					else {
						pw.print("Player 2"+"\t"+movement+"\t"+player2.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 2"+railRoad);
						pw.println();
						show(pw);
						break;
					}
				}
				else if (!company.equals("")) {
					if (!company.equals(" goes bankrupt")) {
						pw.print("Player 2"+"\t"+movement+"\t"+player2.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 2"+company);
						pw.println();
					}
					else {
						pw.print("Player 2"+"\t"+movement+"\t"+player2.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 2"+company);
						pw.println();
						show(pw);
						break;
					}
				}
				else if (!go.equals("")) {
					pw.print("Player 2"+"\t"+movement+"\t"+player2.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 2"+go);
					pw.println();
				}
				else if (!tax.equals("")) {
					pw.print("Player 2"+"\t"+movement+"\t"+player2.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 2"+tax);
					pw.println();
				}
				else if (jail == 11) {
					count2+= 1;
					if (count2 ==1) {
						pw.print(player2.getName()+"\t"+movement+"\t"+player2.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 2"+" went to jail");
						pw.println();
					}
					else if (count2 <= 4) {
						pw.print(player2.getName()+"\t"+movement+"\t"+player2.position+"\t"+player1.getBudget()+"\t"+player2.getBudget()+"\t"+"Player 2"+" in jail "+"(count="+String.valueOf(count2-1)+")");
						pw.println();
					}
					if (count2 == 4) {
						count2 =0;
					}
					
				}
				
			
								
			}
			else {			// Show command in command.txt
				show(pw);
			}
			if (i == totalLines) {
				show(pw);
				
			}
			
		}
		pw.flush();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   public void show(PrintWriter pw) {
	   String formattedString = player1.ownedName.toString()
			    .replace("[", "")  //remove the right bracket
			    .replace("]", "")  //remove the left bracket
			    .trim(); 
		
		String formattedString2 = player2.ownedName.toString()
			    .replace("[", "")  //remove the right bracket
			    .replace("]", "")  //remove the left bracket
			    .trim(); 
		
		pw.println("-------------------------------------------------------------------------------------------------------------------------");
		pw.print("Player 1"+"\t"+player1.getBudget()+"\t"+"have: "+ formattedString);
		pw.println();
		pw.print("Player 2"+"\t"+player2.getBudget()+"\t"+"have: "+ formattedString2);
		pw.println();
		pw.println("Banker"+"\t"+banker.getBudgetForBanker());
		if (player1.getBudget() > player2.getBudget()) {
			pw.println("Winner"+"\t"+ "Player 1");
			pw.println("-------------------------------------------------------------------------------------------------------------------------");
			
		}
		else if (player1.getBudget() < player2.getBudget()) {
			pw.println("Winner"+"\t"+ "Player 2");
			pw.println("-------------------------------------------------------------------------------------------------------------------------");
		}
   }
}

