// Cuadros de texto en el DOM: el equivalente de la lección 51 de WPF.

// ===== 1. Buscar los elementos por su id =====

const txtNombre = document.getElementById("txtNombre");
const txtEdad = document.getElementById("txtEdad");
const btnIngresar = document.getElementById("btnIngresar");
const btnBorrar = document.getElementById("btnBorrar");
const lResultado = document.getElementById("lResultado");


// ===== 2. Botón Ingresar: leer, validar y mostrar =====

btnIngresar.addEventListener("click", () => {
    // value es lo que hay escrito (en WPF, Text); trim() quita los espacios sobrantes de los extremos
    const nombre = txtNombre.value.trim();
    const edad = txtEdad.value.trim();

    // Un texto vacío ("") es falsy: !nombre es true si está vacío
    if (!nombre && !edad) {
        lResultado.textContent = "No colocaste ningún dato";
    } else if (!nombre) {
        lResultado.textContent = "No colocaste nombre";
    } else if (!edad) {
        lResultado.textContent = "No colocaste edad";
    } else {
        // Todos los datos están: se muestran
        lResultado.textContent = "Nombre: " + nombre + ", Edad: " + edad;
    }
});


// ===== 3. Botón Borrar: limpiar =====

btnBorrar.addEventListener("click", () => {
    txtNombre.value = "";                  // en un cuadro de texto es value...
    txtEdad.value = "";
    lResultado.textContent = "Resultado";  // ...y en un párrafo es textContent. No se vacía: orienta al usuario
});
