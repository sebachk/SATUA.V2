package Herramientas;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;



import transformacion.Transformacion;

/**
 *Recorre una imagen de bordes (BufferedImage) de izquierda a derecha y luego 
 *de derecha a izquierda tambien de arriba a abajo y de abajo a arriba 
 *y descarta los pixeles negros hasta chocar con un borde
 **/
public class Segmentador {
	private Vector<Objeto> objetos;
	
	public static boolean[][] visitados;
	
	public Segmentador(){
		objetos = new Vector<Objeto>();
	}
	
	
	public void NuevoBlanco(Point p){
		boolean[] contenidos= new boolean[objetos.size()];
		for(int i=0;i<contenidos.length;i++){
			contenidos[i]=objetos.elementAt(i).esAdyacente(p);
		}
		
		Objeto obj=new ObjetoPoligonal();
		
		obj.addPunto(p);
		Vector<Objeto> nuevo= new Vector<Objeto>();
		Objeto j;
		for(int i=0;i<contenidos.length;i++){
			j=objetos.elementAt(i);
			if(contenidos[i])
				obj.fusionar(j);
			else nuevo.add(j);
		}
		nuevo.add(obj);
		objetos=nuevo;
	}
	
	private void DFS(BufferedImage fuente, Point p,Objeto obj){
		if(!visitados[p.x][p.y]){
			visitados[p.x][p.y]=true;
			//NuevoBlanco(p);
			obj.addPunto(p);
			Vector<Point> vecinos= this.Vecinos(fuente, p);
			for(Point punto:vecinos){
				DFS(fuente,punto,obj);
			}
		}
	}
	
	private Vector<Point> Vecinos(BufferedImage fuente,Point p){
		Vector<Point> res= new Vector<>();
		if(!Transformacion.OutOfBounds(fuente, p.x-1, p.y))
			res.add(new Point(p.x-1,p.y));
		if(!Transformacion.OutOfBounds(fuente, p.x+1, p.y))
			res.add(new Point(p.x+1,p.y));
		if(!Transformacion.OutOfBounds(fuente, p.x, p.y-1))
			res.add(new Point(p.x,p.y-1));
		if(!Transformacion.OutOfBounds(fuente, p.x, p.y+1))
			res.add(new Point(p.x,p.y+1));
		return res;
	}
	
	public void segmentar(BufferedImage fuente){
		Point p=new Point();
		System.out.println(visitados);
		for(int i=0;i<fuente.getWidth();i++){
			for(int j=0;j<fuente.getHeight();j++){
				p.x=i;
				p.y=j;
				if(!visitados[i][j]){
					ObjetoPoligonal obj= new ObjetoPoligonal();
					DFS(fuente, p,obj);
					objetos.add(obj);
				}
			}
		}
	}
	
	public Objeto getMayor(){
		Objeto resultado=new ObjetoPoligonal();
		for(Objeto o:objetos){
			if(o.esMayor(resultado))
				resultado=o;
		}
		return resultado;
	}
	
	protected Vector<Point> segmentar(){
		return null;
	/*	Vector<Point> izq_der = new Vector<Point>();
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
		/*}
		
		
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
			
		
		System.out.println("FIN BARRIDO");
		//interseccion de todos los vectores de puntos
		//izq_der.retainAll(der_izq);
		//izq_der.retainAll(ab_arr);
		//izq_der.retainAll(arr_ab);
		//salida = izq_der;
		return izq_der;*/
	

	
}
