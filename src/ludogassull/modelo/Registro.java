package ludogassull.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Registro {
    
    private final String ruta = "C:\\Users\\HP\\Desktop\\Joya\\Development Carreer\\Licenciatura en Software - UDA\\1er Año\\2do Semestre\\Laboratorio 2\\FINAL\\LudoGassull";

    public void crearArchivo(){
        try{
            new File(this.ruta,"registro.txt").createNewFile();
            System.out.println("El REGISTRO se ha creado de forma correcta.");
        }
        catch(IOException e){
            System.err.println("Error ->" + e.getMessage());
        }
        
    }
    
    public void escribirEnArchivo(String contenido) {
        try {
            FileWriter fileWriter = new FileWriter(this.ruta + "\\registro.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(contenido);
            bufferedWriter.newLine();

            bufferedWriter.close();
            System.out.println("El contenido se ha escrito en el archivo 'registro.txt' de forma correcta.");
        } catch (IOException e) {
            System.err.println("Error -> " + e.getMessage());
        }
    }
    
    public void leerArchivo() {
        try {
            FileReader fileReader = new FileReader(this.ruta + "\\registro.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            System.out.println("Contenido del archivo 'registro.txt':");
            
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line); // Print each line to the console
            }

            bufferedReader.close(); // Close the BufferedReader
        } catch (IOException e) {
            System.err.println("Error -> " + e.getMessage());
        }
    }
    
    public void eliminarArchivo() {
        File archivo = new File(this.ruta + "\\registro.txt");
        
        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("El archivo 'registro.txt' se ha eliminado correctamente.");
            } else {
                System.err.println("No se pudo eliminar el archivo 'registro.txt'.");
            }
        } else {
            System.err.println("El archivo 'registro.txt' no existe.");
        }
    }
}
