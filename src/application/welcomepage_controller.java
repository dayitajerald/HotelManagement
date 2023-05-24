package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class welcomepage_controller {

    @FXML
    private Button loginbtn;

    @FXML
    private Button cancelbtn;

    @FXML
    private Button checkinbtn;

    @FXML
    private Button checkoutbtn;

    @FXML
    void cancel_cust(ActionEvent event) {

    }

    @FXML
    void check_in_cust(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("checkin.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Check In");
    		stage.setScene(new Scene(root1));
    		stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void check_out_btn(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("checkout.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Check Out");
    		stage.setScene(new Scene(root1));
    		stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void login_cust(ActionEvent event) {

    }

}
