package histograma;

import java.awt.Color;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class HistogramaRed extends HistogramaRGB {

	public HistogramaRed(BufferedImage img){super(img);}
	@Override
	public int getColor(Color c) {return c.getRed();}
	@Override
	public String getTitulo() {return "Distribucion de Rojos";}
}
