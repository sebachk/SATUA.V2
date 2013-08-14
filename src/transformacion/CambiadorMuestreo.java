package transformacion;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class CambiadorMuestreo extends Transformacion {
	
	private Point muestreo;
	
	public CambiadorMuestreo(Point p)
	{
		setMuestreo(p);
	}
	
	public CambiadorMuestreo(int x, int y)
	{
		setMuestreo(x,y);
	}
	@Override
	public BufferedImage transformar(BufferedImage original)
	{
		BufferedImage dest = new BufferedImage(getMuestreo().x, getMuestreo().y, original.getType());
		
		for(int i=0;i<dest.getWidth();i++){
			for(int j=0;j<dest.getHeight();j++){
				dest.setRGB(i, j, aplicar(original,i,j));
			}
		}
		return dest;
	}
	
	@Override
	public int aplicar(BufferedImage origen, int x, int y) {
		Point p = pointFromdestiny(origen, x, y);
		return origen.getRGB(p.x, p.y);
		
	}

	public Point pointFromdestiny(BufferedImage img,int x, int y)
	{
		int XX=muestreo.x*x/img.getWidth();
		int YY=muestreo.y*y/img.getHeight();
		
		return new Point(XX,YY);
	}
	public Point getMuestreo() {
		return muestreo;
	}

	public void setMuestreo(Point muestreo) {
		this.muestreo = muestreo;
	}
	
	public void setMuestreo(int width,int height)
	{
		this.setMuestreo(new Point(width,height));
	
	}

}
