package modelo;

import java.util.LinkedList;
import java.util.List;

public class Legion extends Unidad {

	protected List<Unidad> legion = new LinkedList<>();
	private String nombreLegion;
	private int auxiliares;
	private int legionarios;
	private int centuriones;

	public Legion(String nombre) {

		this.nombreLegion = nombre;

	}

	public Legion() {

	}

	@Override
	public double getVida() {

		double vidaTotal = 0;
		for (Unidad unidad : legion) {
			if (unidad.estaVivo()) {
				vidaTotal += unidad.getVida();

			}
		}

		return vidaTotal;
	}

	public List<Unidad> getLegion() {

		return legion;
	}

	@Override
	public double getDanio() {
		double danio = 0;
		for (Unidad unidad : legion) {
			if (unidad.estaVivo()){
				danio += unidad.getDanio();
			}
		}
		return danio * (1 + (double) getCantidadDeCenturiones() / 10);
	}

	@Override
	public double getCosto() {

		double costoTotal = 0;
		for (Unidad unidad : legion) {

			costoTotal += unidad.getCosto();
		}

		return costoTotal;
	}

	public int getCantidadDeCenturiones() {
		int total = 0;

		for (Unidad unidad : legion) {

			if (unidad.getTipo().equals(TipoUnidad.CENTURION)) {
				total++;
			}
		}

		return total;
	}

	public void aniadirUnidad(Unidad unidad) {
		legion.add(unidad);

	}

	public void atacarLegion(Legion otraLegion) {
		double vida = 0;
		double controladorDeDanio = getDanio();

		for (Unidad unidad : otraLegion.getLegion()) {

			if (unidad.estaVivo() && unidad.getTipo().equals(TipoUnidad.AUXILIAR)
					&& controladorDeDanio > 0) {

				if (controladorDeDanio >= unidad.getVida()) {
					vida = unidad.getVida();
					unidad.setVida(vida);
					controladorDeDanio = controladorDeDanio - vida;
				} else {
					unidad.setVida(controladorDeDanio);
					controladorDeDanio = 0;
				}

			} else if (unidad.estaVivo() && !hayAuxiliares()
					&& unidad.getTipo().equals(TipoUnidad.LEGIONARIO)
					&& controladorDeDanio > 0) {
				if (controladorDeDanio >= unidad.getVida()) {
					vida = unidad.getVida();
					unidad.setVida(vida);
					controladorDeDanio = controladorDeDanio - vida;
				} else {
					unidad.setVida(controladorDeDanio);
					controladorDeDanio = 0;
				}

			} else if (unidad.estaVivo()
					&& (!hayAuxiliares() && !hayLegionarios())
					&& unidad.getTipo().equals(TipoUnidad.CENTURION)
					&& controladorDeDanio > 0) {
				if (controladorDeDanio >= unidad.getVida()) {
					vida = unidad.getVida();
					unidad.setVida(vida);
					controladorDeDanio = controladorDeDanio - vida;
				} else {
					unidad.setVida(controladorDeDanio);
					controladorDeDanio = 0;

				}
			}
		}
		//
	}

	public String toString(String separador) {
		return nombreLegion + separador + auxiliares + separador + legionarios + separador
				+ centuriones;
	}

	public void removerSoldadosMuertos() {

		for (Unidad unidad : legion) {

			if (!unidad.estaVivo()) {
				legion.remove(unidad);
			}
		}

	}

	private boolean hayAuxiliares() {

		int totalDeAuxiliares = 0;

		for (Unidad unidad : legion) {
			if (unidad.equals(TipoUnidad.AUXILIAR)) {
				totalDeAuxiliares++;
			}
		}

		return totalDeAuxiliares > 0;
	}

	public boolean hayLegionarios() {
		int totalDeLegionarios = 0;

		for (Unidad unidad : legion) {
			if (unidad.equals(TipoUnidad.LEGIONARIO)) {
				totalDeLegionarios++;
			}
		}

		return totalDeLegionarios > 0;
	}

	public void imprimirListaDeLaUnidades() {
		for (Unidad unidad : legion) {
			System.out.println(unidad.getVida());
		}
	}

	public int contarUnidades() {

		int unidades = 0;

		for (Unidad unidad : legion) {
			if (unidad.estaVivo()) {
				unidades++;
			}
		}

		return unidades;
	}

	public void aumentarAuxiliares() {
		auxiliares++;

	}

	public void aumentarLegionarios() {
		legionarios++;

	}

	public void aumentarCenturiones() {
		centuriones++;

	}

	public String getNombre() {
		return nombreLegion;
	}
}
