// ===== 1. switch: comparar una variable con varios casos =====

// switch compara el valor de una variable con cada case; ejecuta el que coincide.
// Es una alternativa más clara que una cadena larga de else if con la misma variable.

Console.WriteLine("Días de la semana");

// En los nombres de variables no se usan tildes ni la ñ (dia, no día)
int dia = 3;

switch (dia)
{
    case 1:
        Console.WriteLine("Lunes");
        break;
    case 2:
        Console.WriteLine("Martes");
        break;
    case 3:
        Console.WriteLine("Miércoles");
        break;
    case 4:
        Console.WriteLine("Jueves");
        break;
    case 5:
        Console.WriteLine("Viernes");
        break;
    case 6:
        Console.WriteLine("Sábado");
        break;
    case 7:
        Console.WriteLine("Domingo");
        break;
}

// break termina el switch: sin él, el programa no pararía en este case


// ===== 2. Cambiar el valor =====

dia = 7;

switch (dia)
{
    case 1:
        Console.WriteLine("Lunes");
        break;
    case 7:
        Console.WriteLine("Domingo");
        break;
}


// ===== 3. default: ningún case coincide =====

dia = 9;

switch (dia)
{
    case 1:
        Console.WriteLine("Lunes");
        break;
    case 2:
        Console.WriteLine("Martes");
        break;
    default:
        Console.WriteLine("Día no válido");   // equivale al else final
        break;
}


// ===== 4. Varios case con el mismo código =====

dia = 6;

switch (dia)
{
    case 6:
    case 7:
        Console.WriteLine("Fin de semana");
        break;
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
        Console.WriteLine("Día laborable");
        break;
    default:
        Console.WriteLine("Día no válido");
        break;
}


// ===== 5. switch con textos =====

string opcion = "guardar";

switch (opcion)
{
    case "abrir":
        Console.WriteLine("Abriendo el archivo");
        break;
    case "guardar":
        Console.WriteLine("Guardando el archivo");
        break;
    default:
        Console.WriteLine("Opción desconocida");
        break;
}


// ===== 6. C# no permite pasar de un case al siguiente =====

// Cada case debe terminar en break (o return, o throw): si no, no compila.
/*
switch (dia)
{
    case 1:
        Console.WriteLine("Lunes");   // error CS0163: falta el break
    case 2:
        Console.WriteLine("Martes");
        break;
}
*/


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
