// Los operadores de comparación comparan dos valores y dan un resultado bool: true o false.
// Con el operador ternario (condición ? siSeCumple : siNoSeCumple) se convierte en un texto.

int a = 10;
int b = 20;
string resultado;


// ===== 1. Mayor que (>) =====

resultado = (a > b) ? "Sí" : "No";
Console.WriteLine("¿a es mayor que b? " + resultado);   // No

b = 2;
resultado = (a > b) ? "Sí" : "No";
Console.WriteLine("¿a es mayor que b? " + resultado);   // Sí


// ===== 2. Menor que (<) =====

resultado = (a < b) ? "Sí" : "No";
Console.WriteLine("¿a es menor que b? " + resultado);   // No


// ===== 3. Igual que (==) =====

// Dos signos de igual comparan; uno solo (=) asigna
resultado = (a == b) ? "Sí" : "No";
Console.WriteLine("¿a es igual a b? " + resultado);   // No

b = 10;
resultado = (a == b) ? "Sí" : "No";
Console.WriteLine("¿a es igual a b? " + resultado);   // Sí


// ===== 4. Mayor o igual que (>=) =====

resultado = (a >= b) ? "Sí" : "No";
Console.WriteLine("¿a es mayor o igual que b? " + resultado);   // Sí

a = 1;
resultado = (a >= b) ? "Sí" : "No";
Console.WriteLine("¿a es mayor o igual que b? " + resultado);   // No


// ===== 5. Menor o igual que (<=) =====

resultado = (a <= b) ? "Sí" : "No";
Console.WriteLine("¿a es menor o igual que b? " + resultado);   // Sí


// ===== 6. Distinto de (!=) =====

resultado = (a != b) ? "Sí" : "No";
Console.WriteLine("¿a es distinto de b? " + resultado);   // Sí

a = 10;
resultado = (a != b) ? "Sí" : "No";
Console.WriteLine("¿a es distinto de b? " + resultado);   // No


// ===== 7. El resultado es un bool =====

bool esMayor = a > b;
Console.WriteLine(esMayor);          // False
Console.WriteLine(a >= b);           // True


// ===== 8. Comparar textos =====

// En C# == compara el contenido de los strings
string nombre = "Sol";
Console.WriteLine(nombre == "Sol");   // True
Console.WriteLine(nombre == "sol");   // False: distingue mayúsculas y minúsculas


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
