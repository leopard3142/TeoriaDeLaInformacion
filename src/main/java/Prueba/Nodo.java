package Prueba;

public class Nodo {
    private String palabra;
    private int ocurrencias;
    private double probabilidad;

    public Nodo(String palabra) {
        this.palabra = palabra;
        this.ocurrencias = 1;
    }

    public void aumentaOcurrencia() {
        this.ocurrencias++;
    }

    public String getPalabra() {
        return palabra;
    }

    public int getOcurrencias() {
        return ocurrencias;
    }

    public void setProbabilidad(double lecturasT) {
        this.probabilidad = this.ocurrencias / lecturasT;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    @Override
    public String toString() {
        return "La palabra " + this.palabra + " ha ocurrido un total de " + this.ocurrencias
                + " veces teniendo una prob de ocurrencia de " + this.probabilidad;
    }
}
