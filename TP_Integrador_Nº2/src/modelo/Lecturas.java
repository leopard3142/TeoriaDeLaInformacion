package modelo;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import modelo.Nodo;

public abstract class Lecturas {

	public static void lecturaEscenarios(String path, ArrayList<Nodo> chars) {
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
