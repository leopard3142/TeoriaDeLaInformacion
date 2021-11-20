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
		//System.out.println(Calculadora.resultadosHuffman(chars));

		// Shanon-Fano
		ArrayList<NodoShanonFano> palabrasShanonFano = Calculadora.armaArrayParaShanonFano(chars);
		Calculadora.shanonFano(palabrasShanonFano, chars);
		System.out.println("Rendimiento =  " + Calculadora.rendimiento(chars, "shanon-fano"));
		System.out.println("Redundancia =  " + Calculadora.redundancia(chars, "shanon-fano"));
		//System.out.println(Calculadora.resultadosShanonFano(chars));

		// RLC
		String rlcArgentina = Calculadora.RLC(path1);
		String rlcImagen = Calculadora.RLC(path2);
		String rlcRumano = Calculadora.RLC(path3);

		Archivos.escritura(path1, chars, "Argentina.huf", "Huffman");
		Archivos.escritura(path1, chars, "Argentina.fan", "Shanon-Fano");
		Archivos.escritura(path3, chars, "Rumano.huf", "Huffman");
		Archivos.escritura(path3, chars, "Rumano.fan", "Shanon-Fano");
		Archivos.escritura(path2, chars, "imagen.huf", "Huffman");
		Archivos.escritura(path2, chars, "imagen.fan", "Shanon-Fano");
		Archivos.escrituraRLC("Argentina.RLC", rlcArgentina);
		Archivos.escrituraRLC("imagen.RLC", rlcImagen);
		Archivos.escrituraRLC("Rumano.RLC", rlcRumano);

	}

}
