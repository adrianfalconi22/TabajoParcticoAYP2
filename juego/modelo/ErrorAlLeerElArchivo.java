package modelo;

public class ErrorAlLeerElArchivo extends Exception {
	public ErrorAlLeerElArchivo() {
		super("No se pudo leer el archivo");
	}

}
