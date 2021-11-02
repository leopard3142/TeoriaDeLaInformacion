package modelo;

import java.util.ArrayList;

public class Prueba {

	public static void main(String[] args) {
		String path1 = "Argentina.txt";
		ArrayList<Nodo> chars = new ArrayList<Nodo>();
		Lecturas.lecturaEscenarios(path1, chars);
		// en chars esta todo, equivale a palabras en el TP1

		// Huffman
		ArrayList<NodoHuffman> palabrasHuffman = Calculadora.armaArrayParaHuffman(chars);
		Calculadora.huffman(palabrasHuffman, chars);
		System.out.println(Calculadora.resultadosHuffman(chars));
		// Shanon-Fano
		ArrayList<NodoShanonFano> palabrasShanonFano = Calculadora.armaArrayParaShanonFano(chars);
		Calculadora.shanonFano(palabrasShanonFano, chars);
		System.out.println(Calculadora.resultadosShanonFano(chars));
		// RLC
		// Calculadora.RLC(path1);

	}

}
