package modelo;


public class Auxiliar extends Unidad {

	private double da�o;
	private int costo;

	public Auxiliar() {
		tipo = TipoUnidad.AUXILIAR;
		this.da�o = 0.7;
		this.costo=50;
	}

	public double getVida() {

		return super.getVida();
	}

	@Override
	public double getDa�o() {

		if (!probabilidad()) {

			return da�o;
		} else {
			return 0;
		}
	}

	@Override
	public double getCosto() {
		return costo;
	}

	public void setVida(double da�o) {
		super.setVida(da�o);
	}

	public boolean estaVivo() {

		return super.estaVivo();
	}

}
