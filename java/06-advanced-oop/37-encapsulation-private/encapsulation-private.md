# Encapsulamiento `private` en Java

## Qué es `private`

El nivel de acceso **más restrictivo**: un miembro `private` **solo se puede usar dentro de la clase donde se declara**. Ninguna otra clase puede acceder a él directamente, ni siquiera una subclase.

| | `public` | `private` |
|---|---|---|
| Desde la propia clase | Sí | Sí |
| Desde otra clase | **Sí** | **No** |
| Uso habitual | Métodos que forman la "cara visible" | **Atributos** y métodos internos |

Es la forma más común de **proteger por completo los datos internos** de una clase.

## Atributos `private`

```java
class Camion {
    private String marca;
    private String modelo;
    private String color;
    private int cargaMaxima;

    public Camion(String marca, String modelo, String color, int cargaMaxima) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.cargaMaxima = cargaMaxima;
    }
}
```

Desde otra clase ya no se pueden leer ni cambiar:

```java
Camion camion1 = new Camion("Mack", "MK12", "rojo", 18000);

System.out.println(camion1.marca);      // error: marca has private access in Camion
camion1.cargaMaxima = -500;             // error
```

Dentro de **su propia clase** sí se usan con normalidad: el constructor y los métodos de `Camion` pueden acceder a todos sus atributos privados.

> Esto resuelve el problema de la lección anterior: con `public`, cualquiera podía dejar `cargaMaxima = -500`.

## Acceso controlado: *getters* y *setters*

Si los atributos son privados, ¿cómo se leen o se cambian desde fuera? A través de **métodos públicos** que la clase decide ofrecer:

```java
// Getter: permite LEER el atributo
public String obtenerMarca() {
    return marca;
}

// Setter: permite MODIFICARLO, pero validando
public void cambiarCargaMaxima(int nuevaCarga) {
    if (nuevaCarga <= 0) {
        System.out.println("Carga inválida: debe ser mayor que cero");
        return;
    }

    cargaMaxima = nuevaCarga;
}
```

```java
System.out.println(camion1.obtenerMarca());   // Mack
camion1.cambiarCargaMaxima(-500);             // rechazado
camion1.cambiarCargaMaxima(20000);            // aceptado
```

Esta es la gran ventaja: **la clase controla qué valores acepta**. Y se puede decidir qué se permite:

| Se quiere... | Se ofrece... |
|---|---|
| Solo lectura | Solo el *getter* |
| Lectura y escritura | *Getter* y *setter* |
| Escritura validada | *Setter* con comprobaciones |
| Ninguno de los dos | Nada: dato interno |

## La convención de Java: `getX` y `setX`

En Java los métodos de acceso siguen una **convención muy extendida**: `get` + nombre del atributo para leer, y `set` + nombre para modificar.

```java
class Furgoneta {
    private int carga = 500;

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        if (carga <= 0) {
            System.out.println("Carga inválida: debe ser mayor que cero");
            return;
        }

        this.carga = carga;
    }
}
```

```java
Furgoneta furgoneta1 = new Furgoneta();
furgoneta1.setCarga(-5);        // rechazado
furgoneta1.setCarga(1000);      // aceptado
System.out.println(furgoneta1.getCarga());    // 1000
```

- Los booleanos usan `is` (`isActivo()`).
- Los IDE como IntelliJ los **generan automáticamente** (menú *Generate → Getter and Setter*).
- Muchas librerías (frameworks, JSON...) **dependen** de esta convención.
- Java no tiene propiedades como C#; los *getters* y *setters* son métodos normales.

## Métodos `private`

Un método también puede ser privado: un **ayudante interno** que solo usan otros métodos de la misma clase.

```java
private String descripcion() {
    return marca + " " + modelo + " (" + color + ")";
}

public void mostrar() {
    System.out.println(descripcion() + ": carga máxima " + cargaMaxima + " kg");
}
```

Desde fuera solo se ve `mostrar()`. `descripcion()` es un detalle de implementación que se puede cambiar sin afectar a nadie.

```java
camion1.descripcion();    // error: es private
```

Si un método `public` usa atributos `private` de otra clase, tampoco funciona: el acceso depende de **dónde está escrito el código**, no de quién lo llama.

## Constructor `private`

Si el constructor es `private`, **nadie fuera de la clase puede crear objetos** con `new`:

```java
class Utilidades {
    private Utilidades() { }
}

Utilidades u = new Utilidades();    // error: Utilidades() has private access
```

Se usa en clases que no deben instanciarse desde fuera, como las que ofrecen un único objeto (*singleton*) o solo métodos estáticos (lección 47).

## Resumen

| Elemento | Recomendación |
|---|---|
| **Atributos** | `private` |
| Acceso a los atributos | *Getters* y *setters* |
| Métodos internos de apoyo | `private` |
| Métodos que otras clases deben usar | `public` |

## Ejemplo

```java
public class EncapsulationPrivate {
    public static void main(String[] args) {
        Cuenta cuenta1 = new Cuenta();
        cuenta1.depositar(100);
        System.out.println(cuenta1.getSaldo());    // 100.0
    }
}

class Cuenta {
    private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }
}
```

## Comandos

```bash
cd java/06-advanced-oop/37-encapsulation-private
java EncapsulationPrivate.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=sWpp73OUmCQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=37), vídeo 37.
