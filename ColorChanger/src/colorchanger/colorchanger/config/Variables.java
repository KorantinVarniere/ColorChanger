package colorchanger.colorchanger.config;

import java.util.ArrayList;
import java.util.List;

import colorchanger.colorchanger.enums.Methode;
import colorchanger.colorchanger.enums.Mode;
import javafx.scene.shape.Rectangle;

public abstract class Variables {

	public static int nbCouleurs = 5;
	
	public static Methode methode = Methode.MANUEL;

	public static Mode mode = Mode.RGB;
	
	public static List<Rectangle> customColors = new ArrayList<Rectangle>(Variables.nbCouleurs);

	public static List<Rectangle> prevColors = new ArrayList<Rectangle>(Variables.nbCouleurs);

	public static List<Rectangle> beforeOpti = new ArrayList<Rectangle>(Variables.nbCouleurs);
	
}
