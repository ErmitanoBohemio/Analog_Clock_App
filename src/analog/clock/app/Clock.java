/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analog.clock.app;

import java.time.LocalTime;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author OscarFabianHP
 */
public class Clock extends Parent{
    StackPane stackPane = new StackPane();
    Group group = new Group();//se crea gruop para poder facilitar realizar rotaciones a los diales del reloj
    
    Dial segundero = new Dial(120, 30, 5, Color.CRIMSON); //(200, 50, 5, ...)
    Dial minutero = new Dial(100, 20, 10, Color.BURLYWOOD); //(150, 40, 10, ...)
    Dial horario = new Dial(80, 17, 12, Color.BURLYWOOD); //(100, 30, 15, ...)
    
    LocalTime localTime;

    public Clock() {
        grafica();
        ejecutarReloj();
    }
    
    private void grafica(){
        Circle bordeReloj = new Circle(150, Color.BLACK); //240
       bordeReloj.setEffect(new Lighting());
        
        Circle esferaReloj = new Circle(130, Color.WHITESMOKE); //220  
        esferaReloj.setEffect(new InnerShadow()); //efecto q hace que el circulo se incluste en le escena
        
        //numeros del rejo
        Text  text_12 = new Text("12");
        Text  text_3 = new Text("3");
        Text  text_6 = new Text("6");
        Text  text_9 = new Text("9");
        text_12 .setTranslateY(-80); //170
        text_3.setTranslateX(80);
        text_6.setTranslateY(80);
        text_9.setTranslateX(-80);
        
        //este se agrega aqui para que las esferas del reloj no solapen las lineas de minutos
        stackPane.getChildren().addAll(bordeReloj, esferaReloj);
   
        
        //Lineas segundos reloj
        Rectangle[] lineasMinutos =  new Rectangle[12];
        for (int i = 0; i < 12; i++) {
            lineasMinutos[i] = new Rectangle(20, 5, Color.BLACK);
            lineasMinutos[i].setTranslateX(110 * Math.cos(-(Math.PI/6)*i)); //200
            lineasMinutos[i].setTranslateY(110 * Math.sin(-(Math.PI/6)*i));
            lineasMinutos[i].setRotate(-(180/6)*i);
        }
         //Lineas segundos
         Rectangle[] lineasSegundos = new Rectangle[60];
         for (int i = 0; i < 60; i++) {
             if(i % 5==0) //cada quinto palo no dibuje el rectangulo en esa posicioin
                 continue;
             else{
                lineasSegundos[i] = new Rectangle(10, 2, Color.BLACK);
                lineasSegundos[i].setTranslateX(110 * Math.cos(-(Math.PI/30)*i)); //200
                lineasSegundos[i].setTranslateY(110 * Math.sin(-(Math.PI/30)*i));
                lineasSegundos[i].setRotate(-(180/30)*i);
                 stackPane.getChildren().add(lineasSegundos[i]);
             }
        }
        
        
        //stackPane.getChildren().addAll(lineasSegundos);
        stackPane.getChildren().addAll(lineasMinutos); 
        stackPane.getChildren().addAll(text_12, text_3, text_6, text_9);
        
        group.getChildren().add(stackPane);
        group.getChildren().addAll(horario, minutero, segundero);
        group.getChildren().add(new Circle(150, 150, 10, Color.CRIMSON)); //240
        group.getChildren().add(new Circle(150, 150, 5, Color.BURLYWOOD));

        
        //this.getChildren().addAll(stackPane); //se comenta ya que adieromejor al grupo
        this.getChildren().add(group);
    }
    
    private void actualizarReloj(){
        localTime = LocalTime.now();
        
        int hora = localTime.getHour();
        int minutos = localTime.getMinute();
        int segundos = localTime.getSecond();
        
        double angle_seg = 360 / 60 * segundos;
        double angle_min = 360 / 60 * minutos + (360.0/60) / 60 * segundos; //se suma la cuenta de cuantos segundos hay en un minuto
        double angle_hora = 360 / 12 * hora + (360.0 / 60)/60 * minutos + (360.0/60)/3600 * segundos; //se suma la cuenta de cuantos minutos y luego cuantos segundos hay en una hora 
        
        segundero.actualizarDial(angle_seg);
        minutero.actualizarDial(angle_min);
        horario.actualizarDial(angle_hora);
    }
    
    public void ejecutarReloj(){
        Timeline timelineSecundario = new Timeline(new KeyFrame(Duration.seconds(1), (event) -> { actualizarReloj();
        }));
        
        //este timeline evita el retraso pequeño del reloj con la hora actual del sistema, 
        //un segundo en millis es 1000 y a eso se le resta la hora actual en milis para corregir la pequenia diferencia, el %1000 impide que de 0 en el posible resultado de la resta
        Timeline timelinePrimario = new Timeline(new KeyFrame(new Duration(1000 - System.currentTimeMillis()%1000), event -> {
            actualizarReloj(); 
            timelineSecundario.play();}));
        
        //Timeline timelineHora = new Timeline(new KeyFrame(Duration.seconds(), kvs));
                
        timelineSecundario.setCycleCount(Timeline.INDEFINITE);
        timelinePrimario.setCycleCount(Timeline.INDEFINITE);
        timelinePrimario.play();
        //timelineSecundario.play();
                
    }
    
}
