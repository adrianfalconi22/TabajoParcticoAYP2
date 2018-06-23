package modelo;

public class Legionario extends Unidad {

	private double daño;
	private int costo;

	public Legionario() {
		tipo = TipoUnidad.LEGIONARIO;
		this.daño = 1.4;
		costo = 100;
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

	public void setVida(double daño) {
		super.setVida(daño);
	}

	public boolean estaVivo() {

		return super.estaVivo();
	}
}
