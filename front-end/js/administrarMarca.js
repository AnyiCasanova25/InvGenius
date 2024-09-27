// URL de la API se declara una url por si se modifica solo cambiar donde la declaro y no entodas las demas partes
// var url = "http://10.192.80.134:8080/api/v1/marca/";

var url = "http://localhost:8080/api/v1/marca/";
var registrarMarcaBandera = true;
var idMarca = "";

// Función para buscar User por filtro
// function buscarMarcaFiltro(filtro) {
//     if (filtro.trim() !== "") {
//         $.ajax({
//             url: url + "busquedaFiltros/" + filtro,
//             type: "GET",
//             success: function (result) {
//                 mostrarTabla(result);
//             },
//         });
//     } else {
//         listarMarca;
//     }
// }

// Función para listar todos los User
function listarMarca() {
    // const token = localStorage.getItem('authTokens');
    $.ajax({
        url: url,
        type: "GET",
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },
        success: function (result) {
            mostrarTabla(result);
        },
        error: function (xhr, status, error) {
            console.error("Error en la petición:", xhr.responseText);
            alert("Error en la petición: " + error);
        }
    });
}

// Función para mostrar los Marca en la tabla
function mostrarTabla(result) {
    var cuerpoTabla = document.getElementById("cuerpoTablaMarca");
    cuerpoTabla.innerHTML = "";

    for (var i = 0; i < result.length; i++) {
        var trRegistro = document.createElement("tr");
        trRegistro.innerHTML = `
            <td class="text-center align-middle">${result[i]["nombreMarca"]}</td>
            <td class="text-center align-middle">${result[i]["estado"]}</td>
            <td class="text-center align-middle">
                <i class="fas fa-edit editar" data-id="${result[i]["idMarca"]}"></i>
                <i class="fas fa-toggle-on cambiarEstado" data-id="${result[i]["idMarca"]}"></i>
            </td>
        `;
        cuerpoTabla.appendChild(trRegistro);
    }
}

// Función para blanquear los campos del formulario
function blanquearCamposMarca() {
    document.getElementById('texto').value = "";
}

// Función para registrar o actualizar un user
function registrarMarca() {
    var nombreMarca = document.getElementById("nombreMarca");
    var estado = document.getElementById("estado");

    if (!validarCampos()) {
        Swal.fire({
            title: "¡Error!",
            text: "¡Llene todos los campos correctamente!",
            icon: "error"
        });
        return; // Salir si algún campo es inválido
    }

    var forData = {
        "nombreMarca": nombreMarca.value,
        "estado": estado.value,
    };

    var metodo = registrarMarcaBandera ? "POST" : "PUT";
    var urlLocal = registrarMarcaBandera ? url : url + idMarca;

    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlLocal,
        type: metodo,
        headers: {
            'Authorization': 'Bearer ' + token
        },
        contentType: "application/json",
        data: JSON.stringify(forData),
        success: function (response) {
            limpiar();
            Swal.fire({
                title: "LISTO",
                text: "Felicidades, Registro exitoso",
                icon: "success"
            }).then(function () {
                $('#modalMarca').modal('hide');
                listarMarca(); // Refrescar la tabla
            });
        },
        error: function (xhr, status, error) {
            Swal.fire({
                title: "Error",
                text: "¡Error al registrar o actualizar esta Marca!",
                icon: "error"
            });
        }
    });
}

// Función para validar campos
function validarCampos() {
    var nombreMarca = document.getElementById("nombreMarca");
    var estado = document.getElementById("estado");

    return validarnombreMarca(nombreMarca) && validarestado(estado);
}

// Funciones de validación por campo
function validarCampo(cuadroNumero, minLength, maxLength) {
    var valor = cuadroNumero.value.trim();
    var valido = (valor.length >= minLength && valor.length <= maxLength);
    cuadroNumero.className = "form-control " + (valido ? "is-valid" : "is-invalid");
    return valido;
}

function validarnombreMarca(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 50);
}

function validarestado(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 21);
}

// Función para limpiar el formulario
function limpiar() {
    document.querySelectorAll(".form-control").forEach(function (input) {
        input.value = "";
        input.className = "form-control";
    });
}

// Función para editar user
$(document).on("click", ".editar", function () {
    limpiar();
    idMarca = $(this).data("id");
    registrarMarcaBandera = false; // Cambiar bandera para editar

    $.ajax({
        url: url + idMarca,
        type: "GET",
        success: function (marca) {
            document.getElementById("nombreMarca").value = marca.nombreMarca;
            document.getElementById("estado").value = marca.estado;
            $('#modalMarca').modal('show');
        },
        error: function (error) {
            alert("Error al obtener los datos del proveedor: " + error.statusText);
        }
    });
});

$(document).on("click", ".cambiarEstado", function () {
    var idMarca = $(this).data("id");
    $.ajax({
        url: url + idMarca,
        type: "DELETE",
        success: function () {
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Cambio de estado exitoso",
                showConfirmButton: false,
                timer: 1500
            });
            listarMarca();
        }
    });
});

$(document).ready(function () {
    listarMarca();
});

function actualizarlistarMarca() {
    listarMarca();
}
