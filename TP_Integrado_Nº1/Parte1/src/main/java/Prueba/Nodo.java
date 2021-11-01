package Prueba;

public class Nodo {
	private String palabra;
	private static int cantidadDigitos;
	private int ocurrencias;
	private double probabilidad;
	private double cantidadInformacion;
	private String palabraHuffman = "";

	public String getPalabraHuffman() {
		return palabraHuffman;
	}

	public void setPalabraHuffman(String palabraHuffman) {
		this.palabraHuffman = palabraHuffman;
	}

	public void agregarDigitoAHuffman(Integer digito) {
		this.palabraHuffman += digito.toString();
	}

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
		String spacesPalabra = " ";
		String spacesProbabilidad = " ";
		for (int i = 0; i < (10 - this.palabra.length()); i++) {
			spacesPalabra += " ";
		}
		for (int i = 0; i < (25 - String.valueOf(this.probabilidad).length()); i++) {
			spacesProbabilidad += " ";
		}
		return "|   " + this.palabra + spacesPalabra + "| " + this.probabilidad + spacesProbabilidad +  " | " + this.cantidadInformacion + " bits";
	}
}
