package app;

import java.util.ArrayList;
import java.util.Iterator;

public class Calculadora {
	private static Calculadora single_instance = null;

	private Calculadora() {

	}

	public static Calculadora getInstance() {
		if (single_instance == null) {
			single_instance = new Calculadora();
		}
		return single_instance;
	}
	
	public ArrayList<NodoHuffman> armaArrayParaHuffman(ArrayList<Nodo> palabras) {
		NodoHuffman nuevo;
		ArrayList<NodoHuffman> simbolos = new ArrayList<NodoHuffman>();
		int i = 0;
		while (i < palabras.size()) {
			nuevo = new NodoHuffman();
			nuevo.setProbabilidad(palabras.get(i).getProbabilidad());
			nuevo.agregarIndice(i);
			simbolos.add(nuevo);
			i++;
		}
		burbuja(simbolos);
		return simbolos;
	}

	public void huffman(ArrayList<NodoHuffman> simbolosHuffman, ArrayList<Nodo> palabras) {
		if (simbolosHuffman.size() == 2) {
			this.asigna0y1(simbolosHuffman.get(0).getIndices(), palabras, 0);
			this.asigna0y1(simbolosHuffman.get(1).getIndices(), palabras, 1);
		} else {
			// suma los dos ultimos
			NodoHuffman nuevo = new NodoHuffman();
			int ultimo = simbolosHuffman.size() - 1;
			int anteUltimo = simbolosHuffman.size() - 2;
			NodoHuffman NHUltimo = simbolosHuffman.get(ultimo);
			NodoHuffman NHAnteUltimo = simbolosHuffman.get(anteUltimo);
			nuevo.setProbabilidad(NHUltimo.getProbabilidad() + NHAnteUltimo.getProbabilidad());
			agregaTodosLosIndices(nuevo, NHUltimo.getIndices());
			agregaTodosLosIndices(nuevo, NHAnteUltimo.getIndices());
			simbolosHuffman.set(anteUltimo, nuevo);
			simbolosHuffman.remove(ultimo);
			// ordena de nuevo
			burbuja(simbolosHuffman);
			// llama recursividad
			huffman(simbolosHuffman, palabras);
			// aisgna 0 y 1 a los dos que se sumaron
			this.asigna0y1(NHAnteUltimo.getIndices(), palabras, 0);
			this.asigna0y1(NHUltimo.getIndices(), palabras, 1);
		}
	}

	private static void burbuja(ArrayList<NodoHuffman> A) {
		int i, j;
		NodoHuffman aux;
		for (i = 0; i < A.size() - 1; i++) {
			for (j = 0; j < A.size() - i - 1; j++) {
				if (A.get(j + 1).getProbabilidad() > A.get(j).getProbabilidad()) {
					aux = A.get(j + 1);
					A.set(j + 1, A.get(j));
					A.set(j, aux);
				}
			}
		}
	}

	private void asigna0y1(ArrayList<Integer> indices, ArrayList<Nodo> palabras, Integer digito) {
		Iterator<Integer> it = indices.iterator();
		while (it.hasNext()) {
			palabras.get(it.next()).agregarDigitoAHuffman(digito);
		}
	}

	private void agregaTodosLosIndices(NodoHuffman nuevo, ArrayList<Integer> indices) {
		Iterator<Integer> it = indices.iterator();
		while (it.hasNext()) {
			nuevo.agregarIndice(it.next());
		}
	}

	public String resultadosHuffman(ArrayList<Nodo> palabras) {
		String respuesta = "";
		Iterator<Nodo> it = palabras.iterator();
		Nodo actual;
		while (it.hasNext()) {
			actual = it.next();
			respuesta += " Palabra codigo =  " + actual.getPalabra() + "   |  Codigo Huffman  "
					+ actual.getPalabraHuffman() + "\n";
		}
		return respuesta;
	}
}
