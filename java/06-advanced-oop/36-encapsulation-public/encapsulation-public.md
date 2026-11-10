# Encapsulamiento y `public` en Java

## Qué es el encapsulamiento

El proceso de **ocultar los detalles internos de una clase** a quienes la usan, y de **controlar qué partes se pueden ver y modificar**. Es uno de los pilares de la POO (lección 35).

Con él se puede **restringir el acceso** a:

- los **atributos** de una clase,
- sus **métodos**,
- e incluso la **clase entera**.

Para qué sirve:

- **Proteger los datos** y evitar cambios no autorizados.
- **Controlar el acceso**: solo se permite lo necesario.
- Un diseño **más limpio, seguro y fácil de mantener**: el exterior solo conoce lo que la clase decide mostrar.

Se aplica con los **modificadores de acceso**.

## Modificadores de acceso

| Modificador | Misma clase | Mismo paquete | Subclase (otro paquete) | Cualquier sitio |
|---|---|---|---|---|
| `public` | Sí | Sí | Sí | **Sí** |
| `protected` | Sí | Sí | Sí | No |
| *(ninguno)* | Sí | Sí | No | No |
| `private` | Sí | No | No | No |

- Un **paquete** agrupa clases relacionadas (`package lab.introduction;`). Las lecciones de este proyecto no usan paquetes, así que todas las clases están en el mismo.
- **Sin modificador** se llama acceso **de paquete** (*package-private*): es el valor por defecto, y solo lo ven las clases del mismo paquete.
- Las **subclases** se verán con la herencia (lección 38).
- Hoy se trabaja con `public`; `private`, en la lección 37; `protected`, en la 39.

## `public`

Un miembro `public` es **accesible desde cualquier parte** del programa: desde su propia clase y desde cualquier otra.

```java
class Camion {
    public String marca;
    public String modelo;
    public String color;

    public Camion(String marca, String modelo, String color, int cargaMaxima) { /* ... */ }

    public void datosCamion(int velocidad, int acelera) { /* ... */ }
}
```

Y se usa desde **otra clase** distinta:

```java
public class EncapsulationPublic {
    public static void main(String[] args) {
        Camion camion1 = new Camion("Mack", "MK12", "rojo", 18000);
        camion1.datosCamion(230, 40);
    }
}
```

`EncapsulationPublic` y `Camion` son clases distintas. La primera puede crear un `Camion` y usar sus miembros porque son `public`.

### Una clase `public` y su archivo

En Java, una clase **`public` debe estar en un archivo con su mismo nombre**: `Camion.java`. En un proyecto real cada clase pública tiene su propio archivo. Aquí se agrupan varias clases en uno para ejecutar la lección con un solo comando, así que solo la clase con `main` es `public` y `Camion` tiene acceso de paquete.

### Qué pasa con `private` o de paquete

Con `private`, **otra clase no ve el miembro**:

```java
class Camion {
    private String marca;
}

camion1.marca = "Volvo";     // error: marca has private access in Camion
```

Con acceso de paquete, dentro del **mismo paquete** sí se ve; desde otro paquete, no. En esta lección todas las clases comparten paquete, así que la diferencia solo se notaría en un proyecto con varios paquetes.

### También el constructor

Si el **constructor** es `private`, otras clases **no pueden crear objetos** de la clase (es el truco de los *singleton*).

## Leer y cambiar un atributo público

```java
camion1.color = "azul";
System.out.println(camion1.color);    // azul
```

Con un atributo `public` cualquier clase puede **leerlo y modificarlo** directamente.

## El inconveniente de `public`

Ese acceso directo no tiene ningún control:

```java
camion1.cargaMaxima = -500;    // nadie lo impide
```

Una carga máxima negativa no tiene sentido, pero `public` **no puede evitarlo**: la clase no se entera de que alguien cambió el dato.

Por eso, no conviene dejar los **atributos** como `public`. La solución es **ocultarlos con `private`** y dar un acceso controlado mediante *getters* y *setters*, que permiten validar. Se verá en la lección siguiente.

## Qué conviene hacer público

| Elemento | Normalmente |
|---|---|
| Clases que usan otros | `public` |
| Constructores | `public` |
| Métodos que forman la "cara visible" de la clase | `public` |
| **Atributos** | `private` (lección 37) |

La regla general: **mostrar lo mínimo necesario**.

## Ejemplo

```java
public class EncapsulationPublic {
    public static void main(String[] args) {
        Camion camion1 = new Camion("Mack");
        camion1.mostrar();
    }
}

class Camion {
    public String marca;

    public Camion(String marca) {
        this.marca = marca;
    }

    public void mostrar() {
        System.out.println("Marca: " + marca);
    }
}
```

## Comandos

```bash
cd java/06-advanced-oop/36-encapsulation-public
java EncapsulationPublic.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=vem7AznM0Gk&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=36), vídeo 36.
