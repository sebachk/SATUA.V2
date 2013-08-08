package histograma;

import java.awt.Color;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class HistogramaBlue extends HistogramaRGB {
	
	public HistogramaBlue(BufferedImage img){super(img);}
	@Override
	public int getColor(Color c) {return c.getBlue();}
	@Override
	public String getTitulo() {return "Distribucion de Azules";}
}
