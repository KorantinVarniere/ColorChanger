package colorchanger.colorchanger.graphiques.central;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import colorchanger.colorchanger.comparators.ColorComparator;
import colorchanger.colorchanger.config.Variables;
import colorchanger.colorchanger.enums.Methode;
import colorchanger.colorchanger.enums.Mode;
import colorchanger.colorchanger.events.Optimisation;
import colorchanger.colorchanger.graphiques.Ecran;
import colorchanger.colorchanger.graphiques.PickColor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class ColorChangeGrid extends GridPane {

	private List<Rectangle> couleurs;
	private List<Label> codesCouleurs;
	private List<Rectangle> gris;
	
	public ColorChangeGrid(List<Rectangle> list, Methode methode) {
		this.setHeight(500);
		
		for (int i = 0; i < Variables.nbCouleurs; i++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setPercentWidth(100/Variables.nbCouleurs);
			cc.halignmentProperty().set(HPos.CENTER);
			this.getColumnConstraints().add(cc);
		}
		
		if (methode.equals(Methode.MANUEL)) {
			this.couleurs = new ArrayList<Rectangle>(Variables.nbCouleurs);
			for (int i = 0; i < Variables.nbCouleurs; i++) {
				if (i < list.size()) {
					this.couleurs.add(list.get(i));
				} else {
					Rectangle rect = new Rectangle();
					rect.setWidth(30);
					rect.setHeight(30);
					rect.setFill(Color.PINK);
					this.couleurs.add(rect);
				}
			}
		} else {
			Ecran.erreur.setText("");
			this.couleurs = new ArrayList<Rectangle>(Variables.nbCouleurs);
			for (int i = 0; i < Variables.nbCouleurs; i++) {
				if (i < list.size()) {
					this.couleurs.add(list.get(i));
				} else {
					Random rand = new Random();
					Rectangle rect = new Rectangle();
					rect.setWidth(30);
					rect.setHeight(30);
					rect.setFill(Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()));
					this.couleurs.add(rect);
				}
			}
		}

		Optimisation.optimiserColor(Variables.customColors);
		Collections.sort(this.couleurs, new ColorComparator());
		
		this.codesCouleurs = new ArrayList<Label>(Variables.nbCouleurs);
		for (int i = 0; i < Variables.nbCouleurs; i++) {
			Label lab = new Label();
			lab.setPadding(new Insets(50, 0, 50, 0));
			changeLabel(lab, this.couleurs.get(i));
			this.codesCouleurs.add(lab);
		}
		this.gris = new ArrayList<Rectangle>(Variables.nbCouleurs);
		for (int i = 0; i < Variables.nbCouleurs; i++) {
			Rectangle rect = new Rectangle();
			rect.setWidth(30);
			rect.setHeight(30);
			rect.setFill(Color.gray(grayLevelOf((Color) this.couleurs.get(i).getFill())));
			this.gris.add(rect);
		}
		this.ajouterElt();
		this.ajouterEvenements();
	}
	
	public List<Rectangle> getColors() {
		return this.couleurs;
	}
	
	private void ajouterElt() {
		for (Rectangle rect : this.couleurs) {
			this.add(rect, this.couleurs.indexOf(rect), 0);
		}
		for (Label lab : this.codesCouleurs) {
			this.add(lab, this.codesCouleurs.indexOf(lab), 1);
		}
		for (Rectangle rect : this.gris) {
			this.add(rect, this.gris.indexOf(rect), 2);
		}
	}
	
	public void changeAllLabel() {
		for (int i = 0; i < Variables.nbCouleurs; i++) {
			changeLabel(this.codesCouleurs.get(i), this.couleurs.get(i));
		}
	}
	
	private void actualizeGrayLevel() {
		for (int i = 0; i < Variables.nbCouleurs; i++) {
			try {
				((Ecran) this.getParent()).changeGridColor((Variables.customColors));
				this.changeAllLabel();
				this.gris.get(i).setFill(Color.gray(grayLevelOf((Color) this.couleurs.get(i).getFill())));
			} catch (Exception e) {
				
			}
		}
	}
	
	public static void changeLabel(Label label, Rectangle rectangle) {
		Color c = (Color) rectangle.getFill();
		double red = c.getRed() * 255;
		double green = c.getGreen() * 255;
		double blue = c.getBlue() * 255;
		if (Variables.mode.equals(Mode.HEXA)) {
			label.setText(String.format("#%02X%02X%02X", (int) red, (int) green, (int) blue));
		} else {
			label.setText(
					"" + (int) (c.getRed() * 255) + "." + (int) (c.getGreen() * 255) + "." + (int) (c.getBlue() * 255));
		}
	}
	
	public  static double grayLevelOf(Color color) {
		double red = color.getRed();
		double green = color.getGreen();
		double blue = color.getBlue();
		return 0.3 * red + 0.59 * green + 0.11 * blue;
	}
	
	private void ajouterEvenements() {
		for(int i=0;i< Variables.nbCouleurs; i++) {
			Rectangle rectangle = this.couleurs.get(i);
			Label label = this.codesCouleurs.get(i);
			rectangle.setOnMouseClicked(new EventHandler <MouseEvent>() 
			{
				@Override
				public void handle(MouseEvent e) {
					if (e.getButton().equals(MouseButton.PRIMARY)) {
						rectangle.setFill(PickColor.lancerColorPicker((Color) rectangle.getFill(),rectangle,label));
						saveColors();
					}
				}
			});
			rectangle.fillProperty().addListener(new ChangeListener<Paint>() {

				@Override
				public void changed(ObservableValue<? extends Paint> observable, Paint oldValue, Paint newValue) {
					saveColors();
					actualizeGrayLevel();
				}
				
			});
			Tooltip.install(rectangle, new Tooltip("Clic gauche pour changer !"));
		}
		
		for (int i = 0; i < Variables.nbCouleurs; i++) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			this.codesCouleurs.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					String code = ((Label) event.getSource()).getText();
					StringSelection to_copy = new StringSelection(code);
					toolkit.getSystemClipboard().setContents(to_copy, to_copy);
				}
			});
			this.codesCouleurs.get(i).setOnMouseMoved(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (event.getSceneY() > 420 && event.getSceneY() < 455) {
						Cursor cursor = Cursor.HAND;
						setCursor(cursor);
					} else {
						Cursor cursor = Cursor.DEFAULT;
						setCursor(cursor);
					}
				}
			});
			Tooltip.install(this.codesCouleurs.get(i), new Tooltip("Clic gauche pour copier !"));
		}
	}
	
	public void saveColors() {
		Variables.prevColors = this.couleurs;
		Variables.customColors = this.couleurs;
	}
	
}
