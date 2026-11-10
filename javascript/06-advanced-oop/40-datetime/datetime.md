# Fechas y horas con `Date` en JavaScript

## La clase `Date`

JavaScript representa fechas y horas con la clase **`Date`**, disponible sin importar nada. Permite obtener la fecha y hora actuales, formatear, sumar y restar tiempo, calcular diferencias y comparar fechas.

Internamente un `Date` guarda **un número**: los **milisegundos** transcurridos desde el 1 de enero de 1970 (UTC). Todo lo demás se calcula a partir de él.

> Existe una propuesta moderna, `Temporal`, que corrige los problemas de `Date`, pero todavía no está disponible en todos los entornos.

## Tres sorpresas de `Date`

1. **Los meses van de 0 a 11** (enero es `0`, mayo es `4`). Es el error más típico.
2. **Los objetos `Date` son mutables**: los métodos `set...` cambian el propio objeto.
3. **No hay formatos con patrón** como `"dd/MM/yyyy"`: se usan `toLocale...` o se monta el texto a mano.

## Fecha y hora actuales

```javascript
const ahora = new Date();

console.log(ahora.toLocaleString("es-ES"));         // 24/5/2025, 10:53:20
console.log(ahora.toLocaleDateString("es-ES"));     // 24/5/2025
console.log(ahora.toLocaleTimeString("es-ES"));     // 10:53:20
```

`new Date()` sin argumentos da **ahora mismo**. Para solo la fecha, se usan los métodos de formato.

## Crear una fecha concreta

```javascript
const fecha = new Date(2025, 4, 24, 10, 53, 20);
//                     año  mes día hora min seg
```

**¡El mes `4` es mayo!** Los demás valores sí empiezan donde se espera (el día va de 1 a 31).

## Extraer cada parte

| Método | Devuelve | Con `fecha` |
|---|---|---|
| `getFullYear()` | Año | `2025` |
| `getMonth()` | Mes (**0-11**) | `4` |
| `getDate()` | Día del mes | `24` |
| `getHours()` | Hora (0-23) | `10` |
| `getMinutes()` | Minuto | `53` |
| `getSeconds()` | Segundo | `20` |
| `getDay()` | Día de la semana (**0 = domingo**) | `6` |

```javascript
console.log(fecha.getMonth() + 1);    // 5: hay que sumar 1 para el mes "normal"
```

**Cuidado con la confusión**: `getDate()` es el día **del mes** y `getDay()` el día **de la semana**.

No existe un método para el **día del año**; se calcula con la diferencia con el 1 de enero.

## Formatear

### `toLocaleDateString` con opciones

```javascript
fecha.toLocaleDateString("es-ES", { weekday: "long" });    // sábado
fecha.toLocaleDateString("es-ES", { month: "long" });      // mayo
fecha.toLocaleDateString("es-ES", {
    weekday: "long", day: "numeric", month: "long", year: "numeric"
});                                                         // sábado, 24 de mayo de 2025
fecha.toLocaleDateString("es-ES", {
    day: "2-digit", month: "2-digit", year: "numeric"
});                                                         // 24/05/2025
```

El primer argumento es el **idioma** (`"es-ES"`); el segundo, las **opciones** de qué partes mostrar y cómo.

### Montar el formato a mano

Para un formato exacto como `24/05/2025 10:53:20` se componen las partes, rellenando con ceros con `padStart`:

```javascript
const dos = (n) => String(n).padStart(2, "0");

const texto = `${dos(fecha.getDate())}/${dos(fecha.getMonth() + 1)}/${fecha.getFullYear()} ` +
              `${dos(fecha.getHours())}:${dos(fecha.getMinutes())}:${dos(fecha.getSeconds())}`;
```

### AM/PM

No hay un método directo: `fecha.getHours() < 12 ? "AM" : "PM"`.

## Sumar y restar tiempo

`Date` no tiene `add...`. Se usan los **setters**, que **modifican el objeto**:

```javascript
const mas4Dias = new Date(fecha);                  // copia, para no cambiar la original
mas4Dias.setDate(mas4Dias.getDate() + 4);          // 28/05/2025 10:53:20

const menosUnAnio = new Date(fecha);
menosUnAnio.setFullYear(menosUnAnio.getFullYear() - 1);
```

| Setter | Cambia |
|---|---|
| `setDate(n)` | Día del mes |
| `setHours(n)` | Hora |
| `setMonth(n)` | Mes (0-11) |
| `setFullYear(n)` | Año |

- **Sin la copia** (`new Date(fecha)`), la fecha original quedaría modificada. En C# y Java las fechas son inmutables y esto no ocurre.
- Los setters **desbordan bien**: `setDate(35)` pasa al mes siguiente solo.

## Diferencia entre fechas

Restar dos `Date` da la diferencia en **milisegundos**:

```javascript
const inicio = new Date(2025, 1, 3);    // 3 de febrero
const fin = new Date(2025, 4, 24);      // 24 de mayo

const ms = fin - inicio;
console.log(ms / 86400000);    // milisegundos por día: 86 400 000
```

### Cuidado con el cambio de hora

Con fechas **locales**, el cambio de hora de verano hace que un día dure 23 horas. Entre febrero y mayo, en España:

```javascript
(fin - inicio) / 86400000     // 109.958...: casi 110, pero no 110
```

Una solución es calcular con **UTC**, que no tiene cambios de hora:

```javascript
const dias = (Date.UTC(2025, 4, 24) - Date.UTC(2025, 1, 3)) / 86400000;    // 110
console.log(dias * 24);                                                     // 2640 horas
```

(`Date.UTC` también usa meses 0-11.) En C# y Java este problema no aparece con `DateTime` / `LocalDateTime`, que no tienen zona horaria.

## Comparar fechas

Los operadores `<` y `>` **funcionan** (comparan los milisegundos). Pero `==` y `===` **no**: comparan referencias, no valores.

```javascript
inicio < fin;                              // true
inicio === fin;                            // false (aunque fueran la misma fecha)
inicio.getTime() === fin.getTime();        // comparación correcta de igualdad
```

Truco para los **días de un mes**: el día `0` de un mes es el último del anterior:

```javascript
new Date(2025, 2, 0).getDate();            // 28: último día de febrero de 2025
```

## Texto a fecha

```javascript
const leida = new Date("2025-05-24T00:00:00");    // formato ISO con hora: se interpreta como hora local

const invalida = new Date("esto no es una fecha");
Number.isNaN(invalida.getTime());                 // true: fecha inválida
```

- `new Date("esto no es una fecha")` **no lanza error**: da una fecha inválida (`Invalid Date`). Hay que comprobarlo con `getTime()` y `Number.isNaN`.
- **Cuidado**: `new Date("2025-05-24")` (sin hora) se interpreta como **UTC** y, según la zona horaria, puede mostrar el día anterior. Con `"2025-05-24T00:00:00"` es hora local.

## Ejemplo

```javascript
const fecha = new Date(2025, 4, 24);    // 24 de mayo (mes 4)

console.log(fecha.toLocaleDateString("es-ES"));

const mas10 = new Date(fecha);
mas10.setDate(mas10.getDate() + 10);
console.log("Dentro de 10 días: " + mas10.toLocaleDateString("es-ES"));
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 40-datetime/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=H64djiEB9ws&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=40), vídeo 40.
