package colorchanger.colorchanger.events;

import colorchanger.colorchanger.config.Variables;
import colorchanger.colorchanger.graphiques.Ecran;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReturnEvent implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent event) {
		Ecran ecran = (Ecran) ((Stage) (((Button) (event.getSource())).getScene().getWindow())).getScene().getRoot();
		
		ecran.changeGridColor(Variables.beforeOpti);
		ecran.getGridColor().saveColors();
	}

}
