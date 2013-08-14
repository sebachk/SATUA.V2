package transformacion;

import java.awt.Color;

public class Dilatacion extends OpMorfologico {
	@Override
	public boolean cumple(Color c) {return (c.getBlue() > NEGRO);}

	@Override
	public int loadDefault() {return NEGRO;}

	@Override
	public int loadNonDefault() {return BLANCO;}

}
