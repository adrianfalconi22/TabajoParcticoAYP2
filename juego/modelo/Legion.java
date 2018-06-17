package modelo;

import java.util.LinkedList;
import java.util.List;

public class Legion extends Unidad {

	private List<Unidad> unidades = new LinkedList<>();
	private String nombreLegion;
	private int cantidadDeSoldados = 0; 
	private int auxiliares;
	private int legionarios;
	private int centuriones;

	public Legion(String nombre) {
		this.nombreLegion = nombre;
	}

	public Legion() {

	}


	public String getNombre() {
		return nombreLegion;
	}

	public List<Unidad> getUnidades() {
		return unidades;
	}

	public void aniadirUnidad(Unidad unidad) {
		unidades.add(unidad);
		cantidadDeSoldados++;
	}

	public void calcularCosto() {
		double costo = 0;
		for (Unidad unidad : unidades) {
			costo += unidad.getCosto();

		}
		super.setCosto(costo);

	}

	public void calcularDanio() {
		double danio = 0;
		for (Unidad unidad : unidades) {
			if (unidad.estaVivo()) {
				danio += unidad.getDanio();
			}

		}
		super.setDanio(danio);

	}
	
	

	/*
	 * public void calcularVida() { double vida = 0; for (Unidad unidad :
	 * unidades) { vida += unidad.getVida();
	 * 
	 * } super.setVida(vida);
	 * 
	 * }
	 */

	public void eliminarSoldado() {
		for (Unidad unidad : unidades) {
			if (!unidad.estaVivo()) { // Si quien no esta vivo?

			}
		}
	}

	@Override
	public boolean estaVivo() {
		if (cantidadDeSoldados <= 0) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ArmadoDeLegion [nombre=" + nombreLegion + ", auxiliares="
				+ auxiliares + ", legionarios=" + legionarios
				+ ", centuriones=" + centuriones + "]";
	}
	public void comprar(TipoUnidad soldado, int cantidad)
			throws ErrorNombreInvalido {

		if (soldado.equals(TipoUnidad.AUXILIAR)) {

			for (int i = 0; i < cantidad; i++) {

				aniadirUnidad(new Auxiliar());
				auxiliares++;
			}

			Jugador.restarPuntosParaComprar(soldado, cantidad); // Ojo que el nombre de
														// este metodo no es muy
														// descriptivo

		} else if (soldado.equals(TipoUnidad.LEGIONARIO)) {

			for (int i = 0; i < cantidad; i++) {
				aniadirUnidad(new Legionario());
				legionarios++;
			}
			Jugador.restarPuntosParaComprar(soldado, cantidad); // Ademas esta en todos los
														// if

		} else if (soldado.equals(TipoUnidad.CENTURION)) {

			for (int i = 0; i < cantidad; i++) {
				aniadirUnidad(new Centurion());
				centuriones++;
			}

			Jugador.restarPuntosParaComprar(soldado, cantidad);
		}
		

	}

	public void setNombre(String nombre) {
		this.nombreLegion = nombre;
		
	}

}
