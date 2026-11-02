// ===== 1. Repaso: var fija el tipo con el primer valor =====

// Una variable solo se declara una vez; no se puede repetir el nombre
/*
var numero = 50;
var numero = "hola";
var numero = 5.2;
*/

var numero = 10.20;   // el compilador deduce double
Console.WriteLine(numero);

numero = 4000;        // válido: int -> double es implícito
Console.WriteLine(numero);

// No compila: numero es double y un string no se convierte a double
// numero = "hola";

// Curiosidad: un char sí se convierte a double, y guarda su código Unicode
numero = 's';
Console.WriteLine(numero);   // 115, no "s"


// ===== 2. Constantes =====

const int Numero2 = 44;
Console.WriteLine($"Valor de constante: {Numero2}");

// Numero2 = 80;   // error: no se puede cambiar una constante

// Se usan para valores inalterables, como Pi
const double Pi = 3.1416;
const string MensajeBienvenida = "¡Bienvenido!";

Console.WriteLine(Pi);
Console.WriteLine(MensajeBienvenida);


// ===== 3. Ejemplo con constantes =====

const int Dias = 7;
const string Nombre = "Sol";
const decimal Iva = 0.21m;

double radio = 5;
double area = Pi * radio * radio;

decimal precio = 100m;
decimal total = precio * (1 + Iva);

Console.WriteLine($"{Nombre}: una semana tiene {Dias} días");
Console.WriteLine($"Área del círculo de radio {radio}: {area}");
Console.WriteLine($"Precio con IVA: {total}");


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
