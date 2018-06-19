package modelo;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// import org.junit.Assert;
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
	public void testNombreEjercito() throws ErrorNombreInvalido {
		Jugador jugador = new Jugador("adrian");

		jugador.("Ejercito Romano");

		assertEquals("Ejercito Romano", jugador.getEjercito().getLegion().getNombre());
	}

	@Test
	public void puntosParaComprar() throws ErrorNombreInvalido {
		Jugador jugador1 = new Jugador("adrian");

		assertEquals(500000, jugador1.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprarAuxiliar() throws ErrorNombreInvalido {
		Jugador jugador = new Jugador("lalala");
		jugador.setLegion("Ejercito Romano");
		jugador.getLegion();

		jugador.getLegion().comprar(TipoUnidad.AUXILIAR, 10, jugador);
		assertEquals(499500, jugador.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprarLegionario() throws ErrorNombreInvalido {
		Jugador jugador = new Jugador("lalala");
		jugador.setLegion("Ejercito Romano");
		jugador.getLegion();

		jugador.getLegion().comprar(TipoUnidad.LEGIONARIO, 10, jugador);
		assertEquals(499000, jugador.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprarCenturion() throws ErrorNombreInvalido {
		Jugador jugador = new Jugador("lalala");
		jugador.setLegion("Ejercito Romano");
		jugador.getLegion();

		jugador.getLegion().comprar(TipoUnidad.CENTURION, 10, jugador);
		assertEquals(498000, jugador.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprar3() throws ErrorNombreInvalido {
		Jugador jugador2 = new Jugador("assas");
		jugador2.setLegion("Ejercito Romano");
		jugador2.getLegion();

		jugador2.getLegion().comprar(TipoUnidad.CENTURION, 10, jugador2);
		assertEquals(498000, jugador2.getPuntosParaComprar());
	}

	@Test
	public void cantidadDeunidadesDeLaLegion() throws ErrorNombreInvalido {
		Jugador jugador2 = new Jugador("daniel");
		jugador2.setLegion("Ejercito Romano");
		jugador2.getLegion();

		jugador2.getLegion().comprar(TipoUnidad.AUXILIAR, 10, jugador2);
		jugador2.getLegion().comprar(TipoUnidad.LEGIONARIO, 20, jugador2);
		jugador2.getLegion().comprar(TipoUnidad.CENTURION, 10, jugador2);
		assertEquals(40, jugador2.getLegion().getUnidades().size());
	}

	@Test
	public void test() throws ErrorNombreInvalido {
		Jugador jugador2 = new Jugador("daniel");
		Jugador jugador1 = new Jugador("adrian");

		jugador2.setLegion("Ejercito Romano");
		jugador2.getLegion();

		jugador1.setLegion("Ejercito");
		jugador1.getLegion();

		jugador1.getLegion().comprar(TipoUnidad.AUXILIAR, 10, jugador2);
		jugador1.getLegion().comprar(TipoUnidad.LEGIONARIO, 20, jugador2);
		jugador1.getLegion().comprar(TipoUnidad.CENTURION, 40, jugador2);

		jugador2.getLegion().comprar(TipoUnidad.AUXILIAR, 10, jugador2);

		jugador2.getLegion().atacar(jugador1.getLegion());

		// assertEquals(7000, (int)
		// jugador1.getEjercito().getPuntosDeVidaTotal());
		int vidaJugadorUno = (int) jugador1.getLegion().getPuntosDeVidaTotal();
		assertTrue(vidaJugadorUno == 6997 || vidaJugadorUno == 6995);
	}
}
