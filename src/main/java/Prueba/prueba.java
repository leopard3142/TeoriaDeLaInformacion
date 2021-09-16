package Prueba;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class prueba {

    public static void imprimeLista(ArrayList<Nodo> lista){
        Iterator<Nodo> it = lista.iterator();
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }
    }

    public static void calculaProbabilidades(ArrayList<Nodo> lista, int lecturasT){
        Iterator<Nodo> it = lista.iterator();
        Nodo act;
        while(it.hasNext()){
            act = it.next();
            act.setProbabilidad(lecturasT);
        }
    }

    public static void longVar(String path){
        final int MAX;
        int lecturas = 0;
        char [] caracteres;
        String cadena;
        ArrayList<Nodo> palabras = new ArrayList<>();
        Scanner scanner =  new Scanner(System.in);

        System.out.println("Ingrese la longitud de cada símbolo");
        MAX = scanner.nextInt();
        caracteres = new char[MAX];
        try {
            String contenido = Files.readString(Paths.get(path));
            StringReader reader = new StringReader(contenido);
            while(reader.read(caracteres, 0, MAX) != -1){
                int i = 0;
                lecturas++;
                cadena = String.valueOf(caracteres);
                while(i<palabras.size() && !palabras.get(i).getPalabra().equalsIgnoreCase(cadena)){ //recorre la lista de nodos a ver si coincide alguna ocurrencia
                    i++;
                }
                if(i==palabras.size()){
                    palabras.add( new Nodo(cadena));
                }else{
                    palabras.get(i).aumentaOcurrencia();
                }
            }
            calculaProbabilidades(palabras, lecturas);
            imprimeLista(palabras);
        }catch (IOException e){
            System.out.println("No se encontró el archivo");
        }
    }

    public static void main(String[] args) {
        //longVar("text.txt");
        condVar("text.txt");
    }

    private static void condVar(String path) {
        String act, ant;
        char [] lectura =  new char[2];
        int cantT = 0;
        double[][] mat = new double[4][4];
        HashMap<String, Integer> codigos = new HashMap<>();

        codigos.put("00", 0);
        codigos.put("01", 1);
        codigos.put("10", 2);
        codigos.put("11", 3);

        try {
            String contenido = Files.readString(Paths.get(path));
            StringReader reader = new StringReader(contenido);
            reader.read(lectura, 0, 2);
            ant = String.valueOf(lectura);
            cantT++;
            while(reader.read(lectura, 0, 2) != -1){
                act = String.valueOf(lectura);
                cantT++;
                mat[codigos.get(ant)][codigos.get(act)]++; //aumenta la cantidad de ocurrencias en la fila del anterior, columna del actual
                ant = act;
            }

            for(int i = 0; i<4 ; i++)
                for(int j = 0;  j<4 ; j++)
                    mat[i][j]/=cantT; //se hace el promedio de ocurrencias
            for(int i = 0; i<4 ; i++){
                for(int j = 0;  j<4 ; j++)
                    System.out.print(mat[i][j] + " ");
                System.out.println();
            }
        }catch (IOException e){
            System.out.println("No se encontró el archivo");
        }
    }
}
