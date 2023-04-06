import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddFilm extends Info{
	Scene scene;
	Text error = new Text();
	Go getMovie = new Go();
	public void addFilm(Stage primaryStage,String username) throws Exception {
		
		
		primaryStage.setResizable(false);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);	
		grid.setVgap(10);
		grid.setHgap(10);

		
		Text intro =  new Text("Please give name, relative path of the trailer and duration of the film.");
		
		
		//Name	
		Label name = new Label("Name:");
		TextField nameInput = new TextField();
		GridPane.setConstraints(name, 0, 0);
		GridPane.setConstraints(nameInput, 1, 0);
	
		
		//Trailer
		Label trailer = new Label("Trailer (Path):");
		TextField trailerInput = new TextField();
		GridPane.setConstraints(trailer, 0, 1);
		GridPane.setConstraints(trailerInput, 1, 1);
		GridPane.setHalignment(nameInput, HPos.LEFT);
		
		//Duration
		Label duration = new Label("Duration (m):");
		TextField durationInput = new TextField();
		GridPane.setConstraints(duration, 0, 2);
		GridPane.setConstraints(durationInput, 1, 2);
		GridPane.setHalignment(nameInput, HPos.LEFT);
		
		GridPane.setHalignment(backButton, HPos.LEFT);
		GridPane.setValignment(backButton, VPos.BOTTOM);
		GridPane.setConstraints(backButton, 0, 4);
		backButton.setOnAction(e->{
			Login log = new Login();
			Welcome welcomePage = new Welcome();
			try {
				welcomePage.welcome(primaryStage,username);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		oKButton.setOnAction(e->{
			try {
				checkInfo(nameInput, trailerInput, durationInput,primaryStage);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		GridPane.setHalignment(oKButton, HPos.RIGHT);
		GridPane.setValignment(oKButton, VPos.BOTTOM);
		GridPane.setConstraints(oKButton, 1, 4);
		
		grid.getChildren().addAll(name,nameInput,trailer,trailerInput,duration,durationInput,oKButton,backButton);
		VBox paneBox = new VBox();
		VBox.setMargin(intro, new Insets(10, 10, 10, 10));
		VBox.setMargin(error, new Insets(10, 10, 10, 10));
		paneBox.getChildren().addAll(intro,grid);
		paneBox.getChildren().add(error);
		paneBox.setAlignment(Pos.CENTER);
		
		scene = new Scene(paneBox,500,300);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	public void checkInfo(TextField nameInput, TextField trailerInput, TextField durationInput,Stage primaryStage) throws Exception {
		getMovie.go(primaryStage);
		FileWriter file = new FileWriter(
				"assets\\data\\backup.dat", true);
		PrintWriter pw = new PrintWriter(file);
		Boolean a= true;
		if (nameInput.getText().equals("")) {		
			oKButton.setOnMouseClicked(e->{
				error.setText("ERROR: Film name couldn't be empty!");
				es.play();
			});
			a = false;
		}
		
		for (Movies m :getMovie.films) {		
			 if (nameInput.getText().equals(m.getFilmName())) {
				
				oKButton.setOnMouseClicked(e -> {
					error.setText("ERROR: This movie already exists!");
					es.play();
				});
				a= false;
				
			}
		}
		if (a) {
			if (trailerInput.getText().equals("")) {		
				oKButton.setOnMouseClicked(e->{
					error.setText("ERROR: Movie path couldn't be empty!");
					es.play();
				});
			}
		}
		File file2 =new File("assets\\trailers\\"+trailerInput.getText());
	
		
 		if (file2.exists() && !trailerInput.getText().equals("")) {
			try {
				String dur = durationInput.getText();
				if (Integer.parseInt(dur)>0) {
					pw.println("film"+"\t"+nameInput.getText()+"\t"+trailerInput.getText()+"\t"+durationInput.getText());
					pw.flush();
					oKButton.setOnMouseClicked(e -> {
						error.setText("SUCCES: Film added succesfully!");
					});
				}else {
					oKButton.setOnMouseClicked(i->{
						error.setText("ERROR: Duration has to be a positive integer!");
						es.play();
					});
				}
			}catch (Exception e) {
				oKButton.setOnMouseClicked(i->{
					error.setText("ERROR: Duration has to be a positive integer!");
					es.play();
				});
			}
		}
 		else if (!file2.exists()) {
			oKButton.setOnMouseClicked(e->{
				error.setText("ERROR: There is no such a file!");
				es.play();
			});
		}

 			
		
		
	}	
	
}
