/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.runServices;


/**
 *
 * @author oleg
 */
public class JavaFXApplication3 extends Application {
    
    private  Process pr ;
    public runServices srv;

    
    @Override
    public void start(Stage stage) throws Exception {
        String myEnv = System.getenv("opera");
        
        srv = new runServices();
        srv.setVisible(true);
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    
    @Override
    public void stop() throws Exception {
         
        srv.close();
        srv.dispose();
        super.stop();
        System.exit(0);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
   
}
