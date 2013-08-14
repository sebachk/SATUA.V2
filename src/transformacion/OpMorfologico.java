package transformacion;

import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class OpMorfologico extends Transformacion {
	protected int BLANCO = 255;
	protected int NEGRO = 0;
	
	public abstract boolean cumple(Color c);
	public abstract int loadDefault();
	public abstract int loadNonDefault();
	
	public OpMorfologico(){}
	@Override
	public int aplicar(BufferedImage origen, int x, int y) {
		for(int i=x-1;i<=x+1;i++){
			for(int j=y-1;j<=y+1;j++){
				if(!OutOfBounds(origen,i,j)){
					Color color= new Color(origen.getRGB(i, j));
					if(cumple(color)) return loadNonDefault(); 
				}
			}
		}
		return loadDefault();
	}
	

	public boolean OutOfBounds(BufferedImage img,int i,int j){
		if(i<0 || j<0) return true;
		
		if(i>=img.getWidth() || j>=img.getHeight()) return true;
		
		return false;
	}
}
