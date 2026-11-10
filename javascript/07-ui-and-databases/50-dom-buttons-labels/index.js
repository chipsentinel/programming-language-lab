// Etiquetas y botones en el DOM: el equivalente de la lección 50 de WPF.

// ===== 1. Buscar los elementos por su id =====

const labelMensaje = document.getElementById("labelMensaje");
const botonSaludar = document.getElementById("botonSaludar");
const botonDespedir = document.getElementById("botonDespedir");


// ===== 2. Eventos: addEventListener =====

botonSaludar.addEventListener("click", () => {
    // Cambiar el texto de la etiqueta: textContent (en WPF, la propiedad Content)
    labelMensaje.textContent = "Saludos a todos";

    // disabled: si el botón está desactivado (true) o se puede pulsar (false)
    botonDespedir.disabled = false;     // ya se saludó: ahora se puede despedir
    botonSaludar.disabled = true;       // no hace falta saludar otra vez
});

botonDespedir.addEventListener("click", () => {
    labelMensaje.textContent = "Adiós a todos";

    botonSaludar.disabled = false;      // se puede volver a saludar
    botonDespedir.disabled = true;
});


// ===== 3. Cambiar la apariencia desde el código =====

// Además de en el CSS, los estilos se pueden cambiar desde JavaScript (propiedad style):
// labelMensaje.style.color = "red";
// o, mejor, añadiendo y quitando clases definidas en el CSS:
// labelMensaje.classList.add("destacado");
