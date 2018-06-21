package view;

import java.io.IOException;

import modelo.ErrorAlLeerElArchivo;
import modelo.ErrorNombreInvalido;
import modelo.Legion;
import controller.Controller;

// Habria que validar las entradas, sobre todo cuando se piden numeros (pongo otra cosa y rompe)
public class MainJuego {

	static Legion legion = null;

	public static void main(String[] args) throws ErrorNombreInvalido,
			IOException, ErrorAlLeerElArchivo {
		Controller controller = new Controller();

		controller.lector();
		controller.iniciar();

	}

}
