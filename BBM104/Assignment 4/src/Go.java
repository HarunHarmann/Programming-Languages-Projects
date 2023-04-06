import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class Go {
	Login loginPage = new Login();
	String line;
	HashMap<String, String> user = new HashMap<>();
	HashMap<String, String> club = new HashMap<>();
	HashMap<String, String> admin = new HashMap<>();
	

	int maxError;
	String title;
	int discountP;
	int blockTime;
	ObservableList<Users> users = FXCollections.observableArrayList();
	ObservableList<Movies> films = FXCollections.observableArrayList();
	ObservableList<String> moviesName = FXCollections.observableArrayList();
	ObservableList<Hall> halls = FXCollections.observableArrayList();
	ObservableList<String> hallsName = FXCollections.observableArrayList();
	ObservableList<Seat> seats = FXCollections.observableArrayList();
	
	public void go(Stage st) throws Exception{
		BufferedReader properties = new BufferedReader(new FileReader("assets\\data\\properties.dat"));
		while((line = properties.readLine())!= null) {
			String[] content = line.split("=");
			if (content[0].equals("maximum-error-without-getting-blocked")) {
				maxError = Integer.parseInt(content[1]);
			}
			else if (content[0].equals("title")) {
				title = content[1];
			}
			else if (content[0].equals("discount-percentage")) {
				discountP = Integer.parseInt(content[1]);
			}
			else if (content[0].equals("block-time")) {
				blockTime = Integer.parseInt(content[1]);
			}
		}
		properties.close();
		
			
		BufferedReader backup = new BufferedReader(new FileReader("assets\\data\\backup.dat"));
		
		while ((line = backup.readLine()) != null) {
			String[] content = line.split("\t");
			if (content[0].equals("user")) {
				
				Users us = new Users(content[1], content[2],content[3], content[4]);
		
				users.add(us);
				user.put(content[1], content[2]);
				club.put(content[1], content[3]);
				admin.put(content[1], content[4]);
				
				
			}
			else if (content[0].equals("film")) {
				
				Movies movie = new Movies(content[1],content[2],Integer.parseInt(content[3]));
				films.add(movie);
				moviesName.add(content[1]);
				
			}
			else if (content[0].equals("hall")) {
				
				Hall hall = new Hall(content[1], content[2], Integer.parseInt(content[3]), Integer.parseInt(content[4]), Integer.parseInt(content[5]));
				halls.add(hall);
				hallsName.add(content[2]);
			}
			else if (content[0].equals("seat")) {
				Seat seat = new Seat(content[1], content[2], Integer.parseInt(content[3]), Integer.parseInt(content[4]), content[5], Integer.parseInt(content[6]));
				seats.add(seat);
			}
		}
		
	}
	public void load(Stage st) throws Exception {

		loginPage.logIn(st);
	}
}
