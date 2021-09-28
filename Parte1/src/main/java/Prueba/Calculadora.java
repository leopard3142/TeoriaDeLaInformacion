package Prueba;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

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

	public void calculaProbabilidades(ArrayList<Nodo> lista, int lecturasT) {
		Iterator<Nodo> it = lista.iterator();
		Nodo act;
		while (it.hasNext()) {
			act = it.next();
			act.setProbabilidad(lecturasT);
			act.setCantidadInformacion(log2(1 / act.getProbabilidad()));
		}
	}

	public void lecturaEscenarios(String path, ArrayList<Nodo> palabras) {
		final int MAX;
		int lecturas = 0;
		char[] caracteres;
		String cadena;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ingrese la longitud de cada simbolo: ");
		MAX = scanner.nextInt();
		caracteres = new char[MAX];
		Nodo.setCantidadDigitos(MAX);
		try {
			String contenido = Files.readString(Paths.get(path));
			StringReader reader = new StringReader(contenido);
			while (reader.read(caracteres, 0, MAX) != -1) {
				int i = 0;
				lecturas++;
				cadena = String.valueOf(caracteres);
				while (i < palabras.size() && !palabras.get(i).getPalabra().equalsIgnoreCase(cadena)) {
					i++;
				}
				if (i == palabras.size()) {
					palabras.add(new Nodo(cadena));
				} else {
					palabras.get(i).aumentaOcurrencia();
				}
			}
			calculaProbabilidades(palabras, lecturas);
		} catch (IOException e) {
			System.out.println("Error al abrir el archivo.");
		}
		scanner.close();
	}

	public double[][] lecturaSuposicionSecuencia(String path) {
		String act, ant;
		char[] lectura = new char[2];
		int cantT = 0;
		double[][] mat = new double[4][4];
		HashMap<String, Integer> codigos = new HashMap<>();

		codigos.put("00", 0);
		codigos.put("01", 1);
		codigos.put("10", 2);
		codigos.put("11", 3);

		try {
			String contenido = Files.readString(Paths.get(path));
			StringReader reader = new StringReader(contenido);
			reader.read(lectura, 0, 2);
			ant = String.valueOf(lectura);
			cantT++;
			while (reader.read(lectura, 0, 2) != -1) {
				act = String.valueOf(lectura);
				cantT++;
				mat[codigos.get(act)][codigos.get(ant)]++; // aumenta la cantidad de ocurrencias en la fila del
															// actual, columna del anterior
				ant = act;
			}
			System.out.println("---------- Matriz de Ocurrencias ----------");
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat.length; j++) {
					System.out.print(mat[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("----------Ocurrencias totales ---------- ");
			System.out.println(cantT);

			int[] columnas = new int[4]; // vector para almacenar el total de cada columna
			int totalcolumna = 0;

			for (int j = 0; j < mat.length; j++) { // calcula el total de cada columna
				for (int i = 0; i < mat.length; i++) {
					totalcolumna += mat[i][j];
				}
				columnas[j] = totalcolumna;
				totalcolumna = 0;
			}
			System.out.println("---------- Matriz de probabilidades condicionales ---------- ");
			for (int i = 0; i < mat.length; i++) { // calcula la probabilidad de ocurrencia dividiendo por el total de
													// cada columna
				for (int j = 0; j < columnas.length; j++) {
					mat[i][j] /= columnas[j];
					System.out.print(mat[i][j] + " ");
				}
				System.out.println();
			}

			/*
			 * for (int i = 0; i < 4; i++) for (int j = 0; j < 4; j++) mat[i][j] /= cantT;
			 * // se hace el promedio de ocurrencias for (int i = 0; i < 4; i++) { for (int
			 * j = 0; j < 4; j++) System.out.print(mat[i][j] + " "); System.out.println(); }
			 */

		} catch (IOException e) {
			System.out.println("No se encontro el archivo");
		}

		return mat;
	}

	public double[] vectorEstacionario(double[][] matrizCondicional) {
		double[] vector = new double[matrizCondicional.length];
		double[][] matAux = Calculadora.getInstance().calculaPotenciaMatriz(matrizCondicional, 100);
		double sum = 0;
		System.out.println("----------Vector estacionario ----------");
		for (int i = 0; i < matrizCondicional.length; i++) {
			vector[i] = matAux[i][0];
			sum += vector[i];
			System.out.print(vector[i] + " ");

			System.out.println();
		}
		System.out.println("----------Suma del vector estacionario ----------");
		System.out.println(sum);
		return vector;
	}

	public double log2(double numero) {
		double result = (double) (Math.log(numero) / Math.log(2));
		return result;
	}

	public double calculaEntropia(ArrayList<Nodo> palabras) {
		double entropia = 0;
		Iterator<Nodo> it = palabras.iterator();
		Nodo actual;
		while (it.hasNext()) {
			actual = it.next();
			entropia += (double) actual.getProbabilidad() * actual.getCantidadInformacion();
		}
		return entropia;
	}

	public double[][] multiplicaMatrizCuadrada(double[][] matrizA, double[][] matrizB) {

		double[][] result = new double[matrizA.length][matrizB.length];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				result[i][j] = 0;
				for (int k = 0; k < result.length; k++) {
					result[i][j] += matrizA[i][k] * matrizB[k][j];
				}
			}
		}
		return result;
	}

	public double[][] calculaPotenciaMatriz(double[][] matriz, int p) {
		double[][] resultado = matriz;
		for (int i = 1; i < p; i++) {
			resultado = multiplicaMatrizCuadrada(resultado, matriz);
		}
		return resultado;
	}

	public double calculaEntropiaMarkoviana(double[][] matrizCondicional, double[] vectorEstacionario) {
		double entropia = 0;
		double entropialocal = 0;
		for (int i = 0; i < vectorEstacionario.length; i++) {
			for (int j = 0; j < matrizCondicional.length; j++) {
				entropialocal += matrizCondicional[i][j] * log2(1 / matrizCondicional[i][j]);
			}
			entropia += entropialocal * vectorEstacionario[i];
			entropialocal = 0;
		}
		return entropia;
	}

	public boolean noSingular(ArrayList<Nodo> palabras) {
		Iterator<Nodo> it = palabras.iterator();
		ArrayList<String> palabrasCodigo = new ArrayList<String>();
		while (it.hasNext()) {
			palabrasCodigo.add(it.next().getPalabra());
		}
		boolean NOsingular = true;
		Iterator<String> it2 = palabrasCodigo.iterator();
		while (it2.hasNext() && NOsingular) {
			NOsingular = Collections.frequency(palabrasCodigo, it2.next()) == 1;
		}
		return NOsingular;
	}

	public boolean esInstantaneo(ArrayList<Nodo> palabras) {
		boolean condicionDeCorte = true;
		Nodo nodoPrefijo, nodoPostfijo;
		String prefijo;
		Iterator<Nodo> it = palabras.iterator();
		Iterator<Nodo> it2;
		while (it.hasNext() && condicionDeCorte) {
			nodoPrefijo = it.next();
			prefijo = nodoPrefijo.getPalabra();
			it2 = palabras.iterator();
			while (it2.hasNext() && condicionDeCorte) {
				nodoPostfijo = it2.next();
				if (nodoPrefijo != nodoPostfijo)
					condicionDeCorte = !nodoPostfijo.getPalabra().startsWith(prefijo);
			}
		}
		return condicionDeCorte;
	}

	public double Kraft(ArrayList<Nodo> palabras) {
		float resultado = 0;
		int i = 0;
		while (i < palabras.size()) {
			resultado += 1 / Math.pow(2, Nodo.getCantidadDigitos());
			i++;
		}
		return resultado;
	}

	public double McMillan(ArrayList<Nodo> palabras) {
		return this.Kraft(palabras);
	}

	public String imprimeLista(ArrayList<Nodo> lista) {
		String respuesta;
		Iterator<Nodo> it = lista.iterator();
		respuesta="|  Palabra     |    Probabilidad     | Cantidad de Informacion   |\n";
		while (it.hasNext()) {
			respuesta+=it.next().toString()+"\n";
		}
		return respuesta;
	}

	@SuppressWarnings("static-access")
	public double longitudMedia(ArrayList<Nodo> palabras) {
		double longitud = 0;
		int i = 0;
		while (i < palabras.size()) {
			longitud += palabras.get(i).getProbabilidad() * palabras.get(i).getCantidadDigitos();
			i++;
		}
		return longitud;
	}

	@SuppressWarnings("static-access")
	public boolean esCompacto(ArrayList<Nodo> palabras) {
		double suma = 0;
		Iterator<Nodo> it = palabras.iterator();
		Nodo actual;
		while (it.hasNext()) {
			actual = it.next();
			suma += actual.getCantidadDigitos() * actual.getProbabilidad();
		}
		return this.longitudMedia(palabras) <= suma;
	}

	public double rendimiento(ArrayList<Nodo> palabras) {
		return this.calculaEntropia(palabras) / this.longitudMedia(palabras);
	}

	public double redundancia(ArrayList<Nodo> palabras) {
		return 1 - rendimiento(palabras);
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
				if (A.get(j + 1).getProbabilidad() < A.get(j).getProbabilidad()) {
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
	
	public void escrituraEscenario(ArrayList<Nodo> palabras) {
		final int MAX = Nodo.getCantidadDigitos();
		
		char[] caracteres  = new char[MAX];
		String cadena;
		String pathEscritura = "codigo" + MAX + ".txt";
		String pathLectura = "text.txt";
//		StringBuilder st = new StringBuilder();
		String codigoNuevo = "";
		
		try {
			String contenido = Files.readString(Paths.get(pathLectura)); //contenido tiene el txt de la catedra
			StringReader reader = new StringReader(contenido);
			while (reader.read(caracteres, 0, MAX) != -1) {
				int i = 0;
				cadena = String.valueOf(caracteres);
				while (i < palabras.size() && !palabras.get(i).getPalabra().equalsIgnoreCase(cadena)) {
					i++;
				}
				
				codigoNuevo += palabras.get(i).getPalabraHuffman();
			}
			Files.writeString(Paths.get(pathEscritura), codigoNuevo, StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.out.println("Error al abrir el archivo.");
		}
	}

}
