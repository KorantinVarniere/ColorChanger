package colorchanger.colorchanger;

import colorchanger.colorchanger.graphiques.MoteurGraphique;  
import javafx.application.Application;
import javafx.stage.Stage;


public class ColorChanger extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		MoteurGraphique mg = new MoteurGraphique(stage);
		
		mg.afficher();
	}
	
}
