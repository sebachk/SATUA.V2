package transformacion;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.Vector;

public class ReductorRuido extends Convolucion {

	@Override
	public int aplicar(BufferedImage img, int x, int y) {
		// TODO Auto-generated method stub
		//3X3
		
		Vector<Integer> valores = new Vector<Integer>();
		for(int i=-1;i<2;i++){
			for(int j=-1;j<2;j++){
				if(!OutOfBounds(img, x+i, y+j)){
					Color c=new Color(img.getRGB(x+i, y+j));
					valores.add(c.getRed());
				}
			}
		}
		Collections.sort(valores);
		
		int total=0;
		for(int i=1;i<valores.size();i++){
			total+=valores.elementAt(i);
		}
		
		return total/7;
	}
}
