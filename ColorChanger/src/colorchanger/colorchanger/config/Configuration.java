package colorchanger.colorchanger.config;

import java.util.ResourceBundle;

public abstract class Configuration {

	private static ResourceBundle bundle = ResourceBundle.getBundle("colorchanger/colorchanger/config/config");
	
	public static final String TITRE = bundle.getString("titre");
	
	public static final int LARGEUR_ECRAN = Integer.parseInt(bundle.getString("ecran.largeur"));
	public static final int HAUTEUR_ECRAN = Integer.parseInt(bundle.getString("ecran.hauteur"));
	
	public static final boolean REDIMENSIONABLE = bundle.getString("redimensionable").equals("oui");
	
}
