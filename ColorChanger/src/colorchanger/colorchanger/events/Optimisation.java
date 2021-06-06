package colorchanger.colorchanger.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import colorchanger.colorchanger.config.Variables;
import colorchanger.colorchanger.graphiques.Ecran;
import colorchanger.colorchanger.graphiques.footer.ButtonsBar;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public abstract class Optimisation {
	public static void optimiserColor(List<Rectangle> couleur) {
		Variables.beforeOpti = Variables.customColors;
		ButtonsBar.optimiser.setOnMouseClicked(e -> {
			Ecran ecran = (Ecran) ((Stage) (((Button) (e.getSource())).getScene().getWindow())).getScene().getRoot();
			Variables.prevColors = ecran.getGridColor().getColors();
			
			//System.out.println(Variables.beforeOpti);
			
			ArrayList<Integer> indice = colorChanged(couleur);
			if (indice.size() == 0) {
				Ecran.erreur.setText("Veuillez modifier une couleur");
				return;
			} else {
				Ecran.erreur.setText("");
			}
			Random rand = new Random();
			Color firstColorChanged = (Color) couleur.get(indice.get(0)).getFill();
			ArrayList<Double> colorsSum = new ArrayList<Double>();
			colorsSum.add(sommeRVB(firstColorChanged));
			try {
				for (int i = 0; i < couleur.size(); i++) {
					if (!indice.contains(i)) {
						double sum=0;
						  do { 
							  Color c = Color.color(rand.nextDouble(), rand.nextDouble(),rand.nextDouble()); couleur.get(i).setFill(c); 
							  sum= sommeRVB(c);
						  }while(!goodDifference(colorsSum, sum));
						  colorsSum.add(sum);
					}else if(i!=indice.get(0)){
						double sum= sommeRVB((Color)couleur.get(i).getFill());
						double pourcentageOpti = (100/Variables.nbCouleurs-5);
						pourcentageOpti=pourcentageOpti/100;
						if(Math.abs(colorsSum.get(0)-sommeRVB((Color)couleur.get(i).getFill()))<pourcentageOpti) {
							do { 
								  Color c = Color.color(rand.nextDouble(), rand.nextDouble(),rand.nextDouble()); couleur.get(i).setFill(c); 
								  sum= sommeRVB(c);
							  }while(!goodDifference(colorsSum, sum));
						}
						colorsSum.add(sum);
					}
				}
			} catch (Exception exc) {}

			//System.out.println(Variables.beforeOpti);
			
		});
	}
	public static boolean goodDifference(ArrayList<Double> colorsSum, double sum) {
		return goodDifference(colorsSum, sum,0);
	}
	
	public static boolean goodDifference (ArrayList<Double> colorsSum, double sum, double pourcentageOpti) {
		if(pourcentageOpti==0) {
			pourcentageOpti = (100/Variables.nbCouleurs-3);
			pourcentageOpti= pourcentageOpti/100;
		}
		for (int i = 0; i < colorsSum.size(); i++) {
			if(Math.abs(colorsSum.get(i)-sum)<pourcentageOpti)
				return false;
		}
		return true;
	}
	
	public static double sommeRVB(Color c) {
		return 0.3 * c.getRed() + 0.59 * c.getGreen() + 0.11 * c.getBlue();
	}

	private static ArrayList<Integer> colorChanged(List<Rectangle> gris) {
		ArrayList<Integer> indice = new ArrayList<Integer>();
		for (int i = 0; i < Variables.nbCouleurs; i++) {
			if (!gris.get(i).getFill().equals(Color.PINK)) {
				indice.add(i);
			}
		}
		return indice;
	}

}