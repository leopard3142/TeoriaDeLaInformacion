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
		FileOutputStream fos = null;
		DataOutputStream salida = null;
		try {
			fos = new FileOutputStream(pathResultado);
			salida = new DataOutputStream(fos);
			int totalCaracteres = 0;
			char[] caracteres;
			String caracterLeido;
			int MAX = 1;
			caracteres = new char[MAX];
			try {
				String contenido = Files.readString(Paths.get(path));
				StringReader reader = new StringReader(contenido);
				while (reader.read(caracteres, 0, MAX) != -1) {
					totalCaracteres++;
					caracterLeido = String.valueOf(caracteres);
					// busca el caracter leido en chars
					Iterator<Nodo> it = chars.iterator();
					Nodo actual;
					boolean seEncontroCaracter = false;
					while (it.hasNext() && !seEncontroCaracter) {
						actual = it.next();
						if (actual.getPalabra().equals(caracterLeido)) {
							seEncontroCaracter = true;
						}
						// reemplaza por huffman o hsanon fano segun corresponda"
						if (metodo.equalsIgnoreCase("Huffman")) {
							salida.writeBytes(actual.getPalabraHuffman());
						} else { // metodo.equalsIgnoreCase("Shanon-Fano")

							salida.writeBytes(actual.getPalabraShanonFano());
						}
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (fos != null) {
						fos.close();
					}
					if (salida != null) {
						salida.close();
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (IOException e) {
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
