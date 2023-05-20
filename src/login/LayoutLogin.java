package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LayoutLogin extends Application{

    @Override
    public void start(Stage secondStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LayoutLogin.fxml"));
        secondStage.setTitle("Login - GPRO");
        secondStage.setScene(new Scene(root, 680, 400));
        secondStage.show();
       
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
