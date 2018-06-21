package modelo;

public class Auxiliar extends Unidad {

	public Auxiliar() {
		super(TipoUnidad.AUXILIAR, 50, 0.7);
	}

	/**
	 * consulta el danio del ataque del auxiliar verificando primero si su
	 * ataque es efectivo
	 */
	@Override
	public double getDanio() {
		if (!ataqueEfectivo()) {
			return 0;
		}
		return getDanio();
	}

	/*
	 * @Override public void atacar(Unidad otraUnidadAAtacar) { if
	 * (ataqueEfectivo()) {
	 * 
	 * otraUnidadAAtacar.; }
	 * 
	 * }
	 */
	/** verifica si el ataque es efectivo */
	private boolean ataqueEfectivo() {
		int probabilidad = (int) (Math.random() * 100);
		return probabilidad >= 50;
	}

}
