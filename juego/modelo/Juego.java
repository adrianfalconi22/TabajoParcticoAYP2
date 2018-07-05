package modelo;

public class Juego {

	private static final String nombre = "Lucha Romana";
	private Jugador[] jugadores = new Jugador[2];
	private boolean juegoTerminado = false;
	private Jugador jugadorActual;
	private Jugador jugadorRival;
	private String ganador;

	/** Constructor del juego se crea con 2 jugadores */
	public Juego(Jugador jugador1, Jugador jugador2) {
		// this.jugadorActual = jugador1;
		// this.jugadorRival = jugador2;

		jugadores[0] = jugador1;
		jugadores[1] = jugador2;

	}

	public void setJugadorUno(Jugador jugador) {
		this.jugadores[0] = jugador;
	}

	public void setJugadorDos(Jugador jugador) {
		this.jugadores[1] = jugador;
	}

	public Juego() {

	}

	//
	// public void setJugadorActualYRival() {
	// if (jugadorActual.equals(jugadores[0])
	// && jugadorRival.equals(jugadores[1])) {
	// jugadorActual = jugadores[1];
	// jugadorRival = jugadores[0];
	// } else if (jugadorActual.equals(jugadores[1])
	// && jugadorRival.equals(jugadores[0])) {
	// jugadorActual = jugadores[0];
	// jugadorRival = jugadores[1];
	// }
	//
	// }

	/** retorna el nombre del primer jugador */
	public String getNombreJugador1() {
		return jugadores[0].getNombre();
	}

	/** retorna el nombre del segundo jugador */
	public String getNombreJugador2() {
		return jugadores[1].getNombre();
	}

	public Jugador getJugador(int posicion) {
		return jugadores[posicion];
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public Jugador getJugadorRival() {
		return jugadorRival;
	}

	public String getGanador() {
		return ganador;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean juegoTerminado() {

		if (jugadores[0].getLegion().getVida() <= 0) {
			juegoTerminado = true;
			ganador = jugadores[1].getNombre();
		} else if (jugadores[1].getLegion().getVida() <= 0) {
			juegoTerminado = true;
			ganador = jugadores[0].getNombre();
		}
		return juegoTerminado;
	}

}
