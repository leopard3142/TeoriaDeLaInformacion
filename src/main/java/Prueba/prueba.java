package Prueba;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class prueba {

    public static void imprimeLista(ArrayList<Nodo> lista){
        Iterator<Nodo> it = lista.iterator();
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }
    }

    public static void main(String[] args) {
        final int MAX = 3;
        int lecturas = 0;
        char [] caracteres = new char[MAX];
        String cadena = null;
        ArrayList<Nodo> palabras = new ArrayList<>();

        try {
            String contenido = Files.readString(Paths.get("C:\\Users\\matias\\Desktop\\Matia\\Facu\\Teoria de la informacion\\TeoriaDeLaInformacion\\src\\main\\java\\Prueba\\text1.txt"));
            StringReader reader = new StringReader(contenido);
            while(reader.read(caracteres, 0, MAX)!=-1){
                int i = 0;
                lecturas++;
                cadena = String.valueOf(caracteres);
                System.out.println(cadena);
                while(i<palabras.size() && !palabras.get(i).getPalabra().equalsIgnoreCase(cadena)){
                    i++;
                }
                if(i==palabras.size()){
                    palabras.add( new Nodo(cadena));
                }else{
                    palabras.get(i).aumentaOcurrencia();
                }
            }
            imprimeLista(palabras);
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo");
        } catch (IOException e){
            System.out.println("No se encontro el archivo");
        }
    }
}
