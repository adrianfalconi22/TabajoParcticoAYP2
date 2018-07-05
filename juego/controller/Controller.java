package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import modelo.ErrorAlLeerElArchivo;
import modelo.ErrorNombreInvalido;
import modelo.Juego;
import modelo.Jugador;
import modelo.Legion;
import modelo.TipoUnidad;

public class Controller {
	Juego juego;
	Jugador j1 = null;
	Jugador j2 = null;
	int cantidad;
	Jugador jugadorQueArmaPrimero = null;
	Jugador jugadorQueArmaSegundo = null;
	private List<Double> totalesPorPrecioFC = new ArrayList<>();
	private List<Double> totalesPorPrecioFPC = new ArrayList<>();
	private String separador;
	private Scanner scan = new Scanner(System.in);
	private int opcion;
	int resultado1;
	int resultado2;
	String botonTirar;
	static ArrayList<Legion> legionesPreArmadas = new ArrayList<Legion>();

	public void iniciar() throws IOException, ErrorAlLeerElArchivo,
			ErrorNombreInvalido {
		juego = new Juego();
		System.out.println(juego.getNombre());

		System.out.println("Ingrese el nombre del jugador1");
		String nombreJugador1 = scan.nextLine();

		System.out.println("Ingrese el nombre del jugador2");
		String nombreJugador2 = scan.nextLine();
		crearJugador1(nombreJugador1);
		crearJugador2(nombreJugador2);

		tirarDado();
		armarEjercito();
		ataque();

	}

	public void crearJugador1(String nombreJugador1) {
		try {
			j1 = new Jugador(nombreJugador1);
			juego.setJugadorUno(j1);

		} catch (ErrorNombreInvalido e) {
			System.out.println(e.getMessage());
			crearJugador1(nombreJugador1);
		}

	}

	public void crearJugador2(String nombreJugador2) {
		try {
			j2 = new Jugador(nombreJugador2);
			juego.setJugadorDos(j2);

		} catch (ErrorNombreInvalido e) {
			System.out.println(e.getMessage());
			crearJugador1(nombreJugador2);
		}

	}

	public void tirarDado() {
		System.out.println("Turno de tirar el dado de " + j1.getNombre());
		System.out.println("apreta un boton para tirar el dado\n");
		botonTirar = scan.nextLine();
		resultado1 = j1.tirarDado();
		System.out.println(resultado1 + "\n");

		System.out.println("Turno de tirar el dado de " + j2.getNombre());
		System.out.println("apreta un boton para tirar el dado\n");
		botonTirar = scan.nextLine();
		resultado2 = j2.tirarDado();
		System.out.println(resultado2 + "\n");
		if (resultado1 > resultado2) {
			jugadorQueArmaPrimero = j1;
			jugadorQueArmaSegundo = j2;

		} else if (resultado1 < resultado2) {
			jugadorQueArmaPrimero = j2;
			jugadorQueArmaSegundo = j1;
		} else {
			tirarDado();
		}

	}

	public void armarEjercito() throws IOException, ErrorAlLeerElArchivo,
			ErrorNombreInvalido {
		System.out.println("arranca a armar el ejercito el jugador "
				+ jugadorQueArmaPrimero.getNombre());

		do {
			System.out.println(jugadorQueArmaPrimero.getNombre()
					+ " arma tu ejercito\n");
			System.out.println("Puntos que posee para comprar: "
					+ jugadorQueArmaPrimero.getNombre() + " "
					+ jugadorQueArmaPrimero.getPuntosParaComprar());

			System.out.println("Eige una opcion\n"
					+ "1: elegir ejercito pre armado\n"
					+ "2: comprar soldados auxiliares\n"
					+ "3: comprar soldados legionarios\n"
					+ "4: comprar soldados centuriones\n" + "5: salir\n");

			opcion = scan.nextInt();

			if (opcion == 1) {
				subMenuLegionesPreArmadas(jugadorQueArmaPrimero);

			} else if (opcion == 2) {
				System.out
						.println("ingrese la cantidad de auxiliares a comprar");
				cantidad = scan.nextInt();

				jugadorQueArmaPrimero.comprar(TipoUnidad.AUXILIAR, cantidad);
				System.out
						.println(jugadorQueArmaPrimero.getPuntosParaComprar());

			} else if (opcion == 3) {
				System.out
						.println("ingrese la cantidad de legionarios a comprar");
				cantidad = scan.nextInt();
				jugadorQueArmaPrimero.comprar(TipoUnidad.LEGIONARIO, cantidad);
				System.out
						.println(jugadorQueArmaPrimero.getPuntosParaComprar());
				;
			} else if (opcion == 4) {
				System.out
						.println("ingrese la cantidad de centuriones a comprar");
				cantidad = scan.nextInt();
				jugadorQueArmaPrimero.comprar(TipoUnidad.CENTURION, cantidad);
				System.out
						.println(jugadorQueArmaPrimero.getPuntosParaComprar());

			}

		} while (opcion != 5
				&& jugadorQueArmaPrimero.getPuntosParaComprar() >= 0);

		do {
			System.out.println(jugadorQueArmaSegundo.getNombre()
					+ " arma tu ejercito");
			System.out.println("Puntos que posee para comprar: "
					+ jugadorQueArmaSegundo.getNombre() + " "
					+ jugadorQueArmaSegundo.getPuntosParaComprar());
			System.out.println("Eige una opcion\n"
					+ "1: elegir ejercito pre armado\n"
					+ "2: comprar soldados auxiliares\n"
					+ "3: comprar soldados legionarios\n"
					+ "4: comprar soldados centuriones\n" + "5: salir\n");

			opcion = scan.nextInt();
			if (opcion == 1) {
				subMenuLegionesPreArmadas(jugadorQueArmaSegundo);

			} else if (opcion == 2) {
				System.out
						.println("ingrese la cantidad de auxiliares a comprar");
				cantidad = scan.nextInt();

				jugadorQueArmaSegundo.comprar(TipoUnidad.AUXILIAR, cantidad);
				System.out
						.println(jugadorQueArmaSegundo.getPuntosParaComprar());

			} else if (opcion == 3) {
				System.out
						.println("ingrese la cantidad de legionarios a comprar");
				cantidad = scan.nextInt();
				jugadorQueArmaSegundo.comprar(TipoUnidad.LEGIONARIO, cantidad);
				System.out
						.println(jugadorQueArmaSegundo.getPuntosParaComprar());

			} else if (opcion == 4) {
				System.out
						.println("ingrese la cantidad de centuriones a comprar");
				cantidad = scan.nextInt();
				jugadorQueArmaSegundo.comprar(TipoUnidad.CENTURION, cantidad);
				System.out
						.println(jugadorQueArmaSegundo.getPuntosParaComprar());

			}
		} while (opcion != 5
				&& jugadorQueArmaSegundo.getPuntosParaComprar() >= 0);
	}

	public void subMenuLegionesPreArmadas(Jugador jugador) throws IOException,
			ErrorNombreInvalido {
		System.out.println("elija que extension quiere abrir \n" + "1:.FC\n"
				+ "2:.FPC\n");
		opcion = scan.nextInt();
		if (opcion == 1) {
			lectorFC(jugador);
		}
		if (opcion == 2) {
			lectorFPC(jugador);
		}

	}

	/** leo el archivo en extension FC */
	public void lectorFC(Jugador jugador) throws ErrorNombreInvalido {
		Double precio = 0.0;
		separador = ",";
		try {
			File file = null;

			file = new File("Legiones.FC");

			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
				separador = ",";
				precio = contadorTotal(line, separador);
				totalesPorPrecioFC.add(precio);

			}
			bufferedReader.close();
			StringTokenizer stk = new StringTokenizer(stringBuffer.toString(),
					"\n");
			String[] legiones = new String[stk.countTokens()];

			for (int i = 0; i < legiones.length; i++) {
				legiones[i] = stk.nextToken();
			}

			System.out.println("Elija la Legion que desea comprar: \n");
			for (int i = 0; i < legiones.length; i++) {
				System.out.println("Legion " + (i + 1) + ": " + legiones[i]
						+ "(" + totalesPorPrecioFC.get(i) + ")");
			}
			opcion = scan.nextInt();

			creacionLegion(legiones[opcion - 1], separador, jugador);

		} catch (IOException | NumberFormatException e) {
			System.err.println(e.toString());

		}

	}

	/** leo el archivo en extension FPC */
	public void lectorFPC(Jugador jugador) throws ErrorNombreInvalido {
		Double precio = 0.0;
		separador = ";";
		try {
			File file = null;

			file = new File("Legiones.FPC");

			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
				separador = ";";
				precio = contadorTotal(line, separador);
				totalesPorPrecioFPC.add(precio);
			}
			bufferedReader.close();
			StringTokenizer stk = new StringTokenizer(stringBuffer.toString(),
					"\n");
			String[] legiones = new String[stk.countTokens()];

			for (int i = 0; i < legiones.length; i++) {
				legiones[i] = stk.nextToken();
			}

			System.out.println("Elija la Legion que desea comprar: \n");
			for (int i = 0; i < legiones.length; i++) {
				System.out.println("Legion " + (i + 1) + ": " + legiones[i]
						+ "(" + totalesPorPrecioFPC.get(i) + ")");
			}
			opcion = scan.nextInt();

			creacionLegion(legiones[opcion - 1], separador, jugador);

		} catch (IOException | NumberFormatException e) {
			System.err.println(e.toString());

		}

	}

	/** suma el precio de los soldados de la legion */
	public static double contadorTotal(String cadena, String separador) {
		// StringTokenizer st = new StringTokenizer(cadena, separador);
		// int contador = 0;
		double costoPorLinea = 0;
		int costoAux = 50;
		int costoLeg = 100;
		int costoCen = 200;

		if (cadena.length() > 1) {

			int pos = cadena.indexOf(separador);
			String nombre = "";
			if (cadena.contains("¿")) {
				nombre = cadena.substring(3, pos);
			} else {
				nombre = cadena.substring(0, pos);
			}

			int pos2 = cadena.indexOf(separador, pos + 2);
			int auxiliarCosto = costoAux
					* Integer.parseInt(cadena.substring(pos + 2, pos2));

			int pos3 = cadena.indexOf(separador, pos2 + 1);
			int legionarioCosto = costoLeg
					* Integer.parseInt(cadena.substring(pos2 + 2, pos3));

			int centurionCosto = costoCen
					* Integer.parseInt(cadena.substring(pos3 + 2));
			costoPorLinea = auxiliarCosto + legionarioCosto + centurionCosto;
		}

		return costoPorLinea;
	}

	/** una vez leido el archivo crea la legion con sus soldados */
	public static void creacionLegion(String cadena, String separador,
			Jugador jugador) throws NumberFormatException, ErrorNombreInvalido {

		if (cadena.length() > 1) {

			int pos = cadena.indexOf(separador);
			String nombre = "";
			if (cadena.contains("¿")) {
				nombre = cadena.substring(3, pos);
			} else {
				nombre = cadena.substring(0, pos);
			}
			jugador.setLegion(nombre);
			// podria contar los tokens y ver como crearlo si son 5
			// int frequency = new StringTokenizer(myString, " ").countTokens();
			int pos2 = cadena.indexOf(separador, pos + 2);
			int cantAuxiliares = Integer.parseInt(cadena.substring(pos + 2,
					pos2));
			jugador.comprar(TipoUnidad.AUXILIAR, cantAuxiliares);

			int pos3 = cadena.indexOf(separador, pos2 + 1);
			int cantLegionarios = Integer.parseInt(cadena.substring(pos2 + 2,
					pos3));

			jugador.comprar(TipoUnidad.LEGIONARIO, cantLegionarios);
			int cantCenturiones = Integer.parseInt(cadena.substring(pos3 + 2));
			jugador.comprar(TipoUnidad.CENTURION, cantCenturiones);

		}
		System.out.println(jugador.getLegion().toString(separador));
	}

	/**
	 * ataco y me fijo la vida que tiene cada ejercito
	 * 
	 */
	public void ataque() throws ErrorAlLeerElArchivo, IOException,
			ErrorNombreInvalido {

		do {
			int contadorDeAtaque = 0;
			if (contadorDeAtaque > 0) {
				System.out.println(jugadorQueArmaSegundo.getNombre()
						+ " arma tu ejercito\n");
				System.out.println("Puntos que posee para comprar: "
						+ jugadorQueArmaSegundo.getNombre() + " "
						+ jugadorQueArmaSegundo.getPuntosParaComprar());

				System.out.println("Eige una opcion\n"
						+ "1: elegir ejercito pre armado\n"
						+ "2: comprar soldados auxiliares\n"
						+ "3: comprar soldados legionarios\n"
						+ "4: comprar soldados centuriones\n" + "5: salir\n");

				opcion = scan.nextInt();

				if (opcion == 1) {
					try {
						subMenuLegionesPreArmadas(jugadorQueArmaSegundo);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if (opcion == 1) {
						lectorFC(jugadorQueArmaSegundo);
					} else if (opcion == 2) {
						lectorFPC(jugadorQueArmaSegundo);

					}

				}
			}
			System.out.println(jugadorQueArmaSegundo.getNombre()
					+ " presione una tecla para atacar");
			String ataque1 = scan.next();
			jugadorQueArmaSegundo.getLegion().atacarLegion(
					jugadorQueArmaPrimero.getLegion());
			System.out.println(String.format("%.2f", jugadorQueArmaSegundo
					.getLegion().getDanio()));
			System.out.println("vida de la legion del jugador "
					+ jugadorQueArmaPrimero.getNombre());
			System.out.println(String.format("%.2f", jugadorQueArmaPrimero
					.getLegion().getVida()));

			if (contadorDeAtaque > 0) {
				System.out.println(jugadorQueArmaPrimero.getNombre()
						+ " arma tu ejercito\n");
				System.out.println("Puntos que posee para comprar: "
						+ jugadorQueArmaPrimero.getNombre() + " "
						+ jugadorQueArmaPrimero.getPuntosParaComprar());

				System.out.println("Eige una opcion\n"
						+ "1: elegir ejercito pre armado\n"
						+ "2: comprar soldados auxiliares\n"
						+ "3: comprar soldados legionarios\n"
						+ "4: comprar soldados centuriones\n" + "5: salir\n");

				opcion = scan.nextInt();

				if (opcion == 1) {
					subMenuLegionesPreArmadas(jugadorQueArmaPrimero);
					if (opcion == 1) {
						lectorFC(jugadorQueArmaPrimero);
					} else if (opcion == 2) {
						lectorFPC(jugadorQueArmaPrimero);

					}

				}
			}
			System.out.println(jugadorQueArmaPrimero.getNombre()
					+ " presione una tecla para atacar");
			String ataque2 = scan.next();
			jugadorQueArmaPrimero.getLegion().atacarLegion(
					jugadorQueArmaSegundo.getLegion());// ver el getdanio
			System.out.println(String.format("%.2f", jugadorQueArmaPrimero
					.getLegion().getDanio()));
			System.out.println("vida de la legion del jugador"
					+ jugadorQueArmaSegundo.getNombre());
			System.out.println(String.format("%.2f", jugadorQueArmaSegundo
					.getLegion().getVida()));

			contadorDeAtaque++;
		} while (!juego.juegoTerminado());
		System.out.println(juego.getGanador());
	}

	public static void setLegionPrearmada(Legion legion1) {
		legionesPreArmadas.add(legion1);
	}

	public static Legion getLegionPrearmada(String nombreDeLaLegion) {
		Legion legionAuxiliar = null;
		for (Legion legion : legionesPreArmadas) {
			if (nombreDeLaLegion.equals(legion.getNombre())) {
				legionAuxiliar = legion;
			}

		}

		return legionAuxiliar;
	}

}
