package transformacion;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;

import utils.Matriz2D;


public abstract class Transformacion {
	public static final double[] GRAY_ARRAY={0.2989,0.5870,0.1140};
	
    public abstract int aplicar(BufferedImage origen,int x,int y);
    
    /**
     * Recorre una imagen pixel a pixel y para cada uno de ellos aplica una funcion dada por los hijos de la clase
     * @param origen
     * @return
     */
    public BufferedImage transformar(BufferedImage origen){
    	BufferedImage destino= new BufferedImage(origen.getWidth(), origen.getHeight(), origen.getType());
    	
    	Matriz2D<Integer> total = new Matriz2D<Integer>(origen.getWidth(),origen.getHeight()); 
    	int max=0;
    	int min=9999;
    	int value;
    	for(int i=0;i<origen.getWidth();i++){
    		for(int j=0;j<origen.getHeight();j++){
    			value=aplicar(origen,i,j);
    			total.elementAt(i,j,value);
    			//value=total.elementAt(i).elementAt(j);
    			if(value>max) max=value;
    			if(value<min) min=value;
    		}
    	}
    	
    	int grey;
    	
    	double A=255;
    	double B=0;
    	
    	if(min>0 && min<=255)
    		min=0;
    	if(max<255 && max>=0)
    		max=255;
    	if(max==min){
    		A=0;
    		B=128;
    	}
    	else{
    		A/=(max-min);
    		B=-A*min;
    	}
    	
    	
    	double valor=0;
    	for(int i=0;i<origen.getWidth();i++)
    		for(int j=0;j<origen.getHeight();j++){
    			grey = (total.elementAt(i,j));
    			valor=grey*A+B;
    			grey=(int)valor;
    			Color c= new Color(grey,grey,grey);
    			destino.setRGB(i,j,c.getRGB());
    		}
    	
    	return destino;
    }
    
    public static BufferedImage toGrey(BufferedImage origen){
    	Color greyColor;
    	
    	BufferedImage destino= new BufferedImage(origen.getWidth(), origen.getHeight(), origen.getType());
    	int color;
    	for(int i=0;i<origen.getWidth();i++)
    		for(int j=0;j<origen.getHeight();j++){
    			color = origen.getRGB(i, j);
    			greyColor = new Color(color);
    			color =(int)(greyColor.getRed()*GRAY_ARRAY[0]+greyColor.getGreen()*GRAY_ARRAY[1]+greyColor.getBlue()*GRAY_ARRAY[2]);
    			destino.setRGB(i,j,new Color(color,color,color).getRGB());
    		}
    	
		return destino;
    	
    }
    
    public static boolean OutOfBounds(BufferedImage img,int i,int j){
		if(i<0 || j<0) return true;
		
		if(i>=img.getWidth() || j>=img.getHeight()) return true;
		
		return false;
	}
    
    public Vector<Point> vecinos (BufferedImage img, int x, int y){
    	Vector<Point> res = new Vector<Point>();
    	
    	for(int i=0;i<3;i++)
    		for(int j=0;j<3;j++){
    			if(i!=1 || j!=1)
    			if(! OutOfBounds(img, x-1+i, y-1+j))
    				res.add(new Point(x-1+i,y-1+j));
    		}
    	
    	return res;
    }
    
    
    
}
