package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import modelo.ArmadoDeLegion;
import modelo.Auxiliar;
import modelo.Centurion;
import modelo.ErrorNombreInvalido;
import modelo.Legionario;

public class Controller {
	private static final String DIRECTORIOARCHIVO = null;
	static ArmadoDeLegion legion = null;
	static String extension;
	static String separador;
	static BufferedReader br = null;
	static File archivo;
	static Double precio;
	static List<Double> totalesPorPrecio = new ArrayList<Double>();

	public static void creacionLegion(String line, String separador)
			throws NumberFormatException, ErrorNombreInvalido {
		legion = null;
		StringTokenizer st = new StringTokenizer(line, separador);
		legion = new ArmadoDeLegion();
		String text1 = "";
		int contador = 0;
		while (st.hasMoreTokens()) {
			if (contador == 0) {
				text1 = text1 + st.nextToken();
			} else if (contador == 1) {
				text1 = text1 + " " + st.nextToken();
				legion.setNombre(text1);
			} else if (contador == 2) {
				legion.setAuxiliares(Integer.parseInt(st.nextToken()));
			} else if (contador == 3) {
				legion.setLegionarios(Integer.parseInt(st.nextToken()));
			} else if (contador == 4) {
				legion.setCenturiones(Integer.parseInt(st.nextToken()));
			}
			contador++;

		}
		
	}

	public static Double contadorTotal(String line, String separador) {
		StringTokenizer st = new StringTokenizer(line, separador);
		int contador = 0;
		Double costo_por_linea;
		costo_por_linea = 0D;
		while (st.hasMoreTokens()) {
			if (contador == 0 || contador == 1) {
				st.nextToken();

			} else if (contador == 2) {
				Double aux_costo = new Auxiliar().getCosto()
						* Integer.parseInt(st.nextToken());
				costo_por_linea = costo_por_linea + aux_costo;

			} else if (contador == 3) {
				Double leg_costo = new Legionario().getCosto()
						* Integer.parseInt(st.nextToken());
				costo_por_linea = costo_por_linea + leg_costo;

			} else if (contador == 4) {
				Double cent_costo = new Centurion().getCosto()
						* Integer.parseInt(st.nextToken());
				costo_por_linea = costo_por_linea + cent_costo;

			}

			contador++;
		}
		return costo_por_linea;
	}

	public static void lectorDeArchivo() throws IOException {
		
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
		System.out.println(legion.toString());
	}
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
