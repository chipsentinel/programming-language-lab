string nombre = "Sol";
int edad = 30;

// Salidatos
Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
Console.WriteLine($"Nombre de usuario: {nombre} \nEdad: {edad}");

Console.Write($"Nombre usuario: {nombre}");
Console.WriteLine($", edad: {edad}");


// ===== 1. Una línea: // =====

// En su propia línea
int anio = 2025;   // al final de una línea

// Varias líneas
// con // en cada una
// como esta


// ===== 2. Varias líneas: /* */ =====

/*
   Este comentario ocupa
   varias líneas.
*/

int total = 10 /* valor inicial */ + 5;   // en medio de una línea


// ===== 3. Documentación XML: /// =====

Console.WriteLine($"Suma: {Sumar(2, 3)}");

/// <summary>
/// Suma dos números enteros.
/// </summary>
/// <param name="a">Primer número.</param>
/// <param name="b">Segundo número.</param>
/// <returns>La suma de a y b.</returns>
static int Sumar(int a, int b)
{
    return a + b;
}


// ===== 4. Desactivar código =====

// Con //
// Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad +5}");

// Con /* */
/*
Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
Console.WriteLine($"Nombre de usuario: {nombre} \nEdad: {edad}");Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
Console.WriteLine($"Nombre de usuario: {nombre} \nEdad: {edad}");Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
Console.WriteLine($"Nombre de usuario: {nombre} \nEdad: {edad}");Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
Console.WriteLine($"Nombre de usuario: {nombre} \nEdad: {edad}");Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
Console.WriteLine($"Nombre de usuario: {nombre} \nEdad: {edad}");Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
Console.WriteLine($"Nombre de usuario: {nombre} \nEdad: {edad}");Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
Console.WriteLine($"Nombre de usuario: {nombre} \nEdad: {edad}");Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
Console.WriteLine($"Nombre de usuario: {nombre} \nEdad: {edad}");Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
*/

// Con #if false (admite /* */ dentro)
#if false
Console.WriteLine("Esto no se compila");
/* incluso con comentarios de bloque dentro */
#endif


// ===== 5. Organizar =====

// Etiquetas para dejar avisos
// TODO: pedir estos datos por teclado
// FIXME: falla si el nombre está vacío
// NOTE: este valor es provisional
// HACK: solución temporal

// Plegar un bloque en el editor
#region Datos
string ciudad = "Madrid";
int espera = 30;   // segundos
#endregion

Console.WriteLine($"{anio} {total} {ciudad} {espera}");


// ===== 6. Un // dentro de un texto no es un comentario =====

Console.WriteLine("Visita https://ejemplo.com");
Console.WriteLine("// esto no es un comentario");


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
