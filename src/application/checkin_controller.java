package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;

import Utility.*;


public class checkin_controller {
	Connection con =null;


    @FXML
    private Label rno1;

    @FXML
    private Label type3;

    @FXML
    private Label rno3;

    @FXML
    private Label type2;

    @FXML
    private Label type4;
    
    @FXML
    private Label rno2;

    @FXML
    private Label type1;

    @FXML
    private Label rno4;

    @FXML
    private TextField cnotf;

    @FXML
    private TextField nopeopletf;

    @FXML
    private TextField nodaystf;

    @FXML
    private TextField rnotf;

    @FXML
    private Button checkroombtn;

    @FXML
    private DatePicker indatetf;

    @FXML
    private Button bookroombtn;

    @FXML
    void book_room(ActionEvent event) {
    	try {
    	con = DBUtil.getConnection();
    	String sql ="insert into check_in_info(cust_no,room_no,check_in_date,no_of_days,no_of_people) values (?,?,?,?,?)";
		// preparing sql statement
		PreparedStatement p=con.prepareStatement(sql);
		p.setString(1,cnotf.getText());
		p.setString(2,rnotf.getText());
		java.util.Date date = java.util.Date.from(indatetf.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		p.setDate(3, sqlDate);
		p.setString(4,nodaystf.getText());
		p.setString(5,nopeopletf.getText());
		//To update values in database ie to execute
		p.executeUpdate();
		
		String sql1 ="update rooms_hotel set availabilty='No' where room_no= ?";
		// preparing sql statement
		PreparedStatement p1=con.prepareStatement(sql1);
		p1.setString(1,rnotf.getText());
		p1.executeUpdate();
		
		cnotf.clear();
		rnotf.clear();
		indatetf.setValue(null);
		nodaystf.clear();
		nopeopletf.clear();
    	}
    	catch(Exception s)
		{
			s.printStackTrace();
		}
		
    }

    @FXML
    void check_available_rooms(ActionEvent event) {
    	try {
        	rno1.setText("");
        	rno2.setText("");
        	rno3.setText("");
        	rno4.setText("");
        	type1.setText("");
        	type2.setText("");
        	type3.setText("");
        	type4.setText("");
            // Establish a connection to the database
        	con = DBUtil.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT room_no,type FROM rooms_hotel where availabilty='Yes'");

            // Execute the query and retrieve the result set
            ResultSet rs = stmt.executeQuery();

            // Iterate through the result set and update the checkbox items
            int i = 1;
            while (rs.next()) {
                String roomno = rs.getString("room_no");
                Label roomlabel = (Label) this.getClass().getDeclaredField("rno" + i).get(this);
                roomlabel.setText(roomno);
                roomlabel.setVisible(true);
                String type = rs.getString("type");
                Label roomtype = (Label) this.getClass().getDeclaredField("type" + i).get(this);
                roomtype.setText(type);
                roomtype.setVisible(true);
                i++;
                
            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
