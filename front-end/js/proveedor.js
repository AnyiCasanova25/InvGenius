// URL de la API se declara una url por si se modifica solo cambiar donde la declaro y no entodas las demas partes
// var url = "http://10.192.80.134:8080/api/v1/proveedor/";

var url = "http://localhost:8080/api/v1/proveedor/";
var registrarProveedorBandera = true;
var idProveedor = "";

// Función para buscar proveedores por filtro
function buscarProveedorPorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: url + "busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                mostrarTabla(result);
            },
        });
    } else {
        listarProveedor();
    }
}

// Función para listar todos los proveedores
function listarProveedor() {
    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: url,
        type: "GET",
        headers: {
            'Authorization': 'Bearer ' + token
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
            <td class="text-center align-middle">${result[i]["documentoProveedor"]}</td>
            <td class="text-center align-middle">${result[i]["nombreProveedor"]}</td>
            <td class="text-center align-middle">${result[i]["apellidoProveedor"]}</td>
            <td class="text-center align-middle">${result[i]["numeroProveedor"]}</td>
            <td class="text-center align-middle">${result[i]["empresaProveedor"]}</td>
            <td class="text-center align-middle">${result[i]["estadoProveedor"]}</td>
            <td class="text-center align-middle">
                <i class="fas fa-edit editar" data-id="${result[i]["idProveedor"]}"></i>
                <i class="fa-solid fa-user-slash cambiarEstado" data-id="${result[i]["idProveedor"]}"></i>
            </td>
        `;
        cuerpoTabla.appendChild(trRegistro);
    }
}

// Función para blanquear los campos del formulario
function blanquearCampos() {
    document.getElementById('texto').value = "";
}

// Función para registrar o actualizar un proveedor
function registrarProveedor() {
    var nombreProveedor = document.getElementById("nombreProveedor");
    var apellidoProveedor = document.getElementById("apellidoProveedor");
    var documentoProveedor = document.getElementById("documentoProveedor");
    var numeroProveedor = document.getElementById("numeroProveedor");
    var empresaProveedor = document.getElementById("empresaProveedor");
    var estadoProveedor = document.getElementById("estadoProveedor");

    // Verificar si algún campo obligatorio está vacío
    if (!validarCampos()) {
        Swal.fire({
            title: "¡Error!",
            text: "¡Llene todos los campos correctamente!",
            icon: "error"
        });
        return; // Salir si algún campo es inválido
    }

    var forData = {
        "nombreProveedor": nombreProveedor.value,
        "apellidoProveedor": apellidoProveedor.value,
        "documentoProveedor": documentoProveedor.value,
        "numeroProveedor": numeroProveedor.value,
        "empresaProveedor": empresaProveedor.value,
        "estadoProveedor": estadoProveedor.value,
    };

    var metodo = registrarProveedorBandera ? "POST" : "PUT";
    var urlLocal = registrarProveedorBandera ? url : url + idProveedor;

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
                $('#exampleModal').modal('hide');
                listarProveedor(); // Refrescar la tabla
            });
        },
        error: function (xhr, status, error) {
            Swal.fire({
                title: "Error",
                text: "¡Error al registrar o actualizar este Proveedor!",
                icon: "error"
            });
        }
    });
}

// Función para validar campos
function validarCampos() {
    var nombreProveedor = document.getElementById("nombreProveedor");
    var apellidoProveedor = document.getElementById("apellidoProveedor");
    var documentoProveedor = document.getElementById("documentoProveedor");
    var numeroProveedor = document.getElementById("numeroProveedor");
    var empresaProveedor = document.getElementById("empresaProveedor");
    var estadoProveedor = document.getElementById("estadoProveedor");

    return validarnombreProveedor(nombreProveedor) && validarapellidoProveedor(apellidoProveedor) &&
        validardocumentoProveedor(documentoProveedor) && validarnumeroProveedor(numeroProveedor) &&
        validarempresaProveedor(empresaProveedor) && validarestadoProveedor(estadoProveedor);
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

// Función para editar proveedor
$(document).on("click", ".editar", function () {
    limpiar();
    idProveedor = $(this).data("id");
    registrarProveedorBandera = false; // Cambiar bandera para editar

    $.ajax({
        url: url + idProveedor,
        type: "GET",
        success: function (proveedor) {
            document.getElementById("nombreProveedor").value = proveedor.nombreProveedor;
            document.getElementById("apellidoProveedor").value = proveedor.apellidoProveedor;
            document.getElementById("documentoProveedor").value = proveedor.documentoProveedor;
            document.getElementById("numeroProveedor").value = proveedor.numeroProveedor;
            document.getElementById("empresaProveedor").value = proveedor.empresaProveedor;
            document.getElementById("estadoProveedor").value = proveedor.estadoProveedor;
            $('#exampleModal').modal('show');
        },
        error: function (error) {
            alert("Error al obtener los datos del proveedor: " + error.statusText);
        }
    });
});

// Función para cambiar estado del proveedor
$(document).on("click", ".cambiarEstado", function () {
    var idProveedor = $(this).data("id");
    $.ajax({
        url: url + idProveedor,
        type: "DELETE",
        success: function () {
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Cambio de estado exitoso",
                showConfirmButton: false,
                timer: 1500
            });
            listarProveedor();
        }
    });
});

// Llamar a la función para listar proveedores al cargar la página
$(document).ready(function () {
    listarProveedor();
});

// Función adicional para actualizar lista de proveedores después de registrar/editar
function actualizarlistarProveedor() {
    listarProveedor();
}
