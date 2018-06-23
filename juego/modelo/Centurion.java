package modelo;

public class Centurion extends Unidad {

	private double da�o;
	private int costo;

	public Centurion() {
		this.tipo = TipoUnidad.CENTURION;
		this.costo=200;
		this.da�o=1.0;
	}

	public double getVida() {

		return super.getVida();
	}

	@Override
	public double getDa�o() {
		return da�o;
	}

	@Override
	public double getCosto() {
		return costo;
	}

	@Override
	public void setVida(double da�o) {
		if (probabilidad()) {
			super.setVida(da�o);
		}
	}

	public boolean estaVivo() {

		return super.estaVivo();
	}
}
