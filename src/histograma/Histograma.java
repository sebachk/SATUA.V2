package histograma;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

@SuppressWarnings("serial")
public abstract class Histograma extends Component{

	protected Vector<Integer> intensidades;
	protected int x;
	protected int y;
	protected int moda;//valor mas repetido
	protected int altura;
	
	protected double suma;//valor total
	protected int mediana; //el valor parado al medio
	protected double totalValores;
	
	
	public Histograma(){
		moda=0;
		x=0;
		y=0;
		altura=300; //valor por defecto
		suma=0;
		mediana=-1;
		totalValores=0;
	}
	public abstract void fillVector(Object o);
	
	public abstract String getTitulo();
	
	protected void addValue(int index)
	{
		int v= intensidades.elementAt(index)+1;
		if(moda<v)moda=v;
		intensidades.set(index, v);
		totalValores++;
		suma+=index;
	}
	
	public void setUbicacion(int i, int j){x=i;y=j;}
	
	public void setAltura(int h){altura=h;}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(1.0f));
		Shape rect;
		if(moda==0) moda=1;
		g2.setColor(Color.black);
		
		//Ordenadas y absisas
		g2.fill(new Rectangle2D.Float((float)x-3,(float)y-altura,1,altura));
		g2.fill(new Rectangle2D.Float((float)x-3,(float)y,intensidades.size()+4,1));
		
		//carga con normalizacion
		for(int i=0;i<intensidades.size();i++){
			
			float h=intensidades.elementAt(i)*altura; //300 es un valor para achatamiento
			h/=moda;
			rect = new Rectangle2D.Float((float)x+i,(float)y-h,1,h);
		    g2.fill(rect);
		}
		
		
		String encabezado = getTitulo();
		
		//Etiquetas
		g.drawString(encabezado, x, y-altura-10);
		g.drawString("0", x, y+12);
		g.drawString("128",x+128,y+12);
		g.drawString("255", x+255, y+12);
		g.drawString("10", x+10, y+12);
		g.drawString("20",x+20,y+12);
		g.drawString("30", x+30, y+12);
		g.drawString("Moda:"+moda+"", x, y+40);
	}
	
	
	public Dimension getPreferredSize() {return new Dimension(275, 450);}
	
	
	public int getMedia(){
		return (int)(suma/totalValores);
	}
	
	@SuppressWarnings("unused")
	public int getMediana(){
		double mitad=0;
		if(mediana==-1){
			for(int i=0;i<intensidades.size();i++){
				mitad+=intensidades.elementAt(i);
				if(mitad>totalValores)
					mediana=i;
				break;
			}
		}
		
		return mediana;
	}
	
	public int getModa(){
		return moda;
	}
	
	
}