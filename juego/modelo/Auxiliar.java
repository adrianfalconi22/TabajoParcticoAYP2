package modelo;


public class Auxiliar extends Unidad {

	private double daño;
	private int costo;

	public Auxiliar() {
		tipo = TipoUnidad.AUXILIAR;
		this.daño = 0.7;
		this.costo=50;
	}

	public double getVida() {

		return super.getVida();
	}

	@Override
	public double getDaño() {

		if (!probabilidad()) {

			return daño;
		} else {
			return 0;
		}
	}

	@Override
	public double getCosto() {
		return costo;
	}

	public void setVida(double daño) {
		super.setVida(daño);
	}

	public boolean estaVivo() {

		return super.estaVivo();
	}

}
