# Clases abstractas en Java

## Qué es una clase abstracta

Una clase que **no se puede instanciar**: no se pueden crear objetos directamente de ella. Su propósito es **servir de base** para otras clases (herencia, lección 38): define **lo que tienen en común** y **obliga a las clases hijas** a completar ciertas partes.

Es como una **plantilla incompleta**: dice "todo animal puede hacer un sonido", pero no dice cuál. Cada animal concreto lo decide.

Se parece a una **interfaz** (que se verá más adelante), pero con más flexibilidad: una clase abstracta puede tener **atributos y código**, no solo la lista de lo que hay que implementar.

## Declararla: `abstract`

```java
abstract class Animal {
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

    public abstract void hacerSonido();
}
```

Y no se puede crear un objeto de ella:

```java
Animal animal = new Animal("Genérico");    // error: Animal is abstract; cannot be instantiated
```

## Qué puede contener

| Miembro | ¿Permitido? |
|---|---|
| Atributos **normales** | Sí |
| Constructores | Sí (los usan las hijas con `super(...)`) |
| Métodos **con código** | Sí |
| Métodos **abstractos** | Sí (**solo** en clases abstractas) |

No todos los miembros tienen que ser abstractos: `nombre` y `comer()` son normales.

## Métodos abstractos

```java
public abstract void hacerSonido();
```

- Llevan **`abstract`**.
- **No tienen cuerpo**: acaban en `;`, sin llaves. Poner llaves da error.
- **Solo pueden existir en una clase abstracta.**
- Indican **qué** debe hacer toda hija, no **cómo**.

## Implementarlos en las hijas

Una clase que hereda de una abstracta **está obligada a implementar todos sus métodos abstractos**. Si no, el compilador da error ("is not abstract and does not override abstract method"), salvo que la hija también sea abstracta.

```java
class Perro extends Animal {
    public Perro(String nombre) {
        super(nombre);
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + " está ladrando");
    }
}

class Gato extends Animal {
    public Gato(String nombre) {
        super(nombre);
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + " está maullando");
    }
}
```

- **`@Override`** es una **anotación**: indica "este método redefine uno del padre". Es opcional, pero **muy recomendable**: si el nombre o los parámetros no coinciden con el original, el compilador avisa en lugar de crear un método distinto sin querer.
- Misma firma que la original: mismo nombre, parámetros y tipo de retorno.
- Cada hija da **su propia versión**: ahí está la utilidad.

```java
Perro miPerro = new Perro("Snoopy");

miPerro.comer();         // Snoopy está comiendo   (heredado de Animal)
miPerro.hacerSonido();   // Snoopy está ladrando   (implementado en Perro)
```

## Redefinir métodos con código

A diferencia de C#, en Java **todos los métodos se pueden redefinir** por defecto, sin una palabra clave `virtual`:

```java
public void dormir() {
    System.out.println(nombre + " está durmiendo");     // por defecto
}
```

`Gato` puede redefinirlo (`@Override public void dormir() { ... }`); `Perro` lo deja como está. Para **impedir** que se redefina se marca el método como **`final`**.

| | `abstract` | Método normal |
|---|---|---|
| ¿Tiene código? | **No** | **Sí** |
| ¿Las hijas deben redefinirlo? | **Sí, obligatorio** | No, **opcional** |
| ¿Dónde puede estar? | Solo en clases abstractas | En cualquier clase |

## Polimorfismo

El cuarto pilar de la POO (lección 35): **objetos de clases distintas se tratan como uno común**.

```java
List<Animal> animales = List.of(
        new Perro("Snoopy"),
        new Gato("Garfield"),
        new Vaca("Lola"));

for (Animal animal : animales) {
    animal.hacerSonido();
}
```

```text
Snoopy está ladrando
Garfield está maullando
Lola está mugiendo
```

- La lista guarda **`Animal`**, pero cada elemento es **un `Perro`, un `Gato`, una `Vaca`**.
- Al llamar a `hacerSonido()`, Java ejecuta la versión de **la clase real** del objeto, no la del tipo de la variable.
- Quien recorre la lista **no necesita saber** qué animal es cada uno.
- Añadir una clase nueva (`Pato`) **no obliga a cambiar** el código que usa la lista.

Esta es la razón de ser de las clases abstractas: **escribir código para el concepto general** (`Animal`) que funciona con **cualquier clase concreta**.

## Cuándo usar una clase abstracta

| Usar | Cuándo |
|---|---|
| **Clase abstracta** | Hay un **concepto general** que no tiene sentido como objeto concreto (un `Animal`, una `Figura`, un `Empleado`) y las hijas comparten código |
| **Clase normal** | El concepto se puede instanciar tal cual |
| **Interfaz** (más adelante) | Solo se quiere fijar **qué métodos** debe tener algo, sin compartir código, o una clase necesita varios "contratos" |

## Ejemplo

```java
public class AbstractClasses {
    public static void main(String[] args) {
        Figura figura = new Cuadrado(4);
        System.out.println("Área: " + figura.area());      // 16.0
    }
}

abstract class Figura {
    public abstract double area();
}

class Cuadrado extends Figura {
    private double lado;

    public Cuadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double area() {
        return lado * lado;
    }
}
```

## Comandos

```bash
cd java/06-advanced-oop/48-abstract-classes
java AbstractClasses.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=t7ZhwYmIIH4&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=48), vídeo 48.
