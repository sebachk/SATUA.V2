package transformacion;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Herramientas.Segmentador;

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
		if(color.getRed() < umbral){
			Segmentador.visitados[x][y]=true;
			return 0;
		}
		Segmentador.visitados[x][y]=false;
		return 255;
	}

	public int getUmbral() {
		return umbral;
	}

	public void setUmbral(int umbral) {
		this.umbral = umbral;
	}
}
