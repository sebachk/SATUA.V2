package Herramientas;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class Objeto {
	
	public abstract void addPunto(Point p);
	
	public abstract Vector<Point> getPuntos();

	
	public abstract boolean esAdyacente (Point p);
		
	public void fusionar(Objeto o) {
		Vector<Point> v=o.getPuntos();
		for(Point p:v){
			this.addPunto(p);
		}
		
	}
	
	public abstract double getArea();//Retorna el area rectangular	
	
	
	public boolean esMayor(Objeto o){
		return this.getArea()>o.getArea();		
	}
	
	
	public abstract BufferedImage dibujar(BufferedImage img, int ancho,int alto);
	
	
	
}
