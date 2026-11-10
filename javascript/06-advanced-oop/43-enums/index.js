// JavaScript NO tiene enum. Se imita con un objeto cuyas propiedades son las constantes.
// Object.freeze lo "congela" para que nadie pueda añadir, cambiar ni quitar valores.
// (TypeScript sí tiene enum, pero es otro lenguaje.)

// ===== 1. Definir una enumeración =====

// Las constantes se escriben por convención en MAYÚSCULAS. Los números se asignan a mano.
const EstadoPedido = Object.freeze({
    PENDIENTE: 0,
    PROCESADO: 1,
    ENVIADO: 2,
    ENTREGADO: 3,
});

// Valores propios
const Prioridad = Object.freeze({
    BAJA: 1,
    MEDIA: 5,
    ALTA: 10,
});

// Función auxiliar: devuelve el nombre de una constante a partir de su número
const nombreDe = (enumeracion, valor) =>
    Object.keys(enumeracion).find((nombre) => enumeracion[nombre] === valor);


// ===== 2. Usar una enumeración =====

let status = EstadoPedido.PENDIENTE;
console.log(`Estado del pedido: ${nombreDe(EstadoPedido, status)}`);   // PENDIENTE (el valor guardado es el número)
console.log(`ID del estado: ${status}`);                               // 0

status = EstadoPedido.ENVIADO;
console.log(`Estado del pedido: ${nombreDe(EstadoPedido, status)}`);   // ENVIADO
console.log(`ID del estado: ${status}`);                               // 2

// Congelado: no se puede modificar, y ni siquiera da error (salvo en modo estricto)
EstadoPedido.PENDIENTE = 99;
console.log("Sigue valiendo: " + EstadoPedido.PENDIENTE);              // 0

// Pero JavaScript NO impide un valor inventado en la variable:
// status = 42;   // válido, aunque no corresponde a ningún estado


// ===== 3. Comprobar si un valor existe =====

console.log("¿Existe el valor 3? " + Object.values(EstadoPedido).includes(3));   // true
console.log("¿Existe el valor 8? " + Object.values(EstadoPedido).includes(8));   // false


// ===== 4. Comparar =====

console.log("¿Está enviado? " + (status === EstadoPedido.ENVIADO));                  // true
console.log("¿Va más allá de pendiente? " + (status > EstadoPedido.PENDIENTE));      // true


// ===== 5. Enum con switch =====

switch (status) {
    case EstadoPedido.PENDIENTE:
        console.log("El pedido está esperando");
        break;
    case EstadoPedido.PROCESADO:
        console.log("El pedido se está preparando");
        break;
    case EstadoPedido.ENVIADO:
        console.log("El pedido va de camino");
        break;
    case EstadoPedido.ENTREGADO:
        console.log("El pedido ya llegó");
        break;
}


// ===== 6. Recorrer todos los valores: Object.entries =====

for (const [nombre, valor] of Object.entries(EstadoPedido)) {
    console.log(`${valor} = ${nombre}`);
}


// ===== 7. Texto a enum =====

const texto = "enviado";
const leido = EstadoPedido[texto.toUpperCase()];

if (leido !== undefined) {
    console.log("Texto convertido: " + nombreDe(EstadoPedido, leido));
}

if (EstadoPedido["PERDIDO"] === undefined) {
    console.log("No existe el estado Perdido");
}


// ===== 8. Valores propios =====

const prioridad = Prioridad.ALTA;
console.log(`Prioridad ALTA = ${prioridad}`);   // ALTA = 10


// ===== 9. Alternativa más segura: Symbol =====

// Cada Symbol es único: no se confunde con un número cualquiera
const Color = Object.freeze({
    ROJO: Symbol("rojo"),
    VERDE: Symbol("verde"),
});
console.log(Color.ROJO.toString());   // Symbol(rojo)
console.log(Color.ROJO === Color.VERDE);   // false
