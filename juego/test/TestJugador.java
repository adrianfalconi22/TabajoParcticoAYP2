package test;

import static org.junit.Assert.assertEquals;

import modelo.Legionario;
import modelo.Auxiliar;
import modelo.Centurion;
import modelo.ErrorNombreInvalido;
import modelo.Jugador;
import modelo.Legion;
import modelo.TipoUnidad;


import org.junit.Test;
import org.junit.Assert;

public class TestJugador {

	private Legion legion1;
	private Legion legion2;

	public TestJugador() {
		legion1 = new Legion();
		legion2 = new Legion();
	}

	@Test
	public void testNombre() throws ErrorNombreInvalido {
		Jugador jugador = new Jugador("adrian");

		assertEquals("adrian", jugador.getNombre());
	}

	@Test(expected = ErrorNombreInvalido.class)
	public void testNombreInvalido() throws ErrorNombreInvalido {
		Jugador jugador = new Jugador(" ");

		assertEquals("adrian", jugador.getNombre());
	}

	@Test
	public void puntosParaComprar() throws ErrorNombreInvalido {
		Jugador jugador1 = new Jugador("adrian");

		assertEquals(500000, jugador1.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprarAuxiliar() throws ErrorNombreInvalido {
		Jugador jugador = new Jugador("lalala");
		jugador.comprar(TipoUnidad.AUXILIAR, 10);
		assertEquals(499500, jugador.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprarLegionario() throws ErrorNombreInvalido {
		Jugador jugador = new Jugador("lalala");
		jugador.comprar(TipoUnidad.LEGIONARIO, 10);
		assertEquals(499000, jugador.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprarCenturion() throws ErrorNombreInvalido {
		Jugador jugador = new Jugador("lalala");
		jugador.comprar(TipoUnidad.CENTURION, 10);
		assertEquals(498000, jugador.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprar3() throws ErrorNombreInvalido {
		Jugador jugador2 = new Jugador("assas");

		jugador2.comprar(TipoUnidad.CENTURION, 10);
		assertEquals(498000, jugador2.getPuntosParaComprar());
	}

	@Test
	public void cantidadDeunidadesDeLaLegion() throws ErrorNombreInvalido {
		Jugador jugador2 = new Jugador("daniel");

		jugador2.comprar(TipoUnidad.AUXILIAR, 10);
		jugador2.comprar(TipoUnidad.LEGIONARIO, 20);
		jugador2.comprar(TipoUnidad.CENTURION, 10);
		assertEquals(40, jugador2.getLegion().contarUnidades());
	}

	@Test
	public void testAtaque() throws ErrorNombreInvalido {
		Jugador jugador2 = new Jugador("daniel");
		Jugador jugador1 = new Jugador("adrian");

		jugador2.comprar(TipoUnidad.CENTURION, 40);
		System.out.println("----------");
		System.out.println(jugador2.getDañoTotalDeLaLegion());
		jugador2.comprar(TipoUnidad.AUXILIAR, 10);
		jugador2.comprar(TipoUnidad.LEGIONARIO, 20);

		jugador1.comprar(TipoUnidad.AUXILIAR, 10);

		double vidaJugadorUno= jugador1.getVidaDeLaLegion();
		double dañoJugadorDos = jugador2.getDañoTotalDeLaLegion();
		jugador2.getLegion().atacarLegion(jugador1.getLegion());

		
		
		Assert.assertEquals(765,vidaJugadorUno-dañoJugadorDos,0.1 );
		
	}
	
	

	@Test
	public void añadirCenturiones() {
		
		for (int i = 0; i < 10; i++) {
			legion1.añadirUnidad(new Centurion());
		}

		Assert.assertEquals(10, legion1.getCantidadDeCenturiones());
	}

	@Test
	public void calcularCostoDeLegion() {
		for (int i = 0; i < 10; i++) {
			legion1.añadirUnidad(new Centurion());
		}
		Assert.assertEquals(2000, legion1.getCosto(), 0.1);
	}

	@Test
	public void calcularDañoTotalDeXCenturiones() {
		for (int i = 0; i < 100; i++) {
			legion1.añadirUnidad(new Centurion());
		}
		Assert.assertEquals(1100, legion1.getDaño(), 0.1);
	}

	@Test
	public void calcularDañoTotal() {

		for (int i = 0; i < 10; i++) {
			legion1.añadirUnidad(new Centurion());
		}
		for (int i = 0; i < 10; i++) {
			legion1.añadirUnidad(new Legionario());
		}
		Assert.assertEquals(34, legion1.getDaño(), 0.1);
	}

	@Test
	public void calcularVidaTotal() {
		for (int i = 0; i < 10; i++) {
			legion1.añadirUnidad(new Legionario());
		}
		Assert.assertEquals(1000, legion1.calcularVidaTotalDeLaLegion(), 0.1);
	}

	@Test
	public void atacarOtraLegion() {

		for (int i = 0; i < 2; i++) {
			legion1.añadirUnidad(new Legionario());
		}

		for (int i = 0; i < 2; i++) {
			legion2.añadirUnidad(new Auxiliar());
		}

		legion1.atacarLegion(legion2);

		Assert.assertEquals(200 - 2.8, legion2.calcularVidaTotalDeLaLegion(),
				0.1);
	}

	@Test
	public void atacarOtraLegionDe10() {

		for (int i = 0; i < 10; i++) {
			legion1.añadirUnidad(new Legionario());
		}

		for (int i = 0; i < 10; i++) {
			legion2.añadirUnidad(new Auxiliar());
		}

		legion1.atacarLegion(legion2);

		double daño = legion1.getDaño();
		Assert.assertEquals(1000 - daño, legion2.calcularVidaTotalDeLaLegion(),
				0.1);
	}

	@Test
	public void atacarOtraLegionDe100() {

		for (int i = 0; i < 100; i++) {
			legion1.añadirUnidad(new Centurion());
		}

		for (int i = 0; i < 100; i++) {
			legion2.añadirUnidad(new Auxiliar());
		}

		legion1.atacarLegion(legion2);

		double daño = legion1.getDaño();
		Assert.assertEquals(10000 - daño,
				legion2.calcularVidaTotalDeLaLegion(), 0.1);
	}

	@Test
	public void atacarOtraLegionDe100ALegionarios() {

		for (int i = 0; i < 100; i++) {
			legion1.añadirUnidad(new Centurion());
		}

		for (int i = 0; i < 100; i++) {
			legion2.añadirUnidad(new Legionario());
		}

		legion1.atacarLegion(legion2);

		double daño = legion1.getDaño();
		Assert.assertEquals(10000 - daño,
				legion2.calcularVidaTotalDeLaLegion(), 0.1);
	}

	@Test
	public void atacarOtraLegionDe100Auxiliares() {

		for (int i = 0; i < 100; i++) {
			legion1.añadirUnidad(new Legionario());
		}

		for (int i = 0; i < 10; i++) {
			legion2.añadirUnidad(new Auxiliar());
		}
		
		legion2.imprimirListaDeLaUnidades();

		double vidaMaxima = legion2.calcularVidaTotalDeLaLegion();
		legion1.atacarLegion(legion2);

		double daño = legion1.getDaño();
		Assert.assertEquals(vidaMaxima - daño,
				legion2.calcularVidaTotalDeLaLegion(), 0.1);

		System.out.println("--------");
		
		legion2.imprimirListaDeLaUnidades();
	}
}
