package modelo;

public abstract class Unidad {

	private TipoUnidad tipo;
	private double vida = 100;
	private double costo;
	private double danio;

	public Unidad(TipoUnidad tipo, double costo, double danio) {
		setTipo(tipo);
		this.costo = costo;
		this.danio = danio;

	}

	public boolean estaVivo() {

		return vida >= 0;

	}

	public void setVida(double vida) {
		this.vida -= vida;
	}

	public Unidad() {

	}

	public double getVida() {
		return vida;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getDanio() {
		return danio;
	}

	public void setDanio(double danio) {
		this.danio = danio;
	}

	public void restarVida(double danioRecibido) {
		this.vida -= danioRecibido;

	}

	public TipoUnidad getTipo() {
		return tipo;
	}

	public void setTipo(TipoUnidad tipo) {
		this.tipo = tipo;
	}
}