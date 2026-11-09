# Entrada de datos en C#

## Qué es la entrada de datos

El proceso por el que un programa **recibe información externa**, normalmente de un usuario, para procesarla, guardarla o usarla. Rellenar un formulario o escribir usuario y clave son entradas de datos.

Hasta ahora el programa solo **mostraba** datos con `Console.WriteLine`. Para **recoger** lo que el usuario escribe se usa `Console.ReadLine`.

## `Console.ReadLine`

Detiene el programa hasta que el usuario escribe algo y pulsa `Enter`. Devuelve **lo escrito como texto**, que se guarda en una variable.

```csharp
Console.WriteLine("Introduzca su nombre...");
string nombre = Console.ReadLine() ?? "";

Console.WriteLine("Nombre colocado: " + nombre);
```

```text
Introduzca su nombre...
Sol
Nombre colocado: Sol
```

- El mensaje de `WriteLine` es solo la **pregunta**; sin `ReadLine` el programa no espera nada.
- La variable donde se guarda se declara antes del `=`: `string nombre = ...`.

### Por qué `?? ""`

Con `Nullable` activado, `ReadLine` puede devolver `null` (si no hay entrada, por ejemplo al cerrarse la entrada con `Ctrl+Z`). El `?? ""` dice: "si es `null`, usa un texto vacío". Sin él el compilador muestra un aviso.

## Pedir números

`ReadLine` **siempre devuelve texto**. Para guardarlo en un `int` o un `double` hay que convertirlo, con lo que se vio en la lección de casting.

```csharp
Console.WriteLine("Introduzca su edad...");
int edad = Convert.ToInt32(Console.ReadLine());

Console.WriteLine("Indique su salario...");
double salario = Convert.ToDouble(Console.ReadLine());
```

```csharp
int edad = Console.ReadLine();   // error: no se convierte un string a int solo
```

- Si el usuario escribe algo que no es un número, `Convert` lanza `FormatException`.
- `Convert.ToDouble` usa el separador decimal del equipo: en español se escribe `1500,50` con coma, y con punto se lee mal.
- Con `TryParse` se puede evitar el error; se verá con las excepciones.

## `ReadLine` frente a `ReadKey`

| | `Console.ReadLine()` | `Console.ReadKey()` |
|---|---|---|
| Espera | Una línea completa y `Enter` | Una sola tecla |
| Devuelve | Un `string` | La tecla pulsada |
| Se usa para | Recoger datos | Pausar el programa |

Por eso al final de las lecciones va `Console.ReadKey()`: no recoge ningún dato, solo hace una pausa para que la consola no se cierre antes de leer el resultado.

## Presentar mejor la entrada

Una línea de signos `=` separa visualmente las partes del programa:

```csharp
Console.WriteLine("==============================");
Console.WriteLine("Introduzca sus datos");
Console.WriteLine("==============================");
```

## Ejemplo

```csharp
Console.WriteLine("Introduzca su nombre...");
string nombre = Console.ReadLine() ?? "";

Console.WriteLine("Introduzca su edad...");
int edad = Convert.ToInt32(Console.ReadLine());

Console.WriteLine("Indique su salario...");
double salario = Convert.ToDouble(Console.ReadLine());

Console.WriteLine($"{nombre} tiene {edad} años y cobra {salario}");
Console.WriteLine($"En 10 años tendrá {edad + 10}");

Console.ReadKey();
```

## Comandos

```bash
cd csharp/02-fundamentals
dotnet new console -n UserInput -o 11-user-input
cd 11-user-input
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=Ic8VCjLitjE&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=11), vídeo 11.
