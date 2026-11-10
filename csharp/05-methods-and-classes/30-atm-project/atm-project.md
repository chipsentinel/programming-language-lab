# Proyecto: cajero automático en C#

## Objetivo

Un ejercicio práctico que reúne todo lo visto hasta ahora en un **programa completo**: un cajero automático de consola con un menú para **consultar saldo, depositar, retirar y salir**.

Pone en práctica:

- **Clases y objetos**: una clase `CajeroAutomatico` con sus atributos.
- **Métodos**: uno por operación, con y sin retorno.
- **Entrada de datos** y **conversión de tipos**.
- **Condicionales**: `if / else` y `switch`.
- **Bucles**: `do-while` para el menú y las validaciones.

## Estructura

```text
CajeroAutomatico
├── Atributos
│   ├── saldo              (double)  saldo de la cuenta, empieza en 10000
│   ├── operaciones        (int)     operaciones realizadas
│   └── LimiteOperaciones  (const)   máximo permitido: 3
│
└── Métodos
    ├── Menu()                muestra el menú y reparte según la opción
    ├── ConsultarSaldo()
    ├── Depositar()
    ├── Retirar()
    ├── Salir()
    ├── FinalizarOperacion()  cuenta la operación y pregunta si hay otra
    ├── LeerNumero()          convierte la entrada en número (con retorno)
    └── LimpiarPantalla()
```

`Main` solo crea el objeto y llama a `Menu()`:

```csharp
CajeroAutomatico cajero = new CajeroAutomatico();
cajero.Menu();
```

## Flujo del programa

```text
Menu ──► 1 Consultar ──┐
     ├─► 2 Depositar ──┼──► ¿Otra operación? ── Sí ──► vuelve al menú
     ├─► 3 Retirar ────┘                       └─ No ──► Salir
     ├─► 4 Salir
     └─► otra cosa ──► "OPCIÓN NO VÁLIDA" ──► vuelve al menú
```

## El menú: `do-while` + `switch`

```csharp
string opcion;

do
{
    LimpiarPantalla();
    Console.WriteLine(@"==== SELECCIONE LA OPERACIÓN ====
1. Consultar saldo
2. Depositar
3. Retirar
4. Salir
=================================");
    Console.WriteLine("Seleccione una opción:");
    opcion = Console.ReadLine() ?? "";

    switch (opcion)
    {
        case "1": ConsultarSaldo(); break;
        case "2": Depositar(); break;
        case "3": Retirar(); break;
        case "4": Salir(); break;
        default:
            Console.WriteLine("OPCIÓN NO VÁLIDA. Presione Enter para continuar...");
            Console.ReadLine();
            break;
    }
}
while (opcion != "4");
```

- **`do-while`**: el menú se muestra **al menos una vez** y se repite mientras la opción no sea `"4"`.
- **La opción es un `string`**: `ReadLine` ya devuelve texto, así que no hace falta convertir; los `case` van entre comillas: `"1"`.
- **`default`**: recoge cualquier valor que no sea 1 a 4. Al terminar, el bucle vuelve a mostrar el menú.
- **`Console.ReadLine()`** sin guardar el resultado sirve de pausa hasta pulsar `Enter`.
- **`@"..."`** permite escribir el menú en varias líneas (lección 23).

## Depositar y retirar: validar la entrada

```csharp
private void Depositar()
{
    double monto;

    do
    {
        Console.WriteLine("Ingrese el monto a depositar:");
        monto = LeerNumero();

        if (monto <= 0)
        {
            Console.WriteLine("Monto inválido: debe ser mayor que cero. Intente de nuevo.");
        }
    }
    while (monto <= 0);

    saldo += monto;
    Console.WriteLine($"Depósito exitoso. Nuevo saldo: {saldo:F2}");

    FinalizarOperacion();
}
```

- Un `do-while` **vuelve a pedir el monto** mientras sea inválido (cero, negativo o texto).
- Con el monto correcto, `saldo += monto` actualiza el atributo.
- `{saldo:F2}` muestra el saldo con **dos decimales**.

`Retirar` añade una segunda comprobación, con `if / else if / else`:

```csharp
if (retiro <= 0)
{
    Console.WriteLine("Monto inválido: debe ser mayor que cero. Intente de nuevo.");
}
else if (retiro > saldo)
{
    Console.WriteLine("Fondos insuficientes. Intente de nuevo.");
}
else
{
    valido = true;
}
```

| Caso | Resultado |
|---|---|
| `retiro <= 0` | Monto inválido, se vuelve a pedir |
| `retiro > saldo` | Fondos insuficientes, se vuelve a pedir |
| En otro caso | Se acepta: `saldo -= retiro` |

## Convertir la entrada: `LeerNumero`

```csharp
private double LeerNumero()
{
    if (double.TryParse(Console.ReadLine(), out double numero))
    {
        return numero;
    }

    return 0;
}
```

- `TryParse` (lección 9) convierte **sin lanzar error** si el usuario escribe texto.
- Si no es un número devuelve **0**, que el programa ya trata como monto inválido.
- Es un método **con retorno**: devuelve el número leído.
- Un decimal se escribe con la **coma** en un equipo en español.

## Seguir o salir

```csharp
private void FinalizarOperacion()
{
    operaciones++;

    if (operaciones >= LimiteOperaciones)
    {
        Console.WriteLine("LÍMITE DE OPERACIONES PERMITIDAS ALCANZADO");
        Salir();
    }

    Console.WriteLine("¿Desea realizar otra operación? (1 = Sí / 2 = No)");
    string respuesta = Console.ReadLine() ?? "";

    if (respuesta != "1")
    {
        Salir();
    }
}
```

- Cada operación llama a este método **al terminar**: así el código no se repite en las tres.
- Si responde `1`, el método termina y el `do-while` del menú vuelve a mostrarlo.
- **`Salir()`** muestra la despedida y llama a `Environment.Exit(0)`, que **cierra el programa**.

## Ejercicio propuesto en el vídeo: límite de operaciones

La clase ya incluye la mejora que se sugiere al final: un contador `operaciones` y una constante `LimiteOperaciones = 3`. Al llegar a 3 operaciones el cajero avisa y se cierra.

Ideas para seguir practicando:

- Pedir un **PIN** antes de mostrar el menú, con 3 intentos.
- Guardar un **historial** de movimientos en un array o lista (lección 33).
- **Rechazar** retiros por encima de un máximo diario.
- Mostrar el saldo con **separador de miles**: `{saldo:N2}`.

## Mejoras respecto al vídeo

El vídeo hace que cada método se **llame a sí mismo** para repetir una petición inválida, y que `OtraOperacion` llame de nuevo a `Menu`. Eso funciona, pero apila llamadas (recursión, que se verá más adelante). Aquí se usan **bucles `do-while`**, que hacen lo mismo con un flujo más sencillo.

## Ejemplo de ejecución

```text
==== SELECCIONE LA OPERACIÓN ====
1. Consultar saldo
2. Depositar
3. Retirar
4. Salir
=================================
Seleccione una opción:
2
Ingrese el monto a depositar:
0
Monto inválido: debe ser mayor que cero. Intente de nuevo.
Ingrese el monto a depositar:
2000
Depósito exitoso. Nuevo saldo: 12000,00
¿Desea realizar otra operación? (1 = Sí / 2 = No)
```

## Comandos

```bash
cd csharp/05-methods-and-classes
dotnet new console -n AtmProject -o 30-atm-project
cd 30-atm-project
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=DffpxkMj2jY&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=30), vídeo 30.
