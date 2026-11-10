# Métodos con parámetros en Java

## Qué es un parámetro

Un **dato de entrada** que un método recibe cuando se le llama. Gracias a los parámetros, el mismo método sirve para **valores distintos** sin tocar su código.

| Término | Qué es | Dónde está |
|---|---|---|
| **Parámetro** | La variable que el método declara para recibir un dato | En la **definición**: `saludar(String nombre)` |
| **Argumento** | El valor concreto que se envía | En la **llamada**: `saludar("Sol")` |

## El problema que resuelven

Con un atributo de la clase, un método siempre usa el mismo valor:

```java
class Prueba {
    public String nombre = "Sol";

    public void saludoConAtributo() {
        System.out.println("Hola, " + nombre);   // siempre "Sol"
    }
}
```

Funciona, pero para saludar a otra persona habría que cambiar el atributo. Con un parámetro, cada llamada decide el valor.

## Definir un parámetro

Entre los paréntesis del método, con **tipo y nombre**:

```java
public void saludar(String nombre) {
    System.out.println("Hola, " + nombre);
}
```

Y se llama pasando un **argumento**:

```java
objeto1.saludar("Sol");       // Hola, Sol
objeto1.saludar("Marcos");    // Hola, Marcos
```

El parámetro `nombre` se comporta como una variable que **vale el argumento** durante esa llamada.

## Varios parámetros

Se separan con comas, cada uno con su tipo:

```java
public void presentar(String nombre, int edad) {
    System.out.println("Nombre: " + nombre + ", edad: " + edad);
}

objeto1.presentar("Sol", 34);    // Nombre: Sol, edad: 34
```

Pueden mezclarse tipos y no hay límite en la cantidad.

## Reglas de la llamada

Al llamar al método hay que respetar su definición:

- **La misma cantidad** de argumentos que de parámetros.
- **El mismo orden**.
- **Tipos compatibles** con los de los parámetros.

```java
objeto1.presentar("Sol");         // error: falta la edad
objeto1.presentar(34, "Sol");     // error: el orden y los tipos importan
```

El compilador lo detecta antes de ejecutar.

## Parámetros y retorno

Un método puede recibir datos **y** devolver un resultado:

```java
public int sumar(int a, int b) {
    return a + b;
}

public boolean esMayorDeEdad(int edad) {
    return edad >= 18;
}
```

```java
int resultado = objeto1.sumar(34, 22);                  // 56
System.out.println(objeto1.sumar(resultado, 4));        // 60
System.out.println(objeto1.esMayorDeEdad(15));          // false
```

- `return a + b;` calcula y devuelve en un paso.
- El argumento puede ser un **literal** (`34`), una **variable** (`resultado`) o una **expresión**.
- Para ver el resultado hay que **guardarlo** o mostrarlo: llamar a `sumar(34, 22)` sin hacer nada con el valor lo pierde.

## Alcance: dónde existe cada variable

| Variable | Dónde existe |
|---|---|
| **Atributo** (declarado en la clase) | En **todos** los métodos de la clase |
| **Parámetro** | Solo **dentro de su método** |
| **Variable local** (declarada en un método) | Solo dentro de ese método |

> El vídeo llama "variable global" al atributo. En rigor no es global del programa: pertenece a la clase.

```java
public void saludar(String nombre) { /* nombre existe aquí */ }

public void despedir() {
    System.out.println(nombre);   // error: nombre es un parámetro de saludar, no existe aquí
}
```

Dos métodos pueden tener un parámetro con el **mismo nombre** sin que se mezclen.

## Java no tiene parámetros opcionales: sobrecarga

C# y JavaScript permiten un valor por defecto en un parámetro. Java **no**. En su lugar se escriben **dos métodos con el mismo nombre y distintos parámetros**. Se llama **sobrecarga**:

```java
public void despedir(String nombre) {
    despedir(nombre, "Adiós");           // reutiliza la otra versión
}

public void despedir(String nombre, String mensaje) {
    System.out.println(mensaje + ", " + nombre);
}
```

```java
objeto1.despedir("Sol");                    // Adiós, Sol
objeto1.despedir("Sol", "Hasta pronto");    // Hasta pronto, Sol
```

Java elige la versión según **cuántos argumentos y de qué tipo** se pasen. (Tampoco existen los argumentos con nombre.)

## Argumentos que escribe el usuario

Los argumentos pueden venir de la entrada de datos:

```java
Scanner scanner = new Scanner(System.in);

System.out.println("Ingrese su nombre...");
String nombreUsuario = scanner.nextLine();

System.out.println("Ingrese su edad...");
int edadUsuario = Integer.parseInt(scanner.nextLine());

objeto1.presentar(nombreUsuario, edadUsuario);
```

Las variables `nombreUsuario` y `edadUsuario` son del método `main`; se **pasan** al otro método como argumentos. Los nombres no tienen por qué coincidir con los de los parámetros.

## Ejemplo

```java
public class MethodsWithParameters {
    public static void main(String[] args) {
        Prueba objeto1 = new Prueba();

        objeto1.presentar("Sol", 34);
        System.out.println("Suma: " + objeto1.sumar(34, 22));
    }
}

class Prueba {
    public int sumar(int a, int b) {
        return a + b;
    }

    public void presentar(String nombre, int edad) {
        System.out.println("Nombre: " + nombre + ", edad: " + edad);
    }
}
```

## Comandos

```bash
cd java/05-methods-and-classes/28-methods-with-parameters
java MethodsWithParameters.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=6PJSvxFLt9k&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=28), vídeo 28.
