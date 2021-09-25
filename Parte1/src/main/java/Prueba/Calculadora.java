package Prueba;

import java.util.Iterator;
import java.util.Scanner;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

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

	public double log2(double numero) { // Logaritmo en base 2 (hay que crear la funcion porque java no la trae)
		double result = (double) (Math.log(numero) / Math.log(2));
		return result;
	}

	public double calculaEntropia(ArrayList<Nodo> lista) {
		double entropia = 0;
		double probabilidad;
		for (int i = 0; i < lista.size(); i++) {
			probabilidad = lista.get(i).getProbabilidad();
			entropia += probabilidad * log2(1 / probabilidad);
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

	public float Kraft(ArrayList<Nodo> palabras) {
		float resultado = 0;
		int i = 0;
		while (i < palabras.size()) {
			resultado += 1 / Math.pow(2, Nodo.getCantidadDigitos());
			i++;
		}
		return resultado;
	}

	public float McMillan(ArrayList<Nodo> palabras) {
		return this.Kraft(palabras);
	}
	
	public void imprimeLista(ArrayList<Nodo> lista) {
		Iterator<Nodo> it = lista.iterator();
		System.out.println("|  Palabra     |  Probabilidad  ");
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

}
