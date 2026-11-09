// ===== 1. Bucle while =====

// Un bucle repite un bloque de código mientras se cumpla una condición.
// while (condición) { ... }: se comprueba la condición antes de cada vuelta.

int valor = 0;

while (valor < 3)
{
    Console.WriteLine("Valor es: " + valor);
    valor++;   // sin esta línea valor nunca llegaría a 3 y el bucle sería infinito
}

Console.WriteLine("Fin del bucle");


// ===== 2. Bucle infinito =====

// Si la condición siempre se cumple, el bucle no termina nunca (se corta con Ctrl+C).
/*
int contador = 0;
while (contador < 3)
{
    Console.WriteLine("Contador es: " + contador);   // falta contador++
}
*/


// ===== 3. Incluir el límite y empezar en 1 =====

int numero = 1;

while (numero <= 3)   // <= incluye el 3
{
    Console.WriteLine("Vuelta " + numero);
    numero++;
}


// ===== 4. Decisiones dentro del bucle =====

numero = 1;

while (numero <= 5)
{
    if (numero == 2)
    {
        Console.WriteLine("Soy la vuelta dos");
    }
    else
    {
        Console.WriteLine("Vuelta " + numero);
    }

    numero++;
}


// ===== 5. Tabla de multiplicar =====

const int Tabla = 8;
numero = 1;

Console.WriteLine($"Tabla del {Tabla}");

while (numero <= 10)
{
    Console.WriteLine($"{Tabla} x {numero} = {Tabla * numero}");
    numero++;
}

Console.WriteLine("Fin de la tabla");


// ===== 6. while puede no ejecutarse ninguna vez =====

// La condición se comprueba primero: si ya es falsa, el bloque se salta.
int intentos = 5;

while (intentos < 3)
{
    Console.WriteLine("Esto no se imprime nunca");
    intentos++;
}

Console.WriteLine("Intentos: " + intentos);   // 5


// ===== 7. Acumular un resultado =====

int suma = 0;
numero = 1;

while (numero <= 5)
{
    suma += numero;   // suma = suma + numero
    numero++;
}

Console.WriteLine("Suma de 1 a 5: " + suma);   // 15


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
