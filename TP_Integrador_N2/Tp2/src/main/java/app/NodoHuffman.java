package app;

import java.util.ArrayList;

public class NodoHuffman {
	private double probabilidad;
	ArrayList<Integer> indices = new ArrayList<Integer>();

	public double getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(double probabilidad) {
		this.probabilidad = probabilidad;
	}

	public ArrayList<Integer> getIndices() {
		return indices;
	}

	public void setIndices(ArrayList<Integer> indices) {
		this.indices = indices;
	}

	public void agregarIndice(Integer i) {
		this.indices.add(i);
	}
}
