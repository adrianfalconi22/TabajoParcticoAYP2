package modelo;


public class OpcionInvalidaException extends Exception {

	public OpcionInvalidaException(){
		super("Tiene que ingresar un numero de las opciones");
	}
}
