Console.WriteLine("Variables");

string nombre = "informaticonfig";
int edad = 30;
bool activo = true;
DateTime fecha = DateTime.Now;
float precio = 30.5f;
decimal descuento = 4.6m;
double descuento2 = 10.50;

Console.WriteLine($"nombre = {nombre}");
Console.WriteLine($"edad = {edad}");
Console.WriteLine($"activo = {activo}");
Console.WriteLine($"fecha = {fecha}");
Console.WriteLine($"precio = {precio}");
Console.WriteLine($"descuento = {descuento}");
Console.WriteLine($"descuento2 = {descuento2}");

// Recuerda para ver el tipo de dato usa GetType
Console.WriteLine($"\nTipo de nombre: {nombre.GetType()}");

// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();