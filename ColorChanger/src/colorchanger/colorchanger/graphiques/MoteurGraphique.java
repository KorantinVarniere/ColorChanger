package colorchanger.colorchanger.graphiques;

import colorchanger.colorchanger.config.Configuration;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MoteurGraphique {

	private Stage stage;
	private Scene scene;
	private Ecran root;
	
	public MoteurGraphique(Stage stage) {
		this.stage = stage;
		this.root = new Ecran();
		this.root.setStyle("-fx-background-color: white;");
		this.scene = new Scene(this.root);
		
		this.configurer();
	}
	
	public Ecran getEcran() {
		return this.root;
	}
	
	public void configurer() {
		this.stage.setScene(this.scene);
		
		this.stage.getIcons().add(new Image("icone.png"));
		this.stage.setTitle(Configuration.TITRE);
		this.stage.setHeight(Configuration.HAUTEUR_ECRAN);
		this.stage.setWidth(Configuration.LARGEUR_ECRAN);
		this.stage.setResizable(Configuration.REDIMENSIONABLE);
	}
	
	public void afficher() {
		this.stage.show();
	}
	
}
