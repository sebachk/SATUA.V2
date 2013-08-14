package main;
import histograma.HistogramaBlue;

import histograma.HistogramaGreen;
import histograma.HistogramaMatricial;
import histograma.HistogramaRed;

import java.awt.Panel;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Herramientas.DibujadorImagenes;
import Herramientas.ImageHSV;
import Herramientas.Segmento;

import transformacion.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedImage img=null;
		BufferedImage img2=null;
		
		try {
			img = ImageIO.read(new File("FondoNegro.jpg"));
			img2 = Transformacion.toGrey(img);
			//ImageIO.write(img2, "jpg", new File("imagbyn.jpg"));
			
		} catch (IOException e) {
			System.out.println("Fallo la carga de la imagen");
		}
		
		//Transformacion SOBEL
		Integer[] HOR ={1,2,1,0,0,0,-1,-2,-1};
		Integer[] VER ={-1,0,1,-2,0,2,-1,0,1};
		
		Integer[] HORI ={-1,-2,-1,0,0,0,1,2,1};
		Integer[] VERI ={1,0,-1,2,0,-2,1,0,-1};
		
		
		
		Convolucion bordes = new Convolucion(HOR);
		bordes.setElement(VER);
		bordes.setElement(HORI);
		bordes.setElement(VERI);
		
		BufferedImage transformada;// = bordes.transformar(img2);
		
		/*
		//Eliminacion del ruido
		Float[] RUIDO ={1/9f,1/9f,1/9f,1/9f,1/9f,1/9f,1/9f,1/9f,1/9f};
		ConvolucionFloat bordes = new ConvolucionFloat(RUIDO);
		BufferedImage transformada = bordes.transformar(img);
		*/
		Zoom z= new Zoom(25);
		transformada = z.transformar(img2);
		transformada = bordes.transformar(transformada);
		
		//transformada = bordes.transformar(img2);
		DibujadorImagenes DI = new DibujadorImagenes(transformada);
		
		
		//HSV 
		//ImageHSV pepe = new ImageHSV(img);
		//HistogramaMatricial H = new HistogramaMatricial(pepe.getH());
		//H.setUbicacion(10,400);
		
		HistogramaBlue hb= new HistogramaBlue(transformada);
	
		/*
		Segmento seg = new Segmento(transformada);
		Vector<Point> LP = seg.getSegmento();
		BufferedImage imgsmt = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
		Point p;
		System.out.println("TRASPASO");
		for(int i=0;i<LP.size();i++){
			p=LP.elementAt(i);
			imgsmt.setRGB(p.x, p.y, img.getRGB(p.x, p.y));
		}
		DibujadorImagenes DI = new DibujadorImagenes(imgsmt);
		*/
		JFrame f = new JFrame("Load Image Sample");
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Panel p1= new Panel();
		p1.add(DI);
		p1.add(hb);
		
		f.add(p1);
		f.setVisible(true); 
		f.setSize(800, 500);
		
		
		
		
	
	}
	

}
