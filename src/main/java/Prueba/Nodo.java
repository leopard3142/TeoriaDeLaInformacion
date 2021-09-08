package Prueba;

public class Nodo {
    private String palabra;
    private int ocurrencias;

    public Nodo(String palabra){
        this.palabra = palabra;
        this.ocurrencias = 1;
    }

    public void aumentaOcurrencia(){
        this.ocurrencias++;
    }

    public String getPalabra() {
        return palabra;
    }

    public int getOcurrencias() {
        return ocurrencias;
    }

    @Override
    public String toString() {
        return "La palabra " + this.palabra + " ha ocurrido un total de " + this.ocurrencias + " veces";
    }
}
