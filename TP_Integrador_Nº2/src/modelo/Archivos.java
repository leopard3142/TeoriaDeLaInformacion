package modelo;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import modelo.Nodo;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

public abstract class Archivos {

	public static void lectura(String path, ArrayList<Nodo> chars) {
		int totalCaracteres = 0;
		char[] caracteres;
		String cadena;
		int MAX = 1; // leo de a una letra
		caracteres = new char[MAX];
		try {
			String contenido = Files.readString(Paths.get(path));
			StringReader reader = new StringReader(contenido);
			while (reader.read(caracteres, 0, MAX) != -1) {
				totalCaracteres++;
				cadena = String.valueOf(caracteres);
				if (!existeCaracter(chars, cadena)) {
					Nodo nuevo = new Nodo(cadena);
					chars.add(nuevo);
				}
			}
			calculaProbabilidades(chars, totalCaracteres);
		} catch (IOException e) {
			System.out.println("Error al abrir el archivo.");
		}
	}

	@SuppressWarnings("unused")
	public static void escritura(String path, ArrayList<Nodo> chars, String pathResultado, String metodo) {
		// metodo para leer en memoria el archivo original
		String contenido = "";
		try {
			contenido = Files.readString(Paths.get(path));
		} catch (IOException e) {
			System.out.println("Error al abrir el archivo.");
		}
		// metodo para reemplazar cada letra de contenido por su codigo binario Huffman
		// de chars
		String resultado = "";
		// me fijo si el metodo es huffman o shannon
		if (metodo.equalsIgnoreCase("huffman")) {
			for (int i = 0; i < contenido.length(); i++) {
				String letra = contenido.substring(i, i + 1);
				for (int j = 0; j < chars.size(); j++) {
					if (chars.get(j).getPalabra().equals(letra)) {
						resultado += chars.get(j).getPalabraHuffman();
					}
				}
			}
		} else {
			if (metodo.equalsIgnoreCase("Shanon-Fano")) {
				for (int i = 0; i < contenido.length(); i++) {
					String letra = contenido.substring(i, i + 1);
					for (int j = 0; j < chars.size(); j++) {
						if (chars.get(j).getPalabra().equals(letra)) {
							resultado += chars.get(j).getPalabraShanonFano();
						}
					}
				}
			}
		}
		// System.out.println(resultado);
		// escribe en un archivo el resultado txt para visualizarlo
		try {
			FileWriter fw = new FileWriter(pathResultado + ".txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(resultado);
			bw.close();
		} catch (IOException e) {
			System.out.println("Error al escribir el archivo.");
		}

		// escribe el archivo de salida en binario
		FileOutputStream fos2 = null;
		try {
			fos2 = new FileOutputStream(pathResultado);
			BitGenerator bitGenerator = new BitGenerator(fos2);
			for (int i = 0; i < resultado.length(); i++) {
				bitGenerator.writeBit(resultado.charAt(i) - '0');
			}
			bitGenerator.flush(); // flush para los bits que queden en el buffer
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	// metodo para escribir en archivo el String de RLC
	public static void escrituraRLC(String pathResultado, String rlc) {
		try {
			FileWriter fw = new FileWriter(pathResultado + ".txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(rlc);
			bw.close();
		} catch (IOException e) {
			System.out.println("Error al escribir el archivo.");
		}
		FileOutputStream fos2 = null;
		try {
			fos2 = new FileOutputStream(pathResultado);
			BitGenerator bitGenerator = new BitGenerator(fos2);
			for (int i = 0; i < rlc.length(); i++) {
				bitGenerator.writeBit(rlc.charAt(i) - '0');
			}
			bitGenerator.flush(); // flush para los bits que queden en el buffer
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static double log2(double numero) {
		double result = (double) (Math.log(numero) / Math.log(2));
		return result;
	}

	public static void calculaProbabilidades(ArrayList<Nodo> lista, int lecturasT) {
		Iterator<Nodo> it = lista.iterator();
		Nodo act;
		System.out.println("Lecturas totales = " + lecturasT);
		while (it.hasNext()) {
			act = it.next();
			act.setProbabilidad(lecturasT);
			act.setCantidadInformacion(log2(1 / act.getProbabilidad()));
		}
	}

	private static boolean existeCaracter(ArrayList<Nodo> chars, String cadena) {
		Iterator<Nodo> it = chars.iterator();
		Nodo actual;
		boolean seEncontroCaracter = false;
		while (it.hasNext() && !seEncontroCaracter) {
			actual = it.next();
			if (actual.getPalabra().equals(cadena)) {
				actual.aumentaOcurrencia();
				return true;
			}
		}
		return false;
	}
}
