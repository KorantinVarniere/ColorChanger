package colorchanger.colorchanger.comparators;

import java.util.Comparator;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;



public class ColorComparator implements Comparator<Rectangle> {

	@Override
	public int compare(Rectangle first, Rectangle second) {
		Paint firstFillPaint = first.getFill();
		Color firstFillColor = (Color) (firstFillPaint);

		Paint secondFillPaint = second.getFill();
		Color secondFillColor = (Color) (secondFillPaint);
		
		double firstGray = 0.3*firstFillColor.getRed() + 0.59*firstFillColor.getGreen() + 0.11*firstFillColor.getBlue();
		double secondGray = 0.3*secondFillColor.getRed() + 0.59*secondFillColor.getGreen() + 0.11*secondFillColor.getBlue();
		return Double.compare(secondGray, firstGray);
	}

}
