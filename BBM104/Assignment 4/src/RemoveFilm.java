import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RemoveFilm extends Info{
	Scene scene;
	Go getFilms = new Go();
	public void remove(Stage primaryStage,String username) throws Exception {
		
		primaryStage.setResizable(false);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);	
		grid.setVgap(10);
		grid.setHgap(10);
		
		Text intro =  new Text("Select the film that you desire to remove and then click OK.");
		
		ChoiceBox<String> moviesBox = new ChoiceBox<>();
		getFilms.go(primaryStage);
		moviesBox.setItems(getFilms.moviesName);
		moviesBox.setValue(getFilms.moviesName.get(0));
		StackPane.setMargin(moviesBox, new Insets(20,20,20,20));
		StackPane.setAlignment(moviesBox, Pos.CENTER);
		moviesBox.setMinWidth(300);
		
		
		HBox hBox = new HBox();
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
		

		
		HBox.setMargin(backButton, new Insets(20,10,20,10));
		HBox.setMargin(oKButton, new Insets(20,10,20,10));
		hBox.getChildren().addAll(backButton,oKButton);
		hBox.setAlignment(Pos.BOTTOM_CENTER);
		
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(intro,moviesBox,hBox);
		vBox.setAlignment(Pos.CENTER);
		
		
		
		scene = new Scene(vBox,500,200);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
