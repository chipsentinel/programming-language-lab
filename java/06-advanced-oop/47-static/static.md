# La palabra clave `static` en Java

## Qué significa `static`

Un miembro `static` **pertenece a la clase en sí, no a un objeto** creado a partir de ella. Por eso **no hace falta instanciar la clase** (`new`) para usarlo: se usa con el **nombre de la clase**.

| | Miembro normal (de instancia) | Miembro `static` |
|---|---|---|
| Pertenece a | **Cada objeto** | **La clase** |
| Se usa con | `objeto.miembro` | `Clase.miembro` |
| Copias | Una **por objeto** | **Una sola**, compartida |
| Necesita `new` | **Sí** | **No** |

Se puede aplicar a **métodos, variables, bloques de inicialización y clases anidadas**.

## Para qué sirve

- **Llamar a algo sin crear un objeto**: es su función principal.
- **Compartir datos** entre todos los objetos de la clase (un contador).
- Crear **utilidades** que no dependen del estado de ningún objeto (`Math`, `Integer.parseInt`).

## Método `static`

```java
class Utilidad {
    public static void saludar() {
        System.out.println("Hola a todos");
    }
}

Utilidad.saludar();           // sin new
```

Sin `static`, habría que crear un objeto:

```java
class Utilidad {
    public void saludar() { /* ... */ }
}

Utilidad saludo = new Utilidad();
saludo.saludar();
```

Java **permite** llamar a un método `static` desde un objeto (`saludo.saludar()`), pero **está desaconsejado**: parece de instancia y confunde. Lo correcto es `Utilidad.saludar()`.

### `main` es `static`

Ahora se entiende por qué `public static void main(String[] args)` lleva `static`: el programa **arranca sin crear ningún objeto**, así que el punto de entrada debe poder llamarse sin instanciar nada.

Y por qué ya se llamaba así a `Math.sqrt(16)` o `Integer.parseInt("5")`: son **métodos `static`**.

## Variable `static`

```java
class Utilidad {
    public static int total = 5000;
}

System.out.println(Utilidad.total);      // 5000
Utilidad.total = 8000;                   // se puede modificar
```

Tiene **un único valor** para toda la clase, y existe desde que se usa la clase, sin necesidad de ningún objeto. Con `final` además (`static final`) es una **constante de clase** (lección 8): `PI`, `IVA`...

## Un dato compartido entre todos los objetos

El uso más típico: un **contador** que suman todos los objetos.

```java
class Persona {
    public static int creadas = 0;       // una sola copia
    public String nombre;                // una por objeto
    public int numero;

    public Persona(String nombre) {
        creadas++;
        this.nombre = nombre;
        this.numero = creadas;
    }
}

Persona sol = new Persona("Sol");        // creadas = 1, numero = 1
Persona marcos = new Persona("Marcos");  // creadas = 2, numero = 2
Persona lucia = new Persona("Lucía");    // creadas = 3, numero = 3

System.out.println(Persona.creadas);     // 3
```

| Miembro | Cuántas copias | `sol` | `marcos` | `lucia` |
|---|---|---|---|---|
| `nombre` | Una por objeto | `"Sol"` | `"Marcos"` | `"Lucía"` |
| `numero` | Una por objeto | `1` | `2` | `3` |
| `creadas` | **Una para todos** | `3` | `3` | `3` |

## Clases de utilidades

**Java no tiene "clases `static`"** como C#. (Existen las *clases anidadas* `static`, pero son otra cosa.) Para agrupar utilidades se sigue una **convención**:

```java
final class Matematica {
    private Matematica() { }                  // nadie puede crear objetos

    public static int sumar(int a, int b) {
        return a + b;
    }
}

int resultado = Matematica.sumar(50, 20);     // 70
Matematica m = new Matematica();              // error: el constructor es private
```

- **`final`**: la clase no se puede heredar.
- **Constructor `private`** (lección 37): nadie puede instanciarla.
- **Todos los métodos `static`**.

Es el patrón de `Math`, `Arrays`, `Collections`, `Files`... Java no obliga a que todo sea `static`: la clase puede tener también miembros de instancia, y entonces ya no es una clase de utilidades.

## Reglas importantes

### 1. `this` no existe en un contexto `static`

`this` es "el objeto actual" (lección 31), y un miembro `static` **no tiene objeto**:

```java
public static void mostrarTotal() {
    System.out.println(nombre);          // error: non-static variable nombre cannot be referenced from a static context
    System.out.println(this.numero);     // error
}
```

Un método `static` solo puede usar **otros miembros `static`** (o los que reciba como parámetro). Es el error más típico al empezar: llamar desde `main` a un método de la misma clase **sin** `static`.

### 2. Lo `static` sí se puede usar desde un método normal

```java
public void presentar() {
    System.out.println("Soy " + nombre + ", hay " + creadas + " personas");    // permitido
}
```

La dirección contraria (desde `static` hacia instancia) es la prohibida.

### 3. Se comparte entre todas las instancias

Un cambio en un miembro `static` lo ven **todos**. Es útil, pero también **peligroso**: un valor global modificable desde cualquier parte es difícil de controlar. Conviene usar `static` solo cuando de verdad tiene sentido.

## Bloque `static`

Se ejecuta **una sola vez**, automáticamente, **cuando la clase se carga** (en su primer uso):

```java
class Configuracion {
    public static String idioma;

    static {
        System.out.println("Inicializando configuración (solo una vez)");
        idioma = "es";
    }
}

System.out.println(Configuracion.idioma);    // aquí se ejecuta el bloque static
System.out.println(Configuracion.idioma);    // la segunda vez ya no
```

Sirve para preparar los datos `static` de la clase. En C# es el **constructor `static`**.

## Cuándo usar `static`

| Usar `static` | No usar `static` |
|---|---|
| Funciones auxiliares sin estado (`sumar`, `convertir`) | Cuando el método depende de los **datos de un objeto** |
| Constantes (`static final`) y datos compartidos de la clase | Cuando cada objeto necesita **su propia copia** |
| Contadores de instancias | Cuando se necesitará **redefinirlo** (herencia, lección 48) |

## Ejemplo

```java
public class StaticLesson {
    public static void main(String[] args) {
        int resultado = Matematica.sumar(50, 20);
        System.out.println("Suma: " + resultado);
    }
}

final class Matematica {
    private Matematica() { }

    public static int sumar(int a, int b) {
        return a + b;
    }
}
```

## Comandos

```bash
cd java/06-advanced-oop/47-static
java StaticLesson.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=HTFcs3xJVrQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=47), vídeo 47.
