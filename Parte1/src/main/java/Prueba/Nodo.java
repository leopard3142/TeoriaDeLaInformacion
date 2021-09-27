package Prueba;

public class Nodo {
	private String palabra;
	private static int cantidadDigitos;
	private int ocurrencias;
	private double probabilidad;
	private double cantidadInformacion;

	public Nodo(String palabra) {
		this.palabra = palabra;
		this.ocurrencias = 1;
	}

	public void aumentaOcurrencia() {
		this.ocurrencias++;
	}

	public String getPalabra() {
		return palabra;
	}

	public int getOcurrencias() {
		return ocurrencias;
	}

	public void setProbabilidad(double lecturasT) {
		this.probabilidad = this.ocurrencias / lecturasT;
	}

	public double getProbabilidad() {
		return probabilidad;
	}

	public double getCantidadInformacion() {
		return cantidadInformacion;
	}

	public void setCantidadInformacion(double cantidadInformacion) {
		this.cantidadInformacion = cantidadInformacion;
	}

	public static int getCantidadDigitos() {
		return cantidadDigitos;
	}

	public static void setCantidadDigitos(int cantidad) {
		cantidadDigitos = cantidad;
	}

	@Override
	public String toString() {
		String spaces = " ";
		for (int i = 0; i < (10 - this.palabra.length()); i++) {
			spaces += " ";
		}
		return "|   " + this.palabra + spaces + "| " + this.probabilidad + " | " + this.cantidadInformacion + " bits";
	}
}
