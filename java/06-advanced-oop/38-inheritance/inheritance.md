# Herencia en Java

## Qué es la herencia

Una forma de programar en la que **una clase deriva de otra y reutiliza sus miembros**: atributos y métodos.

| Término | Significa | Ejemplo |
|---|---|---|
| **Clase padre** (o superclase) | La clase de la que se hereda | `Animal` |
| **Clase hija** (o subclase) | La clase que hereda | `Perro`, `Gato` |

Una `Animal` sabe **comer** y **dormir**. Un `Perro` y un `Gato` también son animales, así que **ya saben comer y dormir** sin escribirlo otra vez, y además cada uno añade lo suyo (ladrar, maullar).

```text
          Animal
       (nombre, comer, dormir)
        /              \
     Perro            Gato
   (+ ladrar)      (+ maullar)
```

## Para qué sirve

- **Reutilizar código**: lo común se escribe **una sola vez**, en el padre.
- **Evitar repetir** lo mismo en varias clases.
- **Extensibilidad**: se pueden añadir clases hijas o nuevas funciones sin tocar la clase base.
- **Polimorfismo**: tratar objetos distintos como uno común (lección 48).

## Reglas en Java

1. Una clase puede heredar de **una sola clase** (herencia simple). Lo que sí puede es implementar varias interfaces.
2. **Todas las clases derivan de `Object`**. Se hace solo, de forma implícita: por eso cualquier clase tiene métodos como `toString()` o `equals()`, aunque no los haya escrito. `String`, `Math`, `ArrayList`... también.
3. La clase padre debe ser **accesible** para que otra la herede.

## La clase padre

```java
class Animal {
    private String nombre;

    public Animal(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void comer() {
        System.out.println(nombre + " está comiendo");
    }

    public void dormir() {
        System.out.println(nombre + " está durmiendo");
    }
}
```

Es una clase normal: no necesita nada especial para ser un padre. El atributo es `private` con *getter* y *setter* (lección 37).

## La clase hija: `extends`

```java
class Perro extends Animal {

    public Perro(String nombre) {
        super(nombre);
    }

    public void ladrar() {
        System.out.println(getNombre() + " está ladrando");
    }
}

class Gato extends Animal {

    public Gato(String nombre) {
        super(nombre);
    }

    public void maullar() {
        System.out.println(getNombre() + " está maullando");
    }
}
```

`Perro` **recibe** `comer()`, `dormir()`, `getNombre()` y `setNombre()` de `Animal`, y **añade** `ladrar()`.

### El constructor: `super(...)`

**Los constructores no se heredan.** Para que el padre inicialice sus datos, la hija llama a su constructor con **`super(...)`**:

```java
public Perro(String nombre) {
    super(nombre);
}
```

- Los argumentos van **al constructor del padre**.
- `super(...)` debe ser **la primera instrucción** del constructor de la hija.
- Si el padre tiene un constructor **sin parámetros**, `super()` se llama solo y no hace falta escribirlo.

## Usar las clases

```java
Perro miPerro = new Perro("Snoopy");

miPerro.dormir();     // Snoopy está durmiendo   (heredado)
miPerro.comer();      // Snoopy está comiendo    (heredado)
miPerro.ladrar();     // Snoopy está ladrando    (propio)

miPerro.setNombre("Rex");    // el método heredado también se puede usar
```

La **herencia solo va hacia abajo**: lo propio de una hija no existe en la otra.

```java
Gato miGato = new Gato("Michi");
miGato.maullar();     // correcto
miGato.ladrar();      // error: Gato no tiene ladrar
```

## Relación "es un"

La herencia modela una relación **"es un"**: un perro *es un* animal. Por eso un objeto de la hija se puede guardar en una variable del tipo padre:

```java
Animal animal = miPerro;
animal.comer();                                          // Rex está comiendo
System.out.println(miPerro instanceof Animal);           // true
System.out.println(animal instanceof Gato);              // false
```

Pero a través de la variable `Animal` **solo se ven los miembros de `Animal`**: `animal.ladrar()` no compila, aunque el objeto sea un `Perro`. `instanceof` comprueba de qué clase es realmente el objeto.

> Si la relación es "**tiene un**" (una orden *tiene un* cliente, lección 32), no es herencia sino **colaboración**: se guarda como atributo. Conviene preguntarse "¿es un?" antes de heredar.

## La herencia y el encapsulamiento

Heredar **no salta** los niveles de acceso:

| El miembro del padre es... | ¿La hija lo usa directamente? |
|---|---|
| `public` | **Sí** |
| `private` | **No** (solo a través de miembros públicos) |
| `protected` | **Sí** (lección 39) |

Por eso `Perro` usa `getNombre()` en lugar de `nombre`: el atributo es `private` en `Animal`. La solución más fina es **`protected`**, que es justo lo que se verá a continuación.

## Impedir la herencia: `final`

Una clase marcada como `final` **no se puede heredar**:

```java
final class Perro extends Animal { }
```

La clase `String`, por ejemplo, es `final`.

## Ejemplo

```java
public class Inheritance {
    public static void main(String[] args) {
        Perro miPerro = new Perro("Snoopy");
        miPerro.comer();
        miPerro.ladrar();
    }
}

class Animal {
    private String nombre;

    public Animal(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void comer() {
        System.out.println(nombre + " está comiendo");
    }
}

class Perro extends Animal {
    public Perro(String nombre) {
        super(nombre);
    }

    public void ladrar() {
        System.out.println(getNombre() + " está ladrando");
    }
}
```

## Comandos

```bash
cd java/06-advanced-oop/38-inheritance
java Inheritance.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=WD8TZY8gcaU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=38), vídeo 38.
