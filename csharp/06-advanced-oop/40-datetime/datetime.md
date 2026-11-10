# Fechas y horas con `DateTime` en C#

## Qué es `DateTime`

Una **estructura** (`struct`) del espacio de nombres `System` que representa **una fecha y una hora** del calendario gregoriano: año, mes, día, hora, minuto, segundo.

Permite:

- Obtener la **fecha y la hora actuales**.
- **Formatear** una fecha como texto.
- **Sumar y restar** días, meses, años, horas...
- Calcular la **diferencia** entre dos fechas.
- **Comparar** fechas y **validar** textos que deberían ser fechas.

> Una `struct` se parece a una clase, pero es un **tipo valor**: al asignarla se copia, como un `int`.

## Fecha y hora actuales

```csharp
DateTime ahora = DateTime.Now;
DateTime hoy = DateTime.Today;

Console.WriteLine(ahora.ToString());    // 24/05/2025 10:53:20
Console.WriteLine(hoy.ToString());      // 24/05/2025 0:00:00
```

| | Qué devuelve |
|---|---|
| `DateTime.Now` | Fecha **y hora** de este momento |
| `DateTime.Today` | Solo la fecha: la hora es `0:00:00` |
| `DateTime.UtcNow` | La hora universal (UTC), sin zona horaria local |

`ToString()` sin argumentos da la fecha y hora en **el formato de la configuración regional** del equipo.

### Formatos cortos y largos

```csharp
Console.WriteLine(hoy.ToShortDateString());    // 24/05/2025
Console.WriteLine(hoy.ToLongDateString());     // sábado, 24 de mayo de 2025
Console.WriteLine(ahora.ToShortTimeString());  // 10:53
```

Hay que **llamar a los métodos con paréntesis**. Sin ellos se muestra el nombre del método, no su valor.

## Crear una fecha concreta

```csharp
DateTime fecha = new DateTime(2025, 5, 24, 10, 53, 20);
//                            año  mes día hora min seg
```

Los **meses van de 1 a 12**. También existe `new DateTime(2025, 2, 3)` (hora `0:00:00`).

## Extraer cada parte

| Propiedad | Devuelve | Con `fecha` |
|---|---|---|
| `Year` | Año | `2025` |
| `Month` | Mes (1-12) | `5` |
| `Day` | Día del mes | `24` |
| `Hour` | Hora (0-23) | `10` |
| `Minute` | Minuto | `53` |
| `Second` | Segundo | `20` |
| `DayOfWeek` | Día de la semana (`enum`) | `Saturday` |
| `DayOfYear` | Día del año (1-366) | `144` |

Son **propiedades**: no llevan paréntesis.

## Formatear: `ToString("formato")`

Se pasa un **patrón** con letras que representan cada parte:

| Letras | Significan | Ejemplo |
|---|---|---|
| `dd` | Día con dos dígitos | `24` |
| `MM` | Mes con dos dígitos | `05` |
| `MMMM` | Nombre del mes | `mayo` |
| `yyyy` | Año con cuatro dígitos | `2025` |
| `dddd` | Nombre del día de la semana | `sábado` |
| `HH` | Hora 0-23 | `10` |
| `hh` | Hora 1-12 | `10` |
| `mm` | Minutos | `53` |
| `ss` | Segundos | `20` |
| `tt` | AM / PM | `AM` |

```csharp
fecha.ToString("dd/MM/yyyy");                // 24/05/2025
fecha.ToString("dd/MM/yyyy HH:mm:ss");       // 24/05/2025 10:53:20
fecha.ToString("tt");                        // AM
```

**Mayúsculas y minúsculas importan**: `MM` es el mes y `mm` son los minutos.

### El idioma

Los nombres de meses y días dependen de la **cultura**. Para fijar el español:

```csharp
using System.Globalization;

CultureInfo es = new CultureInfo("es-ES");

fecha.ToString("dddd", es);        // sábado
fecha.ToString("MMMM", es);        // mayo
fecha.ToString("D", es);           // sábado, 24 de mayo de 2025   (fecha larga)
```

Letras sueltas como `"D"` o `"t"` son **formatos estándar** que dependen de la cultura.

## Sumar y restar tiempo

| Método | Suma |
|---|---|
| `AddDays(n)` | Días |
| `AddHours(n)` | Horas |
| `AddMinutes(n)` | Minutos |
| `AddMonths(n)` | Meses |
| `AddYears(n)` | Años |

```csharp
DateTime manana = fecha.AddDays(1);     // sin ñ en el nombre de la variable
fecha.AddDays(4);                       // 28/05/2025 10:53:20
fecha.AddYears(-1);                     // 24/05/2024 10:53:20  (negativo: resta)
```

Un `DateTime` es **inmutable**: `Add...` devuelve una **fecha nueva** y la original no cambia. Si no se guarda el resultado, se pierde.

## Diferencia entre fechas: `TimeSpan`

Restar dos fechas da un **`TimeSpan`**, un intervalo de tiempo:

```csharp
DateTime inicio = new DateTime(2025, 2, 3);
DateTime fin = new DateTime(2025, 5, 24);

TimeSpan diferencia = fin - inicio;

Console.WriteLine(diferencia.TotalDays);     // 110
Console.WriteLine(diferencia.TotalHours);    // 2640
```

| Propiedad | Significa |
|---|---|
| `TotalDays` | El intervalo **completo** expresado en días (admite decimales) |
| `TotalHours`, `TotalMinutes`... | Lo mismo en otras unidades |
| `Days`, `Hours`, `Minutes` | Solo **la parte** correspondiente del intervalo |

Esa diferencia entre `Total...` y la parte suelta es la causa de resultados raros: para 1 día y 12 horas, `Days` vale `1` y `TotalDays` vale `1,5`. Para "cuántos días hay entre estas fechas" se usa **`TotalDays`**.

## Comparar fechas

Los operadores normales funcionan:

```csharp
inicio < fin        // True
inicio == fin       // False
```

Y hay utilidades:

```csharp
DateTime.IsLeapYear(2024);        // True: bisiesto
DateTime.DaysInMonth(2025, 2);    // 28
```

Para comparar **solo la fecha**, ignorando la hora: `a.Date == b.Date`.

## Texto a fecha

```csharp
DateTime leida = DateTime.Parse("2025-05-24");

if (!DateTime.TryParse("esto no es una fecha", out DateTime invalida))
{
    Console.WriteLine("Texto no válido como fecha");
}
```

- `Parse` lanza `FormatException` si el texto no es una fecha.
- `TryParse` devuelve `false` en lugar de fallar (lección 9): es lo adecuado cuando el texto lo escribe el usuario.

## Ejemplo

```csharp
DateTime fecha = new DateTime(2025, 5, 24);

Console.WriteLine("Fecha: " + fecha.ToString("dd/MM/yyyy"));
Console.WriteLine("Dentro de 10 días: " + fecha.AddDays(10).ToString("dd/MM/yyyy"));

TimeSpan faltan = new DateTime(2025, 12, 25) - fecha;
Console.WriteLine("Días hasta Navidad: " + faltan.TotalDays);

Console.ReadKey();
```

## Comandos

```bash
cd csharp/06-advanced-oop
dotnet new console -n DateTimeLesson -o 40-datetime
cd 40-datetime
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=H64djiEB9ws&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=40), vídeo 40.
