// En JavaScript las clases también son plantillas para crear objetos,
// pero no hace falta ningún método principal: el archivo se ejecuta de arriba abajo.


// ===== 1. Definir una clase =====

// Una clase es una plantilla (un molde) para crear objetos.
// Sus atributos (campos) son variables que pertenecen a la clase: describen cómo es cada objeto.
// No se declara el tipo de cada atributo.

class Persona {
    nombre = "Sol";
    edad = 34;
    direccion = "Calle Mayor 12";
    salario = 2500;
    activo = true;
}

// Otra clase, con atributos sin valor inicial
class Venta {
    vendedor = "";
    cantidades;   // sin valor: undefined
}


// ===== 2. Instanciar: crear un objeto de la clase =====

// const nombreDelObjeto = new Clase();
const persona1 = new Persona();


// ===== 3. Leer los atributos: objeto.atributo =====

console.log(persona1.nombre);   // Sol

console.log(`Nombre: ${persona1.nombre}`);
console.log(`Edad: ${persona1.edad}`);
console.log(`Dirección: ${persona1.direccion}`);
console.log(`Salario: ${persona1.salario}`);
console.log(`Activo: ${persona1.activo}`);


// ===== 4. Cambiar los atributos de un objeto =====

// const impide reasignar la variable persona1, pero sus atributos sí se pueden cambiar
persona1.edad = 35;
console.log(`Edad tras el cambio: ${persona1.edad}`);   // 35


// ===== 5. Varios objetos de la misma clase =====

// Cada objeto tiene sus propios valores
const persona2 = new Persona();
persona2.nombre = "Marcos";
persona2.edad = 28;

console.log(`${persona1.nombre} tiene ${persona1.edad} años`);     // Sol tiene 35 años
console.log(`${persona2.nombre} tiene ${persona2.edad} años`);     // Marcos tiene 28 años


// ===== 6. Otra clase =====

const venta1 = new Venta();
console.log(`Cantidades por defecto: ${venta1.cantidades}`);   // undefined (en C# y Java sería 0)

venta1.vendedor = "Larry";
venta1.cantidades = 500;
console.log(`Vendedor: ${venta1.vendedor}, cantidades: ${venta1.cantidades}`);
