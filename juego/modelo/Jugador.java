package modelo;

public class Jugador {

	private String nombre;
	private  Legion legion; // Segun el enunciado, los ejercitos se almacenan
								// en memoria como un Composite. Yo volaria la
								// clase y usaria una legion.
	private static int puntosParaComprar = 500000;
	int valorDado;
	private Unidad unidad;

	public Jugador(String nombre) throws ErrorNombreInvalido {
		setNombre(nombre);

	}

	public Jugador() {

	}

	public void setNombre(String nombre) throws ErrorNombreInvalido {
		if (nombre.equals(" ") || nombre == null) {
			throw new ErrorNombreInvalido();

		}
		this.nombre = nombre;

	}

	public String getNombre() {
		return nombre;
	}

	public Legion getLegion() {
		return legion;
	}

	public void setLegion(String nombreLegion) throws ErrorNombreInvalido {
		if (nombreLegion.equals("") || nombreLegion == null) {
			throw new ErrorNombreInvalido();

		}

		legion = new Legion(nombreLegion);
	}

	public int tirarDado() {

		valorDado = (int) Math.floor(Math.random() * 6 + 1);
		return valorDado;

	}

	public int getPuntosParaComprar() {
		return puntosParaComprar;
	}


	public static void restarPuntosParaComprar(TipoUnidad soldado, int cantidad) {
		if (soldado.equals(TipoUnidad.AUXILIAR)) {

			puntosParaComprar -= cantidad * 50;

		} else if (soldado.equals(TipoUnidad.LEGIONARIO)) {
			puntosParaComprar -= cantidad * 100;

		} else if (soldado.equals(TipoUnidad.CENTURION)) {

			puntosParaComprar -= cantidad * 200;
		}
	}

}
