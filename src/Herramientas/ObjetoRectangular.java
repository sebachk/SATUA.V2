package Herramientas;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;

import transformacion.CambiadorMuestreo;

public class ObjetoRectangular extends Objeto {

	private int minH,maxH,minV,maxV;
	
	public ObjetoRectangular(){
		minH=-1;
		maxH=-1;
		minV=-1;
		maxV=-1;
	}
	private boolean vacio(){
		return (minH==-1||maxH==-1||minV==-1||maxV==-1);
	}
	@Override
	public void addPunto(Point p) {
		if(this.vacio()){
			minH=maxH=p.x;
			minV=maxV=p.y;
			return;
		}
		if(p.x<minH) minH=p.x;
		else if(p.x>maxH) maxH=p.x;
		
		if(p.y<minV) minV=p.y;
		else if(p.y>maxV) maxV=p.y;
		// TODO Auto-generated method stub

	}

	@Override
	public Vector<Point> getPuntos() {
		// TODO Auto-generated method stub
		
		Vector<Point> retorno=new Vector<>();
		retorno.add(new Point(minH,minV));
		retorno.add(new Point(minH,maxV));
		retorno.add(new Point(maxH,maxV));
		retorno.add(new Point(maxH,minV));
		
		return retorno;
	}
	
	@Override
	public boolean esAdyacente(Point p) {
		if(p.x>=minH && p.x<=maxH){
			if(p.y==minV-1 || p.y==maxV+1)
				return true;
		}
		if(p.y>=minV && p.y<=maxV){
			if(p.x==minH-1 || p.x==maxH+1)
				return true;
		}
		return false;
	}
	
	@Override
	public double getArea() {
		return(maxH-minH)*(maxV-minV);
	}

	@Override
	public BufferedImage dibujar(BufferedImage img, int ancho, int alto) {
		CambiadorMuestreo cm = new CambiadorMuestreo(ancho, alto);
		Point p=cm.pointFromOriginal(ancho, alto);
		BufferedImage resultado= new BufferedImage(p.x, p.y, img.getType());
		p=cm.pointFromOriginal(minH, minV);
		for(int i=p.x;i<=cm.pointFromOriginal(maxH,minV).x;i++){
			for(int j=p.y;j<=cm.pointFromOriginal(minH,maxV).y;j++){
				p=cm.pointFromDestiny(ancho, alto);
				resultado.setRGB(i, j, img.getRGB(p.x, p.y));
				}
		}
		return null;
	}
}
