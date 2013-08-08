package histograma;
import java.util.Vector;
import utils.Matriz2D;

@SuppressWarnings("serial")
public class HistogramaMatricial extends Histograma {

	public HistogramaMatricial(Matriz2D<Integer> matriz){
		super();
		fillVector(matriz);
		}
	
	@Override
	public void fillVector(Object o) {
		@SuppressWarnings("unchecked")
		Matriz2D<Integer> matriz = (Matriz2D<Integer>) o;
		intensidades = new Vector<Integer>();
		
		for(int t=0;t<=360;t++){intensidades.add(0);} //inicialización del vector
		
		for (int i = 0; i < matriz.getHeight(); i++) {
			for (int j = 0; j < matriz.getWidth(); j++) {
				Integer color = matriz.elementAt(j,i);
				if(color != -1)
					addValue(color);
			}
		}
	}

	@Override
	public String getTitulo() {return "Distribucion Matricial";}

}
