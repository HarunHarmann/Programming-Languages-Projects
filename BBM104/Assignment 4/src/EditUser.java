
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditUser extends Info{
	Scene scene;
	TableView<Users> table;
	Go getUsers = new Go();
	ObservableList<Users> audience = getUsers.users;
	
	public void edit(Stage primaryStage,String username) throws Exception {
		
		primaryStage.setResizable(false);
		
		
		
		
		//Username column
		TableColumn<Users, String> userNameColumn = new TableColumn<>("Username");
		userNameColumn.setMinWidth(100);
		userNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		// Club member column
		TableColumn<Users, String> clubMemberColumn = new TableColumn<>("Club Member");
		clubMemberColumn.setMinWidth(150);
		clubMemberColumn.setCellValueFactory(new PropertyValueFactory<>("clubMember"));
		
		//Admin column
		TableColumn<Users, Boolean> adminColumn = new TableColumn<>("Admin");
		adminColumn.setMinWidth(100);
		adminColumn.setCellValueFactory(new PropertyValueFactory<>("admin"));
		
	
		
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
		
		clubButton.setOnAction(e->{
			int a = 0;
			Users user = table.getSelectionModel().getSelectedItem();
			if (user.getClubMember().equals("false")) {
				a=1;
				user.setClubMember("true");
				table.getItems().add(table.getSelectionModel().getSelectedIndex(), user);
				table.getItems().remove(table.getSelectionModel().getSelectedIndex()-1);
				updateBackUp(user.getName(),user.getPassword(),user.getClubMember(),user.getAdmin(),"false","true",a);
				
			
				
			}else {
				a=1;
				user.setClubMember("false");
				table.getItems().add(table.getSelectionModel().getSelectedIndex(), user);
				table.getItems().remove(table.getSelectionModel().getSelectedIndex()-1);
				updateBackUp(user.getName(),user.getPassword(),user.getClubMember(),user.getAdmin(),"true","false",a);
			}
			table.refresh();
		});
		
		adminButton.setOnAction(e->{
			int a = 0;
			Users user = table.getSelectionModel().getSelectedItem();
			if (user.getAdmin().equals("false")) {		
				a=-1;
				user.setAdmin("true");
				table.getItems().add(table.getSelectionModel().getSelectedIndex(), user);
				table.getItems().remove(table.getSelectionModel().getSelectedIndex() -1);
				updateBackUp(user.getName(),user.getPassword(),user.getClubMember(),user.getAdmin(),"false","true",a);
			}else {
				a=-1;
				user.setAdmin("false");
				table.getItems().add(table.getSelectionModel().getSelectedIndex(), user);
				table.getItems().remove(table.getSelectionModel().getSelectedIndex()-1);
				updateBackUp(user.getName(),user.getPassword(),user.getClubMember(),user.getAdmin(),"true","false",a);
			}
			table.refresh();
			
		});
		
		getUsers.go(primaryStage);
		ObservableList<Users> uba = FXCollections.observableArrayList();
		for (Users u : audience) {		
			if (u.getName().equals(username)) {
	
			}else {
				uba.add(u);
			}
		}
		
		table = new TableView<>();
		table.setItems(uba);
		
		table.setPlaceholder(
			    new Label("No user available in the " + 
			    		"database!"));
		table.getColumns().addAll(userNameColumn,clubMemberColumn,adminColumn);
		
		
		
		table.getSelectionModel().selectFirst();
		
		
		hBox.getChildren().addAll(backButton,clubButton,adminButton);
		HBox.setMargin(clubButton, new Insets(20,10,20,10));
		HBox.setMargin(adminButton, new Insets(20,10,20,10));
		HBox.setMargin(backButton, new Insets(20,10,20,10));
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(table,hBox);
		
		VBox.setMargin(table, new Insets(20,20,20,20));
		
		

		scene = new Scene(vBox,600,550);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
