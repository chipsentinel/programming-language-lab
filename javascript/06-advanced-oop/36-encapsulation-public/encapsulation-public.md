# Encapsulamiento y acceso público en JavaScript

## Qué es el encapsulamiento

El proceso de **ocultar los detalles internos de una clase** a quienes la usan, y de **controlar qué partes se pueden ver y modificar**. Es uno de los pilares de la POO (lección 35).

Para qué sirve:

- **Proteger los datos** y evitar cambios no autorizados.
- **Controlar el acceso**: solo se permite lo necesario.
- Un diseño **más limpio, seguro y fácil de mantener**: el exterior solo conoce lo que la clase decide mostrar.

## JavaScript no tiene modificadores de acceso

En C# y Java existen `public`, `private`, `protected`... En JavaScript **no hay palabras clave de acceso**. Solo hay dos situaciones:

| Situación | Cómo se escribe | Quién accede |
|---|---|---|
| **Público** | Sin nada: `marca;` | Cualquier parte del programa |
| **Privado** | Con `#` delante: `#saldo;` | Solo la propia clase (lección 37) |

Todo lo que se declara de forma normal es **público por defecto**. No existen `protected`, `internal` ni los niveles intermedios.

## Miembros públicos

```javascript
class Camion {
    marca;
    modelo;
    color;
    cargaMaxima;

    constructor(marca, modelo, color, cargaMaxima) { /* ... */ }

    datosCamion(velocidad, acelera) { /* ... */ }
}
```

Y se usa desde **otra parte** del programa:

```javascript
const camion1 = new Camion("Mack", "MK12", "rojo", 18000);
camion1.datosCamion(230, 40);
```

No hace falta escribir nada especial: atributos, métodos y el constructor ya son accesibles desde fuera.

### Clases entre archivos

Para usar una clase en **otro archivo** hay que exportarla e importarla (módulos). Se verá más adelante; aquí cada lección es un archivo.

## Leer y cambiar un atributo público

```javascript
camion1.color = "azul";
console.log(camion1.color);    // azul
```

Con un atributo público cualquier parte del programa puede **leerlo y modificarlo** directamente.

## El inconveniente de lo público

Ese acceso directo no tiene ningún control:

```javascript
camion1.cargaMaxima = -500;    // nadie lo impide
```

Una carga máxima negativa no tiene sentido, pero JavaScript **no puede evitarlo**: la clase no se entera de que alguien cambió el dato.

### Y se pueden inventar atributos

En JavaScript ni siquiera hay que declarar un atributo para usarlo:

```javascript
camion1.ruedas = 6;    // se crea un atributo nuevo, sin error
```

Una errata (`camion1.colr = "azul"`) tampoco da error: crea un atributo `colr` en lugar de cambiar `color`. En C# y Java el compilador lo rechazaría.

Por eso conviene **proteger** los datos importantes. La solución es **ocultar los atributos con `#`** y dar acceso controlado mediante métodos. Se verá en la lección siguiente.

## Convención del guion bajo

Antes de que existiera `#`, se marcaba por **convención** un atributo "que no se debe tocar" con un guion bajo:

```javascript
class Camion {
    _cargaMaxima = 18000;    // "privado" solo por costumbre: sigue siendo accesible
}
```

Es solo una **señal para otros programadores**: JavaScript no lo protege. Hoy se prefiere `#`, que sí es privado de verdad.

## Qué conviene hacer público

| Elemento | Normalmente |
|---|---|
| Métodos que forman la "cara visible" de la clase | Públicos |
| **Atributos** que no se deben modificar libremente | Privados con `#` (lección 37) |

La regla general: **mostrar lo mínimo necesario**.

## Ejemplo

```javascript
class Camion {
    marca;

    constructor(marca) {
        this.marca = marca;
    }

    mostrar() {
        console.log("Marca: " + this.marca);
    }
}

const camion1 = new Camion("Mack");
camion1.mostrar();
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 36-encapsulation-public/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=vem7AznM0Gk&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=36), vídeo 36.
