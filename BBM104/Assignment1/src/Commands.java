
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class Commands {
	
	People persons = new People();
	Food foods = new Food();
	Sport sports = new Sport();
	
	ArrayList<String> listOfPersonFromFood = new ArrayList<>();
	ArrayList<String> listOfPersonFromSport = new ArrayList<>();
	ArrayList<Integer> listOfCaloriGained = new ArrayList<>();
	ArrayList<Integer> listOfCaloriBurned = new ArrayList<>();
	ArrayList<String> listOfPerson = new ArrayList<>();
	ArrayList<String> listOfFeaturesOfPeople1 = new ArrayList<>();
	ArrayList<String> listOfFeaturesOfPeople2 = new ArrayList<>();
	
	
	public void  getCommand() {
		try {
			BufferedReader commands1 = new BufferedReader(new FileReader("command.txt"));
			int totalLines = 0;
			while (commands1.readLine() != null)
				totalLines++;
			commands1.close();
			
			FileWriter file2 = new FileWriter("monitoring.txt", true);
			PrintWriter pw = new PrintWriter(file2);
			
			BufferedReader commands2 = new BufferedReader(new FileReader("command.txt"));
			
			//SPOR ÝÇÝN DE AYARLAMALARI YAP
			int num = 0;
			for (int i = 1; i <= totalLines; i++) 
			{
				num++;
				String currentLine = commands2.readLine();			
				String[] commandContent =currentLine.split("\t");
				
				calculateBurnedCalori();		
				calculateGainedCalori();
				if (commandContent[0].endsWith(")")) {
						String[] splitID = commandContent[0].split("\\(");
						String getPersonID= splitID[1].replace(")", "");
						
						getFeatures(getPersonID,num);
						
				}
				
						
						
						
				else if (commandContent[0].equals("printList")) {
					System.out.println(i);
//					System.out.println(listOfFeaturesOfPeople1);
					int indexOfString = 0;
					for (String string : featuresOfPople(listOfFeaturesOfPeople1)) {
//						System.out.println(string);	
						if (indexOfString >=2 && indexOfString <=5) {
							if (indexOfString==5) {
								if(Integer.parseInt(string) > 0) {
									pw.print("+"+string+"kcal"+"\t");
								}
								else {
									pw.print(string+"kcal"+"\t");
								}
							}
							else {
								pw.print(string+"kcal"+"\t");
							}
							
						}
						else if(indexOfString ==6) {
							pw.println();	
							pw.print(string+"\t");
							indexOfString =0;					
						}
						else {
							
							pw.print(string+"\t");								
						}		
						indexOfString++;
					
					}
					if (i!= totalLines) 
					{
						pw.println();
						pw.println("***************"); 
						pw.flush();
					}
				pw.flush();
				}
				
				else if (commandContent[0].equals("printWarn")) {
					featuresOfPople(listOfFeaturesOfPeople1);
					ArrayList<String> checkWarn= new ArrayList<>();
					
					int number = 6;
					for (int k = -1; k < listOfFeaturesOfPeople1.size(); k+=6) 
					{
						
						
						if(k>=0 && Integer.parseInt(listOfFeaturesOfPeople1.get(k)) > 0) 
						{
						checkWarn.add("c");
						
						for (int j = 0; j <6 ; j++) {
							number--;
							if (number>= 0 && number <=3) {
								
								if (number==0) 
								{
									pw.write("+"+listOfFeaturesOfPeople1.get(k-number)+"kcal"+"\t");
									pw.println();
									
									number =6;									
								}
								else {
									pw.write(listOfFeaturesOfPeople1.get(k-number)+"kcal"+"\t");
								}
							}
							else {
								
								pw.write(listOfFeaturesOfPeople1.get(k-number)+"\t");
														
							}
						}
						}
					
					}
					pw.flush();
					
					if (checkWarn.size()==0) { 
						
						pw.write("There	is	no	such	person");
						pw.println();
						if (i!= totalLines)
						{
							pw.println("***************");	
						}
						
						pw.flush();
					}
					else {
						if (i!= totalLines)
					{
						pw.println("***************");	
						pw.flush();
					}
				}
					
					
				}
				else {
				
				  for (int j = 0; j < persons.listAllPerson().size(); j+=6) {
					if (persons.listAllPerson().get(j).equals(commandContent[0]) ) {
						for (int j2 = 0; j2 < foods.listAllFoods().size() || j2<sports.listAllSports().size(); j2+=3) {
							if (j2<foods.listAllFoods().size() && foods.listAllFoods().get(j2).equals(commandContent[1])) {
								int gainedKcal = Integer.parseInt(foods.listAllFoods().get(j2+2))* Integer.parseInt(commandContent[2]);
								listOfPersonFromFood.add(commandContent[0]);
								listOfCaloriGained.add(gainedKcal);
								if (listOfPerson.contains(commandContent[0])) {
									
								}else {
									listOfPerson.add(commandContent[0]);
								}
								
								pw.format("%s	has	taken	%dkcal	from	%s",commandContent[0],gainedKcal,foods.listAllFoods().get(j2+1));
								pw.println();
								if (i!= totalLines) 
								{
									pw.println("***************");
								}												
								pw.flush();
							}
							else if (j2<sports.listAllSports().size() && sports.listAllSports().get(j2).equals(commandContent[1])) {
								
								for(double minutes =0;minutes<1000;minutes++) {
									
									if (minutes == Integer.parseInt(commandContent[2])){
									int burnedKcal =(int) (Integer.parseInt(sports.listAllSports().get(j2+2))* (minutes/60));
									
									listOfPersonFromSport.add(commandContent[0]);
									listOfCaloriBurned.add(burnedKcal);
									if (listOfPerson.contains(commandContent[0])) {
										
									}else {
										listOfPerson.add(commandContent[0]);
									}
									
									
									pw.format("%s	has	burned	%dkcal	thanks to	%s",commandContent[0],burnedKcal,sports.listAllSports().get(j2+1));
									pw.println();
									if (i!=totalLines) 
									{
										pw.println("***************");	
									}
																	
									pw.flush();	
									}
								}
								
							}
							
						}
						
					}
					
					
				}
				
				}
				
			  }
			
			
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	
	}
	public ArrayList<Integer> calculateGainedCalori() {
		
		for(int i=0; i<listOfPersonFromFood.size();i++) {
			for(int j =1;i+j<listOfCaloriGained.size();j++) {
				if(listOfPersonFromFood.get(i).equals(listOfPersonFromFood.get(i+j))) {
					listOfCaloriGained.set(i,listOfCaloriGained.get(i)+listOfCaloriGained.get(i+j ) );
					listOfPersonFromFood.remove(i+j);
					listOfCaloriGained.remove(i+j);
				}
				else if(Collections.frequency(listOfPersonFromFood,listOfPersonFromFood.get(i)) ==1);				
			}
		}
		return listOfCaloriGained;
	}
	
	public ArrayList<Integer> calculateBurnedCalori() {
		for(int i=0; i<listOfPersonFromSport.size();i++) {
			for(int j =1;i+j<listOfCaloriBurned.size();j++) {
				if(listOfPersonFromSport.get(i).equals(listOfPersonFromSport.get(i+j))) {
					listOfCaloriBurned.set(i,listOfCaloriBurned.get(i)+listOfCaloriBurned.get(i+j ) ); //gfxhfh
				}
				else if(Collections.frequency(listOfPersonFromSport,listOfPersonFromSport.get(i)) ==1);				
			}
		}
		return listOfCaloriBurned;
		
	}
	
	public void getFeatures(String personID,int num) {
		
		FileWriter file2;
		try {
			BufferedReader commands2 = new BufferedReader(new FileReader("command.txt"));
			int totalLines = 0;
			while (commands2.readLine() != null)
				totalLines++;
			commands2.close();
			
			file2 = new FileWriter("monitoring.txt", true);
			PrintWriter pw = new PrintWriter(file2);
			
			for (int j2 = 0; j2 < persons.listAllPerson().size(); j2+=6) 
			{							
				if (personID.equals(persons.listAllPerson().get(j2))) {
					String name = persons.listAllPerson().get(j2+1);
					String gender = persons.listAllPerson().get(j2+2);
					int age =2022 - Integer.parseInt(persons.listAllPerson().get(j2+5));
					int weight = Integer.parseInt(persons.listAllPerson().get(j2+3));
					int height = Integer.parseInt(persons.listAllPerson().get(j2+4));
					int caloriGained = 0;
					for (int j = 0; j < listOfPersonFromFood.size(); j++) {
					
						if (listOfPersonFromFood.get(j).equals(personID)) {
							caloriGained = listOfCaloriGained.get(j); //261 + 964
							break;
						}
					}
					
					int caloriBurned = 0;
					for (int j = 0; j < listOfPersonFromSport.size(); j++) {
						if (listOfPersonFromSport.get(j).equals(personID)) {
							caloriBurned = listOfCaloriBurned.get(j);
							break;
						}
					}
					
					
					if (gender.equals("male")) {
						double caloriForMale = (66+(13.75*weight) + (5*height) - (6.8*age)); 	
						double result = caloriGained - Math.round(caloriForMale) - caloriBurned;
						List<String> features = Arrays.asList(name,String.valueOf(age),String.valueOf(Math.round(caloriForMale)),String.valueOf(caloriGained),String.valueOf(caloriBurned),String.valueOf(Math.round(result)));
						
						pw.format("%s	%d	%dkcal	%dkcal	%dkcal	%dkcal",name,age,Math.round(caloriForMale),caloriGained,caloriBurned,Math.round(result));
						pw.println();
						if (num!= totalLines-1) 
						{
							pw.println("***************");
						}
												
						pw.flush();
						
					}
					else if (gender.equals("female")) {
						double caloriForFemale = (665 + (9.6*weight) + (1.7*height) - (4.7*age)); 
						double result = caloriGained - Math.round(caloriForFemale) - caloriBurned;
						List<String> features =  Arrays.asList(name,String.valueOf(age),String.valueOf(Math.round(caloriForFemale)),String.valueOf(caloriGained),String.valueOf(caloriBurned),String.valueOf(Math.round(result)));
						
						pw.format("%s	%d	%dkcal	%dkcal	%dkcal	%dkcal",name,age,Math.round(caloriForFemale),caloriGained,caloriBurned,Math.round(result));
						pw.println();
						if (num!=totalLines-1) 
						{
							pw.println("***************");	
						}
											
						pw.flush();
						
				}
				}
			}
			
			
		
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public ArrayList<String> featuresOfPople(ArrayList<String> listOfFeaturesOfPeople) {
		for (int j2 = 0; j2 < listOfPerson.size(); j2++) {
		for (int j = 0; j < persons.listAllPerson().size(); j+=6) {		
				if (listOfPerson.get(j2).equals(persons.listAllPerson().get(j))) {
					String name = persons.listAllPerson().get(j+1);
					String gender = persons.listAllPerson().get(j+2);
					int age =2022 - Integer.parseInt(persons.listAllPerson().get(j+5));
					int weight = Integer.parseInt(persons.listAllPerson().get(j+3));
					int height = Integer.parseInt(persons.listAllPerson().get(j+4));
					int caloriGained = 0;
					for (int j3 = 0; j3 < listOfPersonFromFood.size(); j3++) {
						
						if (listOfPersonFromFood.get(j3).equals(listOfPerson.get(j2))) {
							caloriGained = listOfCaloriGained.get(j3); //261 + 964
							break;
						}
					}
					
					int caloriBurned = 0;
					for (int j3 = 0; j3 < listOfPersonFromSport.size(); j3++) {
						if (listOfPersonFromSport.get(j3).equals(listOfPerson.get(j2))) {
							caloriBurned = listOfCaloriBurned.get(j3);
							break;
						}
					}
					
				
					 if (gender.equals("male")) {
						 
						double caloriForMale = (66+(13.75*weight) + (5*height) - (6.8*age)); 	
						double result = caloriGained - Math.round(caloriForMale) - caloriBurned;
						if (listOfFeaturesOfPeople.contains(name)){
							listOfFeaturesOfPeople.set(listOfFeaturesOfPeople.indexOf(name)+3, String.valueOf(caloriGained));	
							listOfFeaturesOfPeople.set(listOfFeaturesOfPeople.indexOf(name)+4, String.valueOf(caloriBurned));	
							listOfFeaturesOfPeople.set(listOfFeaturesOfPeople.indexOf(name)+5, String.valueOf(Math.round(result)));
						}
						else {
							
						
						List<String> features = Arrays.asList(name,String.valueOf(age),String.valueOf(Math.round(caloriForMale)),String.valueOf(caloriGained),String.valueOf(caloriBurned),String.valueOf(Math.round(result)));
						
						for (String s:features) {
							
							if (listOfFeaturesOfPeople.contains(s) && s.equals("0")!= true && s.equals("306")!= true) {
								continue;
							}else 
							{
								listOfFeaturesOfPeople.add(s);
							}
						
						}
						}
					}
					else if (gender.equals("female")) {
						
						double caloriForFemale = (665 + (9.6*weight) + (1.7*height) - (4.7*age)); 
						double result = caloriGained - Math.round(caloriForFemale) - caloriBurned;
						if (listOfFeaturesOfPeople.contains(name)){
							listOfFeaturesOfPeople.set(listOfFeaturesOfPeople.indexOf(name)+3, String.valueOf(caloriGained));	
							listOfFeaturesOfPeople.set(listOfFeaturesOfPeople.indexOf(name)+5, String.valueOf(Math.round(result)));
						}
						else {
							
						
						List<String> features =  Arrays.asList(name,String.valueOf(age),String.valueOf(Math.round(caloriForFemale)),String.valueOf(caloriGained),String.valueOf(caloriBurned),String.valueOf(Math.round(result)));
						
						for (String s:features) {
							if (listOfFeaturesOfPeople.contains(s) && s.equals("0")!= true) {
								continue;
							}else 
							{
								listOfFeaturesOfPeople.add(s);
							}
						}
						}
						
				}
					
				
				}
				
				
			}
			
		}
		return listOfFeaturesOfPeople;
		
	}
	
}