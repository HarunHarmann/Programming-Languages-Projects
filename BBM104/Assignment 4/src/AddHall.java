

import java.io.FileWriter;
import java.io.PrintWriter;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddHall extends Info{
	Scene scene;
	Go	hall = new Go();
	Text error = new Text();
 	public void addHall(Stage primaryStage,String username,Text intro,ChoiceBox<String> movies,String filmName) {
		primaryStage.setResizable(false);
		
		Text row = new Text("Row:");
		Text col = new Text("Column:");
		Text hallName = new Text("Name:");
		Text price = new Text("Price:");
		
		ChoiceBox<Integer> chooseRowBox = new ChoiceBox<>();
		ChoiceBox<Integer> chooseColBox = new ChoiceBox<>();
		for (int i = 3; i < 11; i++) {
			chooseRowBox.getItems().add(i);
			chooseColBox.getItems().add(i);
		}
		chooseRowBox.setValue(7);
		chooseColBox.setValue(3);
		
		TextField hallInput = new TextField();
		TextField priceInput = new TextField();

		backButton.setOnAction(e->{
			FilmWindow movieWindow = new FilmWindow();
			
			try {
				movieWindow.showMovie(primaryStage, movies, username);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		oKButton.setOnAction(e->{
			try {
				checkInfo(primaryStage, hallInput, priceInput, filmName, chooseRowBox, chooseColBox);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		VBox leftPart = new VBox();
		VBox.setMargin(row, new Insets(10,10,20,10));
		VBox.setMargin(col, new Insets(5,10,20,10));
		VBox.setMargin(hallName, new Insets(5,10,20,10));
		VBox.setMargin(price, new Insets(5,10,5,10));
		VBox.setMargin(backButton, new Insets(20,10,5,10));
		
		leftPart.getChildren().addAll(row,col,hallName,price,backButton);
		
		VBox rightPart = new VBox();
		VBox.setMargin(chooseRowBox, new Insets(10,10,5,10));
		VBox.setMargin(chooseColBox, new Insets(5,10,5,10));
		VBox.setMargin(hallInput, new Insets(5,10,5,10));
		VBox.setMargin(priceInput,  new Insets(5,10,5,10));
		VBox.setMargin(oKButton,  new Insets(20,10,5,10));
		
		rightPart.getChildren().addAll(chooseRowBox,chooseColBox,hallInput,priceInput,oKButton);
		
		HBox paneHBox = new HBox();
		paneHBox.getChildren().addAll(leftPart,rightPart);
		
		GridPane grid = new GridPane();
		GridPane.setConstraints(paneHBox, 0, 1);
		GridPane.setConstraints(intro, 0, 0);
		GridPane.setConstraints(error, 0, 2);
		GridPane.setValignment(error, VPos.BOTTOM);
		GridPane.setHalignment(error, HPos.CENTER);
		GridPane.setValignment(intro, VPos.TOP);
		GridPane.setHalignment(intro, HPos.CENTER);
		grid.getChildren().addAll(intro,paneHBox);
		grid.getChildren().add(error);
		
	
		scene = new Scene(grid,320,300);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	
	public void checkInfo(Stage primaryStage,TextField hallInput,TextField priceInput,String filmName, ChoiceBox<Integer> ChooseRowBox, ChoiceBox<Integer> ChooseColBox) throws Exception {
		hall.go(primaryStage);
		FileWriter file = new FileWriter(
				"assets\\data\\backup.dat", true);
		PrintWriter pw = new PrintWriter(file);
		
		if (hallInput.getText().equals("")) {
			oKButton.setOnMouseClicked(e->{
				error.setText("ERROR: Hall name couldn't be empty!");
				es.play();
			});
		}
		for (String i : hall.hallsName) {
			if (i.equals(hallInput.getText())) {
				oKButton.setOnMouseClicked(e->{
					error.setText("ERROR: This hall already exists!");
					es.play();
				});
			}
		}
		if (priceInput.getText().equals("") && !hallInput.getText().equals("")) {
			oKButton.setOnMouseClicked(e -> {
				error.setText("SUCCES: Price couldn't be empty!");
			});
		}
		if (!priceInput.getText().equals("")) {
		  try {	
			if (Integer.parseInt(priceInput.getText())>0) {
				String r = String.valueOf(ChooseRowBox.getSelectionModel().getSelectedItem());
				String c = String.valueOf(ChooseColBox.getSelectionModel().getSelectedItem());
				pw.println("hall"+"\t"+filmName+"\t"+hallInput.getText()+"\t"+priceInput.getText()+"\t"+r+"\t"+c);
				pw.flush();
				oKButton.setOnMouseClicked(e -> {
					error.setText("SUCCES: Hall added succesfully!");
					es.play();
				});
			}
			else {
				 oKButton.setOnMouseClicked(i -> {
						error.setText("ERROR: Price has to be a positive integer!");
						es.play();
					});
			}
		  }catch (Exception e) {
			  oKButton.setOnMouseClicked(i -> {
					error.setText("ERROR: Price has to be a positive integer!");
					es.play();
				});
		}
		}
	}
}
