package colorchanger.colorchanger.graphiques.footer;


import colorchanger.colorchanger.events.ImportEvent;
import colorchanger.colorchanger.events.ReturnEvent;
import colorchanger.colorchanger.events.SaveEvent;
import colorchanger.colorchanger.graphiques.Ecran;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ButtonsBar extends HBox {
	
	public Ecran ecran;

	private Button importer = new Button("Importer");
	public static Button optimiser = new Button("Optimiser");
	private Button retour = new Button("Retour");
	private Button sauvegarder = new Button("Sauvegarder");

	public ButtonsBar(Ecran ecran) {
		super(100);
		this.ecran = ecran;
		this.setAlignment(Pos.BOTTOM_CENTER);
		this.getChildren().addAll(this.importer, optimiser, this.retour, this.sauvegarder);
		this.addEvents();
	}
	
	private void addEvents() {
		this.sauvegarder.addEventHandler(MouseEvent.MOUSE_CLICKED, new SaveEvent());
		this.importer.addEventHandler(MouseEvent.MOUSE_CLICKED, new ImportEvent());
		this.retour.addEventHandler(MouseEvent.MOUSE_CLICKED, new ReturnEvent());
	}

}
