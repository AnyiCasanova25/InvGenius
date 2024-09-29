// URL de la API se declara una url por si se modifica solo cambiar donde la declaro y no entodas las demas partes
// var url = "http://10.192.80.134:8080/api/v1/novedad/";

var url = "http://localhost:8080/api/v1/novedad/";
var registrarNovedadBandera = true;
var idNovedad = "";

// Función para buscar proveedores por filtro
function buscarNovedadPorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: url + "busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                mostrarTabla(result);
            },
        });
    } else {
        listarNovedad();
    }
}

// Función para listar todos los proveedores
function listarNovedad() {
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
            <td class="text-center align-middle">${result[i]["para"]}</td>
            <td class="text-center align-middle">${result[i]["asunto"]}</td>
            <td class="text-center align-middle">${result[i]["cuerpo"]}</td>
            <td class="text-center align-middle">${result[i]["fechaNovedad"]}</td>
            <td class="text-center align-middle">${result[i]["estadoNovedad"]}</td>
            <td class="text-center align-middle">
                <i class="fas fa-edit editar" data-id="${result[i]["idNovedad"]}" title="Editar Novedad"></i>
            </td>
        `;
        cuerpoTabla.appendChild(trRegistro);
    }
    
    // <td class="text-center align-middle">${result[i]["estado"]}</td>
}

// Función para blanquear los campos del formulario
function blanquearCampos() {
    document.getElementById('texto').value = "";
}

// Función para registrar o actualizar un proveedor
function registrarNovedad() {
    var para = document.getElementById("para");
    var asunto = document.getElementById("asunto");
    var cuerpo = document.getElementById("cuerpo");
    var fechaNovedad = document.getElementById("fechaNovedad");
    var imagenNovedad = document.getElementById("imagenNovedad");
    var estadoNovedad = document.getElementById("estadoNovedad");

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
        "para": para.value,
        "asunto": asunto.value,
        "cuerpo": cuerpo.value,
        "fechaNovedad": fechaNovedad.value,
        "imagenNovedad": imagenNovedad.value,
        "estadoNovedad": estadoNovedad.value,
    };

    var metodo = registrarNovedadBandera ? "POST" : "PUT";
    var urlLocal = registrarNovedadBandera ? url : url + idNovedad;

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
                listarNovedad(); // Refrescar la tabla
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
    var para = document.getElementById("para");
    var asunto = document.getElementById("asunto");
    var cuerpo = document.getElementById("cuerpo");
    var fechaNovedad = document.getElementById("fechaNovedad");
    // var estado = document.getElementById("estado");

    return validarpara(para) && validarasunto(asunto) &&
        validarcuerpo(cuerpo) && validarfechaNovedad(fechaNovedad);
        // && validarestado(estado) ;
}

// Funciones de validación por campo
function validarCampo(cuadroNumero, minLength, maxLength) {
    var valor = cuadroNumero.value.trim();
    var valido = (valor.length >= minLength && valor.length <= maxLength);
    cuadroNumero.className = "form-control " + (valido ? "is-valid" : "is-invalid");
    return valido;
}

function validarpara(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 100);
}

function validarasunto(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 100);
}

function validarcuerpo(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 501);
}

function validarfechaNovedad(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 100);
}

function validarestadoNovedad(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 41);
}


// Función para limpiar el formulario
function limpiar() {
    document.querySelectorAll(".form-control").forEach(function (input) {
        // Comprobar si el input es el campo de fecha o el campo de estado
        if (input.id !== "fechaNovedad" && input.id !== "estadoNovedad") {
            input.value = "";
            input.className = "form-control";
        }
    });
}



// Función para editar proveedor
$(document).on("click", ".editar", function () {
    limpiar();
    idNovedad = $(this).data("id");
    registrarNovedadBandera = false; // Cambiar bandera para editar

    $.ajax({
        url: url + idNovedad,
        type: "GET",
        success: function (novedad) {
            document.getElementById("para").value = novedad.para;
            document.getElementById("asunto").value = novedad.asunto;
            document.getElementById("cuerpo").value = novedad.cuerpo;
            document.getElementById("fechaNovedad").value = novedad.fechaNovedad;
            document.getElementById("imagenNovedad").value = novedad.imagenNovedad;
            document.getElementById("estadoNovedad").value = novedad.estadoNovedad;
            $('#exampleModal').modal('show');
        },
        error: function (error) {
            alert("Error al obtener los datos de la novedad: " + error.statusText);
        }
    });
});

// Función para cambiar estado del novedad
$(document).on("click", ".cambiarEstado", function () {
    var idNovedad = $(this).data("id");
    $.ajax({
        url: url + idNovedad,
        type: "DELETE",
        success: function () {
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Cambio de estado exitoso",
                showConfirmButton: false,
                timer: 1500
            });
            listarNovedad();
        }
    });
});

// Llamar a la función para listar novedades al cargar la página
$(document).ready(function () {
    listarNovedad();
});

// Función adicional para actualizar lista de novedad después de registrar/editar
function actualizarlistarNovedad() {
    listarNovedad();
}
