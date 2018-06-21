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

	public void setLegion(String nombre) {
		legion.setNombre(nombre);
	}

	public int tirarDado() {

		valorDado = (int) Math.floor(Math.random() * 6 + 1);
		return valorDado;

	}

	public int getPuntosParaComprar() {
		return puntosParaComprar;
	}

	public void restarPuntosParaComprar(TipoUnidad soldado, int cantidad) {
		if (soldado.equals(TipoUnidad.AUXILIAR)) {

			puntosParaComprar -= cantidad * 50;

		} else if (soldado.equals(TipoUnidad.LEGIONARIO)) {
			puntosParaComprar -= cantidad * 100;

		} else if (soldado.equals(TipoUnidad.CENTURION)) {

			puntosParaComprar -= cantidad * 200;
		}
	}

	public void comprar(TipoUnidad soldado, int cantidad)
			throws ErrorNombreInvalido {

		if (soldado.equals(TipoUnidad.AUXILIAR)) {

			for (int i = 0; i < cantidad; i++) {

				legion.aniadirUnidad(new Auxiliar());
				legion.aumentarAuxiliares();
			}

		} else if (soldado.equals(TipoUnidad.LEGIONARIO)) {

			for (int i = 0; i < cantidad; i++) {
				legion.aniadirUnidad(new Legionario());

			}

		} else if (soldado.equals(TipoUnidad.CENTURION)) {

			for (int i = 0; i < cantidad; i++) {
				legion.aniadirUnidad(new Centurion());

			}

		}

		restarPuntosParaComprar(soldado, cantidad);

	}
}
