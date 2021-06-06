package colorchanger.colorchanger.graphiques;


import java.util.ArrayList;
import java.util.List;

import colorchanger.colorchanger.config.Variables;
import colorchanger.colorchanger.enums.Methode;
import colorchanger.colorchanger.graphiques.central.ColorChangeGrid;
import colorchanger.colorchanger.graphiques.footer.ButtonsBar;
import colorchanger.colorchanger.graphiques.header.BarreChoixCouleur;
import colorchanger.colorchanger.graphiques.header.HelpBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ecran extends VBox {
	
	private HelpBar helpBar = new HelpBar();
	private BarreChoixCouleur barreChoixCouleur = new BarreChoixCouleur(this);
	private Separator sep1 = new Separator();
	private ColorChangeGrid gridColor = new ColorChangeGrid(Variables.customColors, Variables.methode);
	private Separator sep2 = new Separator();
	private ButtonsBar bb = new ButtonsBar(this);
	public static Label erreur = new Label();
	
	public Ecran() {
		erreur.setTextFill(Color.RED);
		this.sep1.setPadding(new Insets(15, 15, 70, 15));
		this.sep2.setPadding(new Insets(70, 15, 15, 15));
		erreur.setAlignment(Pos.BOTTOM_CENTER);
		this.getChildren().addAll(this.helpBar, this.barreChoixCouleur, this.sep1, this.gridColor, this.sep2,erreur, this.bb);
		
		this.addEvenements();
	}
	
	public void changeGridColor(List<Rectangle> list) {
		this.gridColor = new ColorChangeGrid(list, Variables.methode);
		this.getChildren().set(3, this.gridColor);
	}
	
	public void changeAllLabelGrid() {
		gridColor.changeAllLabel();
	}
	
	public ColorChangeGrid getGridColor() {
		return this.gridColor;
	}
	
	public Slider getSlider() {
		return this.barreChoixCouleur.getSlider();
	}
	
	public void addEvenements() {
		this.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
			if (event.getCode().equals(KeyCode.R) && Variables.methode.equals(Methode.AUTO)) {
				Variables.prevColors = this.getGridColor().getColors();
				changeGridColor(new ArrayList<Rectangle>());
			}
		});
	}
	
}
