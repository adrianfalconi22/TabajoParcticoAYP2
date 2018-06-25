package modelo;

public class Jugador {

	private String nombre;
	private Legion legion;
	private int puntosParaComprar = 500000;
	int valorDado;

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

		} else if (soldado.equals(TipoUnidad.LEGION)) {

			legion.añadirUnidad(añadirLegion(soldado, cantidad));

		}

		restarPuntosParaComprar(soldado, cantidad);

	}

	public double getVidaDeLaLegion() {

		return legion.getVida();
	}

	public double getDañoTotalDeLaLegion() {

		return legion.getDaño();
	}

	public void setLegion(String nombre) {
		this.legion = new Legion(nombre);

	}

	private Legion añadirLegion(TipoUnidad soldado, int cantidad) {

		Legion legionAuxiliar = new Legion();
		if (soldado.equals(TipoUnidad.AUXILIAR)) {

			for (int i = 0; i < cantidad; i++) {

				legionAuxiliar.añadirUnidad(new Auxiliar());
				legionAuxiliar.aumentarAuxiliares();
			}

		} else if (soldado.equals(TipoUnidad.LEGIONARIO)) {

			for (int i = 0; i < cantidad; i++) {
				legionAuxiliar.añadirUnidad(new Legionario());
				legionAuxiliar.aumentarLegionarios();
			}

		} else if (soldado.equals(TipoUnidad.CENTURION)) {

			for (int i = 0; i < cantidad; i++) {
				legionAuxiliar.añadirUnidad(new Centurion());
				legionAuxiliar.aumentarCenturiones();
			}

		}
		return legionAuxiliar;
	}
}
