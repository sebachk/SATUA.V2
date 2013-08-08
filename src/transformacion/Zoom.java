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
	
	private BufferedImage original;
	private BufferedImage modificada;
	
	public Zoom(BufferedImage buf,int muestreo){
		original=buf;
		this.setPorcentaje(muestreo);
		
	}
	
	public BufferedImage getModificada(){
		return modificada;
	}
	
	public BufferedImage getOriginal(){
		return original;
	}
	
	private void promediar(int fila,int col, int ancho,int alto){
		double valor=0;
		int prom=ancho*alto;
		Color c;
		
		for(int i=0;i<ancho;i++)
			for(int j=0;j<alto;j++){
				int colorin=original.getRGB(fila+i, col+j);
				c= new Color(colorin);
				
				int r,g,b;
				r=c.getRed();
				g=c.getGreen();
				b=c.getBlue();
				valor+=r*0.299+g*0.587+b*0.114;
		}
		
		prom=(int) Math.round(valor/prom);
		
		c=new Color(prom,prom,prom,255);
		for(int i=0;i<ancho;i++)
			for(int j=0;j<alto;j++){
				modificada.setRGB(fila+i,col+j,c.getRGB());
		}
	}
	
	public Point pointFromOriginal(int row,int col){
		Point p= new Point();
		
		p.x=(row*porcentaje)/100;
		p.y=(col*porcentaje)/100;
		
		return p;
	}
	
	public void setPorcentaje(int ptje){
		porcentaje=ptje;
		System.out.println(porcentaje);
		modificada= new BufferedImage((original.getWidth()*porcentaje)/100, (original.getHeight()*porcentaje)/100, original.getType());
	}
	
	public Point pointFromDestiny(int row,int col){
		Point p= new Point();
		
		p.x=(row*100)/porcentaje;
		p.y=(col*100)/porcentaje;
	
		return p;
	}
	
	@Override
	public BufferedImage transformar(BufferedImage origen){
		
		int width=(original.getWidth()*porcentaje)/100;
		int height=(original.getHeight()*porcentaje)/100;
		
		Point point;
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++){
				point = this.pointFromDestiny(i,j);
				int rgb=original.getRGB(point.x, point.y);
				modificada.setRGB(i, j, rgb);
			}
		return this.modificada;
	}

	@Override
	public int aplicar(BufferedImage origen, int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
