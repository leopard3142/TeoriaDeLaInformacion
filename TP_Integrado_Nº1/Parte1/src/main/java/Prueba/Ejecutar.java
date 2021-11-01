package Prueba;

import java.util.ArrayList;


public class Ejecutar {

	private static ArrayList<Nodo> palabras = new ArrayList<>();
	private static double[][] matrizCondicional = null;
	private static double[] vectorEstacionario = null;

	public static void main(String[] args) {

		int [] escenarios = new int[3];
		escenarios[0] = 5;
		escenarios[1] = 7;
		escenarios[2] = 9;

		for (int i : escenarios) {
			Calculadora.getInstance().lecturaEscenarios("text.txt", palabras, i);	
			P1_incisoA(i);
			P2_incisoA(i);
			P2_incisoB(i);
			P2_incisoC(i);
			P2_incisoD(i);
			palabras.clear();
			Impresora.getInstance().reset();
		}


		P1_incisoB();

		P1_incisoC();

		//P2_incisoA();

		//P2_incisoB();

		//P2_incisoC();

		//P2_incisoD();

	}

	public static void P1_incisoA(int escenario) {
		// System.out.println(Calculadora.getInstance().imprimeLista(palabras));
		// System.out.println("La cantidad de palabras codigo distintas son: " + palabras.size());
		// System.out.println("La entropia de la fuente es: " + Calculadora.getInstance().calculaEntropia(palabras));
		Impresora.getInstance().appendParte1a(Calculadora.getInstance().imprimeLista(palabras), escenario);
		Impresora.getInstance().appendParte1a("La cantidad de palabras codigo distintas son: " + palabras.size() + '\n', escenario);
		Impresora.getInstance().appendParte1a("La entropia de la fuente es: " + Calculadora.getInstance().calculaEntropia(palabras), escenario);
		Impresora.getInstance().imprimeParte1a(escenario);
	}

	public static void P1_incisoB() {
		matrizCondicional = Calculadora.getInstance().lecturaSuposicionSecuencia("text.txt");
		Impresora.getInstance().imprimeParte1b();
	}
	

	public static void P1_incisoC() {
		vectorEstacionario = Calculadora.getInstance().vectorEstacionario(matrizCondicional);
		Impresora.getInstance().appendParte1c("Entropia de la fuente Markoviana: " + Calculadora.getInstance().calculaEntropiaMarkoviana(matrizCondicional, vectorEstacionario));
		Impresora.getInstance().imprimeParte1c();
	}

	public static void P2_incisoA(int i) {
		if (Calculadora.getInstance().noSingular(palabras))
			//System.out.println("Es NO singular");
			Impresora.getInstance().appendParte2a("Es NO singular \n");
		else
			//System.out.println("Es singular");
			Impresora.getInstance().appendParte2a("Es singular \n");

		if (Calculadora.getInstance().esInstantaneo(palabras))
			//System.out.println("Es instantaneo");
			Impresora.getInstance().appendParte2a("Es instantaneo \n");
		else
			//System.out.println("NO es instantaneo");
			Impresora.getInstance().appendParte2a("NO es instantaneo \n");
		Impresora.getInstance().imprimeParte2a(i);
			
	}

	public static void P2_incisoB(int i) {
		//System.out.println("Kraft = " + Calculadora.getInstance().Kraft(palabras));
		//System.out.println("McMillan = " + Calculadora.getInstance().McMillan(palabras));
		//System.out.println("Longitud Media del Codigo = " + Calculadora.getInstance().longitudMedia(palabras));
		Impresora.getInstance().appendParte2b("Kraft = " + Calculadora.getInstance().Kraft(palabras) + "\n" );
		Impresora.getInstance().appendParte2b("McMillan = " + Calculadora.getInstance().McMillan(palabras) + "\n");
		Impresora.getInstance().appendParte2b("Longitud Media del Codigo = " + Calculadora.getInstance().longitudMedia(palabras) + "\n");
		
		if (Calculadora.getInstance().esCompacto(palabras))
			//System.out.println("Es compacto");
			Impresora.getInstance().appendParte2b("Es compacto \n");
		else
			//System.out.println("NO es compacto");
			Impresora.getInstance().appendParte2b("NO es compacto \n");
		Impresora.getInstance().imprimeParte2b(i);
	}

	public static void P2_incisoC(int i) {
		//System.out.println("Rendimiento = " + Calculadora.getInstance().rendimiento(palabras));
		//System.out.println("Redundancia = " + Calculadora.getInstance().redundancia(palabras));
		Impresora.getInstance().appendParte2c("Rendimiento = " + Calculadora.getInstance().rendimiento(palabras) + "\n");
		Impresora.getInstance().appendParte2c("Redundancia = " + Calculadora.getInstance().redundancia(palabras) + "\n");
		Impresora.getInstance().imprimeParte2c(i);
	}

	public static void P2_incisoD(int i) {
		ArrayList<NodoHuffman> simbolos = Calculadora.getInstance().armaArrayParaHuffman(palabras);
		Calculadora.getInstance().huffman(simbolos, palabras);
		//System.out.println(Calculadora.getInstance().resultadosHuffman(palabras));
		Impresora.getInstance().appendParte2d(Calculadora.getInstance().resultadosHuffman(palabras) + "\n");
		Calculadora.getInstance().escrituraEscenario(palabras);
		//System.out.println("La longitud media del codigo de Huffman es " + Calculadora.getInstance().longitudMediaHoffman(palabras));
		Impresora.getInstance().appendParte2d("La longitud media del codigo de Huffman es " + Calculadora.getInstance().longitudMediaHoffman(palabras) + "\n");
		//System.out.println("Por lo tanto la longitud media del codigo se redujo un " + (100 - Calculadora.getInstance().longitudMediaHoffman(palabras) * 100/Calculadora.getInstance().longitudMedia(palabras)) + "%");
		Impresora.getInstance().appendParte2d("Por lo tanto la longitud media del codigo se redujo un " + (100 - Calculadora.getInstance().longitudMediaHoffman(palabras) * 100/Calculadora.getInstance().longitudMedia(palabras)) + "%");
		Impresora.getInstance().imprimeparte2d(i);
	}
}
