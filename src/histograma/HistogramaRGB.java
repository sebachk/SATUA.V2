package histograma;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Vector;

@SuppressWarnings("serial")
public abstract class HistogramaRGB extends Histograma {

	public abstract int getColor(Color c);
	
	public HistogramaRGB(BufferedImage img){
		super();
		fillVector(img);
		x = 10;
		y = 350;
	}
	
	public void fillVector(Object o){
		BufferedImage img = (BufferedImage) o;
		intensidades = new Vector<Integer>();
		
		for(int t=0;t<256;t++){intensidades.add(0);} //inicialización del vector
		
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				Color c = new Color(img.getRGB(i, j));
				int color = getColor(c); //Funcion que llenan los hijos
				addValue(color);
				}
		}
		//for(int t=0;t<intensidades.size();t++){System.out.println(intensidades.elementAt(t));}
	}
	
}
