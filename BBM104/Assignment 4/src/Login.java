
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Info {

	Stage window;
	Scene scene;
	Text error = new Text();
	int i = 0;

	public void logIn(Stage primaryStage) throws Exception {
		Go backup = new Go();
		backup.go(window);

		window = primaryStage;
		window.setTitle(backup.title); // BU DEÐÝÞECEK PROPERTÝES

		GridPane grid = new GridPane();

		grid.setAlignment(Pos.CENTER);

		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		GridPane.setConstraints(intro, 0, 0, 2, 1);

		// Name
		GridPane.setConstraints(name, 0, 1);
		nameInput.setPromptText("Enter a name");
		GridPane.setConstraints(nameInput, 1, 1);

		// Password
		GridPane.setConstraints(password, 0, 2);
		passInput.setPromptText("Enter a password");
		GridPane.setConstraints(passInput, 1, 2);

		// Buttons
		int count = 0;
		ArrayList<Integer> nums = new ArrayList<>();

		login.setOnAction(e -> {

			try {
				checkName(nameInput, passInput, backup, primaryStage, grid, count, nums);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		});
		GridPane.setConstraints(login, 1, 3);
		GridPane.setHalignment(login, HPos.RIGHT);

		signup.setOnAction(e -> {
			Signup signupPage = new Signup();
			try {
				signupPage.signUp(window);
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		});
		GridPane.setConstraints(signup, 0, 3);
		GridPane.setConstraints(error, 1, 4);
		grid.getChildren().addAll(name, error, nameInput, password, passInput, login, signup, intro);

		scene = new Scene(grid, 500, 300);
		window.setScene(scene);
		window.show();

	}

	public void checkName(TextField nameInput, TextField passwordInput, Go backup, Stage st, GridPane grid, int count,
			ArrayList<Integer> nums) throws Exception {
		backup.go(st);
		Set<String> usernames = backup.user.keySet();

		if (usernames.contains(nameInput.getText())) {
			checkPassword(nameInput, passwordInput, backup, st, grid, count, nums);

		} else {

			login.setOnMouseClicked(e -> {
				error.setText("ERROR: There is no such a credential!");
				es.stop();
				es.play();
				nameInput.clear();
			});
			nums.add(count);

			checkMaxError(nums, backup, grid);
		}

	}

	public void checkPassword(TextField nameInput, TextField passwordInput, Go backup, Stage st, GridPane grid, int num,
			ArrayList<Integer> nums) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(passwordInput.getText().getBytes());
		byte[] digest = md.digest();
		String myHash = Base64.getEncoder().encodeToString(digest);

		if (myHash.equals(backup.user.get(nameInput.getText()))) {
			Welcome welcomePage = new Welcome();
			try {
				welcomePage.welcome(window, nameInput.getText());
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		} else {

			login.setOnMouseClicked(e -> {
				error.setText("ERROR: Wrong password!");
				passwordInput.clear();
				es.stop();
				es.play();

			});
			nums.add(num);

			try {
				checkMaxError(nums, backup, grid);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void checkMaxError(ArrayList<Integer> num, Go backup, GridPane grid) throws InterruptedException {

		if (num.size() == backup.maxError) {
			Timer timer = new Timer();
			TimerTask tt = new TimerTask() {
				
				
				@Override
				public void run() {
			
						error.setText("ERROR: Please wait "+String.valueOf(backup.blockTime)+" seconds to make a new operation!");
						try {
							timer.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
			};
			login.setOnMouseClicked(e -> {		
				
				timer.schedule(tt, 0, backup.blockTime * 1000);
					
				es.stop();
				es.play();
			});
		
		

		}

	}

}
