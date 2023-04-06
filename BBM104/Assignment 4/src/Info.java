import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

public class Info {
	
	Text intro = new Text("Welcome to the HUCS Cinema Reservation System!"
			+ "\n"
			+ "Please enter your credentials below and click LOG IN."
			+ "\n"
			+ "You can create a new account by clicking SIGN UP button.");
	
	Text intro2 = new Text("           Welcome to the HUCS Cinema Reservation System!"
			+ "\n"
			+ "                 Fill the form below to create an account."
			+ "\n"
			+ "          You can go to LOG IN page by clicking LOG IN button.");
	
	
	//Name	
	Label name = new Label("Username:");
	TextField nameInput = new TextField();
	//Password
	Label password = new Label("Password:");
	PasswordField passInput = new PasswordField();
	
	Label password2 = new Label("Password:");
	PasswordField passInput2 = new PasswordField();
	
	Button login = new Button("LOG IN");
	Button signup = new Button("SIGN UP");
	Button oKButton = new Button("OK");
	Button backButton = new Button("< BACK");
	Button addButton = new Button("Add Film");
	Button removeButton = new Button("Remove Film");
	Button editButton = new Button("Edit Users");
	Button clubButton = new Button("Promote/Demote Club Member");
	Button adminButton = new Button("Promote/Demote Admin");
	
	Media errorSound = new Media(new File("assets\\effects\\error.mp3").toURI().toString());
	MediaPlayer es = new MediaPlayer(errorSound);
	
	
	public void updateBackUp(String name,String password,String club,String admin,String bool1, String bool,int a) {
		try {
			String newContent ="";
			String line;
			String[] oldContent = null;
			String[] content = null;
			BufferedReader backup = new BufferedReader(new FileReader("assets\\data\\backup.dat"));
			
			Path p = Paths.get("assets\\data\\backup.dat");
			List<String> fileContent = new ArrayList<>(Files.readAllLines(p, StandardCharsets.UTF_8));
			oldContent = content;
	
			for (int i = 0; i < fileContent.size(); i++) {		
				
				if (fileContent.get(i).contains(name)) {
					if (a==1) {
						fileContent.set(i, "user"+"\t"+name+"\t"+password+"\t"+bool+"\t"+admin);
						break;
					}else if (a== -1) {
						
						fileContent.set(i, "user"+"\t"+name+"\t"+password+"\t"+club+"\t"+bool);
						break;
					}
					
				}
			    
			}
			Files.write(p, fileContent, StandardCharsets.UTF_8);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
