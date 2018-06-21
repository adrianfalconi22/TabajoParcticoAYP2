package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import modelo.ErrorNombreInvalido;
import modelo.Jugador;
import modelo.Legion;
import modelo.TipoUnidad;

import org.junit.Test;
// import org.junit.Assert;

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
		Legion legion = new Legion();
		legion.comprar(TipoUnidad.AUXILIAR, 10, jugador);
		assertEquals(499500, jugador.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprarLegionario() throws ErrorNombreInvalido {
		Jugador jugador = new Jugador("lalala");
		Legion legion = new Legion();
		legion.comprar(TipoUnidad.LEGIONARIO, 10, jugador);
		assertEquals(499000, jugador.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprarCenturion() throws ErrorNombreInvalido {
		Jugador jugador = new Jugador("lalala");
		Legion legion = new Legion();
		legion.comprar(TipoUnidad.CENTURION, 10, jugador);
		assertEquals(498000, jugador.getPuntosParaComprar());
	}

	@Test
	public void puntosParaComprar3() throws ErrorNombreInvalido {
		Jugador jugador2 = new Jugador("assas");
		Legion legion = new Legion();

		legion.comprar(TipoUnidad.CENTURION, 10, jugador2);
		assertEquals(498000, jugador2.getPuntosParaComprar());
	}

	@Test
	public void cantidadDeunidadesDeLaLegion() throws ErrorNombreInvalido {
		Jugador jugador2 = new Jugador("daniel");
		Legion legion = new Legion();
		legion.comprar(TipoUnidad.AUXILIAR, 10, jugador2);
		legion.comprar(TipoUnidad.LEGIONARIO, 20, jugador2);
		legion.comprar(TipoUnidad.CENTURION, 10, jugador2);
		assertEquals(40, legion.getUnidades().size());
	}

	@Test
	public void test() throws ErrorNombreInvalido {
		Jugador jugador2 = new Jugador("daniel");
		Jugador jugador1 = new Jugador("adrian");
		Legion legion = new Legion();
		Legion legion2 = new Legion();

		legion.comprar(TipoUnidad.AUXILIAR, 10, jugador2);
		legion.comprar(TipoUnidad.LEGIONARIO, 20, jugador2);
		legion.comprar(TipoUnidad.CENTURION, 40, jugador2);

		legion2.comprar(TipoUnidad.AUXILIAR, 10, jugador1);

		legion.atacar(legion2);

		// assertEquals(7000, (int)
		// jugador1.getEjercito().getPuntosDeVidaTotal());
		int vidaJugadorUno = (int) legion.getPuntosDeVidaTotal();
		assertTrue(vidaJugadorUno == 6997 || vidaJugadorUno == 6995);
	}
}
