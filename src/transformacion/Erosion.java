package transformacion;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Erosion extends Transformacion {
	public static final int BLANCO = 255;
	
	public Erosion(){}
	@Override
	public int aplicar(BufferedImage origen, int x, int y) {
		for(int i=x-1;i<=x+1;x++){
			for(int j=y-1;j<=j+1;j++){
				if(!OutOfBounds(origen,i,j)){
					Color color= new Color(origen.getRGB(i, j));
					if(color.getBlue() < BLANCO) return 0; 
				}
			}
		}
		return BLANCO;
	}
	

	public boolean OutOfBounds(BufferedImage img,int i,int j){
		if(i<0 || j<0) return true;
		
		if(i>=img.getWidth() || j>=img.getHeight()) return true;
		
		return false;
	}
}
