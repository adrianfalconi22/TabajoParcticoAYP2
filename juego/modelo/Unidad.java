package modelo;

public abstract class Unidad {

	protected double vida;
	protected double da�o;
	protected int costo;
	protected TipoUnidad tipo;

	public Unidad() {
		vida = 100;
	}

	public abstract double getDa�o();

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

	public void setVida(double da�o) {
		this.vida = vida - da�o;

	}

}
