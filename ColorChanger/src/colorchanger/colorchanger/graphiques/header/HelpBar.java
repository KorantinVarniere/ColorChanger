package colorchanger.colorchanger.graphiques.header;

import colorchanger.colorchanger.events.Help;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class HelpBar extends HBox {

	private Image img = new Image("help.png", 30, 30, true, true);
	private ImageView imgView = new ImageView(img);
	
	public HelpBar() {
		this.setAlignment(Pos.CENTER_RIGHT);
		HBox.setMargin(this.imgView, new Insets(15));
		this.getChildren().add(this.imgView);
		
		this.ajouterEvenements();
	}
	
	private void ajouterEvenements() {
		this.imgView.addEventHandler(MouseEvent.MOUSE_CLICKED, new Help());
	}
	
}
