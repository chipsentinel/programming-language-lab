# Introducción a las clases en Java

## Una idea que Java ya usaba

En Java **todo el código va dentro de clases**: desde la primera lección el programa era una clase con un método `main`. Ahora se entiende para qué sirven las clases y cómo crear las propias.

## Qué es una clase

Una **plantilla** (un molde) para crear objetos. Define **qué datos** tiene algo y, más adelante, **qué puede hacer**.

- Tipos como `String` o `Integer` son clases de Java. Una variable de ese tipo es un **objeto** de esa clase. (`int`, `double` y `boolean` son tipos primitivos, no clases.)
- Con `class` se pueden crear **clases propias**: `Persona`, `Empleado`, `Venta`...

Sirven para:

- **Agrupar datos relacionados**: nombre, edad y salario de una persona van juntos.
- **Organizar el código** de forma estructurada y reutilizable.

## Definir una clase y sus atributos

```java
class Persona {
    public String nombre = "Sol";
    public int edad = 34;
    public String direccion = "Calle Mayor 12";
    public double salario = 2500;
    public boolean activo = true;
}
```

- **`class Persona`**: el nombre de la clase, en `PascalCase` y en singular.
- **Las llaves `{ }`** delimitan el **cuerpo** de la clase.
- **Atributos** (o campos): variables declaradas dentro de la clase. Describen cómo es cada objeto de esa clase.
- **`public`** permite usarlos desde fuera de la clase. Se verá con el encapsulamiento.

Un atributo sin valor inicial recibe el **valor por defecto** de su tipo (`0`, `false`, `null`...):

```java
class Venta {
    public String vendedor = "";
    public int cantidades;    // vale 0
}
```

Una clase, por sí sola, **no hace nada**: es solo la plantilla.

## El método `main`

Todo programa empieza en **`main`**: es el primer método que se ejecuta.

```java
public class ClassesIntroduction {
    public static void main(String[] args) {
        // aquí empieza el programa
    }
}
```

`public`, `static`, `void` y `String[] args` se explicarán en la lección de métodos. Por ahora basta con memorizar esta estructura.

### Varias clases en un archivo

Con `java Archivo.java` se ejecuta **la primera clase** del archivo. Por eso la clase con `main` va arriba y `Persona` y `Venta` van después. (En proyectos grandes cada clase va en su propio archivo; aquí se agrupan para ejecutar una lección con un solo comando.)

## Instanciar: crear un objeto

Para usar una clase hay que **crear un objeto** de ella. Se llama **instanciar**:

```java
Persona persona1 = new Persona();
```

```text
Persona   persona1   =   new   Persona();
clase     objeto          instancia
```

- Se escribe primero la **clase** (el tipo) y después el **nombre del objeto**.
- **`new Persona()`** construye el objeto a partir de la plantilla. Los paréntesis se explicarán con los constructores.
- De una misma clase se pueden crear **tantos objetos como se quiera**.

## Acceder a los atributos: `objeto.atributo`

Con un punto entre el objeto y el atributo:

```java
System.out.println(persona1.nombre);                    // Sol
System.out.println("Edad: " + persona1.edad);           // Edad: 34
```

Al escribir el punto, el IDE lista todos los atributos disponibles. También se pueden **cambiar**:

```java
persona1.edad = 35;
```

## Cada objeto tiene sus propios valores

La clase es el molde; los objetos son las copias. Cambiar uno **no afecta** a los demás:

```java
Persona persona2 = new Persona();
persona2.nombre = "Marcos";
persona2.edad = 28;

System.out.println(persona1.nombre + " tiene " + persona1.edad + " años");   // Sol tiene 35 años
System.out.println(persona2.nombre + " tiene " + persona2.edad + " años");   // Marcos tiene 28 años
```

## Ejemplo

```java
public class ClassesIntroduction {
    public static void main(String[] args) {
        Persona persona1 = new Persona();

        System.out.println("Nombre: " + persona1.nombre);
        System.out.println("Edad: " + persona1.edad);
    }
}

class Persona {
    public String nombre = "Sol";
    public int edad = 34;
}
```

## Comandos

```bash
cd java/05-methods-and-classes/26-classes-introduction
java ClassesIntroduction.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=7Kn9S2Sy1Hc&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=26), vídeo 26.
