package transformacion;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Vector;
import utils.*;





///////////////////////////////////////////////////////////********************
//////Cambios mios (Seba)**********************************



public class Convolucion extends Transformacion {
	private Vector<Matriz2D<Integer>> transfs; //transformaciones
	
	public void setElement(Matriz2D<Integer> v){
		transfs.add(v);
	}
	public void setElement(Integer[] v){
		Matriz2D<Integer> m = new Matriz2D<Integer>(3,3);
		int t=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++){
				m.elementAt(j,i,v[t]);
				t++;
			}
		this.setElement(m);
	}
	public Matriz2D<Integer> getElement(int i){
		return transfs.elementAt(i);
	}
	
	public Convolucion(){
		transfs = new Vector<Matriz2D<Integer>>();
	}
	public Convolucion(Integer[] v){
		this();
		this.setElement(v);
	}
	
	public int aplicar(BufferedImage img,int x,int y){
		int v = 0;
		int max=0;
		
		for(int z=0;z<transfs.size();z++){
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(!OutOfBounds(img,x-1+i,y-1+j)){
						Color c = new Color(img.getRGB(x-1+i,y-1+j));
						int vB = c.getRed();
					
					v = v + (transfs.elementAt(z).elementAt(i,j)*vB);
					}	
				}
			}
			//promedio
			//max+=v;
			//max
			if(v>max) max=v;
			//min
			//if(v<max) max=v;
			
			v=0;
		}
		
		return max;	
	}
	
	
	
}
