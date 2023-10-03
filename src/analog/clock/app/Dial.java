/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analog.clock.app;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 *
 * @author OscarFabianHP
 */
public class Dial extends Rectangle{

    Rotate rotate = new Rotate(0, 150, 150); //rota en el punto (240, 240)
    
    public Dial(double body, double tail, double width, Paint fill) {
        super(width, body+tail, fill);
        this.setX(150-width/2); //240
        this.setY(150-body);
        this.setEffect(new DropShadow());
        this.setArcHeight(width); //redondea las esquinas del rectangulo
        this.setArcWidth(width); //redondea las esquinas del rectangulo
        this.getTransforms().add(rotate); //anadimos a los diales la transformacion de rotar
    }
    
    public void actualizarDial(double angle){
        rotate.setAngle(angle);
    }
    
}
