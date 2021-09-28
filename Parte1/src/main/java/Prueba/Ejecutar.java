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

		P2_incisoD();

	}

	public static void P1_incisoA() {
		System.out.println(Calculadora.getInstance().imprimeLista(palabras));
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
		if (Calculadora.getInstance().noSingular(palabras))
			System.out.println("Es NO singular");
		else
			System.out.println("Es singular");

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
		ArrayList<NodoHuffman> simbolos = Calculadora.getInstance().armaArrayParaHuffman(palabras);
		Calculadora.getInstance().huffman(simbolos, palabras);
		System.out.println(Calculadora.getInstance().resultadosHuffman(palabras));
	}
}
