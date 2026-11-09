using System.Globalization;

// ===== 1. Casting implícito =====

// Casting: convertir un valor de un tipo de dato a otro.
// Implícito: el compilador lo hace solo cuando no se pierde información.

int valor = 200;
int total = valor;                 // mismo tipo: no hay conversión
Console.WriteLine($"Casting implícito: {total}");

double totalDecimal = valor;       // int -> double: implícito
Console.WriteLine($"Casting implícito: {totalDecimal}");


// ===== 2. Casting explícito =====

// Explícito: hay que pedirlo con (tipo) porque se puede perder información.
double precio = 500.23;

// int descuento = precio;         // error: no se convierte double a int solo
int descuento = (int)precio;       // se descartan los decimales, no se redondea
Console.WriteLine($"Casting explícito: {descuento}");   // 500

// Trunca hacia cero, también con negativos
double temperatura = -7.9;
Console.WriteLine((int)temperatura);   // -7, no -8


// ===== 3. De char a int y de int a char =====

char letra = 'A';
int codigoAscii = letra;           // char -> int: implícito
Console.WriteLine($"Código ASCII de {letra}: {codigoAscii}");   // 65

char letraDeVuelta = (char)codigoAscii;   // int -> char: explícito
Console.WriteLine($"Letra del código {codigoAscii}: {letraDeVuelta}");   // A


// ===== 4. De texto a número: Convert.ToInt32 =====

string palabra = "123456";

// int numero = palabra;           // error: un string no se convierte a int solo
int numero = Convert.ToInt32(palabra);
Console.WriteLine($"Texto a entero: {numero}");   // 123456

// Si el texto no es un número válido, falla en ejecución
// Convert.ToInt32("hola");        // FormatException


// ===== 5. De texto a decimal: double.Parse =====

string textoDecimal = "150.60";

// InvariantCulture: el punto es siempre el separador decimal.
// Sin ella, en un equipo configurado en español "150.60" se leería como 15060.
double valor3 = double.Parse(textoDecimal, CultureInfo.InvariantCulture);
Console.WriteLine($"Texto a double: {valor3}");   // 150,6 en un equipo en español


// ===== 6. De número a texto =====

int edad = 30;
string edadTexto = edad.ToString();
Console.WriteLine("Edad: " + edadTexto);

double pi = 3.1416;
Console.WriteLine(Convert.ToString(pi, CultureInfo.InvariantCulture));   // 3.1416


// ===== 7. TryParse: convertir sin que el programa falle =====

// Devuelve true o false en lugar de lanzar una excepción
string entrada = "hola";

if (int.TryParse(entrada, out int resultado))
{
    Console.WriteLine($"Convertido: {resultado}");
}
else
{
    Console.WriteLine($"\"{entrada}\" no es un número entero");
}


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
