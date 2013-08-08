package Herramientas;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 *Recorre una imagen de bordes (BufferedImage) de izquierda a derecha y luego 
 *de derecha a izquierda tambien de arriba a abajo y de abajo a arriba 
 *y descarta los pixeles negros hasta chocar con un borde
 **/
public class Segmento {
	private Vector<Point> salida;
	private BufferedImage fuente;
	
	public Segmento(BufferedImage f){
		salida = new Vector<Point>();
		fuente=f;
	}
	
	public Vector<Point> getSegmento(){
		if(salida.isEmpty()){
			salida=segmentar();
		
		}
		return salida;
	}
	
	protected Vector<Point> segmentar(){
		Vector<Point> izq_der = new Vector<Point>();
		Vector<Point> der_izq = new Vector<Point>();
		Vector<Point> arr_ab = new Vector<Point>();
		Vector<Point> ab_arr = new Vector<Point>();
		int tono;
		int y1=0;
		int y2=0;
		int x1=10000;
		int x2=0;
		int umbral = 50; // > 
		boolean habilitado = false;
		System.out.println("COMIENZO BARRIDO");
		
		// barrido de izquierda a derecha
		for(int i=0;i<fuente.getHeight();i++){
			for(int j=0;j<fuente.getWidth();j++){
				if(habilitado)
					izq_der.add(new Point(j,i));
				else{
					tono = (new Color(fuente.getRGB(j, i)).getRed());
					
					if(tono < umbral){
						habilitado = true;
						izq_der.add(new Point(j,i));
						if(y1==0){y1=i;} //obtengo el Ymin
						y2=i; //cada vez que habilito cargo el Ymax
						if(j<x1){x1=j;} //pregunto si encontre el Xmin
					}
				}
			}
			habilitado = false;
		}
		System.out.println(y1+", "+y2+", "+x1);
		System.out.println("COMIENZO BARRIDO");
		//barrido de derecha a izquierda
		for(int i=y1;i<y2;i++){
			bucle2:
			for(int j=fuente.getWidth()-1;j>=x1;j--){
				tono = (new Color(fuente.getRGB(j, i)).getRed());
				if(tono > umbral){izq_der.remove(new Point(j,i));}
				else {
					if(j>x2){x2=j;}
					break bucle2;
				}
				/*if(habilitado)
					der_izq.add(new Point(j,i));       		
				else{
				tono = (new Color(fuente.getRGB(j, i)).getRed());
				if(tono > umbral)
					der_izq.add(new Point(j,i));       		
					habilitado = true;
				}*/
			}
			//habilitado = false;
		}
		/*
		
		//barrido de arriba a abajo
		for(int i=0;i<fuente.getWidth();i++){
			for(int j=0;j<fuente.getHeight();j++){
				if(habilitado)
					arr_ab.add(new Point(i,j));       		
				else{		
					tono = (new Color(fuente.getRGB(i, j)).getRed());
					if(tono > umbral){
						habilitado = true;
						arr_ab.add(new Point(i,j));       		
					}	
					}
				}
			habilitado = false;
		}
		//barrido de abajo a arriba
		for(int i=0;i>fuente.getWidth();i++){
			for(int j=fuente.getHeight();j>=0;j--){
				if(habilitado)
					ab_arr.add(new Point(i,j));       		
				else{
					tono = (new Color(fuente.getRGB(i, j)).getRed());
					if(tono > umbral){
						habilitado = true;
						ab_arr.add(new Point(i,j));       		
					}
					}
			}
			habilitado = false;
		}
			
		*/
		System.out.println("FIN BARRIDO");
		//interseccion de todos los vectores de puntos
		//izq_der.retainAll(der_izq);
		//izq_der.retainAll(ab_arr);
		//izq_der.retainAll(arr_ab);
		//salida = izq_der;
		return izq_der;
	}
}
