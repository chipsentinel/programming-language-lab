// ===== 1. Incremento (++) =====

// ++ suma 1 a la variable, sin escribir numero = numero + 1
int numero1 = 5;
Console.WriteLine("Valor original: " + numero1);   // 5

numero1++;
Console.WriteLine("Valor incrementado: " + numero1);   // 6

numero1++;
Console.WriteLine("Valor incrementado: " + numero1);   // 7


// ===== 2. Decremento (--) =====

// -- resta 1 a la variable
numero1--;
Console.WriteLine("Valor decrementado: " + numero1);   // 6

numero1--;
Console.WriteLine("Valor decrementado: " + numero1);   // 5


// ===== 3. Postincremento y preincremento =====

// numero++ (post): primero usa el valor y después lo incrementa
// ++numero (pre): primero incrementa y después usa el valor
// Solas, en una línea, dan el mismo resultado; la diferencia aparece al asignar:

int valor = 7;
int valor2 = valor++;   // valor2 recibe 7 y después valor pasa a 8
Console.WriteLine($"Post: valor2 = {valor2}, valor = {valor}");   // 7 y 8

int valor3 = 7;
int valor4 = ++valor3;  // valor3 pasa a 8 y valor4 recibe 8
Console.WriteLine($"Pre: valor4 = {valor4}, valor3 = {valor3}");   // 8 y 8


// ===== 4. Operadores compuestos de asignación =====

// variable op= valor equivale a variable = variable op valor
int numero2 = 5;

numero2 += 3;   // numero2 = numero2 + 3
Console.WriteLine("+= 3: " + numero2);   // 8

numero2 -= 3;   // numero2 = numero2 - 3
Console.WriteLine("-= 3: " + numero2);   // 5

numero2 *= 3;   // numero2 = numero2 * 3
Console.WriteLine("*= 3: " + numero2);   // 15

numero2 /= 3;   // numero2 = numero2 / 3 (división entera)
Console.WriteLine("/= 3: " + numero2);   // 5

numero2 %= 3;   // numero2 = numero2 % 3 (residuo)
Console.WriteLine("%= 3: " + numero2);   // 2

// El operador siempre va antes del igual: =+ o =- no son operadores compuestos
// numero2 =+ 3;   // asigna +3, no suma

// Con otros valores se ve que /= entre enteros descarta los decimales: 5 / 3
int numero3 = 5;
numero3 /= 3;
Console.WriteLine("5 /= 3: " + numero3);   // 1


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
