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
        String spaces = " ";
        for (int i = 0; i < (10 - this.palabra.length()); i++) {
            spaces+= " ";
        }
        return "|   "+this.palabra + spaces + "| " + this.probabilidad;
    }
}
