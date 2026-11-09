// ===== 1. Pedir un texto =====

// Entrada de datos: el programa recibe información externa, normalmente del usuario.
// Console.WriteLine solo muestra un mensaje; para recoger lo que se escribe hace falta Console.ReadLine.

Console.WriteLine("==============================");
Console.WriteLine("Introduzca sus datos");
Console.WriteLine("==============================");

Console.WriteLine("Introduzca su nombre...");
string nombre = Console.ReadLine() ?? "";   // espera a que se pulse Enter


// ===== 2. Mostrar lo que se ha escrito =====

Console.WriteLine("Nombre colocado: " + nombre);


// ===== 3. Pedir un número entero =====

// ReadLine siempre devuelve texto: hay que convertirlo (casting, lección anterior)
Console.WriteLine("Introduzca su edad...");
int edad = Convert.ToInt32(Console.ReadLine());
Console.WriteLine("Edad colocada: " + edad);


// ===== 4. Pedir un número decimal =====

// Se escribe con el separador decimal del equipo: coma en un equipo en español
Console.WriteLine("Indique su salario...");
double salario = Convert.ToDouble(Console.ReadLine());
Console.WriteLine("Salario colocado: " + salario);


// ===== 5. Usar los datos =====

Console.WriteLine("==============================");
Console.WriteLine($"{nombre} tiene {edad} años y cobra {salario}");
Console.WriteLine($"En 10 años tendrá {edad + 10}");
Console.WriteLine("==============================");


// Si el texto no es un número válido, Convert lanza FormatException
// (con TryParse se evita; se verá con las excepciones)


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
