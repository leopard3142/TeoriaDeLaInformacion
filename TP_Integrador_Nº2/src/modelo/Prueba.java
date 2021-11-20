package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Prueba {

	public static void main(String[] args) {
		String path1 = "Argentina.txt";
		String path2 = "imagen.raw";
		String path3 = "Rumano.txt";
		ArrayList<Nodo> chars = new ArrayList<Nodo>();
		Archivos.lectura(path3, chars);

		// Huffman
		ArrayList<NodoHuffman> palabrasHuffman = Calculadora.armaArrayParaHuffman(chars);
		Calculadora.huffman(palabrasHuffman, chars);
		System.out.println("Rendimiento =  " + Calculadora.rendimiento(chars, "huffman"));
		System.out.println("Redundancia =  " + Calculadora.redundancia(chars, "huffman"));
		// System.out.println(Calculadora.resultadosHuffman(chars));

		// Shanon-Fano
		ArrayList<NodoShanonFano> palabrasShanonFano = Calculadora.armaArrayParaShanonFano(chars);
		Calculadora.shanonFano(palabrasShanonFano, chars);
		System.out.println("Rendimiento =  " + Calculadora.rendimiento(chars, "shanon-fano"));
		System.out.println("Redundancia =  " + Calculadora.redundancia(chars, "shanon-fano"));
		// System.out.println(Calculadora.resultadosShanonFano(chars));

		// RLC
		String rlcArgentina = Calculadora.RLC(path1);
		String rlcImagen = Calculadora.RLC(path2);
		String rlcRumano = Calculadora.RLC(path3);

		System.out.println("Escribe Argentina.huf ");
		Archivos.escritura(path1, chars, "Argentina.huf", "Huffman");
		System.out.println("Escribe imagen.huf ");
		Archivos.escritura(path1, chars, "Argentina.fan", "Shanon-Fano");
		System.out.println("Escribe Rumano.huf ");
		Archivos.escritura(path3, chars, "Rumano.huf", "Huffman");
		System.out.println("Escribe Rumano.fan ");
		Archivos.escritura(path3, chars, "Rumano.fan", "Shanon-Fano");
		System.out.println("Escribe Imagen.huf ");
		Archivos.escritura(path2, chars, "imagen.huf", "Huffman");
		System.out.println("Escribe Imagen.fan ");
		Archivos.escritura(path2, chars, "imagen.fan", "Shanon-Fano");
		System.out.println("Escribe Argentina.rlc ");
		Archivos.escrituraRLC("Argentina.RLC", rlcArgentina);
		System.out.println("Escribe Imagen.rlc ");
		Archivos.escrituraRLC("imagen.RLC", rlcImagen);
		System.out.println("Escribe Rumano.rlc ");
		Archivos.escrituraRLC("Rumano.RLC", rlcRumano);
		System.out.println("Todos los archivos creados correctamente.");

	}

}
