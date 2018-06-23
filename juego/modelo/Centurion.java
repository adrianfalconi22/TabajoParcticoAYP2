package modelo;

public class Centurion extends Unidad {

	private double daño;
	private int costo;

	public Centurion() {
		this.tipo = TipoUnidad.CENTURION;
		this.costo=200;
		this.daño=1.0;
	}

	public double getVida() {

		return super.getVida();
	}

	@Override
	public double getDaño() {
		return daño;
	}

	@Override
	public double getCosto() {
		return costo;
	}

	@Override
	public void setVida(double daño) {
		if (probabilidad()) {
			super.setVida(daño);
		}
	}

	public boolean estaVivo() {

		return super.estaVivo();
	}
}
