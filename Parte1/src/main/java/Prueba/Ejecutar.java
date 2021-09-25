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

		// P2_incisoA();

		P2_incisoB();

		// P2_incisoC();

		// P2_incisoD();

	}

	public static void P1_incisoA() {
		Calculadora.getInstance().imprimeLista(palabras);
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

	}

	public static void P2_incisoB() {
		System.out.println("Kraft = " + Calculadora.getInstance().Kraft(palabras));
		System.out.println("McMillan = " + Calculadora.getInstance().McMillan(palabras));
		System.out.println("Longitud Media del Codigo = "+Calculadora.getInstance().longitudMedia(palabras));
	}

	public static void P2_incisoC() {
		System.out.println("Rendimiento = "+Calculadora.getInstance().rendimiento(palabras));
		System.out.println("Redundancia = "+Calculadora.getInstance().redundancia(palabras));
	}

	public static void P2_incisoD() {

	}
}
