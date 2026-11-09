// Los operadores lógicos combinan o modifican expresiones bool (verdadero o falso)

int numero1 = 40;
int numero2 = 90;
int numero3 = 5;
bool resultado;


// ===== 1. AND (&&): se cumplen las dos condiciones =====

resultado = numero1 < numero2 && numero1 < numero3;   // true && false
Console.WriteLine("AND (40 < 90 y 40 < 5): " + resultado);   // False

numero3 = 50;
resultado = numero1 < numero2 && numero1 < numero3;   // true && true
Console.WriteLine("AND (40 < 90 y 40 < 50): " + resultado);  // True


// ===== 2. OR (||): se cumple al menos una condición =====

resultado = numero1 < numero2 || numero1 < numero3;   // true || true
Console.WriteLine("OR (40 < 90 o 40 < 50): " + resultado);    // True

numero1 = 100;
resultado = numero1 < numero2 || numero1 < numero3;   // false || false
Console.WriteLine("OR (100 < 90 o 100 < 50): " + resultado);  // False


// ===== 3. NOT (!): invierte el valor =====

numero1 = 40;
numero3 = 5;
resultado = numero1 < numero2 && numero1 < numero3;   // false
Console.WriteLine("Sin negar: " + resultado);         // False

resultado = !(numero1 < numero2 && numero1 < numero3);
Console.WriteLine("Negado con !: " + resultado);      // True


// ===== 4. Tablas de verdad =====

Console.WriteLine("true && true   = " + (true && true));     // True
Console.WriteLine("true && false  = " + (true && false));    // False
Console.WriteLine("false || true  = " + (false || true));    // True
Console.WriteLine("false || false = " + (false || false));   // False
Console.WriteLine("!true          = " + !true);              // False


// ===== 5. Evaluación en cortocircuito =====

// && y || dejan de evaluar en cuanto saben el resultado
int divisor = 0;
bool seguro = divisor != 0 && 10 / divisor > 1;   // 10 / 0 nunca se calcula
Console.WriteLine("Sin error gracias al cortocircuito: " + seguro);   // False


// ===== 6. Orden de evaluación =====

// ! primero, después &&, y al final ||
Console.WriteLine(true || false && false);     // True: se evalúa false && false primero
Console.WriteLine((true || false) && false);   // False: los paréntesis cambian el orden


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
