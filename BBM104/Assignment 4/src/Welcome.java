
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Welcome extends Info{
	Stage window;
	Scene scene;
	Go getFilms = new Go();

	public void welcome(Stage primaryStage,String username) throws Exception {
		getFilms.go(primaryStage);
		window = primaryStage;
		
		
		
		StackPane page = new StackPane();
		StackPane page2 = new StackPane();
		Text intro = new Text();
		window.setResizable(false);
		
		
		int a = 0;
		for (Users i : getFilms.users) {
			if (i.getClubMember().equals("true") && i.getName().equals(username)) {
				a = 1;
			}
			else if (i.getClubMember().equals("false") && i.getAdmin().equals("true") && i.getName().equals(username) || i.getClubMember().equals("true") && i.getAdmin().equals("true")&& i.getName().equals(username)) {
				a=-1;
			}
		}
		if(a == 1) {
			intro = new Text("  Welcome ("+username+" - Club Member"+")!"+"\n"
					 + " Select a film and then click OK to continue.");
		}
		else if (a == -1) {
			intro = new Text("  Welcome "+username+"!"+"\n"
					 + " You can either select a film below or edits.");
		}
		else {
			intro = new Text("  Welcome "+username+"!"+ "\n"
						 + " Select a film and then click OK to continue.");
		}
	
		StackPane.setMargin(intro, new Insets(20,20,20,20));
		StackPane.setAlignment(intro, Pos.TOP_CENTER);
		
		ChoiceBox<String> moviesBox = new ChoiceBox<>();
		
		moviesBox.setItems(getFilms.moviesName);
		moviesBox.setValue(getFilms.moviesName.get(0));
		StackPane.setMargin(moviesBox, new Insets(20,20,20,20));
		StackPane.setAlignment(moviesBox, Pos.CENTER_LEFT);
		moviesBox.setMaxWidth(300);
		
		
		StackPane.setMargin(oKButton, new Insets(20,20,20,0));
		StackPane.setAlignment(oKButton, Pos.CENTER_RIGHT);
		
		//Log out button
		Button logOutButton = new Button("LOG OUT");
		StackPane.setMargin(logOutButton, new Insets(50,20,20,20));
		StackPane.setAlignment(logOutButton, Pos.BOTTOM_RIGHT);
		logOutButton.setOnAction(e->{
			Login loginPage = new Login();
			try {
				loginPage.logIn(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		//Add film button
		addButton.setOnAction(e->{
			AddFilm add = new AddFilm();
			try {
				add.addFilm(primaryStage,username);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 		});
		
		
		//Remove film button
		removeButton.setOnAction(e->{
			RemoveFilm remove = new RemoveFilm();
			try {
				remove.remove(primaryStage,username);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		//Edit user button
		editButton.setOnAction(e->{
			EditUser editUser = new EditUser();
			try {
				editUser.edit(primaryStage,username);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		oKButton.setOnAction(e->{
			FilmWindow film = new FilmWindow();
			
			try {
				film.showMovie(primaryStage, moviesBox,username);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	
		
		HBox buttons = new HBox();
		
		HBox.setMargin(addButton, new Insets(20,10,30,10));
		HBox.setMargin(removeButton, new Insets(20,10,30,10));
		HBox.setMargin(editButton, new Insets(20,10,30,10));
		buttons.getChildren().addAll(addButton,removeButton,editButton);
		Group group = new Group(buttons);
		
		StackPane.setAlignment(group, Pos.BOTTOM_LEFT);
		
		
		
		page.setAlignment(Pos.CENTER);
		page2.setAlignment(Pos.CENTER);
		
		Boolean b = false;
		for (Users i : getFilms.users) {
			if (i.getAdmin().equals("true") && i.getName().equals(username)) {
				b = true;
			}
		}
		if (b)
	    {
			page.getChildren().addAll(intro,group,moviesBox,oKButton,logOutButton);
			scene = new Scene(page,450,200);
			window.setScene(scene);
		}
		else {
			page2.getChildren().addAll(intro,moviesBox,oKButton,logOutButton);
			scene = new Scene(page2, 450, 200);
			window.setScene(scene);
		}
		
		window.show();
	}

}
