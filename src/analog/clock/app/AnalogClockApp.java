/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package analog.clock.app;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author OscarFabianHP
 */
public class AnalogClockApp extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Clock clock = new Clock(); //este dibuja la interfaz del reloj
        Scene scene = new Scene(clock);
        scene.getStylesheets().add("analog/clock/app/Estilo.css");  //se enlaza la hoja de estilo a la escena
        scene.setFill(Color.TRANSPARENT);
        
        stage.setTitle("Reloj Analogo");
        stage.sizeToScene();
        stage.initStyle(StageStyle.TRANSPARENT); //le quita a la ventana el estilo y lo pone transparente
        stage.setScene(scene);
        stage.show();
    }
    
}
