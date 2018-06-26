package test;

import modelo.Auxiliar;
import modelo.Centurion;
import modelo.Legion;
import modelo.Legionario;

import org.junit.Assert;
import org.junit.Test;

public class TestLegion {
	 Legion legion1 = new Legion();
	 Legion legion2 = new Legion();

	@Test
	public void añadirCenturiones() {
		
		for (int i = 0; i < 10; i++) {
			legion1.aniadirUnidad(new Centurion());
		}

		Assert.assertEquals(10, legion1.getCantidadDeCenturiones());
	}

	@Test
	public void calcularCostoDeLegion() {
		for (int i = 0; i < 10; i++) {
			legion1.aniadirUnidad(new Centurion());
		}
		Assert.assertEquals(2000, legion1.getCosto(), 0.1);
	}

	@Test
	public void calcularDañoTotalDeXCenturiones() {
		for (int i = 0; i < 100; i++) {
			legion1.aniadirUnidad(new Centurion());
		}
		Assert.assertEquals(1100, legion1.getDanio(), 0.1);
	}

	@Test
	public void calcularDañoTotal() {

		for (int i = 0; i < 10; i++) {
			legion1.aniadirUnidad(new Centurion());
		}
		for (int i = 0; i < 10; i++) {
			legion1.aniadirUnidad(new Legionario());
		}
		Assert.assertEquals(34, legion1.getDanio(), 0.1);
	}

	@Test
	public void calcularVidaTotal() {
		for (int i = 0; i < 10; i++) {
			legion1.aniadirUnidad(new Legionario());
		}
		Assert.assertEquals(1000, legion1.getVida(), 0.1);
	}

	@Test
	public void atacarOtraLegion() {

		for (int i = 0; i < 2; i++) {
			legion1.aniadirUnidad(new Legionario());
		}

		for (int i = 0; i < 2; i++) {
			legion2.aniadirUnidad(new Auxiliar());
		}

		legion1.atacarLegion(legion2);

		Assert.assertEquals(200 - 2.8, legion2.getVida(),
				0.1);
	}

	@Test
	public void atacarOtraLegionDe10() {

		for (int i = 0; i < 10; i++) {
			legion1.aniadirUnidad(new Legionario());
		}

		for (int i = 0; i < 10; i++) {
			legion2.aniadirUnidad(new Auxiliar());
		}

		legion1.atacarLegion(legion2);

		double danio = legion1.getDanio();
		Assert.assertEquals(1000 - danio, legion2.getVida(),
				0.1);
	}

	@Test
	public void atacarOtraLegionDe100() {

		for (int i = 0; i < 100; i++) {
			legion1.aniadirUnidad(new Centurion());
		}

		for (int i = 0; i < 100; i++) {
			legion2.aniadirUnidad(new Auxiliar());
		}

		legion1.atacarLegion(legion2);

		double danio = legion1.getDanio();
		Assert.assertEquals(10000 - danio,
				legion2.getVida(), 0.1);
	}

	@Test
	public void atacarOtraLegionDe100ALegionarios() {

		for (int i = 0; i < 100; i++) {
			legion1.aniadirUnidad(new Centurion());
		}

		for (int i = 0; i < 100; i++) {
			legion2.aniadirUnidad(new Legionario());
		}

		legion1.atacarLegion(legion2);

		double danio = legion1.getDanio();
		Assert.assertEquals(10000 - danio,
				legion2.getVida(), 0.1);
	}

	@Test
	public void atacarOtraLegionDe100Auxiliares() {

		for (int i = 0; i < 100; i++) {
			legion1.aniadirUnidad(new Legionario());
		}

		for (int i = 0; i < 10; i++) {
			legion2.aniadirUnidad(new Auxiliar());
		}
		
		legion2.imprimirListaDeLaUnidades();

		double vidaMaxima = legion2.getVida();
		legion1.atacarLegion(legion2);

		double daño = legion1.getDanio();
		Assert.assertEquals(vidaMaxima - daño,
				legion2.getVida(), 0.1);

		System.out.println("--------");
		
		legion2.imprimirListaDeLaUnidades();
	}

}
