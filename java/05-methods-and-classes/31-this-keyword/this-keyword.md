# La palabra clave `this` en Java

## Qué es

`this` es una **referencia al objeto actual**: el objeto sobre el que se está ejecutando el código de la clase. Dentro de un método o constructor, `this` significa "yo, este objeto".

```java
class Persona {
    public String nombre = "";
    public int edad = 0;
}

Persona persona1 = new Persona();   // cada objeto tiene su propio nombre y su propia edad
Persona persona2 = new Persona();
```

Cuando se ejecuta un método de `persona1`, `this` es `persona1`; cuando se ejecuta uno de `persona2`, `this` es `persona2`.

## Uso 1: distinguir atributo de parámetro

Es el motivo principal. Si un **parámetro** se llama igual que un **atributo**, dentro del método el nombre se refiere al **parámetro** (lo "oculta"). Para llegar al atributo hay que escribir `this.`:

```java
class Persona {
    public String nombre = "";
    public int edad = 0;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;    // atributo = parámetro
        this.edad = edad;
    }
}
```

```text
this.nombre = nombre;
└─ atributo    └─ parámetro
```

Al crear `new Persona("Bart", 10)`, los valores entran por los parámetros y `this.nombre = nombre` los **copia a los atributos** del objeto.

### Qué pasa sin `this`

Sin él, el parámetro se asigna **a sí mismo** y el atributo no cambia:

```java
public Persona(String nombre, int edad) {
    nombre = nombre;    // parámetro = parámetro: no hace nada
    edad = edad;
}
```

El objeto queda con `nombre = ""` y `edad = 0`: los valores por defecto. Java compila sin quejarse; el IDE suele avisar de una asignación a sí mismo. Es un error muy típico y difícil de ver.

### Si los nombres son distintos, no hace falta

```java
public Persona(String elNombre, int laEdad) {
    nombre = elNombre;     // sin ambigüedad: this es opcional
    edad = laEdad;
}
```

Con nombres diferentes no hay conflicto. Pero usar el **mismo nombre con `this`** es la convención habitual: el parámetro se llama `nombre` porque es el nombre de la persona. Los IDE como IntelliJ generan el constructor así.

## Uso 2: pasar o comparar el propio objeto

`this` es un valor: se puede comparar, pasar a otro método o devolver.

```java
public boolean esMayorQue(Persona otra) {
    return this.edad > otra.edad;    // this: quien recibe la llamada; otra: el argumento
}
```

```java
Persona persona1 = new Persona("Bart", 10);
Persona persona2 = new Persona("Lisa", 8);

System.out.println(persona1.esMayorQue(persona2));   // true: this = persona1
System.out.println(persona2.esMayorQue(persona1));   // false: this = persona2
```

El mismo método da resultados distintos según **sobre qué objeto** se llama.

## Uso 3: devolver `this` para encadenar

Si un método devuelve `this`, se pueden **encadenar llamadas** sobre el mismo objeto:

```java
public Persona cumplirAnios() {
    this.edad++;
    return this;
}

persona1.cumplirAnios().cumplirAnios();    // la edad sube 2
```

Es el mismo patrón que ya se usó con los métodos de `String` (`texto.trim().toUpperCase()`).

## Uso 4: encadenar constructores: `this(...)`

En la lección de constructores, el constructor sin parámetros **repetía** el código del otro. Con `this(...)` un constructor llama a otro de la misma clase:

```java
public Persona(String nombre, int edad) {
    this.nombre = nombre;
    this.edad = edad;
}

public Persona() {
    this("Sol", 34);    // reutiliza el de arriba
}
```

- La llamada `this(...)` debe ser **la primera instrucción** del constructor.
- `new Persona()` ejecuta primero el constructor de dos parámetros con `"Sol"` y `34`. Si hay que cambiar cómo se inicializa un objeto, se cambia en un solo sitio.

## Dónde no se puede usar

`this` solo existe **dentro de un objeto**. En un método `static` (como `main`) no hay objeto actual, así que no se puede usar:

```java
public static void main(String[] args) {
    System.out.println(this.nombre);   // error: this no existe en un contexto static
}
```

## Resumen

| Uso | Ejemplo |
|---|---|
| Atributo con el mismo nombre que un parámetro | `this.nombre = nombre;` |
| Referirse al propio objeto | `return this.edad > otra.edad;` |
| Encadenar llamadas | `return this;` |
| Encadenar constructores | `this("Sol", 34);` |

## Ejemplo

```java
public class ThisKeyword {
    public static void main(String[] args) {
        Persona persona1 = new Persona("Bart", 10);
        persona1.mostrar();
    }
}

class Persona {
    public String nombre = "";
    public int edad = 0;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void mostrar() {
        System.out.println("Nombre: " + this.nombre + ", edad: " + this.edad);
    }
}
```

## Comandos

```bash
cd java/05-methods-and-classes/31-this-keyword
java ThisKeyword.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=jyUaUF79uWc&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=31), vídeo 31.
