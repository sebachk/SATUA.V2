package transformacion;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Umbralador utiliza una variable entera internamente. Si un valor de un pixel es menor a él cambia el valor a 0. Si el valor
 * es mayor lo cambia  a 255.
 * @author Ibrian
 *
 */
public class Umbralador extends Transformacion {

	private int umbral;
		
	public Umbralador(int umbral){
		this.umbral=umbral;
	}
	 
	@Override
	public int aplicar(BufferedImage origen, int x, int y) {
		Color color= new Color(origen.getRGB(x, y));
		return (color.getRed() < umbral)?  0: 255;
	}

	public int getUmbral() {
		return umbral;
	}

	public void setUmbral(int umbral) {
		this.umbral = umbral;
	}
}
