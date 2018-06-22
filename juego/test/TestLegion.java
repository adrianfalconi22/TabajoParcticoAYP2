package test;

import static org.junit.Assert.assertEquals;
import modelo.Auxiliar;
import modelo.Centurion;
import modelo.ErrorNombreInvalido;
import modelo.Jugador;
import modelo.Legion;
import modelo.Legionario;
import modelo.TipoUnidad;

import org.junit.Test;

public class TestLegion {

	@Test
	public void testNombreLegion() {
		Legion legion = new Legion("Legion romana");
		assertEquals("Legion romana", legion.getNombre());
	}

	@Test
	public void testVida() {
		Legion legion = new Legion("Legion romana");
		legion.aniadirUnidad(new Auxiliar());
		legion.aniadirUnidad(new Legionario());
		legion.aniadirUnidad(new Centurion());
		legion.calcularVida();
		assertEquals(300.0, legion.getVida(), 0.01);
	}

	@Test
	public void testDanio() {
		Legion legion = new Legion("Legion romana");
		legion.aniadirUnidad(new Auxiliar());
		legion.aniadirUnidad(new Legionario());
		legion.aniadirUnidad(new Centurion());
		legion.calcularDanio();
		assertEquals(2.4, legion.getDanio(), 0.01);
	}

	@Test
	public void testCosto() {
		Legion legion = new Legion("Legion romana");
		legion.aniadirUnidad(new Auxiliar());
		legion.aniadirUnidad(new Legionario());
		legion.aniadirUnidad(new Centurion());

		legion.calcularCosto();
		assertEquals(350, legion.getCosto(), 0.01);

	}

	@Test
	public void cantidadDeunidadesDeLaLegion() throws ErrorNombreInvalido {
		Legion legion = new Legion("Legion romana");
		for (int i = 0; i < 10; i++) {
			legion.aniadirUnidad(new Auxiliar());
		}
		for (int i = 0; i < 20; i++) {
			legion.aniadirUnidad(new Legionario());
		}
		for (int i = 0; i < 10; i++) {
			legion.aniadirUnidad(new Centurion());
		}
		assertEquals(40, legion.getUnidades().size());
	}

	@Test
	public void testAtaque() throws ErrorNombreInvalido {
		Legion legion = new Legion("Legion romana");
		Legion legion2 = new Legion("Legion2");
		for (int i = 0; i < 10; i++) {
			legion.aniadirUnidad(new Auxiliar());
		}
		for (int i = 0; i < 20; i++) {
			legion.aniadirUnidad(new Legionario());
		}
//		for (int i = 0; i < 10; i++) {
//			legion.aniadirUnidad(new Centurion());
//		}

		for (int i = 0; i < 10; i++) {
			legion2.aniadirUnidad(new Centurion());
		}
		legion2.atacar(legion);

	legion.calcularVida();

		assertEquals(2990, legion.getVida(), 0.1);
	}

}
