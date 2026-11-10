# Proyecto: cajero automático en Java

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
│   ├── saldo               (double)  saldo de la cuenta, empieza en 10000
│   ├── operaciones         (int)     operaciones realizadas
│   ├── LIMITE_OPERACIONES  (static final)  máximo permitido: 3
│   └── scanner             (Scanner) lee del teclado
│
└── Métodos
    ├── menu()                muestra el menú y reparte según la opción
    ├── consultarSaldo()
    ├── depositar()
    ├── retirar()
    ├── salir()
    ├── finalizarOperacion()  cuenta la operación y pregunta si hay otra
    ├── leerNumero()          convierte la entrada en número (con retorno)
    └── limpiarPantalla()
```

`main` solo crea el objeto y llama a `menu()`:

```java
CajeroAutomatico cajero = new CajeroAutomatico();
cajero.menu();
```

Los atributos son `private`: solo la propia clase los usa. Es la primera vez que aparece; se explica con el encapsulamiento.

## Flujo del programa

```text
menu ──► 1 Consultar ──┐
     ├─► 2 Depositar ──┼──► ¿Otra operación? ── Sí ──► vuelve al menú
     ├─► 3 Retirar ────┘                       └─ No ──► salir
     ├─► 4 Salir
     └─► otra cosa ──► "OPCIÓN NO VÁLIDA" ──► vuelve al menú
```

## El menú: `do-while` + `switch`

```java
String opcion;

do {
    limpiarPantalla();
    System.out.println("""
            ==== SELECCIONE LA OPERACIÓN ====
            1. Consultar saldo
            2. Depositar
            3. Retirar
            4. Salir
            =================================""");
    System.out.println("Seleccione una opción:");
    opcion = scanner.nextLine();

    switch (opcion) {
        case "1": consultarSaldo(); break;
        case "2": depositar(); break;
        case "3": retirar(); break;
        case "4": salir(); break;
        default:
            System.out.println("OPCIÓN NO VÁLIDA. Presione Enter para continuar...");
            scanner.nextLine();
            break;
    }
} while (!opcion.equals("4"));
```

- **`do-while`**: el menú se muestra **al menos una vez** y se repite mientras la opción no sea `"4"`.
- **La opción es un `String`**: `nextLine` ya devuelve texto, así que no hace falta convertir; los `case` van entre comillas: `"1"`.
- **`!opcion.equals("4")`**: los textos se comparan con `equals`, no con `==` (lección 13).
- **`default`**: recoge cualquier valor que no sea 1 a 4. Al terminar, el bucle vuelve a mostrar el menú.
- **`scanner.nextLine()`** sin guardar el resultado sirve de pausa hasta pulsar `Enter`.
- **`"""..."""`** permite escribir el menú en varias líneas (lección 23).

## Depositar y retirar: validar la entrada

```java
private void depositar() {
    double monto;

    do {
        System.out.println("Ingrese el monto a depositar:");
        monto = leerNumero();

        if (monto <= 0) {
            System.out.println("Monto inválido: debe ser mayor que cero. Intente de nuevo.");
        }
    } while (monto <= 0);

    saldo += monto;
    System.out.println("Depósito exitoso. Nuevo saldo: " + String.format("%.2f", saldo));

    finalizarOperacion();
}
```

- Un `do-while` **vuelve a pedir el monto** mientras sea inválido (cero, negativo o texto).
- Con el monto correcto, `saldo += monto` actualiza el atributo.
- `String.format("%.2f", saldo)` muestra el saldo con **dos decimales**.

`retirar` añade una segunda comprobación, con `if / else if / else`:

```java
if (retiro <= 0) {
    System.out.println("Monto inválido: debe ser mayor que cero. Intente de nuevo.");
} else if (retiro > saldo) {
    System.out.println("Fondos insuficientes. Intente de nuevo.");
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

```java
private double leerNumero() {
    try {
        return Double.parseDouble(scanner.nextLine());
    } catch (NumberFormatException e) {
        return 0;
    }
}
```

- `Double.parseDouble` lanza un error si el usuario escribe texto. **`try / catch`** lo captura y el método devuelve **0**, que el programa ya trata como monto inválido. Java no tiene `TryParse`; `try / catch` se verá con las excepciones (lección 41).
- Es un método **con retorno**: devuelve el número leído.
- Un decimal se escribe con el **punto**.

## Seguir o salir

```java
private void finalizarOperacion() {
    operaciones++;

    if (operaciones >= LIMITE_OPERACIONES) {
        System.out.println("LÍMITE DE OPERACIONES PERMITIDAS ALCANZADO");
        salir();
    }

    System.out.println("¿Desea realizar otra operación? (1 = Sí / 2 = No)");
    String respuesta = scanner.nextLine();

    if (!respuesta.equals("1")) {
        salir();
    }
}
```

- Cada operación llama a este método **al terminar**: así el código no se repite en las tres.
- Si responde `1`, el método termina y el `do-while` del menú vuelve a mostrarlo.
- **`salir()`** muestra la despedida y llama a `System.exit(0)`, que **cierra el programa**.

## Limpiar la pantalla

Java no tiene un equivalente de `Console.Clear()`. Se imprime una **secuencia ANSI** que borra el terminal:

```java
System.out.print("\033[H\033[2J");
System.out.flush();
```

Funciona en terminales modernos (Windows Terminal, VS Code, IntelliJ). Si en la consola antigua de Windows se ven caracteres extraños, basta con quitar la llamada a `limpiarPantalla()`.

## Ejercicio propuesto en el vídeo: límite de operaciones

La clase ya incluye la mejora que se sugiere al final: un contador `operaciones` y una constante `LIMITE_OPERACIONES = 3`. Al llegar a 3 operaciones el cajero avisa y se cierra.

Ideas para seguir practicando:

- Pedir un **PIN** antes de mostrar el menú, con 3 intentos.
- Guardar un **historial** de movimientos en un array o lista (lección 33).
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
Depósito exitoso. Nuevo saldo: 12000,00
¿Desea realizar otra operación? (1 = Sí / 2 = No)
```

## Comandos

```bash
cd java/05-methods-and-classes/30-atm-project
java AtmProject.java
```

En Windows con Java 17, si las letras `Á` o `Í` en mayúscula salen mal, hay que indicar la codificación del archivo: `java -Dfile.encoding=UTF-8 AtmProject.java`. Desde Java 18 UTF-8 es el valor por defecto.

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=DffpxkMj2jY&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=30), vídeo 30.
