import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

// Manejo de archivos: crear, leer, modificar, copiar y borrar archivos del sistema.
// Se usa la clase Files y la interfaz Path del paquete java.nio.file.
//
// Muchas operaciones de archivos pueden lanzar IOException, una excepción COMPROBADA:
// el compilador obliga a capturarla con try/catch o a declararla con throws.
// Aquí main declara "throws IOException" para no repetir try/catch en cada línea.
//
// Para no ensuciar el equipo, esta lección trabaja dentro de una carpeta temporal
// que se crea al empezar y se borra al terminar.

public class FileHandling {

    public static void main(String[] args) throws IOException {

        // ===== 0. La ruta del archivo =====

        // Una ruta puede ser relativa (archivo1.txt: junto al programa) o absoluta (C:\Carpeta\archivo1.txt).
        // Path.resolve une las partes con el separador correcto del sistema operativo.
        Path carpeta = Files.createTempDirectory("leccion42-java");
        Path ruta = carpeta.resolve("archivo1.txt");

        // En una ruta escrita a mano, la barra invertida \ es especial en los textos: se escribe doble (\\)
        //   Path rutaManual = Path.of("C:\\Datos\\archivo1.txt");   // o con barras normales: "C:/Datos/archivo1.txt"


        // ===== 1. Crear un archivo y escribir en él: Files.writeString =====

        // Si el archivo no existe lo crea; si existe, LO SOBRESCRIBE
        Files.writeString(ruta, "Hola a todos");
        System.out.println("Archivo creado");


        // ===== 2. Leer todo el contenido: Files.readString =====

        String contenido = Files.readString(ruta);
        System.out.println("Contenido: " + contenido);


        // ===== 3. Añadir texto al final: opción APPEND =====

        Files.writeString(ruta, "\nEsta es una nueva línea de texto", StandardOpenOption.APPEND);

        System.out.println("Tras añadir una línea:");
        System.out.println(Files.readString(ruta));


        // ===== 4. Leer línea a línea: Files.readAllLines =====

        List<String> lineas = Files.readAllLines(ruta);

        System.out.println("Número de líneas: " + lineas.size());
        for (int i = 0; i < lineas.size(); i++) {
            System.out.println("Línea " + (i + 1) + ": " + lineas.get(i));
        }


        // ===== 5. Escribir varias líneas: Files.write =====

        Files.write(ruta, List.of("primera", "segunda", "tercera"));
        System.out.println("Reescrito con 3 líneas");
        System.out.println("Número de líneas: " + Files.readAllLines(ruta).size());


        // ===== 6. Comprobar si existe y copiar =====

        System.out.println("¿Existe el archivo? " + Files.exists(ruta));

        Path copia = carpeta.resolve("copia.txt");
        Files.copy(ruta, copia, StandardCopyOption.REPLACE_EXISTING);   // sobrescribe si la copia ya existe
        System.out.println("Copia creada: contenido igual = " + Files.readString(ruta).equals(Files.readString(copia)));


        // ===== 7. Borrar un archivo: Files.delete =====

        if (Files.exists(ruta)) {
            try {
                Files.delete(ruta);
                System.out.println("Archivo borrado correctamente");
            } catch (IOException error) {
                System.out.println("No se pudo borrar: " + error.getMessage());
            }
        } else {
            System.out.println("El archivo no existe");
        }

        System.out.println("¿Existe el archivo? " + Files.exists(ruta));

        // Files.delete SÍ lanza NoSuchFileException si no existe.
        // Files.deleteIfExists no da error y devuelve false.
        Files.deleteIfExists(ruta);


        // ===== 8. Leer un archivo que no existe: excepción =====

        try {
            Files.readString(ruta);
        } catch (NoSuchFileException error) {
            System.out.println("Error: el archivo no existe");
        }


        // ===== 9. Cerrar los recursos con try-with-resources =====

        // Los recursos declarados entre paréntesis del try se cierran solos al terminar (equivale a un finally)
        try (BufferedWriter escritor = Files.newBufferedWriter(ruta)) {
            escritor.write("línea escrita con BufferedWriter");
            escritor.newLine();
            escritor.write("segunda línea");
            escritor.newLine();
        }

        try (BufferedReader lector = Files.newBufferedReader(ruta)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println("Leída: " + linea);
            }
        }


        // ===== Limpieza: se borran los archivos y la carpeta temporal =====

        Files.deleteIfExists(copia);
        Files.deleteIfExists(ruta);
        Files.delete(carpeta);
        System.out.println("¿Existe la carpeta? " + Files.exists(carpeta));
    }
}
