package Herramientas;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class ObjetoPoligonal extends Objeto {
	
	private Vector<Point> objeto;
	public ObjetoPoligonal() {
		objeto=new Vector<Point>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addPunto(Point p) {
		// TODO Auto-generated method stub
		if(!objeto.contains(p))
			objeto.add(p);
	}

	@Override
	public Vector<Point> getPuntos() {
		// TODO Auto-generated method stub
		return objeto;
	}

	@Override
	public boolean esAdyacente(Point p) {
		for(int i=0;i<objeto.size();i++){
			int disX=Math.abs(p.x-objeto.elementAt(i).x);
			int disY=Math.abs(p.y-objeto.elementAt(i).y);
			if(disX==1){
				if(disY==0)
					return true;
			}
			if(disY==1){
				if(disX==0)
					return true;
			}
		}
		return false;
		
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return this.getPuntos().size();
	}

	@Override
	public BufferedImage dibujar(BufferedImage img, int ancho, int alto) {
		// TODO Auto-generated method stub
		return null;
	}
}
