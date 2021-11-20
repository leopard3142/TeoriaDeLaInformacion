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
		Archivos.lectura(path1, chars);

		// en chars esta todo, equivale a palabras en el TP1
		// huffman y ShanonFano crean los codigos en los atributos de cada nodo de chars
		// en cambio el RLC crea directamente el string que iria en el archivo
		// igual el RLC hay que corregirle un par de cosas

		// Huffman
		ArrayList<NodoHuffman> palabrasHuffman = Calculadora.armaArrayParaHuffman(chars);
		Calculadora.huffman(palabrasHuffman, chars);
		System.out.println(Calculadora.resultadosHuffman(chars));

		// Shanon-Fano
		ArrayList<NodoShanonFano> palabrasShanonFano = Calculadora.armaArrayParaShanonFano(chars);
		Calculadora.shanonFano(palabrasShanonFano, chars);
		System.out.println(Calculadora.resultadosShanonFano(chars));

		// RLC
		// retorna en un String el archivo codificado ya sea txt o raw
		// habria que hacer el metodo de la escritura
		String rlcArgentina = Calculadora.RLC(path1);
		String rlcImagen = Calculadora.RLC(path2);
		String rlcRumano = Calculadora.RLC(path3);

		Archivos.escritura(path1, chars, "Argentina.huf", "Huffman");
		Archivos.escritura(path1, chars, "Argentina.fan", "Shanon-Fano");
		Archivos.escrituraRLC("Argentina.RLC", rlcArgentina);
		Archivos.escrituraRLC("imagen.RLC", rlcImagen);
		// Archivos.escrituraRLC("Rumano.RLC", rlcRumano);

	}

}
