package modelo;

public class Legionario extends Unidad {

	private double da�o;
	private int costo;

	public Legionario() {
		tipo = TipoUnidad.LEGIONARIO;
		this.da�o = 1.4;
		costo = 100;
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

	public void setVida(double da�o) {
		super.setVida(da�o);
	}

	public boolean estaVivo() {

		return super.estaVivo();
	}
}
