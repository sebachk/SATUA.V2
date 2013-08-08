package Herramientas;
import utils.Matriz2D;
import java.awt.Color;
import java.awt.image.BufferedImage;


public class ImageHSV {
	private Matriz2D<Integer> H;
	private Matriz2D<Integer> S;
	private Matriz2D<Integer> V;
	
	/**
	 * Compara los valores de los colores R,G y B y devuelve el maximo de ellos
	 * @param c
	 * @return
	 */
	private int colorMax(Color c){
		int r = c.getRed();
		int b = c.getBlue();
		int g = c.getGreen();
		if((r >= b)&&(r >= g)){return r;}
		else
			if((b > r)&&(b >= g)){return b;}
			else {return g;}
	}
	/**
	 * Compara los valores de los colores R,G y B y devuelve el minimo de ellos
	 * @param c
	 * @return
	 */
	private int colorMin(Color c){
		int r = c.getRed();
		int b = c.getBlue();
		int g = c.getGreen();
		if((r <= b)&&(r <= g)){return r;}
		else
			if((b < r)&&(b <= g)){return b;}
			else {return g;}
	}
	/**
	 * Genera las tres matrices con los valores de Hue, Saturation y Value
	 * @param f
	 */
	private void generateHSV(BufferedImage f){
		double v = 0;
		for(int j=0;j<f.getHeight();j++){
			for(int i=0;i<f.getWidth();i++){
				Color c = new Color(f.getRGB(i,j));
				int max = colorMax(c);
				int min = colorMin(c);
				int r = c.getRed();
				int b = c.getBlue();
				int g = c.getGreen();
				//int maxlessmin = max - min;
				if ((max-min) != 0){
					if (max == r){
						//v=g-b;
						//v*=60;
						//v/=maxlessmin;
						v = 60*(g-b)/(max-min);
						if (g < b)
							v+=360;
					}	
					else {
						if (max == g){v = (60*(b-r)/(max-min))+120;}
							//v=b-r;
							//v*=60;
							//v/=maxlessmin;
							//v+=120;
						else if (max == b){v = (60*(r-g)/(max-min))+240;}
								//v=r-g;
								//v*=60;
								//v/=maxlessmin;
								//v+=240;
					}
				}
				else {v = -1;}
				H.elementAt(i,j,(Integer)(int)v);
				
				if (max == 0) 
					S.elementAt(i,j,0);
				else 
					S.elementAt(i,j,(Integer)((1-(min/max))*100));
				
				V.elementAt(i,j,max);
				}
			}
	}
	/**
	 * Constructor de clase. 
	 * @param f
	 */
	public ImageHSV(BufferedImage f){
		H = new Matriz2D<Integer>(f.getWidth(),f.getHeight());
		S = new Matriz2D<Integer>(f.getWidth(),f.getHeight());
		V = new Matriz2D<Integer>(f.getWidth(),f.getHeight());

		generateHSV(f); // Genero las 3 matrices en simultaneo
	}
	/**
	 * Retorna la Matriz con los valores Hue
	 * @return
	 */
	public Matriz2D<Integer> getH(){return H;}
	/**
	 * Retorna la Matriz con los valores de Saturacion
	 * @return
	 */
	public Matriz2D<Integer> getS(){return S;}
	/**
	 * Retorna la Matriz con los valores Value
	 * @return
	 */
	public Matriz2D<Integer> getV(){return V;}
	/**
	 * Retorna el valor Hue del pixel pasado por posicion
	 * @param x
	 * @param y
	 * @return
	 */
	public Integer getH(int x, int y){return H.elementAt(x, y);}
	/**
	 * Retorna el valor Saturacion del pixel pasado por posicion
	 * @param x
	 * @param y
	 * @return
	 */
	public Integer getS(int x, int y){return S.elementAt(x, y);}
	/**
	 * Retorna el valor Value del pixel pasado por posicion
	 * @param x
	 * @param y
	 * @return
	 */
	public Integer getV(int x, int y){return V.elementAt(x, y);}
}
