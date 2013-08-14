package transformacion;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;


public class Zoom extends Transformacion {

	private int porcentaje;
	
	public Zoom(int muestreo){
		this.setPorcentaje(muestreo);
		
	}
	
	
	
	public Point pointFromOriginal(int row,int col){
		Point p= new Point();
		
		p.x=(row*porcentaje)/100;
		p.y=(col*porcentaje)/100;
		
		return p;
	}
	
	public void setPorcentaje(int ptje){
		porcentaje=ptje;
	}
	
	public Point pointFromDestiny(int row,int col){
		Point p= new Point();
		
		p.x=(row*100)/porcentaje;
		p.y=(col*100)/porcentaje;
	
		return p;
	}
	
	@Override
	public BufferedImage transformar(BufferedImage original){
		
		BufferedImage modificada;
		int width=(original.getWidth()*porcentaje)/100;
		int height=(original.getHeight()*porcentaje)/100;
		
		modificada = new BufferedImage(width, height, original.getType());
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++){
				int rgb= aplicar(original,i,j);
				modificada.setRGB(i, j, rgb);
			}
		return modificada;
	}

	@Override
	public int aplicar(BufferedImage origen, int x, int y) {
		// TODO Auto-generated method stub
		Point point = this.pointFromDestiny(x,y);
		return origen.getRGB(point.x, point.y);
	}
}