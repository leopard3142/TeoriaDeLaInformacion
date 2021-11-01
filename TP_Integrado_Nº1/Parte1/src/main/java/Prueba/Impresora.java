package Prueba;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Impresora {
    
    private static Impresora single_instance = null;
    private StringBuilder parte1escenario5 = new StringBuilder();
    private StringBuilder parte1escenario7 = new StringBuilder();
    private StringBuilder parte1escenario9 = new StringBuilder();
    private StringBuilder parte1b = new StringBuilder();
    private StringBuilder parte1c = new StringBuilder();
    private StringBuilder parte2a = new StringBuilder();
    private StringBuilder parte2b = new StringBuilder();
    private StringBuilder parte2c = new StringBuilder();
    private StringBuilder parte2d = new StringBuilder();

	private Impresora() {

	}

	public static Impresora getInstance() {
		if (single_instance == null) {
			single_instance = new Impresora();
		}
		return single_instance;
	}

    public void appendParte1a(String texto, int escenario){
        switch (escenario) {
            case 5:
                this.parte1escenario5.append(texto);
                break;
            case 7:
                this.parte1escenario7.append(texto);
            case 9: 
                this.parte1escenario9.append(texto);
            default:
                break;
        }
    }

    public void imprimeParte1a(int escenario){
        File file = new File("Parte1)a)Escenario" + escenario +".txt");
        StringBuilder sb = new StringBuilder();
        switch (escenario) {
            case 5:
                sb = this.parte1escenario5;
                break;
            case 7:
                sb = this.parte1escenario7;
            case 9: 
                sb = this.parte1escenario9;
            default:
                break;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
    		writer.write(sb.toString());
		}
		catch (Exception e) {
			e.getStackTrace();
		}
    }

    public void appendParte1b(String texto){
        this.parte1b.append(texto);
    }
    public void imprimeParte1b(){
        File file = new File("Parte1)b).txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
    		writer.write(this.parte1b.toString());
		}
		catch (Exception e) {
			e.getStackTrace();
		}
    }

    public void appendParte1c(String texto){
        this.parte1c.append(texto);
    }

    public void imprimeParte1c(){
        File file = new File("Parte1)c).txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
    		writer.write(this.parte1c.toString());
		}
		catch (Exception e) {
			e.getStackTrace();
		}
    }

    public void appendParte2a(String texto){
        this.parte2a.append(texto);
    }
    
    public void imprimeParte2a(int escenario){
        File file = new File("Parte2)a)Escenario"+escenario +".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
    		writer.write(this.parte2a.toString());
		}
		catch (Exception e) {
			e.getStackTrace();
		}
    }

    public void appendParte2b(String texto){
        this.parte2b.append(texto);
    }

    public void imprimeParte2b(int escenario){ 
        File file = new File("Parte2)b)Escenario"+escenario +".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
    		writer.write(this.parte2b.toString());
		}
		catch (Exception e) {
			e.getStackTrace();
		}
    }
    public void appendParte2c(String texto){
        this.parte2c.append(texto);
    }
    public void imprimeParte2c(int escenario){
        File file = new File("Parte2)c)Escenario"+escenario +".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
    		writer.write(this.parte2c.toString());
		}
		catch (Exception e) {
			e.getStackTrace();
		}
    }

    public void appendParte2d(String texto){
        this.parte2d.append(texto);
    }

    public void imprimeparte2d(int escenario){
        File file = new File("Parte2)d)Escenario"+escenario +".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
    		writer.write(this.parte2d.toString());
		}
		catch (Exception e) {
			e.getStackTrace();
		}
    }

    public void reset(){
        this.parte1escenario5 = new StringBuilder();
        this.parte1escenario7 = new StringBuilder();
        this.parte1escenario9 = new StringBuilder();
        this.parte1b = new StringBuilder();
        this.parte1c = new StringBuilder();
        this.parte2a = new StringBuilder();
        this.parte2b = new StringBuilder();
        this.parte2c = new StringBuilder();
        this.parte2d = new StringBuilder();
    }
}
