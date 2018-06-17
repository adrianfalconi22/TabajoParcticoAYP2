package view;

import java.util.Scanner;

import modelo.ErrorNombreInvalido;
import modelo.Juego;
import modelo.Jugador;
import modelo.Mostrable;

public class MenuPrincipal implements Mostrable {

	@Override
	public void mostrar() throws ErrorNombreInvalido {
		Scanner scan = new Scanner(System.in);
		Jugador j1 = null;
		Jugador j2 = null;

		String botonTirar;
		int resultado1;
		int resultado2;

		System.out.println(Juego.getNombre());

		System.out.println("Ingrese el nombre del jugador1");
		String nombreJugador1 = scan.nextLine();
		j1 = new Jugador(nombreJugador1);

		System.out.println("Ingrese el nombre del jugador2");
		String nombreJugador2 = scan.nextLine();
		j2 = new Jugador(nombreJugador2);

		System.out.println("Turno de tirar el dado de " + j1.getNombre());
		System.out.println("apreta un boton para tirar el dado\n");
		botonTirar = scan.nextLine();
		resultado1 = j1.tirarDado();
		System.out.println(resultado1);

		System.out.println("Turno de tirar el dado de " + j2.getNombre());
		System.out.println("apreta un boton para tirar el dado\n");
		botonTirar = scan.nextLine();
		resultado2 = j2.tirarDado();
		System.out.println(resultado2);

	}

}
