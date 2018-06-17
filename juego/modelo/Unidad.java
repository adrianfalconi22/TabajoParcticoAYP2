package modelo;
public abstract class Unidad {

	private TipoUnidad tipo;
	private double vida = 100;
	private double costo;
	private double danio;

	public Unidad(TipoUnidad tipo, double costo, double danio) {
		setTipo(tipo); // Aca no va a pasar nada, pero estaria copado acostumbrarse a usar this
		this.costo = costo; // Quiza convendria usar el setter, asi si despues quieren hacer alguna validacion puede aplicarse tambien al constructor sin copiar codigo
		this.danio = danio;
		// Podriamos aprovechar y setear la vida aca tambien
	}

	public boolean estaVivo() {
		if (vida <= 0) {
			return false;
		}
		return true;
		// En este caso, return vida <= 0 funca exactamente igual y es mas simple
	}

	public void setVida(double vida) {
		this.vida = vida;
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
