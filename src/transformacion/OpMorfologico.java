package transformacion;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class OpMorfologico extends Transformacion {
	protected final int BLANCO = 255;
	protected final int NEGRO = 0;
	
	public abstract boolean cumple(Color c);
	public abstract int loadDefault();
	public abstract int loadNonDefault();
	
	public OpMorfologico(){}
	@Override
	public int aplicar(BufferedImage origen, int x, int y) {
		Vector<Point> vec = this.vecinos(origen, x, y);
		Point p;
		for(int i=0;i<vec.size();i++){
			p=vec.elementAt(i);
			Color color= new Color(origen.getRGB(p.x, p.y));
			if(cumple(color)) return loadNonDefault(); 
		}
		
	

		return loadDefault();
	}
	

	
}
