package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import modelo.Auxiliar;
import modelo.Centurion;
import modelo.ErrorAlLeerElArchivo;
import modelo.ErrorNombreInvalido;
import modelo.Juego;
import modelo.Jugador;
import modelo.Legion;
import modelo.Legionario;
import modelo.TipoUnidad;

public class Controller {
	Juego juego;
	Jugador j1 = null;
	Jugador j2 = null;
	int cantidad;
	Jugador jugadorQueArmaPrimero = null;
	Jugador jugadorQueArmaSegundo = null;
	private List<Double> totalesPorPrecio = new ArrayList<>();
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

		try {
			j1 = new Jugador(nombreJugador1);
			j2 = new Jugador(nombreJugador2);

		} catch (ErrorNombreInvalido e) {
			System.out.println(e.getMessage());
		}
		tirarDado();
		armarEjercito();
		ataque();

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
				|| jugadorQueArmaPrimero.getPuntosParaComprar() <= 0);

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
		} while (opcion != 5);
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
		separador = " ,";
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
				// separador = " ,";
				// precio = contadorTotal(line, separador);
				// totalesPorPrecio.add(precio);

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
				// + "(" + totalesPorPrecio.get(i) + ")"
						);
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
		separador = " ;";
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
				// +"("+ totalesPorPrecio.get(i + 1) + ")"
						);
			}
			opcion = scan.nextInt();

			creacionLegion(legiones[opcion - 1], separador, jugador);

		} catch (IOException | NumberFormatException e) {
			System.err.println(e.toString());

		}

	}

	/** suma el precio de los soldados de la legion */
	public static double contadorTotal(String line, String separador) {
		StringTokenizer st = new StringTokenizer(line, separador);
		int contador = 0;
		double costoPorLinea = 0;
		while (st.hasMoreTokens()) {
			if (contador == 0) {
				st.nextToken();

			} else if (contador == 1) {
				double auxiliarCosto = new Auxiliar().getCosto()
						* Integer.parseInt(st.nextToken());
				costoPorLinea = costoPorLinea + auxiliarCosto;

			} else if (contador == 2) {
				double legionarioCosto = new Legionario().getCosto()
						* Integer.parseInt(st.nextToken());
				costoPorLinea = costoPorLinea + legionarioCosto;

			} else if (contador == 3) {
				Double centurionCosto = new Centurion().getCosto()
						* Integer.parseInt(st.nextToken());
				costoPorLinea = costoPorLinea + centurionCosto;

			}

			contador++;
		}
		return costoPorLinea;
	}

	/** una vez leido el archivo crea la legion con sus soldados */
	public static void creacionLegion(String line, String separador,
			Jugador jugador) throws NumberFormatException, ErrorNombreInvalido {

		StringTokenizer st = new StringTokenizer(line, separador);
		String text1 = "";
		int contador = 0;

		while (st.hasMoreTokens()) {
			if (contador == 0) {
				text1 = text1 + "" + st.nextToken();
				jugador.setLegion(text1);
			} else if (contador == 1) {
				jugador.comprar(TipoUnidad.AUXILIAR,
						Integer.parseInt(st.nextToken()));

			} else if (contador == 2) {

				jugador.comprar(TipoUnidad.LEGIONARIO,
						Integer.parseInt(st.nextToken()));

			} else if (contador == 3) {
				jugador.comprar(TipoUnidad.CENTURION,
						Integer.parseInt(st.nextToken()));
			}
			contador++;

		}
		System.out.println(jugador.getLegion().toString(separador));
		jugador.getLegion().getVida();
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
					.getLegion().getDaño()));
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
					jugadorQueArmaSegundo.getLegion());
			System.out.println(String.format("%.2f", jugadorQueArmaPrimero
					.getLegion().getDaño()));
			System.out.println("vida de la legion del jugador"
					+ jugadorQueArmaSegundo.getNombre());
			System.out.println(String.format("%.2f", jugadorQueArmaSegundo
					.getLegion().getVida()));

			contadorDeAtaque++;
		} while (jugadorQueArmaPrimero.getLegion().getVida() > 20);
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
	// public static void lectorDeArchivo() throws IOException {
	//
	// File archivo = new File("DosAuxiliares.FC");
	// br = new BufferedReader(new FileReader(archivo));
	// String line = br.readLine();
	// separador = " ,";
	// Double precio = contadorTotal(line, separador);
	// totalesPorPrecio.add(precio);
	//
	// archivo = new File("DosLegionarios.FPC");
	// br = new BufferedReader(new FileReader(archivo));
	// line = br.readLine();
	// separador = " ;";
	// precio = contadorTotal(line, separador);
	// totalesPorPrecio.add(precio);
	// System.out.println(legion.toString());
	// }
	//
	// File[] files = new File(DIRECTORIOARCHIVO).listFiles();
	// // If this pathname does not denote a directory, then
	// // listFiles() returns null.
	//
	// for (File file : files) {
	// if (file.isFile()) {
	// extension = file.getName();
	// if (extension.lastIndexOf(".FC") != -1) {
	// archivo = new File("DosAuxiliares.FC");
	// br = new BufferedReader(new FileReader(archivo));
	// String line = br.readLine();
	// separador = " ,";
	// precio = contadorTotal(line, separador);
	// totalesPorPrecio.add(precio);
	//
	// } else if (extension.lastIndexOf(".FPC") != -1) {
	// archivo = new File("DosLegionarios.FPC");
	// ;
	// br = new BufferedReader(new FileReader(archivo));
	// String line = br.readLine();
	// separador = " ,";
	// precio = contadorTotal(line, separador);
	// totalesPorPrecio.add(precio);
	//
	// }
	//
	// System.out.println(extension);
	// }
	//
	// }
	//
	// }
}
