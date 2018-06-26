package modelo;

public class Centurion extends Unidad {


	public Centurion() {

		super(TipoUnidad.CENTURION, 200, 1);
	}

	@Override
	public void restarVida(double danioRecibido) {

		if (!esquivar()) {

			super.restarVida(danioRecibido);

		}
	}

	private boolean esquivar() {

		int esquivar = (int) (Math.random() * 100);
		return esquivar >= 50;

	}

}
