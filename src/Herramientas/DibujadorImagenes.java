package Herramientas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class DibujadorImagenes extends Component {

	private BufferedImage img;
	
	public DibujadorImagenes(BufferedImage img){
		this.img=img;
	}
	public void paint(Graphics g){
		g.drawImage(img, 0, 0,800,600, null);
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	
}
}
