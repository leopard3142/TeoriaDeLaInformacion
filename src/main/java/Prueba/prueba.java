package Prueba;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class prueba {

    public static void main(String[] args) {
        final int MAX = 6;
        int longitud;
        char [] caracteres = new char[MAX];
        String cadena = null;
        try {
            String contenido = Files.readString(Paths.get("C:\\Users\\matias\\Desktop\\Matia\\Facu\\Teoria de la informacion\\TeoriaDeLaInformacion\\src\\main\\java\\Prueba\\text1.txt"));
            StringReader reader = new StringReader(contenido);
            while(reader.read(caracteres, 0, MAX)!=-1){
                longitud = 0;
                cadena = String.valueOf(caracteres);
                System.out.println(cadena);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo");
        } catch (IOException e){
            System.out.println("No se encontro el archivo");
        }
    }
}
