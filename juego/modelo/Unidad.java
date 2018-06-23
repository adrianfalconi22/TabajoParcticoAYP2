package modelo;

public abstract class Unidad {

	protected double vida;
	protected double daño;
	protected int costo;
	protected TipoUnidad tipo;

	public Unidad() {
		vida = 100;
	}

	public abstract double getDaño();

	public abstract double getCosto();

	public double getVida() {

		return vida;
	}

	protected boolean probabilidad() {
		int probabilidad = (int) Math.random() * 100;

		return probabilidad > 50;
	}

	public boolean estaVivo() {

		return vida > 0;
	}

	public void setVida(double daño) {
		this.vida = vida - daño;

	}

}
