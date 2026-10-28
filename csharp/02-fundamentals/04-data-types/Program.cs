// Tipos de datos básicos

// Enteros
byte nivel = 255;
short anio = 2025;
int edad = 25;
long poblacion = 8000000000L;

// Decimales
float temperatura = 36.6f;
double pi = 3.14159;
decimal precio = 19.99m;

// Lógico
bool esMayorDeEdad = true;

// Texto
char letra = 'S';
string nombre = "Sol";

// Mostrar valores
Console.WriteLine("\nMostrar valores".ToUpper());
Console.WriteLine($"byte nivel = {nivel}");
Console.WriteLine($"short anio = {anio}");
Console.WriteLine($"int edad = {edad}");
Console.WriteLine($"long poblacion = {poblacion}");
Console.WriteLine($"float temperatura = {temperatura}");
Console.WriteLine($"double pi = {pi}");
Console.WriteLine($"decimal precio = {precio}");
Console.WriteLine($"bool esMayorDeEdad = {esMayorDeEdad}");
Console.WriteLine($"char letra = {letra}");
Console.WriteLine($"string nombre = {nombre}");

// Mostrar el tipo y los límites
Console.WriteLine("\nMostrar el tipo y los límites".ToUpper());
Console.WriteLine($"Tipo de nivel: {nivel.GetType()}");
Console.WriteLine($"Tipo de anio: {anio.GetType()}");
Console.WriteLine($"Tipo de edad: {edad.GetType()}");
Console.WriteLine($"Tipo de poblacion: {poblacion.GetType()}");
Console.WriteLine($"Tipo de temperatura: {temperatura.GetType()}");
Console.WriteLine($"Tipo de pi: {pi.GetType()}");
Console.WriteLine($"Tipo de precio: {precio.GetType()}");
Console.WriteLine($"Tipo de esMayorDeEdad: {esMayorDeEdad.GetType()}");
Console.WriteLine($"Tipo de letra: {letra.GetType()}");
Console.WriteLine($"Tipo de nombre: {nombre.GetType()}");

Console.WriteLine("\nValor mínimo a valor máximo".ToUpper());
Console.WriteLine($"byte:    {byte.MinValue} a {byte.MaxValue}");
Console.WriteLine($"short:   {short.MinValue} a {short.MaxValue}");
Console.WriteLine($"int:     {int.MinValue} a {int.MaxValue}");
Console.WriteLine($"long:    {long.MinValue} a {long.MaxValue}");
