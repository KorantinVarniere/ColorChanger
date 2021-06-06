package colorchanger.colorchanger.events;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import colorchanger.colorchanger.graphiques.Ecran;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SaveEvent implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent event) {
		Ecran ecran = (Ecran) ((Stage) (((Button) (event.getSource())).getScene().getWindow())).getScene().getRoot();
		
		FileChooser chooser = new FileChooser();
		File file = chooser.showSaveDialog(ecran.getScene().getWindow());
		
		List<String> toSave = new ArrayList<String>();
		for (Rectangle rect : ecran.getGridColor().getColors()) {
			toSave.add(rect.getFill().toString());
		}

		try (FileWriter writer = new FileWriter(file)) {
			for (String str : toSave) {
				writer.write(str + System.lineSeparator());
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
