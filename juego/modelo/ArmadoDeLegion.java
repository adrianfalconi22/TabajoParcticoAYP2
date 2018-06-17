package modelo;

// No me cierra la idea de esta clase, o que funcion cumpliria

public class ArmadoDeLegion {

	private String nombre;
	private int auxiliares;
	private int legionarios;
	private int centuriones;

	public ArmadoDeLegion(String nombre, int auxiliares, int legionarios,
			int centuriones) {

		this.nombre = nombre;
		this.auxiliares = auxiliares;
		this.legionarios = legionarios;
		this.centuriones = centuriones;
	}
	
	public ArmadoDeLegion(){
		
	}

	@Override
	public String toString() {
		return "ArmadoDeLegion [nombre=" + nombre + ", auxiliares="
				+ auxiliares + ", legionarios=" + legionarios
				+ ", centuriones=" + centuriones + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAuxiliares() {
		return auxiliares;
	}

	public void setAuxiliares(int auxiliares) throws ErrorNombreInvalido {
		this.auxiliares = auxiliares;
	
		
	}
	

	public int getLegionarios() {
		return legionarios;
	}

	public void setLegionarios(int legionarios) throws ErrorNombreInvalido {
		this.legionarios = legionarios;
	
	}

	public int getCenturiones() {
		return centuriones;
	}

	public void setCenturiones(int centuriones) throws ErrorNombreInvalido {
		this.centuriones = centuriones;
	

	}

}
