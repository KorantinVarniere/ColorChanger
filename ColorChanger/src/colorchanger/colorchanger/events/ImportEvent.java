package colorchanger.colorchanger.events;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import colorchanger.colorchanger.graphiques.Ecran;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImportEvent implements EventHandler<MouseEvent> {
	
	@Override
	public void handle(MouseEvent event) {
		Ecran ecran = (Ecran) ((Stage) (((Button) (event.getSource())).getScene().getWindow())).getScene().getRoot();
		
		List<String> toImport = new ArrayList<String>();
		String line;
		
		FileChooser chooser = new FileChooser();
		File file = chooser.showOpenDialog(ecran.getScene().getWindow());
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while ((line = reader.readLine()) != null) {
				toImport.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ecran.getSlider().setValue(toImport.size());
		
		for (int i = 0; i < ecran.getGridColor().getColors().size(); i++) {
			ecran.getGridColor().getColors().get(i).setFill(Color.web(toImport.get(i)));
		}
		
	}
}
