package modelo;

public class Jugador {

	private String nombre;
	private Legion legion; // Segun el enunciado, los ejercitos se almacenan
							// en memoria como un Composite. Yo volaria la
							// clase y usaria una legion.
	private int puntosParaComprar = 500000;
	int valorDado;

	public Jugador(String nombre) throws ErrorNombreInvalido {
		setNombre(nombre);
		legion = new Legion();
	}

	public Jugador() {
		legion = new Legion();
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

	public int tirarDado() {

		valorDado = (int) Math.floor(Math.random() * 6 + 1);
		return valorDado;

	}

	public int getPuntosParaComprar() {
		return puntosParaComprar;
	}

	public void restarPuntosParaComprar(TipoUnidad soldado, int cantidad) {
		if (soldado == (TipoUnidad.AUXILIAR)) {

			puntosParaComprar -= cantidad * 50;

		} else if (soldado == (TipoUnidad.LEGIONARIO)) {
			puntosParaComprar -= cantidad * 100;

		} else if (soldado == (TipoUnidad.CENTURION)) {

			puntosParaComprar -= cantidad * 200;
		}
	}

	public void comprar(TipoUnidad soldado, int cantidad)
			throws ErrorNombreInvalido {

		if (soldado.equals(TipoUnidad.AUXILIAR)) {

			for (int i = 0; i < cantidad; i++) {

				legion.añadirUnidad(new Auxiliar());
				legion.aumentarAuxiliares();
			}

		} else if (soldado.equals(TipoUnidad.LEGIONARIO)) {

			for (int i = 0; i < cantidad; i++) {
				legion.añadirUnidad(new Legionario());
				legion.aumentarLegionarios();
			}

		} else if (soldado.equals(TipoUnidad.CENTURION)) {

			for (int i = 0; i < cantidad; i++) {
				legion.añadirUnidad(new Centurion());
				legion.aumentarCenturiones();
			}

		}

		restarPuntosParaComprar(soldado, cantidad);

	}

	public double getVidaDeLaLegion() {

		return legion.calcularVidaTotalDeLaLegion();
	}

	public double getDañoTotalDeLaLegion() {
		// TODO Apéndice de método generado automáticamente
		return legion.getDaño();
	}
}
