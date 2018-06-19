package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

// Habria que validar las entradas, sobre todo cuando se piden numeros (pongo otra cosa y rompe)
public class MainJuego {

	static Legion legion = null;

	// El main es bastante grande
	public static void main(String[] args) throws ErrorNombreInvalido,
			IOException {

		// String extension;

		String separador;
		Scanner scan = new Scanner(System.in);
		Jugador j1 = null;
		Jugador j2 = null;
		int opcion;
		int cantidad;
		String botonTirar;
		int resultado1;
		int resultado2;
		Jugador jugadorQueArmaPrimero = null;
		Jugador jugadorQueArmaSegundo = null;
		BufferedReader br = null;
		List<Double> totalesPorPrecio = new ArrayList<>();
		try {
			// Aca y abajo se repite bastante codigo. Si hubiera alguna forma de
			// extraer lo que tienen en comun...

			File archivo = new File("DosAuxiliares.FC");
			br = new BufferedReader(new FileReader(archivo));
			String line = br.readLine();
			separador = " ,";
			Double precio = contadorTotal(line, separador);
			totalesPorPrecio.add(precio);

			archivo = new File("DosLegionarios.FPC");
			br = new BufferedReader(new FileReader(archivo));
			line = br.readLine();
			separador = " ;";
			precio = contadorTotal(line, separador);
			totalesPorPrecio.add(precio);

		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(Juego.getNombre());

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

		System.out
				.println("Turno de tirar el dado de " + j1.getNombre() + "\n");
		System.out.println("apreta un boton para tirar el dado\n");
		botonTirar = scan.nextLine();
		resultado1 = j1.tirarDado();
		System.out.println(resultado1);

		System.out
				.println("Turno de tirar el dado de " + j2.getNombre() + "\n");
		System.out.println("apreta un boton para tirar el dado\n");
		botonTirar = scan.nextLine();
		resultado2 = j2.tirarDado();
		System.out.println(resultado2);

		// El if y el ultimo else son exactamente iguales. Ademas no hacen falta
		// 3 syso, con uno que sea
		// "arranca a armar el ejercito el jugador " +
		// jugadorQueArmaPrimero.getNombre alcanza

		if (resultado1 > resultado2) {
			jugadorQueArmaPrimero = j1;
			jugadorQueArmaSegundo = j2;

		} else if (resultado1 < resultado2) {
			jugadorQueArmaPrimero = j2;
			jugadorQueArmaSegundo = j1;
		} else {
			jugadorQueArmaPrimero = j1;
			jugadorQueArmaSegundo = j2;
		}
		System.out.println("arranca a armar el ejercito el jugador "
				+ jugadorQueArmaPrimero.getNombre());

		do {
			System.out.println(jugadorQueArmaPrimero.getNombre()
					+ " arma tu ejercito\n");

			System.out.println("Eige una opcion\n"
					+ "1: elegir ejercito pre armado\n"
					+ "2: comprar soldados auxiliares\n"
					+ "3: comprar soldados legionarios\n"
					+ "4: comprar soldados centuriones\n" + "5: salir\n");

			opcion = scan.nextInt();

			if (opcion == 1) {
				System.out.println("Puntos que posee para comprar: "
						+ jugadorQueArmaPrimero.getPuntosParaComprar());

				System.out.println("elija una legion\n"
						+ "1: 2 auxiliares Precio (" + totalesPorPrecio.get(0)
						+ ")\n" + "2: 2 legionarios Precio ("
						+ totalesPorPrecio.get(1) + ")"); // Que pasa si aca
															// cargo otros
															// ejercitos (con
															// nombre distinto,
															// mas de 2, etc.)?
				opcion = scan.nextInt();
				if (opcion == 1) {

					try {
						br = new BufferedReader(new FileReader(
								"DosAuxiliares.FC")); // En el enunciado, el
														// archivo se llama
														// "Legiones"

						String line = br.readLine();
						separador = " ,";

						creacionLegion(line, separador, jugadorQueArmaPrimero);

					} catch (Exception e) {
						// mandar exception
					} finally {
						if (null != br) {
							br.close();
						}
					}

					System.out.println(legion.toString());
				} else if (opcion == 2) {

					try {
						br = new BufferedReader(new FileReader(
								"DosLegionarios.FPC"));

						String line = br.readLine();
						separador = " ;";
						creacionLegion(line, separador, jugadorQueArmaPrimero);

					} catch (Exception e) {
						// mandar exception
					} finally {
						if (null != br) {
							br.close();
						}
					}
					System.out.println(legion.toString());

				}

			} else if (opcion == 2) {
				System.out
						.println("ingrese la cantidad de auxiliares a comprar");
				cantidad = scan.nextInt();

				// jugadorQueArmaPrimero.comprar(TipoUnidad.AUXILIAR, cantidad);
				System.out
						.println(jugadorQueArmaPrimero.getPuntosParaComprar());

			} else if (opcion == 3) {
				System.out
						.println("ingrese la cantidad de legionarios a comprar");
				cantidad = scan.nextInt();
				// jugadorQueArmaPrimero.comprar(TipoUnidad.LEGIONARIO,
				// cantidad);
				System.out
						.println(jugadorQueArmaPrimero.getPuntosParaComprar());
				;
			} else if (opcion == 4) {
				System.out
						.println("ingrese la cantidad de centuriones a comprar");
				cantidad = scan.nextInt();
				// jugadorQueArmaPrimero.comprar(TipoUnidad.CENTURION,
				// cantidad);
				System.out
						.println(jugadorQueArmaPrimero.getPuntosParaComprar());

			}

		} while (opcion != 5
				|| jugadorQueArmaPrimero.getPuntosParaComprar() <= 0);

		do {
			System.out.println(jugadorQueArmaSegundo.getNombre()
					+ " arma tu ejercito");

			System.out.println("Eige una opcion\n"
					+ "1: elegir ejercito pre armado\n"
					+ "2: comprar soldados auxiliares\n"
					+ "3: comprar soldados legionarios\n"
					+ "4: comprar soldados centuriones\n" + "5: salir\n");

			opcion = scan.nextInt();
			if (opcion == 1) {
				System.out.println("Puntos que posee para comprar: "
						+ jugadorQueArmaSegundo.getPuntosParaComprar());

				System.out.println("elija una legion\n"
						+ "1: 2 auxiliares Precio (" + totalesPorPrecio.get(0)
						+ ")\n" + "2: 2 legionarios Precio ("
						+ totalesPorPrecio.get(1) + ")"); // Que pasa si aca
															// cargo otros
															// ejercitos (con
															// nombre distinto,
															// mas de 2, etc.)?
				opcion = scan.nextInt();
				if (opcion == 1) {

					try {
						br = new BufferedReader(new FileReader(
								"DosAuxiliares.FC")); // En el enunciado, el
														// archivo se llama
														// "Legiones"

						String line = br.readLine();
						separador = " ,";

						creacionLegion(line, separador, jugadorQueArmaSegundo);

					} catch (Exception e) {
						// mandar exception
					} finally {
						if (null != br) {
							br.close();
						}
					}

					System.out.println(legion.toString());
				} else if (opcion == 2) {

					try {
						br = new BufferedReader(new FileReader(
								"DosLegionarios.FPC"));

						String line = br.readLine();
						separador = " ;";
						creacionLegion(line, separador, jugadorQueArmaSegundo);

					} catch (Exception e) {
						// mandar exception
					} finally {
						if (null != br) {
							br.close();
						}
					}
					System.out.println(legion.toString());

				}

			} else if (opcion == 2) {
				System.out
						.println("ingrese la cantidad de auxiliares a comprar");
				cantidad = scan.nextInt();

				// jugadorQueArmaSegundo.comprar(TipoUnidad.AUXILIAR, cantidad);
				System.out
						.println(jugadorQueArmaSegundo.getPuntosParaComprar());

			} else if (opcion == 3) {
				System.out
						.println("ingrese la cantidad de legionarios a comprar");
				cantidad = scan.nextInt();
				// jugadorQueArmaSegundo.comprar(TipoUnidad.LEGIONARIO,
				// cantidad);
				System.out
						.println(jugadorQueArmaSegundo.getPuntosParaComprar());
				;
			} else if (opcion == 4) {
				System.out
						.println("ingrese la cantidad de centuriones a comprar");
				cantidad = scan.nextInt();
				// jugadorQueArmaSegundo.comprar(TipoUnidad.CENTURION,
				// cantidad);
				System.out
						.println(jugadorQueArmaSegundo.getPuntosParaComprar());

			}
		} while (opcion != 5);

		System.out.println("empieza a atacar el jugador "
				+ jugadorQueArmaSegundo.getNombre());

		System.out.println("presione una tecla para atacar");
		String ataque = scan.next();
		jugadorQueArmaSegundo.getEjercito().atacar(
				jugadorQueArmaPrimero.getEjercito());

	}

	public static void creacionLegion(String line, String separador,
			Jugador jugador) throws NumberFormatException,
			ErrorNombreInvalido {

		StringTokenizer st = new StringTokenizer(line, separador);
		String text1 = "";
		int contador = 0;
		legion = new Legion();
		while (st.hasMoreTokens()) {
			if (contador == 0) {
				text1 = text1 + st.nextToken();
			} else if (contador == 1) {
				text1 = text1 + "" + st.nextToken();
				legion.setNombre(text1);
			} else if (contador == 2) {

				legion.comprar(TipoUnidad.AUXILIAR,
						Integer.parseInt(st.nextToken()), jugador);

			} else if (contador == 3) {

				legion.comprar(TipoUnidad.LEGIONARIO,
						Integer.parseInt(st.nextToken()), jugador);
			} else if (contador == 4) {

				legion.comprar(TipoUnidad.CENTURION,
						Integer.parseInt(st.nextToken()), jugador);
			}
			contador++;

		}
		jugador.getEjercito().aniadirUnidad(legion);
	}

	public static double contadorTotal(String line, String separador) {
		StringTokenizer st = new StringTokenizer(line, separador);
		int contador = 0;
		double costoPorLinea;
		costoPorLinea = 0;
		while (st.hasMoreTokens()) {
			if (contador == 0 || contador == 1) {
				st.nextToken();

			} else if (contador == 2) {
				double aux_costo = new Auxiliar().getCosto()
						* Integer.parseInt(st.nextToken());
				costoPorLinea = costoPorLinea + aux_costo;

			} else if (contador == 3) {
				double leg_costo = new Legionario().getCosto()
						* Integer.parseInt(st.nextToken());
				costoPorLinea = costoPorLinea + leg_costo;

			} else if (contador == 4) {
				Double cent_costo = new Centurion().getCosto()
						* Integer.parseInt(st.nextToken());
				costoPorLinea = costoPorLinea + cent_costo;

			}

			contador++;
		}
		return costoPorLinea;
	}
}
