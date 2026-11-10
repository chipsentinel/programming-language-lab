// CheckBox y RadioButton en el DOM: el equivalente de la lección 52 de WPF.

// ===== 1. Buscar los elementos por su id =====

const ck01 = document.getElementById("ck01");
const ck02 = document.getElementById("ck02");
const ck03 = document.getElementById("ck03");

const rb01 = document.getElementById("rb01");
const rb02 = document.getElementById("rb02");
const rb03 = document.getElementById("rb03");

const bOrdenar = document.getElementById("bOrdenar");
const bCancelar = document.getElementById("bCancelar");
const lTotal = document.getElementById("lTotal");


// ===== 2. Botón Ordenar: calcular el total =====

bOrdenar.addEventListener("click", () => {
    // checked: true si la casilla / el botón de opción está marcado
    // Regla de negocio: no se puede ordenar sin pizza (una bebida sola no vale)
    if (!ck01.checked && !ck02.checked && !ck03.checked) {
        lTotal.textContent = "Debe seleccionar una pizza con la orden";
    } else {
        let total = 0.0;

        // Selección de pizzas: se suman todas las marcadas (pueden ser una, dos o las tres)
        if (ck01.checked) total += 150.00;
        if (ck02.checked) total += 250.00;
        if (ck03.checked) total += 200.00;

        // Selección de bebidas: solo una puede estar marcada (radio)
        if (rb01.checked) total += 30.00;
        if (rb02.checked) total += 30.00;
        if (rb03.checked) total += 25.00;

        // toFixed(2): dos decimales
        lTotal.textContent = "$" + total.toFixed(2);
    }
});


// ===== 3. Botón Cancelar: desmarcar todo =====

bCancelar.addEventListener("click", () => {
    ck01.checked = false;
    ck02.checked = false;
    ck03.checked = false;

    rb01.checked = false;
    rb02.checked = false;
    rb03.checked = false;

    lTotal.textContent = "";
});
