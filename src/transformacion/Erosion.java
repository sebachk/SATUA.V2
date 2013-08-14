package transformacion;

import java.awt.Color;

public class Erosion extends OpMorfologico {
	@Override
	public boolean cumple(Color c) {return (c.getBlue() < BLANCO);}
	
	@Override
	public int loadDefault() {return BLANCO;}

	@Override
	public int loadNonDefault() {return NEGRO;}

}
