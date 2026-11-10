# Encapsulamiento `protected` en Java

## Qué es `protected`

Un nivel de acceso intermedio entre `public` y `private`: un miembro `protected` es accesible **dentro de su propia clase, en las clases que heredan de ella** y, en Java, **en las clases de su mismo paquete**. No lo ve el resto del programa.

| | Propia clase | Mismo paquete | Clase hija (otro paquete) | Resto |
|---|---|---|---|---|
| `public` | Sí | Sí | Sí | **Sí** |
| `protected` | Sí | **Sí** | **Sí** | No |
| *(ninguno)* | Sí | Sí | No | No |
| `private` | Sí | No | No | No |

Por eso hay que conocer **la herencia** (lección 38) antes de entender `protected`.

## Una diferencia con C#

En **C#** `protected` solo da acceso a la clase y a sus hijas. En **Java** además da acceso a **todas las clases del mismo paquete**, aunque no hereden. Es decir, en Java `protected` es "paquete **más** subclases", mientras que el nivel sin modificador es "solo paquete".

Como todas las clases de esta lección están en el mismo paquete, aquí `Main` **sí podría** llamar a un método `protected`. Desde otro paquete, no.

## Para qué sirve

Para miembros que son **detalles internos** del padre, que las hijas pueden reutilizar, pero que **no deben formar parte de lo que ve el exterior**.

## Un método `protected`

```java
class Animal {
    private String nombre;

    protected void respirar() {
        System.out.println(nombre + " está respirando");
    }

    public void dormir() {
        System.out.println(nombre + " está durmiendo");
        respirar();                       // la propia clase lo usa
    }
}
```

- **`respirar()`** es un detalle interno de cualquier animal.
- **`dormir()`**, público, **lo usa** internamente: la propia clase siempre ve sus miembros.

## Las hijas lo usan

```java
class Perro extends Animal {
    public Perro(String nombre) {
        super(nombre);
    }

    public void ladrar() {
        respirar();                                      // permitido: Perro hereda de Animal
        System.out.println(getNombre() + " está ladrando");
    }
}
```

`Perro` y `Gato` pueden llamar a `respirar()` porque heredan de `Animal`.

## Desde fuera del paquete no se ve

```java
// En una clase de OTRO paquete que no hereda de Animal:
Perro miPerro = new Perro("Rex");

miPerro.ladrar();       // correcto: es public
miPerro.respirar();     // error: respirar() has protected access in Animal
```

> **Una aclaración sobre el vídeo.** El vídeo (que es de C#) da a entender que `protected` impide el acceso "incluso dentro de la misma clase" y que las hijas no lo heredan. No es así: la propia clase y las hijas **sí** lo usan siempre; lo que queda excluido es el **código externo**. Es lo que muestra `respirar()` dentro de `dormir()` y de `ladrar()`.

## Atributos `protected`

```java
class Animal {
    protected int energia = 100;

    public void descansar() {
        energia = 100;
        System.out.println(getNombre() + " descansa. Energía: " + energia);
    }
}

class Perro extends Animal {
    public void correr() {
        energia -= 30;                      // permitido: la hija modifica el atributo heredado
        System.out.println(getNombre() + " corre. Energía: " + energia);
    }
}
```

Con `private`, `Perro` **no podría** tocar `energia` (tendría que usar métodos del padre). Con `protected`, sí.

En el ejemplo `nombre` sigue siendo `private`, y las hijas lo leen con `getNombre()`. Se podría haber hecho `protected` también.

## Riesgo de `protected` en atributos

Un atributo `protected` está **abierto a todas las hijas** (y, en Java, a todo el paquete). Si se modifica sin cuidado, se pueden romper reglas del padre. Por eso, para **atributos** suele preferirse `private` con *getters* y *setters*, y reservar `protected` para **métodos** pensados para ser reutilizados o **redefinidos** por las hijas (lección 48).

## Resumen de los niveles

```text
public     ──►  todo el programa
protected  ──►  la clase + sus hijas + su paquete
(ninguno)  ──►  la clase + su paquete
private    ──►  solo la clase
```

## Ejemplo

```java
public class EncapsulationProtected {
    public static void main(String[] args) {
        Perro miPerro = new Perro("Rex");
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

    protected void respirar() {
        System.out.println(nombre + " está respirando");
    }
}

class Perro extends Animal {
    public Perro(String nombre) {
        super(nombre);
    }

    public void ladrar() {
        respirar();
        System.out.println(getNombre() + " está ladrando");
    }
}
```

## Comandos

```bash
cd java/06-advanced-oop/39-encapsulation-protected
java EncapsulationProtected.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=TrVo7IxkH44&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=39), vídeo 39.
