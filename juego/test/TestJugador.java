package test;

import static org.junit.Assert.assertEquals;
import modelo.ErrorNombreInvalido;
import modelo.Jugador;
import modelo.TipoUnidad;

import org.junit.Assert;
import org.junit.Test;

public class TestJugador {

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
		jugador.setLegion("lalala");
		jugador.comprar(TipoUnidad.AUXILIAR, 10);
		assertEquals(499500, jugador.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprarLegionario() throws ErrorNombreInvalido {
		Jugador jugador = new Jugador("lalala");
		jugador.setLegion("lalala");
		jugador.comprar(TipoUnidad.LEGIONARIO, 10);
		assertEquals(499000, jugador.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprarCenturion() throws ErrorNombreInvalido {
		Jugador jugador = new Jugador("lalala");
		jugador.setLegion("lalala");
		jugador.comprar(TipoUnidad.CENTURION, 10);
		assertEquals(498000, jugador.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprar3() throws ErrorNombreInvalido {
		Jugador jugador2 = new Jugador("assas");
		jugador2.setLegion("lalala");
		jugador2.comprar(TipoUnidad.CENTURION, 10);
		assertEquals(498000, jugador2.getPuntosParaComprar());
	}

	@Test
	public void cantidadDeunidadesDeLaLegion() throws ErrorNombreInvalido {
		Jugador jugador2 = new Jugador("daniel");
		jugador2.setLegion("lalala");
		jugador2.comprar(TipoUnidad.AUXILIAR, 10);
		jugador2.comprar(TipoUnidad.LEGIONARIO, 20);
		jugador2.comprar(TipoUnidad.CENTURION, 10);

		assertEquals(40, jugador2.getLegion().contarUnidades());
	}

	@Test
	public void testAtaque() throws ErrorNombreInvalido {
		Jugador jugador2 = new Jugador("daniel");
		Jugador jugador1 = new Jugador("adrian");
		jugador2.setLegion("Romana");
		jugador1.setLegion("Pretoriana");
		jugador2.comprar(TipoUnidad.CENTURION, 40);
		System.out.println("----------");
		System.out.println(jugador2.getDañoTotalDeLaLegion());
		jugador2.comprar(TipoUnidad.AUXILIAR, 10);
		jugador2.comprar(TipoUnidad.LEGIONARIO, 20);

		jugador1.comprar(TipoUnidad.AUXILIAR, 10);

		double vidaJugadorUno = jugador1.getVidaDeLaLegion();
		double dañoJugadorDos = jugador2.getDañoTotalDeLaLegion();
		jugador2.getLegion().atacarLegion(jugador1.getLegion());

		Assert.assertEquals(765, vidaJugadorUno - dañoJugadorDos, 0.1);

	}

}
