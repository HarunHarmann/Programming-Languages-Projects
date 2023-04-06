

import java.io.FileInputStream;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HallWindow extends Info{
	Stage window;
	Scene scene;
	int row;
	int col;
	Go getFilms = new Go();
	Button availableSeat;
	int newPrice=0;
	
	public void showHall(Stage primaryStage, String selectedHallName,String text, ChoiceBox<String> movies,String username) throws Exception {
		window = primaryStage;  
		getFilms.go(primaryStage);
		
		for (Hall i : getFilms.halls) {
			if (i.getHallName().equals(selectedHallName)) {
				row = i.getRow();
				col = i.getColumn();
			}
		}
		
		Text intro = new Text(text+" Hall: "+selectedHallName);
		Text warning = new Text();
		  
	    ChoiceBox<String> users = new ChoiceBox<>();
	    for (Users u : getFilms.users) {
			users.getItems().add(u.getName());
			users.setValue(u.getName());
		}
	    
	    VBox page = new VBox();
	    VBox.setMargin(intro, new Insets(10, 10, 10, 10));
	    page.getChildren().add(intro);
	    
	    for (int i = 0; i < row; i++) {
	    	HBox seatCol = new HBox();
	 	    for (int j = 0; j < col; j++) {
	 	    	FileInputStream input = new FileInputStream("assets\\icons\\empty_seat.png");
	 			Image img = new Image(input);
	 			ImageView view = new ImageView(img);
	 			view.setFitHeight(40);
	 		    view.setPreserveRatio(true);
	 	    	
	 			availableSeat = new Button();
	 			HBox.setMargin(availableSeat, new Insets(10, 10, 10, 10));
	 			availableSeat.setPrefSize(30,40);
	 			availableSeat.setGraphic(view);
	 			seatCol.getChildren().add(availableSeat);
	 			availableSeat.setOnMouseEntered(e->{
	 				warning.setText("Not bought yet!");
	 			});
	 			availableSeat.setOnMouseExited(e->{
	 				warning.setText("");
	 			});
	 			
	 			
	 		}
			page.getChildren().add(seatCol);
			
		}
	    
		backButton.setOnAction(e->{
			FilmWindow movieWindow = new FilmWindow();
			
			try {
				movieWindow.showMovie(primaryStage, movies, username);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		availableSeat.setOnAction(e->{
			String currentUser = users.getSelectionModel().getSelectedItem();
			for (Users u : getFilms.users) {
				if (u.getName().equals(currentUser)) {
					if (u.getClubMember().equals("true")) {
						for (Hall h: getFilms.halls) {
							if (h.getHallName().equals(selectedHallName)) {
								newPrice = h.getPrice() - h.getPrice()*getFilms.discountP/100;
							}
						}
					}
					else if(u.getClubMember().equals("false")){
						for (Hall h: getFilms.halls) {
							if (h.getHallName().equals(selectedHallName)) {
								newPrice = h.getPrice();
							}
						}
					}
				}
			}
		});
	    
	    VBox.setMargin(users, new Insets(10, 10, 10, 10));
	    VBox.setMargin(intro, new Insets(10, 10, 1, 1));
	    
	    
	    
		Boolean b = false;
		for (Users i : getFilms.users) {
			if (i.getAdmin().equals("true") && i.getName().equals(username)) {
				b = true;
			}
		}
		
		if (b) {
			page.getChildren().add(users);
		    page.getChildren().add(backButton);
		    page.setAlignment(Pos.TOP_CENTER);
			scene = new Scene(page,800,850);
			
			
			page.getChildren().add(warning);
			
			window.setScene(scene);
			window.show();
		}
		else {
			page.getChildren().add(warning);
		    page.getChildren().add(backButton);
		    page.setAlignment(Pos.TOP_CENTER);
			scene = new Scene(page,800,850);
			window.setScene(scene);
			window.show();
			
		}
	    
	    
	   
	
	}
}
