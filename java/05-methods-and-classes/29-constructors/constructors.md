# Constructores en Java

## Qué es un constructor

Un **método especial que se ejecuta automáticamente al crear un objeto** (`new`). Su función principal es **dar valores iniciales** a los atributos y preparar el objeto para usarse.

Hasta ahora, al escribir `new Persona()`, ya se estaba llamando a un constructor: el que Java crea **por defecto**.

## Constructor por defecto

Si una clase **no define ninguno**, Java crea uno vacío, sin parámetros y sin instrucciones. Por eso esto funciona sin escribir nada más:

```java
class Contador {
    public int valor = 10;
}

Contador contador1 = new Contador();    // llama al constructor por defecto
```

Los atributos con valor inicial (`valor = 10`) se asignan al crear el objeto, antes del código del constructor.

## Definir un constructor propio

```java
class Persona {
    public String nombre = "";
    public int edad;

    public Persona() {
        nombre = "Sol";
        edad = 34;
        System.out.println("Creando persona: " + nombre + ", " + edad);
    }
}
```

Al escribir `new Persona()` el mensaje aparece **en ese momento**:

```java
Persona persona1 = new Persona();    // Creando persona: Sol, 34
```

## Reglas

1. **Se llama igual que la clase**: `Persona` dentro de `class Persona`.
2. **No tiene tipo de retorno**, ni siquiera `void`.
3. **Se ejecuta una sola vez por objeto**, en el `new`.

```java
public Persona() { }          // constructor
public void Persona() { }     // compila, pero es un MÉTODO normal, no un constructor
```

- En Java, escribir `void` es un **error muy frecuente** que no da aviso: el código compila, pero ese método no se ejecuta al crear el objeto. En C# sí da error.
- **`public`** no es obligatorio, pero se escribe casi siempre: sin él, el constructor solo es visible dentro de su paquete.
- **No se puede llamar de nuevo**: `persona1.Persona()` no compila. Solo se ejecuta con `new`, una vez por cada objeto creado.

## Constructor con parámetros

Para que cada objeto empiece con **sus propios valores**, el constructor puede recibir parámetros, como cualquier método:

```java
public Persona(String elNombre, int laEdad) {
    nombre = elNombre;
    edad = laEdad;
    System.out.println("Creando persona: " + nombre + ", " + edad);
}
```

```java
Persona persona2 = new Persona("Marcos", 28);    // Creando persona: Marcos, 28
```

- Los **argumentos** del `new` van a los **parámetros** del constructor.
- Los parámetros se copian a los **atributos** (`nombre = elNombre`): el parámetro desaparece al terminar el constructor, el atributo se queda en el objeto.
- Aquí los parámetros se llaman `elNombre` y `laEdad` para no confundirlos con los atributos. En la lección sobre `this` se verá cómo usar el mismo nombre.

Como con cualquier método con parámetros, el `new` **debe** pasar los argumentos que pide: `new Persona("Marcos")` no compila.

## Varios constructores: sobrecarga

Una clase puede tener **varios constructores** si sus parámetros son distintos. `new` elige el que coincide con los argumentos:

```java
Persona persona1 = new Persona();                // el de sin parámetros
Persona persona2 = new Persona("Marcos", 28);    // el de dos parámetros
```

### Cuidado: el constructor vacío desaparece

Java crea el constructor por defecto **solo si la clase no define ninguno**. Si se escribe uno con parámetros, el vacío **ya no existe**:

```java
class Persona {
    public Persona(String nombre, int edad) { }
}

Persona p = new Persona();    // error: no hay un constructor sin argumentos
```

Si se necesitan los dos, hay que escribir ambos.

## Un constructor no es el sitio para otros métodos

Se pueden escribir instrucciones dentro del constructor (asignar, mostrar...), pero los **métodos van en la clase**, no dentro del constructor: Java ni siquiera permite declararlos dentro de otro método.

## Para qué sirve

- **Garantizar** que el objeto nace con valores válidos.
- **Evitar** asignar atributo por atributo después del `new`.
- **Preparar** lo que el objeto necesite al empezar (abrir una conexión, inicializar una lista...).

## Ejemplo

```java
public class Constructors {
    public static void main(String[] args) {
        Persona persona1 = new Persona("Sol", 34);
        Persona persona2 = new Persona("Marcos", 28);

        System.out.println(persona1.nombre + ": " + persona1.edad);
        System.out.println(persona2.nombre + ": " + persona2.edad);
    }
}

class Persona {
    public String nombre = "";
    public int edad;

    public Persona(String elNombre, int laEdad) {
        nombre = elNombre;
        edad = laEdad;
    }
}
```

## Comandos

```bash
cd java/05-methods-and-classes/29-constructors
java Constructors.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=AIESIKJzq5g&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=29), vídeo 29.
