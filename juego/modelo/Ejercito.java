package modelo;

import java.util.LinkedList;
import java.util.List;

public class Ejercito {

	private List<Unidad> unidades = new LinkedList<>();
	private String nombre;
	private double vidaTotal;

	public Ejercito(String nombreEjercito) throws ErrorNombreInvalido {
		this.nombre = nombreEjercito;

	}

	public double getPuntosDeVidaTotal() {

		for (Unidad uni : unidades) {
			vidaTotal += uni.getVida();
		}
		return vidaTotal;
	}

	public double getDanio() {
		double danio = 0;
		for (Unidad unidad : unidades) {
			danio += unidad.getDanio();

		}
		return danio;
	}

	public List<Unidad> getUnidades() {
		return this.unidades;
	}

	public String getNombre() {
		return nombre;
	}

	public void atacar(Ejercito ejercitoContrario) {
		ejercitoContrario.recibirAtaque(getDanio());

	}

	private void recibirAtaque(double danio) {
		vidaTotal -= danio;

	}

	public void aniadirUnidad(Unidad unidad) {
		unidades.add(unidad);
	}

}
