// ===== 1. Bucle do-while =====

// do { ... } while (condición);
// Ejecuta el bloque UNA VEZ como mínimo y después repite mientras la condición sea true.
// La condición se comprueba al FINAL de cada vuelta.

int i = 1;

do
{
    Console.WriteLine("Vuelta " + i);
    i++;
}
while (i <= 3);

Console.WriteLine("Fin del bucle");


// ===== 2. Se ejecuta al menos una vez =====

// Aunque la condición sea falsa desde el principio, el bloque corre una vez
int contador = 10;

do
{
    Console.WriteLine("do-while: se ejecuta una vez con contador = " + contador);
    contador++;
}
while (contador < 3);

// Con while, en cambio, el bloque no se ejecutaría ninguna vez
while (contador < 3)
{
    Console.WriteLine("while: esto no se imprime");
}


// ===== 3. Pedir datos hasta que el usuario escriba 0 =====

// La variable se declara ANTES del bucle: la condición está fuera del bloque do { }
// y no vería una variable declarada dentro.
int numero;

do
{
    Console.WriteLine("Introduce 0 para salir");
    numero = Convert.ToInt32(Console.ReadLine());
    Console.WriteLine("Número colocado: " + numero);
}
while (numero != 0);

Console.WriteLine("Fin del programa");


// ===== 4. Mismo resultado con while =====

// Con while hay que dar un valor inicial a la variable para poder entrar al bucle
// (aquí el valor de salida es 3 en lugar de 0)
numero = 0;

while (numero != 3)
{
    Console.WriteLine("Introduce el número 3 para salir");
    numero = Convert.ToInt32(Console.ReadLine());
    Console.WriteLine("Número colocado: " + numero);
}

Console.WriteLine("Fin del programa");


// Si se escribe un texto que no es un número, Convert lanza una excepción (FormatException)


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
