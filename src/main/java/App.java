import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application{
    public void start(Stage stage){
        stage.setTitle("RainMinder");

        StackPane pane = new StackPane();

        TextField locField = new TextField();
        TextField dateField = new TextField();
        TextField recField = new TextField();
        TextField checkField = new TextField();

        Label locLabel = new Label("Your Location (City or Zip-Code): ");
        Label dateLabel = new Label("The date you want to keep track of: ");
        Label recLabel = new Label("Your Email address: ");
        Label checkLabel = new Label("A significant change in amount of rain (.5 inches is default): ");

        pane.getChildren().add(locField);
        pane.getChildren().add(locLabel);
        pane.getChildren().add(dateField);
        pane.getChildren().add(dateLabel);
        pane.getChildren().add(recLabel);
        pane.getChildren().add(recField);
        pane.getChildren().add(checkLabel);
        pane.getChildren().add(checkField);

        Button btn  = new Button();
        btn.setText("Setup a RainMinder");

        pane.getChildren().add(btn);

        Scene scene = new Scene(pane, 300, 250);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
