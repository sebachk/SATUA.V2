package transformacion;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Robert extends Transformacion {

	@Override
	public int aplicar(BufferedImage origen, int x, int y) {
		double a,b,c=0,d=0;
		a=(double)new Color(origen.getRGB(x, y)).getBlue();
		if(x==origen.getWidth()-1){
			b=0;
			d=0;
			if(y==origen.getHeight()-1){
				c=0;
			}
			else{
				c=(double)new Color(origen.getRGB(x, y+1)).getBlue();
			}
		}
		else if(y==origen.getHeight()-1){
			c=0;
			d=0;
			b=(double)new Color(origen.getRGB(x+1, y)).getBlue();
		
		}
		else{
			c=(double)new Color(origen.getRGB(x, y+1)).getBlue();
			b=(double)new Color(origen.getRGB(x+1, y)).getBlue();
			d=(double)new Color(origen.getRGB(x+1, y+1)).getBlue();
			
		}
		
		double g= Math.pow(a-d, 2) + Math.pow(c-b, 2);
		g=Math.sqrt(g);
		// TODO Auto-generated method stub
		return (int)g;
		
	}
	

}
