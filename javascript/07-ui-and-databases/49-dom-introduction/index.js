// index.js: el COMPORTAMIENTO de la página, escrito en JavaScript.
// Se ejecuta en el NAVEGADOR (no con Node.js): se abre index.html y este archivo se carga solo.
//
// El navegador convierte el HTML en un árbol de objetos llamado DOM (Document Object Model):
//   document
//   └── html
//       ├── head
//       └── body
//           └── main
//               ├── h1  (id="mensaje")
//               └── button  (id="aceptar")
// JavaScript puede leer y modificar ese árbol: es lo que hace "viva" la página.


// ===== 1. Buscar elementos: document.getElementById =====

// Devuelve el elemento con ese id (el equivalente de usar un elemento por su x:Name en WPF)
const mensaje = document.getElementById("mensaje");
const aceptar = document.getElementById("aceptar");


// ===== 2. Dar funcionalidad: eventos =====

// addEventListener asocia un código a un evento. "click" es el evento del clic.
// (() => {...}) es una función flecha (lección 25)
aceptar.addEventListener("click", () => {
    mensaje.textContent = "Has pulsado Aceptar";      // textContent: el texto del elemento
    aceptar.disabled = true;                          // el botón se desactiva
});


// ===== 3. Crear elementos nuevos =====

// El DOM también permite crear elementos desde el código y añadirlos a la página
const nota = document.createElement("p");
nota.textContent = "Esta línea se ha creado con JavaScript";
document.querySelector("main").appendChild(nota);    // querySelector busca con un selector CSS
