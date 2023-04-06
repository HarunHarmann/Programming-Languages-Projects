
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	Go command = new Go();
	public static void main(String[] args) {
		launch(args);  
   
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		command.load(primaryStage);
		
	}

}
