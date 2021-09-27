package Prueba;

import java.util.ArrayList;

public class Ejecutar {

	private static ArrayList<Nodo> palabras = new ArrayList<>();
	private static double[][] matrizCondicional = null;
	private static double[] vectorEstacionario = null;

	public static void main(String[] args) {

		Calculadora.getInstance().lecturaEscenarios("text.txt", palabras);

		P1_incisoA();

		P1_incisoB();

		P1_incisoC();

		P2_incisoA();

		P2_incisoB();

		P2_incisoC();

		// P2_incisoD();

	}

	public static void P1_incisoA() {
		Calculadora.getInstance().imprimeLista(palabras);
		double informacion;
		for (int i = 0; i < palabras.size(); i++) {
			informacion = Calculadora.getInstance().calculaCantInformacion(palabras.get(i));
			System.out.println(
					"La informacion de la palabra " + palabras.get(i).getPalabra() + " es : " + informacion + " bits");
		}
		System.out.println("La entropia de la fuente es: " + Calculadora.getInstance().calculaEntropia(palabras));
	}

	public static void P1_incisoB() {
		matrizCondicional = Calculadora.getInstance().lecturaSuposicionSecuencia("text.txt");
	}

	public static void P1_incisoC() {
		vectorEstacionario = Calculadora.getInstance().vectorEstacionario(matrizCondicional);
		System.out.println("Entropia de la fuente Markoviana: "
				+ Calculadora.getInstance().calculaEntropiaMarkoviana(matrizCondicional, vectorEstacionario));
	}

	public static void P2_incisoA() {
		if (Calculadora.getInstance().singular(palabras))
			System.out.println("Es singular");
		else
			System.out.println("Es NO singular");

		if (Calculadora.getInstance().esInstantaneo(palabras))
			System.out.println("Es instantaneo");
		else
			System.out.println("NO es instantaneo");
	}

	public static void P2_incisoB() {
		System.out.println("Kraft = " + Calculadora.getInstance().Kraft(palabras));
		System.out.println("McMillan = " + Calculadora.getInstance().McMillan(palabras));
		System.out.println("Longitud Media del Codigo = " + Calculadora.getInstance().longitudMedia(palabras));
		if (Calculadora.getInstance().esCompacto(palabras))
			System.out.println("Es compacto");
		else
			System.out.println("NO es compacto");
	}

	public static void P2_incisoC() {
		System.out.println("Rendimiento = " + Calculadora.getInstance().rendimiento(palabras));
		System.out.println("Redundancia = " + Calculadora.getInstance().redundancia(palabras));
	}

	public static void P2_incisoD() {

	}
}
