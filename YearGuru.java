package yearguru;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class YearGuru extends Application {
    
    private final YearGuruClient client;
    private final Label response = new Label("[Disconnected]");
    private final Label unicode = new Label("");
    private final Button submit = new Button("Submit");
    private final Button connect = new Button("Connect");
    private final TextField hostName = new TextField();
    private final TextField portNumber = new TextField();
    private final ChoiceBox day = new ChoiceBox();
    private final ChoiceBox month = new ChoiceBox();
    private final ChoiceBox year = new ChoiceBox();
    private final Label dy = new Label("Day:");
    private final Label mnt = new Label("Month:");
    private final Label yr = new Label("Year:");

    
    public YearGuru() {
	client = new YearGuruClient();
	day.setDisable(true);
	month.setDisable(true);
	year.setDisable(true);
	submit.setDisable(true);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane root;
            root = new AnchorPane();
            root.setPrefSize(600, 400);
	    
            //Layouts
            connect.setLayoutX(14);
            connect.setLayoutY(47);
            submit.setLayoutX(18);
            submit.setLayoutY(213);
            hostName.setLayoutX(14);
            hostName.setLayoutY(14);
            hostName.setPromptText("Host Name");
            portNumber.setLayoutX(181);
            portNumber.setLayoutY(14);
            portNumber.setPromptText("Port Number");
            day.setLayoutX(14);
            day.setLayoutY(173);
            day.setPrefWidth(150);
            month.setLayoutX(164);
            month.setLayoutY(173);
            month.setPrefWidth(150);
            year.setLayoutX(314);
            year.setLayoutY(173);
            month.setPrefWidth(150);
            dy.setLayoutX(14);
            dy.setLayoutY(156);
            mnt.setLayoutX(164);
            mnt.setLayoutY(156);
            yr.setLayoutX(314);
            yr.setLayoutY(156);
	    
	    response.setFont(new Font(32));
            response.setLayoutX(18);
            response.setLayoutY(276);
	    
	    unicode.setLayoutX(18);
            unicode.setLayoutY(320);
            unicode.setFont(new Font(130));
            /////////////////////////////

            //setting up values
            ObservableList<Integer> days = FXCollections.observableArrayList();
            for (int i = 1; i <= 31; i++) {
                days.add(i);
            }
            day.setItems(days);

            ObservableList<Integer> months = FXCollections.observableArrayList();
            for (int i = 1; i <= 12; i++) {
                months.add(i);
            }
	    
	    ObservableList<String> months1 = FXCollections.observableArrayList();
	    months1.add("January");
	    months1.add("February");
	    months1.add("March");
	    months1.add("April");
	    months1.add("May");
	    months1.add("June");
	    months1.add("July");
	    months1.add("August");
	    months1.add("September");
	    months1.add("October");
	    months1.add("November");
	    months1.add("December");
	
	    
            month.setItems(months1);

            ObservableList<Integer> years = FXCollections.observableArrayList();
            for (int i = 1900; i <= 2025; i++) {
                years.add(i);
            }

            year.setItems(years);
            ///////////////////////

            //event handling
            connect.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    
                    String hostname = hostName.getText();
                    int portnumber = Integer.parseInt(portNumber.getText().trim());
                    boolean connected = connectToServer(hostname, portnumber);
                }
            });

            submit.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) { 
                    int _day = Integer.parseInt(day.getValue().toString());
                    String _month = month.getValue().toString();
                    int _year = Integer.parseInt(year.getValue().toString());
		    
		    int _m = 0;	    
		    switch(_month) {
			case "January": _m = 1; break;
			case "February": _m = 2; break;
			case "March": _m = 3; break;
			case "April": _m = 4; break;
			case "May": _m = 5; break;
			case "June": _m = 6; break;
			case "July": _m = 7; break;
			case "August": _m = 8; break;
			case "September": _m = 9; break;
			case "October": _m = 10; break;
			case "November": _m = 11; break;
			case "December": _m = 12; break;
			default: break;
		    }
		    	    
                    getResult(_day, _m, _year);
                }
            });

            root.getChildren().addAll(submit, connect, hostName, portNumber,
		    day, month, year, dy, mnt, yr, response, unicode);

            Scene scene = new Scene(root);

            primaryStage.setTitle("Year Guru by Nick, Dylan & Anthony");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean connectToServer(String host, int port) {
	
	System.out.println("Host: " + host + ", port: " + port);
	try {
	    client.connect(host, port);
	} catch(Exception e) {
	    	Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Connection problem");
		alert.setHeaderText("Cannot connect to the server");
		alert.setContentText(e.getMessage());
		alert.showAndWait();
		return false;
	}
	
	day.setDisable(false);
	month.setDisable(false);
	year.setDisable(false);
	submit.setDisable(false);
	response.setText("[Connected and ready for query]");
        return true;
    }

    private void getResult(int day, int month, int year) {
        //sets the response to a new text
	
		System.out.println("Day: " + day + ", Month: " + month + ", Year: " + year);
		String s = month + "/" + day + "/" + year;
		
		String result = client.query(s);
		response.setText(result);
        
		unicode.setText(YearGuruClient.getElementUnicode(result)
			+ YearGuruClient.getBeastUnicode(result)
			+ YearGuruClient.getZodiacUnicode(result));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}