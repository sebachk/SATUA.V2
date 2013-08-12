package transformacion;

import java.awt.Color;
import java.awt.image.BufferedImage;


public class Umbralador extends Transformacion {

	private int umbral;
	private BufferedImage imagen;
	
	private BufferedImage destino;
	
	public Umbralador(int umbral, BufferedImage img){
		this.umbral=umbral;
		this.imagen = img;
	}
	
	
	@Override
	public int aplicar(BufferedImage origen, int x, int y) {
		Color color= new Color(origen.getRGB(x, y));
		return (color.getRed() < umbral)?  0: 255;
	}


	public int getUmbral() {
		return umbral;
	}


	public void setUmbral(int umbral) {
		this.umbral = umbral;
		
		this.destino = this.transformar(imagen);
	}


	public BufferedImage getImagen() {
		return imagen;

	}


	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;

		this.destino = this.transformar(imagen);
	}
	
	public BufferedImage getDestino(){
	
		return destino;
	}
	

}
