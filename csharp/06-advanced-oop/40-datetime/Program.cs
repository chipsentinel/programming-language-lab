// DateTime representa una fecha y una hora (calendario gregoriano).
// Sirve para obtener la fecha y hora actuales, formatearlas, sumar y restar tiempo,
// calcular diferencias entre fechas y compararlas.

using System.Globalization;

class Program
{
    static void Main()
    {
        // Cultura española para que los nombres de días y meses salgan en español
        CultureInfo es = new CultureInfo("es-ES");


        // ===== 1. Fecha y hora actuales =====

        // DateTime.Now: fecha y hora de este momento
        DateTime ahora = DateTime.Now;
        Console.WriteLine("Fecha y hora actuales: " + ahora.ToString());

        // DateTime.Today: solo la fecha de hoy (la hora es 00:00:00)
        DateTime hoy = DateTime.Today;
        Console.WriteLine("Fecha de hoy: " + hoy.ToShortDateString());
        Console.WriteLine("Hora actual: " + ahora.ToShortTimeString());


        // ===== 2. Crear una fecha concreta =====

        // new DateTime(año, mes, día, hora, minuto, segundo)
        DateTime fecha = new DateTime(2025, 5, 24, 10, 53, 20);


        // ===== 3. Extraer cada parte =====

        Console.WriteLine("Año: " + fecha.Year);
        Console.WriteLine("Mes: " + fecha.Month);        // 5: los meses van de 1 a 12
        Console.WriteLine("Día: " + fecha.Day);
        Console.WriteLine("Hora: " + fecha.Hour);
        Console.WriteLine("Minuto: " + fecha.Minute);
        Console.WriteLine("Segundo: " + fecha.Second);

        Console.WriteLine("Día de la semana: " + fecha.ToString("dddd", es));   // sábado
        Console.WriteLine("Día del año: " + fecha.DayOfYear);                   // 144
        Console.WriteLine("Mes (nombre): " + fecha.ToString("MMMM", es));       // mayo


        // ===== 4. Formatear: ToString con un formato =====

        // dd día, MM mes, yyyy año, HH hora (0-23), mm minutos, ss segundos, tt AM/PM
        Console.WriteLine("Formato corto: " + fecha.ToString("dd/MM/yyyy", es));
        Console.WriteLine("Formato con hora: " + fecha.ToString("dd/MM/yyyy HH:mm:ss", es));
        Console.WriteLine("Formato largo: " + fecha.ToString("D", es));          // "D" = fecha larga
        Console.WriteLine("Hora corta: " + fecha.ToString("HH:mm", es));
        Console.WriteLine("AM/PM: " + fecha.ToString("tt", CultureInfo.InvariantCulture));


        // ===== 5. Sumar y restar tiempo: AddDays, AddHours... =====

        // Los DateTime son inmutables: Add... devuelve una fecha nueva y no cambia la original
        Console.WriteLine("Dentro de 4 días: " + fecha.AddDays(4).ToString("dd/MM/yyyy HH:mm:ss", es));
        Console.WriteLine("Dentro de 5 horas: " + fecha.AddHours(5).ToString("dd/MM/yyyy HH:mm:ss", es));
        Console.WriteLine("Dentro de un mes: " + fecha.AddMonths(1).ToString("dd/MM/yyyy HH:mm:ss", es));
        Console.WriteLine("Hace un año: " + fecha.AddYears(-1).ToString("dd/MM/yyyy HH:mm:ss", es));   // con negativos se resta


        // ===== 6. Diferencia entre fechas: TimeSpan =====

        DateTime inicio = new DateTime(2025, 2, 3);
        DateTime fin = new DateTime(2025, 5, 24);

        TimeSpan diferencia = fin - inicio;   // restar dos fechas da un TimeSpan (un intervalo)

        Console.WriteLine("Diferencia en días: " + diferencia.TotalDays);     // 110
        Console.WriteLine("Diferencia en horas: " + diferencia.TotalHours);   // 2640

        // OJO: Days es solo la parte "días" del intervalo; TotalDays es el intervalo completo
        TimeSpan unDiaYMedio = new TimeSpan(1, 12, 0, 0);
        Console.WriteLine(unDiaYMedio.Days + " / " + unDiaYMedio.TotalDays);   // 1 / 1,5


        // ===== 7. Comparar fechas =====

        Console.WriteLine("¿Inicio es anterior a fin? " + (inicio < fin));       // True
        Console.WriteLine("¿Son la misma fecha? " + (inicio == fin));            // False
        Console.WriteLine("¿2024 es bisiesto? " + DateTime.IsLeapYear(2024));    // True
        Console.WriteLine("Días de febrero de 2025: " + DateTime.DaysInMonth(2025, 2));   // 28


        // ===== 8. Convertir un texto en fecha =====

        DateTime leida = DateTime.Parse("2025-05-24");
        Console.WriteLine("Fecha leída: " + leida.ToString("dd/MM/yyyy", es));

        // TryParse no lanza error si el texto no es una fecha
        if (!DateTime.TryParse("esto no es una fecha", out DateTime invalida))
        {
            Console.WriteLine("Texto no válido como fecha");
        }


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
