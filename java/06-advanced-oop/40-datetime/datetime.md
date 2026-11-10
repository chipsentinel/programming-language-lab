# Fechas y horas en Java

## `java.time`

Desde Java 8 las fechas y horas se manejan con el paquete **`java.time`**. Sus clases principales:

| Clase | Representa | Equivale en C# |
|---|---|---|
| `LocalDate` | Solo la **fecha** | `DateTime` (sin hora) |
| `LocalTime` | Solo la **hora** | `TimeOnly` |
| `LocalDateTime` | **Fecha y hora** | **`DateTime`** |
| `Duration` | Intervalo en **horas, minutos, segundos** | `TimeSpan` |
| `Period` | Intervalo en **años, meses, días** | — |
| `DateTimeFormatter` | **Formatear** y leer textos | `ToString("formato")` |

> Java también tiene `java.util.Date` y `Calendar`, **obsoletas**: no se deben usar en código nuevo.

Permiten obtener la fecha y hora actuales, formatear, sumar y restar tiempo, calcular diferencias y comparar fechas.

## Fecha y hora actuales

```java
LocalDateTime ahora = LocalDateTime.now();
LocalDate hoy = LocalDate.now();
LocalTime hora = LocalTime.now();

System.out.println(ahora);    // 2025-05-24T10:53:20.123456
System.out.println(hoy);      // 2025-05-24
```

`toString()` usa el **formato ISO** (`año-mes-día`), siempre igual en cualquier equipo. Para otro formato se usa `DateTimeFormatter`.

## Crear una fecha concreta

```java
LocalDateTime fecha = LocalDateTime.of(2025, 5, 24, 10, 53, 20);
//                                     año  mes día hora min seg
```

Los **meses van de 1 a 12**. Con `LocalDate.of(2025, 2, 3)` se crea solo la fecha.

## Extraer cada parte

| Método | Devuelve | Con `fecha` |
|---|---|---|
| `getYear()` | Año | `2025` |
| `getMonthValue()` | Mes (1-12) | `5` |
| `getDayOfMonth()` | Día del mes | `24` |
| `getHour()` | Hora (0-23) | `10` |
| `getMinute()` | Minuto | `53` |
| `getSecond()` | Segundo | `20` |
| `getDayOfWeek()` | Día de la semana (`enum`) | `SATURDAY` |
| `getDayOfYear()` | Día del año (1-366) | `144` |

Son **métodos**, con paréntesis.

## Formatear: `DateTimeFormatter`

Se crea un formateador con un **patrón** y se aplica con `format`:

```java
DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

fecha.format(formato);    // 24/05/2025 10:53:20
```

| Letras | Significan | Ejemplo |
|---|---|---|
| `dd` | Día con dos dígitos | `24` |
| `MM` | Mes con dos dígitos | `05` |
| `MMMM` | Nombre del mes | `mayo` |
| `yyyy` | Año con cuatro dígitos | `2025` |
| `EEEE` | Nombre del día de la semana | `sábado` |
| `HH` | Hora 0-23 | `10` |
| `hh` | Hora 1-12 | `10` |
| `mm` | Minutos | `53` |
| `ss` | Segundos | `20` |
| `a` | AM / PM | `AM` |

**Mayúsculas y minúsculas importan**: `MM` es el mes y `mm` son los minutos.

### El idioma

Los nombres de meses y días dependen del **`Locale`**:

```java
Locale es = new Locale("es", "ES");

fecha.format(DateTimeFormatter.ofPattern("EEEE", es));                          // sábado
fecha.format(DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", es));   // sábado, 24 de mayo de 2025
```

El texto fijo dentro del patrón va entre **comillas simples** (`'de'`).

## Sumar y restar tiempo

| Método | Suma | Resta |
|---|---|---|
| Días | `plusDays(n)` | `minusDays(n)` |
| Horas | `plusHours(n)` | `minusHours(n)` |
| Minutos | `plusMinutes(n)` | `minusMinutes(n)` |
| Meses | `plusMonths(n)` | `minusMonths(n)` |
| Años | `plusYears(n)` | `minusYears(n)` |

```java
fecha.plusDays(4);       // 28/05/2025 10:53:20
fecha.minusYears(1);     // 24/05/2024 10:53:20
```

Los objetos de `java.time` son **inmutables**: `plus...` devuelve una **fecha nueva** y la original no cambia. Si no se guarda el resultado, se pierde.

## Diferencia entre fechas: `Duration` y `Period`

```java
LocalDateTime inicio = LocalDateTime.of(2025, 2, 3, 0, 0);
LocalDateTime fin = LocalDateTime.of(2025, 5, 24, 0, 0);

Duration diferencia = Duration.between(inicio, fin);

System.out.println(diferencia.toDays());     // 110
System.out.println(diferencia.toHours());    // 2640
```

- **`Duration`** mide tiempo **exacto** (días de 24 horas, horas, minutos, segundos).
- **`Period`** mide en **años, meses y días** de calendario: `Period.between(fechaA, fechaB)` con `LocalDate`.
- Otra opción es `ChronoUnit.DAYS.between(inicio, fin)`.

## Comparar fechas

En Java **no se comparan con `==`** (compararía referencias). Se usan sus métodos:

```java
inicio.isBefore(fin);       // true
inicio.isAfter(fin);        // false
inicio.equals(fin);         // false
inicio.compareTo(fin);      // negativo si es anterior
```

Y hay utilidades en `LocalDate`:

```java
LocalDate.of(2024, 1, 1).isLeapYear();        // true: bisiesto
LocalDate.of(2025, 2, 1).lengthOfMonth();     // 28
```

## Texto a fecha

```java
LocalDate leida = LocalDate.parse("2025-05-24");    // formato ISO: año-mes-día

try {
    LocalDate.parse("esto no es una fecha");
} catch (DateTimeParseException e) {
    System.out.println("Texto no válido como fecha");
}
```

`parse` lanza `DateTimeParseException` si el texto no es una fecha. Java no tiene `TryParse`: se usa `try / catch` (lección 41). Para otros formatos: `LocalDate.parse("24/05/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy"))`.

## Ejemplo

```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeLesson {
    public static void main(String[] args) {
        LocalDate fecha = LocalDate.of(2025, 5, 24);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Fecha: " + fecha.format(formato));
        System.out.println("Dentro de 10 días: " + fecha.plusDays(10).format(formato));

        long faltan = ChronoUnit.DAYS.between(fecha, LocalDate.of(2025, 12, 25));
        System.out.println("Días hasta Navidad: " + faltan);
    }
}
```

## Comandos

```bash
cd java/06-advanced-oop/40-datetime
java DateTimeLesson.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=H64djiEB9ws&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=40), vídeo 40.
