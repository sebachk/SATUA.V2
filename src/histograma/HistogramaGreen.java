package histograma;

import java.awt.Color;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class HistogramaGreen extends HistogramaRGB {

	public HistogramaGreen(BufferedImage img){super(img);}
	@Override
	public int getColor(Color c) {return c.getGreen();}
	@Override
	public String getTitulo() {return "Distribucion de Verdes";}
}
