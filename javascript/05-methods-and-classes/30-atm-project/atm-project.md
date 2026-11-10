# Proyecto: cajero automático en JavaScript

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
│   ├── saldo        (number)  saldo de la cuenta, empieza en 10000
│   ├── operaciones  (number)  operaciones realizadas
│   └── rl           (readline) lee del teclado
│
└── Métodos
    ├── menu()                muestra el menú y reparte según la opción
    ├── consultarSaldo()
    ├── depositar()
    ├── retirar()
    ├── salir()
    ├── finalizarOperacion()  cuenta la operación y pregunta si hay otra
    └── leerNumero()          convierte la entrada en número (con retorno)

LIMITE_OPERACIONES = 3        (constante fuera de la clase)
```

El archivo termina creando el objeto y llamando a `menu()`:

```javascript
const cajero = new CajeroAutomatico();
cajero.menu();
```

## `async` y `await`

Leer del teclado es **asíncrono** (lección 11). Por eso todo método que pregunta al usuario es **`async`**, y cada pregunta lleva **`await`**:

```javascript
async depositar() {
    monto = await this.leerNumero("Ingrese el monto a depositar:\n");
}
```

Y a su vez, cuando un método `async` llama a otro, también escribe `await` para **esperar a que termine**:

```javascript
await this.finalizarOperacion();
```

Los detalles se explican en la lección de programación asíncrona; aquí basta con seguir la regla: *si un método pregunta, es `async`; si se llama a un método `async`, se escribe `await`*.

## Flujo del programa

```text
menu ──► 1 Consultar ──┐
     ├─► 2 Depositar ──┼──► ¿Otra operación? ── Sí ──► vuelve al menú
     ├─► 3 Retirar ────┘                       └─ No ──► salir
     ├─► 4 Salir
     └─► otra cosa ──► "OPCIÓN NO VÁLIDA" ──► vuelve al menú
```

## El menú: `do-while` + `switch`

```javascript
let opcion;

do {
    console.clear();
    console.log(`==== SELECCIONE LA OPERACIÓN ====
1. Consultar saldo
2. Depositar
3. Retirar
4. Salir
=================================`);

    opcion = await this.rl.question("Seleccione una opción:\n");

    switch (opcion) {
        case "1": await this.consultarSaldo(); break;
        case "2": await this.depositar(); break;
        case "3": await this.retirar(); break;
        case "4": this.salir(); break;
        default:
            await this.rl.question("OPCIÓN NO VÁLIDA. Presione Enter para continuar...\n");
            break;
    }
} while (opcion !== "4");
```

- **`do-while`**: el menú se muestra **al menos una vez** y se repite mientras la opción no sea `"4"`.
- **La opción es un texto**: `question` ya devuelve texto, así que no hace falta convertir; los `case` van entre comillas: `"1"`. (`switch` compara con `===`: el número `1` no coincidiría.)
- **`default`**: recoge cualquier valor que no sea 1 a 4. Al terminar, el bucle vuelve a mostrar el menú.
- **`console.clear()`** limpia la consola.
- **Las comillas invertidas** permiten escribir el menú en varias líneas (lección 23).
- **`this.`** es obligatorio para llamar a otro método o usar un atributo de la clase.

## Depositar y retirar: validar la entrada

```javascript
async depositar() {
    let monto;

    do {
        monto = await this.leerNumero("Ingrese el monto a depositar:\n");

        if (monto <= 0) {
            console.log("Monto inválido: debe ser mayor que cero. Intente de nuevo.");
        }
    } while (monto <= 0);

    this.saldo += monto;
    console.log(`Depósito exitoso. Nuevo saldo: ${this.saldo.toFixed(2)}`);

    await this.finalizarOperacion();
}
```

- Un `do-while` **vuelve a pedir el monto** mientras sea inválido (cero, negativo o texto).
- Con el monto correcto, `this.saldo += monto` actualiza el atributo.
- `toFixed(2)` muestra el saldo con **dos decimales** (devuelve un texto).

`retirar` añade una segunda comprobación, con `if / else if / else`:

```javascript
if (retiro <= 0) {
    console.log("Monto inválido: debe ser mayor que cero. Intente de nuevo.");
} else if (retiro > this.saldo) {
    console.log("Fondos insuficientes. Intente de nuevo.");
} else {
    valido = true;
}
```

| Caso | Resultado |
|---|---|
| `retiro <= 0` | Monto inválido, se vuelve a pedir |
| `retiro > saldo` | Fondos insuficientes, se vuelve a pedir |
| En otro caso | Se acepta: `saldo -= retiro` |

## Convertir la entrada: `leerNumero`

```javascript
async leerNumero(pregunta) {
    const numero = Number(await this.rl.question(pregunta));

    return Number.isNaN(numero) ? 0 : numero;
}
```

- `Number("hola")` da `NaN` en lugar de un error (lección 9). Si no es un número, se devuelve **0**, que el programa ya trata como monto inválido.
- Es un método **con retorno**: devuelve el número leído.
- Un decimal se escribe con el **punto**.

## Seguir o salir

```javascript
async finalizarOperacion() {
    this.operaciones++;

    if (this.operaciones >= LIMITE_OPERACIONES) {
        console.log("LÍMITE DE OPERACIONES PERMITIDAS ALCANZADO");
        this.salir();
    }

    const respuesta = await this.rl.question("¿Desea realizar otra operación? (1 = Sí / 2 = No)\n");

    if (respuesta !== "1") {
        this.salir();
    }
}
```

- Cada operación llama a este método **al terminar**: así el código no se repite en las tres.
- Si responde `1`, el método termina y el `do-while` del menú vuelve a mostrarlo.
- **`salir()`** muestra la despedida, cierra la lectura y llama a `process.exit(0)`, que **cierra el programa**.

## Ejercicio propuesto en el vídeo: límite de operaciones

El código ya incluye la mejora que se sugiere al final: un contador `operaciones` y una constante `LIMITE_OPERACIONES = 3`. Al llegar a 3 operaciones el cajero avisa y se cierra.

Ideas para seguir practicando:

- Pedir un **PIN** antes de mostrar el menú, con 3 intentos.
- Guardar un **historial** de movimientos en un array (lección 24).
- **Rechazar** retiros por encima de un máximo diario.

## Mejoras respecto al vídeo

El vídeo hace que cada método se **llame a sí mismo** para repetir una petición inválida, y que `otraOperacion` llame de nuevo a `menu`. Eso funciona, pero apila llamadas (recursión, que se verá más adelante). Aquí se usan **bucles `do-while`**, que hacen lo mismo con un flujo más sencillo.

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
Depósito exitoso. Nuevo saldo: 12000.00
¿Desea realizar otra operación? (1 = Sí / 2 = No)
```

## Comandos

```bash
cd javascript/05-methods-and-classes
node 30-atm-project/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=DffpxkMj2jY&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=30), vídeo 30.
