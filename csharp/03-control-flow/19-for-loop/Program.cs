// ===== 1. Bucle for =====

// for reúne en una sola línea las tres partes de un bucle con contador:
// for (inicialización; condición; actualización)

for (int i = 0; i < 4; i++)
{
    Console.WriteLine("Número de vuelta: " + i);
}

Console.WriteLine("Fin del bucle");

// i es la variable de control; por costumbre se llama i, j, k
// Se lee: "para i empezando en 0, mientras i sea menor que 4, sumando 1 en cada vuelta"
// El número que aparece en la condición (4) indica cuántas vueltas da el bucle


// ===== 2. Empezar en 1 e incluir el límite =====

for (int i = 1; i <= 5; i++)
{
    Console.WriteLine("Vuelta " + i);
}


// ===== 3. Cambiar el paso =====

// i += 2 salta de dos en dos: da menos vueltas
for (int i = 1; i <= 5; i += 2)
{
    Console.WriteLine("Impar: " + i);   // 1, 3, 5
}


// ===== 4. Cuenta atrás =====

for (int i = 3; i >= 1; i--)
{
    Console.WriteLine("Faltan " + i);
}

Console.WriteLine("¡Despegue!");


// ===== 5. Decisiones dentro del bucle =====

for (int i = 1; i <= 5; i++)
{
    if (i == 4)
    {
        Console.WriteLine("Soy la vuelta cuatro");
    }
    else
    {
        Console.WriteLine("Vuelta " + i);
    }
}


// ===== 6. Tabla de multiplicar =====

const int Tabla = 8;

Console.WriteLine($"Tabla del {Tabla}");

for (int i = 1; i <= 10; i++)
{
    Console.WriteLine($"{Tabla} x {i} = {Tabla * i}");
}


// ===== 7. La variable del bucle solo existe dentro de él =====

// Console.WriteLine(i);   // error: i no existe fuera del for


// ===== 8. El usuario decide cuántas vueltas =====

Console.WriteLine("Indique la cantidad de vueltas...");
int vueltas = Convert.ToInt32(Console.ReadLine());

for (int i = 1; i <= vueltas; i++)
{
    Console.WriteLine("Número de vuelta: " + i);
}


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
