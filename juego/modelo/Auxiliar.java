package modelo;

public class Auxiliar extends Unidad {

	public Auxiliar() {
		super(TipoUnidad.AUXILIAR, 50, 0.7);
	}
	
	@Override
	public double getDanio(){
		if (!ataqueEfectivo()) {
			return 0;
		}
		return super.getDanio();
	}
/*
	@Override
	public void atacar(Unidad otraUnidadAAtacar) {
		if (ataqueEfectivo()) {

			otraUnidadAAtacar.;
		}

	}*/

	private boolean ataqueEfectivo() {
		int probabilidad = (int) (Math.random() * 100);
		return probabilidad >= 50;
	}

}
