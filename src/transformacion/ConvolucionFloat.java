package transformacion;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Vector;

import utils.Matriz2D;

public class ConvolucionFloat extends Transformacion {
private Vector<Matriz2D<Float>> transfs; //transformaciones
	
	public void setElement(Matriz2D<Float> v){
		transfs.add(v);
	}
	public void setElement(Float[] v){
		Matriz2D<Float> m = new Matriz2D<Float>(3,3);
		int t=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++){
				m.elementAt(j,i,v[t]);
				t++;
			}
		this.setElement(m);
	}
	public Matriz2D<Float> getElement(int i){
		return transfs.elementAt(i);
	}
	
	public ConvolucionFloat(){
		transfs = new Vector<Matriz2D<Float>>();
	}
	public ConvolucionFloat(Float[] v){
		this();
		this.setElement(v);
	}
	
	public int aplicar(BufferedImage img,int x,int y){
		float v = 0;
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(!OutOfBounds(img,x-1+i,y-1+j)){
					Color c = new Color(img.getRGB(x-1+i,y-1+j));
					int vB = c.getBlue();
				for(int z=0;z<transfs.size();z++){
					v = v + (transfs.elementAt(z).elementAt(i,j)*vB);
				}
			}				
		}
			
		}
		return (int)v;	
	}
	
	public boolean OutOfBounds(BufferedImage img,int i,int j){
		if(i<0 || j<0) return true;
		
		if(i>=img.getWidth() || j>=img.getHeight()) return true;
		
		return false;
	}
	
}

