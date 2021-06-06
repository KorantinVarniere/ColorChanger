package colorchanger.colorchanger.graphiques;

import colorchanger.colorchanger.graphiques.central.ColorChangeGrid;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
 
public abstract class PickColor extends Application {
 
    public static Color lancerColorPicker(Color color,Rectangle rectangle,Label label) {
    	Stage stage=new Stage();
    	Color c = null;
        final ColorPicker colorPicker = new ColorPicker(color);
        
        c=colorPicker.getValue();
        
        Button enregistre=new Button("Enregistrer");
        
        enregistre.setOnMouseClicked(e->{
        	rectangle.setFill(colorPicker.getValue());
        	ColorChangeGrid.changeLabel(label, rectangle);
        	stage.close();
        });
        
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.getChildren().addAll(colorPicker,enregistre);
        
        Scene scene = new Scene(root, 300, 200);
       
        stage.setTitle("ColorPicker");
 
        stage.setScene(scene);
        stage.show();
        
        return c;
    } 
}