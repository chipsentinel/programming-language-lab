// string es una clase: representa una secuencia de caracteres y trae funciones para trabajar con texto.


// ===== 1. Texto en varias líneas =====

// Opción 1: concatenar con + y \n para el salto de línea
string lineasConcatenadas = "Esta es la primera línea\n" +
                            "Esta es la segunda línea\n" +
                            "Esta es la tercera línea";
Console.WriteLine(lineasConcatenadas);

// Opción 2: texto literal con @ ("verbatim"): respeta los saltos de línea tal como se escriben
string lineasLiterales = @"Esta es la primera línea
Esta es la segunda línea
Esta es la tercera línea";
Console.WriteLine(lineasLiterales);

// Caracteres especiales dentro de un texto (secuencias de escape)
Console.WriteLine("Comillas: \"mundo\"");     // \"  comillas
Console.WriteLine("Ruta: C:\\Datos\\foto");   // \\  barra invertida
Console.WriteLine("Col1\tCol2");              // \t  tabulador


// ===== 2. Longitud: Length =====

string texto = "Hola a todos";

Console.WriteLine("Texto: " + texto);
Console.WriteLine($"Cantidad de caracteres: {texto.Length}");   // 12 (los espacios también cuentan)

// Un texto de solo espacios también tiene longitud
Console.WriteLine("    ".Length);   // 4


// ===== 3. Mayúsculas y minúsculas =====

Console.WriteLine($"En mayúsculas: {texto.ToUpper()}");   // HOLA A TODOS
Console.WriteLine($"En minúsculas: {texto.ToLower()}");   // hola a todos


// ===== 4. Buscar: Contains, StartsWith, EndsWith =====

// Devuelven true o false, y distinguen mayúsculas de minúsculas
Console.WriteLine($"Contiene \"mundo\": {texto.Contains("mundo")}");       // False
Console.WriteLine($"Contiene \"todos\": {texto.Contains("todos")}");       // True
Console.WriteLine($"Inicia con \"hola\": {texto.StartsWith("hola")}");     // False (minúscula)
Console.WriteLine($"Inicia con \"Hola\": {texto.StartsWith("Hola")}");     // True
Console.WriteLine($"Finaliza con \"todos\": {texto.EndsWith("todos")}");   // True


// ===== 5. Posición: IndexOf =====

// Cada carácter tiene un índice, y el primero es el 0:
// H o l a _ a _ t o d o s
// 0 1 2 3 4 5 6 7 8 9 10 11
Console.WriteLine($"Posición de \"do\": {texto.IndexOf("do")}");   // 9
Console.WriteLine($"Posición de \"la\": {texto.IndexOf("la")}");   // 2
Console.WriteLine($"Posición de \"tr\": {texto.IndexOf("tr")}");   // -1: no está

// Un solo carácter, por su índice
Console.WriteLine($"Primer carácter: {texto[0]}");   // H


// ===== 6. Extraer una parte: Substring =====

// Substring(inicio, cantidad): desde la posición 2, cuatro caracteres
Console.WriteLine($"Substring(2, 4): \"{texto.Substring(2, 4)}\"");   // "la a"
Console.WriteLine($"Substring(1, 4): \"{texto.Substring(1, 4)}\"");   // "ola "

// Con un solo número, hasta el final
Console.WriteLine($"Substring(5): \"{texto.Substring(5)}\"");         // "a todos"


// ===== 7. Reemplazar: Replace =====

Console.WriteLine($"Replace: {texto.Replace("a todos", "mundo")}");   // Hola mundo


// ===== 8. Quitar espacios: Trim =====

string conEspacios = "   Hola a todos   ";

Console.WriteLine($"Con espacios: {conEspacios.Length} caracteres");          // 18
Console.WriteLine($"Con Trim: {conEspacios.Trim().Length} caracteres");       // 12
Console.WriteLine($"Entre corchetes: [{conEspacios.Trim()}]");                // [Hola a todos]


// ===== 9. Los strings no cambian =====

// Estas funciones devuelven un texto nuevo; el original sigue igual
texto.ToUpper();   // el resultado se pierde si no se guarda
Console.WriteLine(texto);   // Hola a todos

string mayusculas = texto.ToUpper();   // así sí se conserva
Console.WriteLine(mayusculas);         // HOLA A TODOS


// ===== 10. Encadenar funciones =====

// Se aplican de izquierda a derecha, y el resultado final es el de la última
Console.WriteLine(conEspacios.Trim().ToUpper());   // HOLA A TODOS


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
