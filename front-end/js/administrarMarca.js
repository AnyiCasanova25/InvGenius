// URL de la API se declara una url por si se modifica solo cambiar donde la declaro y no entodas las demas partes
// var url = "http://10.192.80.134:8080/api/v1/marca/";

var url = "http://localhost:8080/api/v1/marca/";
var registrarMarcaBandera = true;  
var idMarca = "";

// Función para buscar proveedores por filtro
function buscarMarcaPorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: url + "busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                mostrarTabla(result);
            },
        });
    } else {
        listarMarca();
    }
}
// Función para registrar o actualizar una marca
function registrarMarca() {
    var nombreMarca = document.getElementById("nombreMarca").value.trim();
    var estado = document.getElementById("estado").value.trim();

    if (registrarMarcaBandera) {
        estado = "Activo";  // Forzar el estado a "Activo" al registrar una nueva marca
    }

    if (!validarCampos()) {
        Swal.fire({
            title: "¡Error!",
            text: "¡Llene todos los campos correctamente!",
            icon: "error"
        });
        return; 
    }

    var forData = {
        "nombreMarca": nombreMarca,
        "estado": estado
    };

    var metodo = registrarMarcaBandera ? "POST" : "PUT";
    var urlLocal = registrarMarcaBandera ? url : url + idMarca;
    

    $.ajax({
        url: urlLocal,
        type: metodo,
        contentType: "application/json",
        data: JSON.stringify(forData),
        success: function (response) {
            limpiarFormulario();
            Swal.fire({
                title: "LISTO",
                text: "Registro exitoso",
                icon: "success"
            }).then(function () {
                $('#modalMarca').modal('hide'); 
                listarMarca(); 
            });
        },
        error: function (xhr, status, error) {
            Swal.fire({
                title: "Error",
                text: "¡Error al registrar o actualizar la marca!",
                icon: "error"
            });
        }
    });
}

// Función para listar marcas (se debe definir según tu implementación)
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
                <i class="fas fa-edit editar" data-id="${result[i]["idMarca"]}"></i>  <!-- Icono de editar -->
                <i class="fas fa-toggle-on cambiarEstado" data-id="${result[i]["idMarca"]}"></i>  <!-- Icono de cambio de estado -->
            </td>
        `;
        cuerpoTabla.appendChild(trRegistro);
    }
}

// Función para limpiar el formulario completamente, incluyendo el estado si es necesario
function limpiarFormulario() {
    document.getElementById("nombreMarca").value = "";  // Limpiar el campo de nombre de marca

    if (registrarMarcaBandera) {
        document.getElementById("estado").value = "Activo";
    }

    document.getElementById("nombreMarca").className = "form-control";
    document.getElementById("estado").className = "form-control";
}
// Validaciones para campos individuales
function validarCampos() {
    return validarnombreMarca(document.getElementById("nombreMarca")) && validarestado(document.getElementById("estado"));
}

function validarnombreMarca(input) {
    return validarCampo(input, 1, 50);  // Validar que tenga entre 1 y 50 caracteres
}

function validarestado(input) {
    return validarCampo(input, 1, 20);  // Validar que tenga entre 1 y 20 caracteres
}

function validarCampo(input, minLength, maxLength) {
    var valor = input.value.trim();
    var valido = (valor.length >= minLength && valor.length <= maxLength);
    input.className = "form-control " + (valido ? "is-valid" : "is-invalid");  // Agregar clase de validación
    return valido;
}


// Función para editar marca
$(document).on("click", ".editar", function () {
    idMarca = $(this).data("id");
    registrarMarcaBandera = false;


    $.ajax({
        url: url + idMarca,
        type: "GET",
        success: function (marca) {
            // Rellenar los campos con los datos de la marca
            document.getElementById("nombreMarca").value = marca.nombreMarca;
            document.getElementById("estado").value = marca.estado;  
            $('#modalMarca').modal('show');  
        },
        error: function (error) {
            Swal.fire({
                title: "Error",
                text: "Error al obtener los datos de la marca: " + error.statusText,
                icon: "error"
            });
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


// Inicializar listado de marcas al cargar la página
$(document).ready(function () {
    listarMarca();
});

function actualizarlistarMarca() {
    listarMarca();
}
function blanquearCampos() {
    document.getElementById('texto1').value = "";
}

