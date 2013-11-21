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
import Herramientas.Objeto;
import Herramientas.ObjetoRectangular;
import Herramientas.Segmentador;

import transformacion.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedImage imgOriginal=null;
		BufferedImage imgGris=null;
	
		/**Abro y transformo**/
		try {
			imgOriginal = ImageIO.read(new File("FondoNegro.jpg"));
			imgGris = Transformacion.toGrey(imgOriginal);
			//ImageIO.write(img2, "jpg", new File("imagbyn.jpg"));
			
		} catch (IOException e) {
			System.out.println("Fallo la carga de la imagen");
		}
		
		/** Creo el archivo de salida con la imagen en gris**/
		BufferedImage salida = new BufferedImage(imgOriginal.getWidth(),imgOriginal.getHeight(),imgOriginal.getType());
		for(int i=0;i<imgOriginal.getWidth();i++)
			for(int j=0;j<imgOriginal.getHeight();j++){{
					salida.setRGB(i, j, imgGris.getRGB(i, j)&0X00FFFFFF);
				}
			}
		try {
			ImageIO.write(imgOriginal,"png", new File("trans.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("FALLA");
			e1.printStackTrace();
		}
		BufferedImage transformada;
		
		/**Cambio el muestreo de la imagen**/
		CambiadorMuestreo cam = new CambiadorMuestreo(800, 600);
		transformada = cam.transformar(imgGris);
		
		/** Elimino ruido*/
		transformada = new ReductorRuido().transformar(transformada);
		
		
		/** Obtencion de Bordes**/
		//Transformacion SOBEL
		Integer[] HOR ={1,2,1,0,0,0,-1,-2,-1};
		Integer[] VER ={-1,0,1,-2,0,2,-1,0,1};
		
		Integer[] HORI ={-1,-2,-1,0,0,0,1,2,1};
		Integer[] VERI ={1,0,-1,2,0,-2,1,0,-1};
		
		Convolucion bordes = new Convolucion(HOR);
		bordes.setElement(VER);
		bordes.setElement(HORI);
		bordes.setElement(VERI);
		
		
		transformada = bordes.transformar(transformada);
		
		
		
		/** Calculo el histograma*/
		HistogramaBlue hb= new HistogramaBlue(transformada);
		
		
		/** Umbralo*/
		Segmentador.visitados=new boolean[transformada.getWidth()][transformada.getHeight()];
		Umbralador umbral = new Umbralador(hb.getpercentil());
		transformada = umbral.transformar(transformada);
		
		
		/** Erosion y dilatacion para discriminar los objetos de los No objetos*/
		Erosion erosion = new Erosion();
		transformada = erosion.transformar(transformada);
		//transformada = erosion.transformar(transformada);
		//transformada = erosion.transformar(transformada);
		
		Dilatacion dil = new Dilatacion();
		transformada = dil.transformar(transformada);
		//transformada = dil.transformar(transformada);
		//transformada = dil.transformar(transformada);
		//transformada = dil.transformar(transformada);
		
		
		
		
		//HSV 
		//ImageHSV pepe = new ImageHSV(img);
		//HistogramaMatricial H = new HistogramaMatricial(pepe.getH());
		//H.setUbicacion(10,400);
		
		
		/** Segmentar la imagen*/
		System.out.println("EMPEZANDO A SEGMENTAR");
		Segmentador seg = new Segmentador();
		seg.segmentar(transformada);
		ObjetoRectangular objRect= new ObjetoRectangular();
		objRect.fusionar(seg.getMayor());
		System.out.println("EMPEZANDO A DIBUJAR");
		transformada = objRect.dibujar(imgOriginal, imgOriginal.getWidth(), imgOriginal.getHeight());
		System.out.println("FIN DEL DIBUJADOR");
//		Segmento seg = new Segmento(transformada);
//		Vector<Point> LP = seg.getSegmento();
//		BufferedImage imgsmt = new BufferedImage(imgOriginal.getWidth(),imgOriginal.getHeight(),imgOriginal.getType());
//		Point p;
//		System.out.println("TRASPASO");
//		for(int i=0;i<LP.size();i++){
//			p=LP.elementAt(i);
//			imgsmt.setRGB(p.x, p.y, imgOriginal.getRGB(p.x, p.y));
//		}
		//DibujadorImagenes DI = new DibujadorImagenes(imgsmt);
		

		/**Dibujador de imagen*/
		DibujadorImagenes DI = new DibujadorImagenes(transformada);
		
		
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
