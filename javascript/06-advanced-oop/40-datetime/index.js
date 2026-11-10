// Date representa una fecha y una hora.
// Sirve para obtener la fecha y hora actuales, formatearlas, sumar y restar tiempo,
// calcular diferencias entre fechas y compararlas.
//
// CUIDADO: en JavaScript los meses van de 0 a 11 (enero = 0), y los objetos Date son MUTABLES.

const es = "es-ES";   // español para los nombres de días y meses

// Rellena con ceros a la izquierda hasta dos dígitos: 5 -> "05"
const dos = (n) => String(n).padStart(2, "0");

// Formato "dd/MM/yyyy HH:mm:ss" (JavaScript no tiene patrones como C# o Java)
const formatear = (f) =>
    `${dos(f.getDate())}/${dos(f.getMonth() + 1)}/${f.getFullYear()} ` +
    `${dos(f.getHours())}:${dos(f.getMinutes())}:${dos(f.getSeconds())}`;


// ===== 1. Fecha y hora actuales =====

// new Date(): fecha y hora de este momento
const ahora = new Date();
console.log("Fecha y hora actuales: " + ahora.toLocaleString(es));

console.log("Fecha de hoy: " + ahora.toLocaleDateString(es));
console.log("Hora actual: " + ahora.toLocaleTimeString(es));


// ===== 2. Crear una fecha concreta =====

// new Date(año, mes, día, hora, minuto, segundo) -- ¡el mes empieza en 0!
const fecha = new Date(2025, 4, 24, 10, 53, 20);   // 4 = mayo


// ===== 3. Extraer cada parte =====

console.log("Año: " + fecha.getFullYear());
console.log("Mes: " + (fecha.getMonth() + 1));     // getMonth() da 4: hay que sumar 1
console.log("Día: " + fecha.getDate());            // getDate() es el día del mes (getDay() es el de la semana)
console.log("Hora: " + fecha.getHours());
console.log("Minuto: " + fecha.getMinutes());
console.log("Segundo: " + fecha.getSeconds());

console.log("Día de la semana: " + fecha.toLocaleDateString(es, { weekday: "long" }));   // sábado

// No hay un método para el día del año: se calcula (con UTC para evitar el cambio de hora)
const diaDelAnio = (Date.UTC(2025, 4, 24) - Date.UTC(2025, 0, 1)) / 86400000 + 1;
console.log("Día del año: " + diaDelAnio);   // 144

console.log("Mes (nombre): " + fecha.toLocaleDateString(es, { month: "long" }));   // mayo


// ===== 4. Formatear: toLocaleDateString y compañía =====

console.log("Formato corto: " + fecha.toLocaleDateString(es, { day: "2-digit", month: "2-digit", year: "numeric" }));
console.log("Formato con hora: " + formatear(fecha));
console.log("Formato largo: " + fecha.toLocaleDateString(es, { weekday: "long", day: "numeric", month: "long", year: "numeric" }));
console.log("Hora corta: " + `${dos(fecha.getHours())}:${dos(fecha.getMinutes())}`);
console.log("AM/PM: " + (fecha.getHours() < 12 ? "AM" : "PM"));


// ===== 5. Sumar y restar tiempo =====

// Date no tiene add...: se usan los setters, que MODIFICAN el objeto. Para no cambiar la original, se copia.
const mas4Dias = new Date(fecha);
mas4Dias.setDate(mas4Dias.getDate() + 4);
console.log("Dentro de 4 días: " + formatear(mas4Dias));

const mas5Horas = new Date(fecha);
mas5Horas.setHours(mas5Horas.getHours() + 5);
console.log("Dentro de 5 horas: " + formatear(mas5Horas));

const masUnMes = new Date(fecha);
masUnMes.setMonth(masUnMes.getMonth() + 1);
console.log("Dentro de un mes: " + formatear(masUnMes));

const menosUnAnio = new Date(fecha);
menosUnAnio.setFullYear(menosUnAnio.getFullYear() - 1);   // con negativos se resta
console.log("Hace un año: " + formatear(menosUnAnio));


// ===== 6. Diferencia entre fechas =====

// Restar dos Date da la diferencia en MILISEGUNDOS
const inicio = new Date(2025, 1, 3);   // 3 de febrero
const fin = new Date(2025, 4, 24);     // 24 de mayo

const milisegundos = fin - inicio;
console.log("Milisegundos: " + milisegundos);

// OJO: con fechas locales el cambio de hora de verano falsea el resultado
// (entre febrero y mayo hay un día de 23 horas en España):
console.log("Días (con hora local): " + milisegundos / 86400000);   // 109.958...: casi 110

// Con UTC no hay cambios de hora y el cálculo es exacto
const diasExactos = (Date.UTC(2025, 4, 24) - Date.UTC(2025, 1, 3)) / 86400000;
console.log("Diferencia en días: " + diasExactos);          // 110
console.log("Diferencia en horas: " + diasExactos * 24);    // 2640


// ===== 7. Comparar fechas =====

console.log("¿Inicio es anterior a fin? " + (inicio < fin));              // true: < y > comparan bien
console.log("¿Son la misma fecha? " + (inicio.getTime() === fin.getTime()));   // false: == y === comparan referencias

const esBisiesto = (anio) => new Date(anio, 1, 29).getDate() === 29;
console.log("¿2024 es bisiesto? " + esBisiesto(2024));                   // true
console.log("Días de febrero de 2025: " + new Date(2025, 2, 0).getDate());   // 28: el día 0 de marzo es el último de febrero


// ===== 8. Convertir un texto en fecha =====

// Con la hora, el formato ISO se interpreta como hora local
const leida = new Date("2025-05-24T00:00:00");
console.log("Fecha leída: " + leida.toLocaleDateString(es, { day: "2-digit", month: "2-digit", year: "numeric" }));

// Un texto que no es una fecha no lanza error: da una fecha inválida
const invalida = new Date("esto no es una fecha");
if (Number.isNaN(invalida.getTime())) {
    console.log("Texto no válido como fecha");
}
