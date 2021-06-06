package colorchanger.colorchanger.events;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Help implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent event) {
		VBox vbox= new VBox();
		Label l1 =new Label("Commandes :");
		Label l2 =new Label("Clic gauche sur la couleur : pour changer la couleur.");
		Label l3 =new Label("Clic gauche sur le code couleur : pour copier le code couleur.");
		Label l4 =new Label("Mode Automatique appuyer sur R : pour générer de nouvelles couleurs.");

		vbox.getChildren().addAll(l1,l2,l3,l4);
		VBox.setMargin(l1, new Insets(20));
		VBox.setMargin(l2, new Insets(5));
		VBox.setMargin(l3, new Insets(5));
		VBox.setMargin(l4, new Insets(5));
		
		Scene scene=new Scene(vbox);
		
		Stage stage = new Stage();
		
		stage.setHeight(250);
		stage.setWidth(550);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle("Help");
		stage.show();
	}
}
