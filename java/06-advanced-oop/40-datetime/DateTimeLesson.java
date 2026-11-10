import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

// Java representa fechas y horas con el paquete java.time (desde Java 8):
//   LocalDate      solo fecha           LocalTime      solo hora
//   LocalDateTime  fecha y hora         Duration / Period  intervalos
// LocalDateTime es el equivalente de DateTime de C#. (Existe también java.util.Date, pero está obsoleta.)

public class DateTimeLesson {

    public static void main(String[] args) {

        // Idioma español para que los nombres de días y meses salgan en español
        Locale es = new Locale("es", "ES");


        // ===== 1. Fecha y hora actuales =====

        // now(): fecha y hora de este momento
        LocalDateTime ahora = LocalDateTime.now();
        System.out.println("Fecha y hora actuales: " + ahora);

        // LocalDate.now(): solo la fecha de hoy
        LocalDate hoy = LocalDate.now();
        System.out.println("Fecha de hoy: " + hoy);
        System.out.println("Hora actual: " + LocalTime.now().withNano(0));


        // ===== 2. Crear una fecha concreta =====

        // LocalDateTime.of(año, mes, día, hora, minuto, segundo)
        LocalDateTime fecha = LocalDateTime.of(2025, 5, 24, 10, 53, 20);


        // ===== 3. Extraer cada parte =====

        System.out.println("Año: " + fecha.getYear());
        System.out.println("Mes: " + fecha.getMonthValue());      // 5: los meses van de 1 a 12
        System.out.println("Día: " + fecha.getDayOfMonth());
        System.out.println("Hora: " + fecha.getHour());
        System.out.println("Minuto: " + fecha.getMinute());
        System.out.println("Segundo: " + fecha.getSecond());

        System.out.println("Día de la semana: " + fecha.format(DateTimeFormatter.ofPattern("EEEE", es)));   // sábado
        System.out.println("Día del año: " + fecha.getDayOfYear());                                          // 144
        System.out.println("Mes (nombre): " + fecha.format(DateTimeFormatter.ofPattern("MMMM", es)));        // mayo


        // ===== 4. Formatear: DateTimeFormatter =====

        // dd día, MM mes, yyyy año, HH hora (0-23), mm minutos, ss segundos, a AM/PM
        System.out.println("Formato corto: " + fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", es)));
        System.out.println("Formato con hora: " + fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", es)));
        System.out.println("Formato largo: " + fecha.format(DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", es)));
        System.out.println("Hora corta: " + fecha.format(DateTimeFormatter.ofPattern("HH:mm", es)));
        System.out.println("AM/PM: " + fecha.format(DateTimeFormatter.ofPattern("a", Locale.ENGLISH)));


        // ===== 5. Sumar y restar tiempo: plusDays, plusHours... =====

        // Los objetos de java.time son inmutables: plus... devuelve una fecha nueva y no cambia la original
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", es);

        System.out.println("Dentro de 4 días: " + fecha.plusDays(4).format(formato));
        System.out.println("Dentro de 5 horas: " + fecha.plusHours(5).format(formato));
        System.out.println("Dentro de un mes: " + fecha.plusMonths(1).format(formato));
        System.out.println("Hace un año: " + fecha.minusYears(1).format(formato));   // minus... resta


        // ===== 6. Diferencia entre fechas: Duration =====

        LocalDateTime inicio = LocalDateTime.of(2025, 2, 3, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2025, 5, 24, 0, 0);

        Duration diferencia = Duration.between(inicio, fin);   // un intervalo de tiempo

        System.out.println("Diferencia en días: " + diferencia.toDays());     // 110
        System.out.println("Diferencia en horas: " + diferencia.toHours());   // 2640

        // Duration mide tiempo exacto (horas, minutos...). Para diferencias en años, meses y días se usa Period.


        // ===== 7. Comparar fechas =====

        System.out.println("¿Inicio es anterior a fin? " + inicio.isBefore(fin));        // true
        System.out.println("¿Son la misma fecha? " + inicio.equals(fin));                  // false
        System.out.println("¿2024 es bisiesto? " + LocalDate.of(2024, 1, 1).isLeapYear());   // true
        System.out.println("Días de febrero de 2025: " + LocalDate.of(2025, 2, 1).lengthOfMonth());   // 28


        // ===== 8. Convertir un texto en fecha =====

        LocalDate leida = LocalDate.parse("2025-05-24");   // formato ISO: año-mes-día
        System.out.println("Fecha leída: " + leida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", es)));

        // parse lanza DateTimeParseException si el texto no es una fecha (try/catch: lección 41)
        try {
            LocalDate.parse("esto no es una fecha");
        } catch (DateTimeParseException e) {
            System.out.println("Texto no válido como fecha");
        }
    }
}
