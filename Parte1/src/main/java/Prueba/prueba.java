package Prueba;

import java.util.ArrayList;

public class prueba {

	public static void main(String[] args) {
		ArrayList<Nodo> palabras = new ArrayList<>();
		double[][] matrizCondicional;
		double[] vectorEstacionario;
		
		
		Calculadora.getInstance().longVar("text.txt",palabras);
		matrizCondicional = Calculadora.getInstance().condVar("text.txt");
		vectorEstacionario = Calculadora.getInstance().vectorEstacionario(matrizCondicional);
		System.out.println("Entropia de la fuente Markoviana: ");
		System.out.println(Calculadora.getInstance().calculaEntropiaMarkoviana(matrizCondicional, vectorEstacionario));
		
	}

}
