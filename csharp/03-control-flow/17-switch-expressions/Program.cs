// ===== 1. Repaso: el switch clásico es una instrucción =====

// switch (dia) { case 1: ... break; } ejecuta código, pero ocupa mucho espacio:
// un case y un break por cada valor.
// La expresión switch hace lo mismo y además devuelve un valor, en mucho menos código.


// ===== 2. Expresión switch con valores =====

// Una expresión switch devuelve un valor: se guarda directamente en una variable.
// Cada rama es: patrón => valor, separadas por comas.

int dia = 3;

string nombreDia = dia switch
{
    1 => "Lunes",
    2 => "Martes",
    3 => "Miércoles",
    4 => "Jueves",
    5 => "Viernes",
    6 => "Sábado",
    7 => "Domingo",
    _ => "Día no válido"      // _ recoge cualquier otro valor (equivale a default)
};

Console.WriteLine(nombreDia);   // Miércoles


// ===== 3. Expresión switch con rangos =====

int nota = 80;

string resultado = nota switch
{
    >= 70 => "Pasaste el examen",
    >= 50 => "Debes repetir el examen",   // ya es menor que 70: se evalúa en orden
    _ => "Has reprobado el examen"
};

Console.WriteLine($"Nota {nota}: {resultado}");   // Pasaste el examen

nota = 65;
resultado = nota switch
{
    >= 70 => "Pasaste el examen",
    >= 50 => "Debes repetir el examen",
    _ => "Has reprobado el examen"
};
Console.WriteLine($"Nota {nota}: {resultado}");   // Debes repetir el examen

nota = 40;
resultado = nota switch
{
    >= 70 => "Pasaste el examen",
    >= 50 => "Debes repetir el examen",
    _ => "Has reprobado el examen"
};
Console.WriteLine($"Nota {nota}: {resultado}");   // Has reprobado el examen


// ===== 4. Rango cerrado con and =====

nota = 64;

resultado = nota switch
{
    >= 80 => "Pasaste el examen",
    >= 60 and <= 69 => "Repetir examen",   // entre 60 y 69, ambos incluidos
    _ => "Reprobaste el examen"
};

Console.WriteLine($"Nota {nota}: {resultado}");   // Repetir examen


// ===== 5. Varios valores con or =====

dia = 6;

string tipoDia = dia switch
{
    6 or 7 => "Fin de semana",
    >= 1 and <= 5 => "Día laborable",
    _ => "Día no válido"
};

Console.WriteLine(tipoDia);   // Fin de semana


// ===== 6. El orden importa =====

// Se toma la primera rama que coincide. Una condición general antes de una concreta
// la tapa, y el compilador lo detecta como error:
/*
resultado = nota switch
{
    >= 50 => "Debes repetir el examen",
    >= 70 => "Pasaste el examen",         // error: el patrón ya está cubierto por >= 50
    _ => "Has reprobado el examen"
};
*/


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
