var registrarInformeBandera = true;
var idInforme = "";

// Función para buscar proveedores por filtro
function buscarInformePorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: urlInforme + "busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                mostrarTabla(result);
            },
        });
    } else {
        listarInforme();
    }
}

// Función para listar todos los proveedores
function listarInforme() {
    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlInforme,
        type: "GET",
        headers: {
            "Authorization": "Bearer " + token
        },
        success: function (result) {
            mostrarTabla(result);
        },
        error: function (xhr, status, error) {
            console.error("Error en la petición:", xhr.responseText);
            alert("Error en la petición: " + error);
        }
    });
}

// Función para mostrar los proveedores en la tabla
function mostrarTabla(result) {
    var cuerpoTabla = document.getElementById("cuerpoTabla");
    cuerpoTabla.innerHTML = "";

    for (var i = 0; i < result.length; i++) {
        var trRegistro = document.createElement("tr");
        trRegistro.innerHTML = `
            <td class="text-center align-middle">${result[i]["movimientos"]}</td>
            <td class="text-center align-middle">${result[i]["categoria"]}</td>
            <td class="text-center align-middle">${result[i]["producto"]}</td>
            <td class="text-center align-middle">${result[i]["lote"]}</td>
            <td class="text-center align-middle">${result[i]["fechaInforme"]}</td>
            <td class="text-center align-middle">${result[i]["proveedor"]}</td>
            <td class="text-center align-middle">
            </td>
        `;
        cuerpoTabla.appendChild(trRegistro);
    }
}


// Funciones de validación por campo
function validarCampo(cuadroNumero, minLength, maxLength) {
    var valor = cuadroNumero.value.trim();
    var valido = (valor.length >= minLength && valor.length <= maxLength);
    cuadroNumero.className = "form-control " + (valido ? "is-valid" : "is-invalid");
    return valido;
}

function validarnombreProveedor(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 41);
}

function validarapellidoProveedor(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 41);
}

function validardocumentoProveedor(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 12);
}

function validarnumeroProveedor(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 16);
}

function validarempresaProveedor(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 41);
}

function validarestadoProveedor(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 21);
}

// Función para limpiar el formulario
function limpiar() {
    document.querySelectorAll(".form-control").forEach(function (input) {
        input.value = "";
        input.className = "form-control";
    });
}

// Llamar a la función para listar proveedores al cargar la página
$(document).ready(function () {
    listarProveedor();
});
