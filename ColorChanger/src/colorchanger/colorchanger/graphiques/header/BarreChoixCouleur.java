package colorchanger.colorchanger.graphiques.header;

import java.util.ArrayList;

import colorchanger.colorchanger.config.Variables;
import colorchanger.colorchanger.enums.Methode;
import colorchanger.colorchanger.enums.Mode;
import colorchanger.colorchanger.graphiques.Ecran;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class BarreChoixCouleur extends GridPane {
	
	private Ecran ecran;

	private Label nbCouleurs = new Label("Nombre de couleur :");
	private Label choixCouleurs = new Label("Choix des couleurs :");
	
	private Slider sliderCouleurs = new Slider(1, 10, 5);
	private TextField nbCouleursActuel = new TextField("5");
	
	private RadioButton manuel = new RadioButton("Manuel");
	private RadioButton auto = new RadioButton("Automatique");
	private HBox methode = new HBox(100);

	private RadioButton rgb = new RadioButton("RGB");
	private RadioButton hexa = new RadioButton("Hexadécimal");
	private HBox mode = new HBox(100);
	
	private ColumnConstraints col1 = new ColumnConstraints();
	private ColumnConstraints col2 = new ColumnConstraints();
	
	private Separator sep = new Separator();
	
	public BarreChoixCouleur(Ecran ecran) {
		setMargin(this.sep, new Insets(0, 15, 0, 15));
		
		this.ecran = ecran;
		
		this.sep.setPadding(new Insets(0));
		
		this.manuel.setSelected(true);
		this.rgb.setSelected(true);
		
		this.methode.getChildren().addAll(this.manuel, this.auto);
		this.mode.getChildren().addAll(this.rgb, this.hexa);
		
		this.methode.setPadding(new Insets(20, 0, 0, 100));
		this.mode.setPadding(new Insets(15, 0, 0, 100));
		
		this.nbCouleurs.setStyle("-fx-font-size: 18;");
		this.choixCouleurs.setStyle("-fx-font-size: 18;");
		
		this.sliderCouleurs.setPadding(new Insets(20, 15, 15, 15));
		this.sliderCouleurs.setMaxWidth(250);
		
		this.nbCouleursActuel.setPadding(new Insets(15));
		this.nbCouleursActuel.setMaxWidth(50);
		
		this.col1.setPercentWidth(50);
		this.col2.setPercentWidth(50);
		this.col1.halignmentProperty().set(HPos.CENTER);
		this.col2.halignmentProperty().set(HPos.CENTER);
		this.getColumnConstraints().addAll(col1, col2);

		this.add(this.nbCouleurs, 0, 0);
		this.add(this.sliderCouleurs, 0, 1);
		this.add(this.nbCouleursActuel, 0, 3);
		
		this.add(this.choixCouleurs, 1, 0);
		this.add(this.methode, 1, 1);
		this.add(this.sep, 1, 2);
		this.add(this.mode, 1, 3);
		
		this.ajouterEvenements();
	}
	
	public Slider getSlider() {
		return this.sliderCouleurs;
	}
	
	private void ajouterEvenements() {
		this.sliderCouleurs.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() != oldValue.intValue()) {
					nbCouleursActuel.setText("" + newValue.intValue());
					Variables.nbCouleurs = newValue.intValue();
					if (Variables.methode.equals(Methode.MANUEL)) {
						ecran.changeGridColor(Variables.customColors);
					} else {
						ecran.changeGridColor(new ArrayList<Rectangle>());
					}
				}
			}
			
		});
		
		this.nbCouleursActuel.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.length() > 0) {
					try {
						if (Integer.parseInt(newValue) > 0 && Integer.parseInt(newValue) < 11) {
							sliderCouleurs.setValue(Integer.parseInt(newValue));
							Variables.nbCouleurs = Integer.parseInt(newValue);
							if (Variables.methode.equals(Methode.MANUEL)) {
								ecran.changeGridColor(Variables.customColors);
							} else {
								ecran.changeGridColor(new ArrayList<Rectangle>());
							}
						}
					} catch (Exception e) {
						
					}
				}
			}
			
		
		});
		
		this.auto.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					manuel.setSelected(false);
					Variables.methode = Methode.AUTO;
					ecran.changeGridColor(new ArrayList<Rectangle>());
				}
			}
			
		});
		
		this.manuel.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					auto.setSelected(false);
					Variables.methode = Methode.MANUEL;
					ecran.changeGridColor(Variables.customColors);
				}
			}
			
		});
		
		this.rgb.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					hexa.setSelected(false);
					Variables.mode = Mode.RGB;
					ecran.changeAllLabelGrid();
				}
			}
			
		});
		
		this.hexa.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					rgb.setSelected(false);
					Variables.mode = Mode.HEXA;
					ecran.changeAllLabelGrid();
				}
			}
			
		});
	
	}
	
}
