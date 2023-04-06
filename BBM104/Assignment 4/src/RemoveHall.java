import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RemoveHall extends Info {
	Scene scene;
	public void removeHall(Stage primaryStage,String username,ChoiceBox<String> halls, ChoiceBox<String> movies,String filmName) {
		
		Text intro = new Text("Select the hall that you desire to remove from the "+filmName+" and then click OK.");
		
		
		
		HBox buttonsBox = new HBox();
		HBox.setMargin(backButton, new Insets(10, 10, 10, 220));
		HBox.setMargin(oKButton, new Insets(10,10,10,10));
		buttonsBox.getChildren().addAll(backButton,oKButton);
		
		VBox page = new VBox();
		VBox.setMargin(intro, new Insets(20, 10, 10, 10));
		VBox.setMargin(halls, new Insets(10, 10, 10, 10));
		page.getChildren().addAll(intro,halls,buttonsBox);
		page.setAlignment(Pos.CENTER);
		
		backButton.setOnAction(e->{
			FilmWindow movieWindow = new FilmWindow();			
			try {
				movieWindow.showMovie(primaryStage, movies, username);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		scene = new Scene(page,600,200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
