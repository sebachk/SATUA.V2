package transformacion;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class CambiadorMuestreo extends Zoom {
	
	private Point muestreo;
	
	public CambiadorMuestreo(int x, int y)
	{
		super(100,100);
		setMuestreo(x,y);
	}
	@Override
	public BufferedImage transformar(BufferedImage original)
	{
		setPorcentajeX(muestreo.x*100/original.getWidth());
		setPorcentajeY(muestreo.y*100/original.getHeight());
		System.out.println(this.porcentajeX);
		System.out.println(this.porcentajeY);
		
		return super.transformar(original);
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
