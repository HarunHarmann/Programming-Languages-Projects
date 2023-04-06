
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Signup extends Info {

	Scene scene;
	Go getData = new Go();
	Text error = new Text();

	public void signUp(Stage primaryStage) throws Exception {

		getData.go(primaryStage);
		primaryStage.setResizable(false);
		primaryStage.setTitle("HU Cinema Reservation System");

		GridPane grid = new GridPane();

		grid.setAlignment(Pos.CENTER);

		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(20, 20, 20, 20));

		// Username
		GridPane.setConstraints(name, 0, 1);
		nameInput.setPromptText("Enter a name");
		GridPane.setConstraints(nameInput, 1, 1);
		GridPane.setHalignment(nameInput, HPos.LEFT);

		// Password
		GridPane.setConstraints(password, 0, 2);
		passInput.setPromptText("Enter a password");
		GridPane.setConstraints(passInput, 1, 2);
		GridPane.setHalignment(passInput, HPos.LEFT);

		GridPane.setConstraints(password2, 0, 3);
		passInput2.setPromptText("Enter a password");
		GridPane.setConstraints(passInput2, 1, 3);
		GridPane.setHalignment(passInput2, HPos.LEFT);

		login.setOnAction(e -> {
			Login log = new Login();
			try {
				log.logIn(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		GridPane.setConstraints(login, 0, 4);
		GridPane.setHalignment(login, HPos.RIGHT);
		GridPane.setConstraints(signup, 1, 4);
		GridPane.setHalignment(signup, HPos.RIGHT);

		grid.getChildren().addAll(name, nameInput, password, passInput, login, signup, password2, passInput2);

		VBox pane = new VBox();
		pane.getChildren().addAll(intro2, grid);
		pane.setAlignment(Pos.CENTER);

		signup.setOnAction(e -> {
			try {

				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(passInput.getText().getBytes());
				byte[] digest = md.digest();
				String myHash = Base64.getEncoder().encodeToString(digest);

				MessageDigest md2 = MessageDigest.getInstance("MD5");
				md2.update(passInput2.getText().getBytes());
				byte[] digest2 = md2.digest();
				String myHash2 = Base64.getEncoder().encodeToString(digest2);

				checkInfo(myHash, myHash2);

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		pane.getChildren().add(error);

		scene = new Scene(pane, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void checkInfo(String hashedpass, String hashedpass2) throws IOException {

		FileWriter file = new FileWriter(
				"assets\\data\\backup.dat", true);
		PrintWriter pw = new PrintWriter(file);
		Boolean a= true;
		if (nameInput.getText().equals("")) {
			signup.setOnMouseClicked(e -> {
				error.setText("ERROR: Username cannot be empty!");
				es.stop();
				es.play();
			});
			a=false;
		}
		
		for (Users u : getData.users) {
			 if (nameInput.getText().equals(u.getName())) {
				
				signup.setOnMouseClicked(e -> {
					error.setText("ERROR: This username already exists!");
					nameInput.clear();
					es.stop();
					es.play();
				});
				a= false;
			}
		}
		if (a) {
			if (passInput.getText().equals("")) {
				signup.setOnMouseClicked(e -> {
					error.setText("ERROR: Password cannot be empty!");
					passInput.clear();
					es.stop();
					es.play();
				});	
		}
			else if (!hashedpass.equals(hashedpass2)) {
				signup.setOnMouseClicked(e -> {
					error.setText("ERROR: Passwords don't match!");
					passInput2.clear();
					es.stop();
					es.play();
				});
				
			} 
			else {
				pw.println("user" + "\t" + nameInput.getText() + "\t" + hashedpass + "\t" + "false" + "\t" + "false");
				pw.flush();
				signup.setOnMouseClicked(e -> {
					error.setText("SUCCES: You have succesfully registered with your new credentials!");
				});
				
				
			}
			
		}
	}

}
