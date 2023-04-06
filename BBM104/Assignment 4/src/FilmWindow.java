
import java.io.File;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FilmWindow extends Info{
	Stage window;
	Scene scene;
	Go getFilms = new Go();
	Media media; 
	String path = "";
	String filmName;
	int duration;
	public void showMovie(Stage primaryStage,ChoiceBox<String> movies,String username) throws Exception {
		getFilms.go(primaryStage);
		window = primaryStage;
	
		
		String film = movies.getSelectionModel().getSelectedItem();
		for (Movies i : getFilms.films) {
			if (i.getFilmName().equals(film)) {
				path = "assets\\trailers\\"+i.getPath();
				filmName = i.getFilmName();
				duration = i.getDuration();
			}
			
		}
		
		GridPane grid = new GridPane();
	
		Media movie = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(movie); 
		mediaPlayer.setAutoPlay(false);
		MediaView mediaView = new MediaView (mediaPlayer);
		mediaView.setFitWidth(800);
		
		String text = filmName+"("+String.valueOf(duration)+" minutes)";
		Text intro = new Text(text);
		
		
		Button playButton = new Button("\u25B6");
		playButton.setMinWidth(40);	
		Button fivesecb = new Button("<<"); 
		Button fivesecf = new Button (">>");
		Button rewindButton = new Button("|<<");
		Button addHall = new Button("Add Hall");
		Button removeHall = new Button("Remove Hall");
		
		Slider volumeSlider = new Slider();
		volumeSlider.setOrientation(Orientation.VERTICAL);
		volumeSlider.setValue(mediaPlayer.getVolume()* 100);
		volumeSlider.valueProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				mediaPlayer.setVolume(volumeSlider.getValue()/100);
				
			}
		});
		
		VBox rightPart = new VBox();
		VBox.setMargin(playButton, new Insets(150,10,5,10));
		VBox.setMargin(fivesecb, new Insets(5,10,5,10));
		VBox.setMargin(fivesecf, new Insets(5,10,5,10));
		VBox.setMargin(rewindButton, new Insets(5,10,5,10));
		VBox.setMargin(volumeSlider, new Insets(5,10,5,10));
		
		rightPart.getChildren().addAll(playButton,fivesecb,fivesecf,rewindButton,volumeSlider);
		
		ChoiceBox<String> hallBox = new ChoiceBox<>();
		for (Hall h : getFilms.halls) {
			if(h.getFilmName().equals(filmName)){
				hallBox.getItems().add(h.getHallName());
				hallBox.setValue(h.getHallName());
			}
		}
		
		hallBox.setMinWidth(100);
		
		HBox bottombuttons = new HBox();
		HBox bottombuttons2 = new HBox();
		HBox.setMargin(backButton, new Insets(20,10,30,100));
		HBox.setMargin(hallBox, new Insets(20,10,30,10));
		HBox.setMargin(oKButton, new Insets(20,10,30,10));
		HBox.setMargin(addHall, new Insets(20,10,30,10));
		HBox.setMargin(removeHall, new Insets(20,10,30,10));
		
		
		
		VBox leftPart = new VBox();
		VBox.setMargin(intro, new Insets(30,10,5,300));
		VBox.setMargin(mediaView, new Insets(20,10,20,10));
		VBox.setMargin(bottombuttons, new Insets(20,10,30,300));
			
		
		backButton.setOnAction(e->{
			Welcome welcomePage = new Welcome();
			try {
				mediaPlayer.stop();
				welcomePage.welcome(primaryStage,username);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		playButton.setOnAction(e->{
			if (mediaPlayer.getStatus() == Status.PLAYING) {
				mediaPlayer.pause();
			}
			else {
				mediaPlayer.play();
			}			
		});
		
		
		
		fivesecb.setOnAction(e->{
			mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(5)));
		});
		
		fivesecf.setOnAction(e->{
			mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(5)));
		});
		
		rewindButton.setOnAction(e->{
			mediaPlayer.seek(mediaPlayer.getStartTime());
		});
		
		addHall.setOnAction(e->{
			AddHall newHall = new AddHall();
			newHall.addHall(window, username,intro,movies,filmName);
		});
		
		removeHall.setOnAction(e->{
			RemoveHall removeHal = new RemoveHall();
			removeHal.removeHall(window, username, hallBox, movies,filmName);
		});
		
		oKButton.setOnAction(e->{
			HallWindow hallWindow = new HallWindow();
			String hall = hallBox.getSelectionModel().getSelectedItem();
			try {
				hallWindow.showHall(window, hall, text,movies,username);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		Boolean b = false;
		for (Users i : getFilms.users) {
			if (i.getAdmin().equals("true") && i.getName().equals(username)) {
				b = true;
			}
		}
		
		GridPane.setConstraints(leftPart, 0, 0);
		GridPane.setConstraints(rightPart, 1, 0);
		if (b)
	    {
			bottombuttons2.getChildren().addAll(backButton,addHall,removeHall,hallBox,oKButton);
			leftPart.getChildren().addAll(intro,mediaView,bottombuttons2);
			grid.getChildren().addAll(rightPart,leftPart);
			
			scene = new Scene(grid,900,700);
			window.setScene(scene);
			window.show();
		}
		else {
			bottombuttons.getChildren().addAll(backButton,hallBox,oKButton);
			VBox.setMargin(bottombuttons, new Insets(30, 10,10, 190));
			leftPart.getChildren().addAll(intro,mediaView,bottombuttons);
			grid.getChildren().addAll(rightPart,leftPart);
			
			scene = new Scene(grid,900,700);
			window.setScene(scene);
			window.show();
		}
		
		
		
		
		
	}
}
