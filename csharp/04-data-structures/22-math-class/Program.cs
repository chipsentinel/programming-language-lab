// La clase Math reúne funciones matemáticas ya hechas: Math.Funcion(valores).
// (Qué son una clase y una función se verá más adelante: por ahora solo se usan.)


// ===== 1. Potencia: Math.Pow =====

int numero = 4;

Console.WriteLine("Potenciación (4 al cuadrado): " + Math.Pow(numero, 2));   // 16
Console.WriteLine("Potenciación (4 al cubo): " + Math.Pow(numero, 3));       // 64


// ===== 2. Raíz cuadrada: Math.Sqrt =====

Console.WriteLine("Raíz cuadrada de 4: " + Math.Sqrt(numero));   // 2


// ===== 3. Valor absoluto: Math.Abs =====

numero = -4;
Console.WriteLine("Valor absoluto de -4: " + Math.Abs(numero));   // 4


// ===== 4. Redondeo: Ceiling, Floor y Round =====

double numero2 = 50.23;

Console.WriteLine("Hacia arriba (Ceiling): " + Math.Ceiling(numero2));   // 51
Console.WriteLine("Hacia abajo (Floor): " + Math.Floor(numero2));        // 50
Console.WriteLine("Normal (Round) 50.23: " + Math.Round(numero2));       // 50

numero2 = 50.53;
Console.WriteLine("Normal (Round) 50.53: " + Math.Round(numero2));       // 51


// ===== 5. Redondear el punto medio (.5) =====

// En C#, Math.Round redondea al número PAR más cercano cuando está justo en la mitad
Console.WriteLine(Math.Round(2.5));   // 2
Console.WriteLine(Math.Round(3.5));   // 4

// Para el redondeo "de toda la vida" (.5 sube) se indica:
Console.WriteLine(Math.Round(2.5, MidpointRounding.AwayFromZero));   // 3

// Redondear a un número de decimales
Console.WriteLine(Math.Round(3.14159, 2));   // 3,14


// ===== 6. Máximo y mínimo: Math.Max y Math.Min =====

numero = 4;

Console.WriteLine("Máximo: " + Math.Max(numero, numero2));   // 50,53
Console.WriteLine("Mínimo: " + Math.Min(numero, numero2));   // 4


// ===== 7. Pi y funciones trigonométricas =====

Console.WriteLine("Valor de pi: " + Math.PI);               // 3,141592653589793
Console.WriteLine("Seno de 90 grados: " + Math.Sin(Math.PI / 2));   // 1 (los ángulos van en radianes)
Console.WriteLine("Coseno de 0: " + Math.Cos(0));                    // 1


// ===== 8. Ejercicio: área de un círculo =====

Console.WriteLine("Indique el radio del círculo...");
double radio = Convert.ToDouble(Console.ReadLine());

double area = Math.PI * Math.Pow(radio, 2);   // pi * radio al cuadrado

Console.WriteLine("Área del círculo: " + area);
Console.WriteLine("Área redondeada: " + Math.Round(area));
Console.WriteLine("Área con 2 decimales: " + Math.Round(area, 2));


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
