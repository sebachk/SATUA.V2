package utils;

import java.util.Vector;


public class Matriz2D <T>{

	private Vector<Vector<T>> nucleo;
	private int columnas;
	private int filas;
	
	
	public Matriz2D(int col,int fil){
		columnas=col;
		filas=fil;
		nucleo = new Vector<Vector<T>>();
		Vector<T> v;
		for(int i=0;i<col;i++){
			v=new Vector<T>();
			nucleo.add(v);
			v.setSize(fil);
		}
	}
	/**
	 * llena la matriz a partir de un vector planchado, el size del vector debe ser mayor o igual
	 * al size de la matriz (columnas*filas)
	 */
	public void fill(Vector<T> vec){
		int t=0;
		for(int i=0;i<columnas;i++){
			for(int j=0;j<filas;j++){
				elementAt(i, j, vec.elementAt(t));
				t++;
			}
		}
	}
	
	
	public T elementAt(int i,int j){
		return nucleo.elementAt(i).elementAt(j);
	}
	
	public void elementAt(int i,int j,T t){
		nucleo.elementAt(i).setElementAt(t, j);
	}
	
	public void resize(int col, int fila){
		nucleo.setSize(col);
		Vector<T> v;
		for(int i=0;i<col;i++){
			v=nucleo.elementAt(i);
			if(v==null){
				v= new Vector<T>();
				nucleo.setElementAt(v,i);
			}
			v.setSize(fila);
		}
	}
	
	public int size (){
		return columnas*filas;
	}
	
	public int getHeight(){return filas;}
	public int getWidth(){return columnas;}
	
	
	
	
}
