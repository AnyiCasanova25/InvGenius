
$(document).ready(function () {
    listarNovedad();
    listarMarca();
    listarUser();
});


function blanquearCampos() {
    document.getElementById('texto1').value = "";
    document.getElementById('texto2').value = "";
    document.getElementById('texto3').value = "";
}

//Apartado de marca
var registrarMarcaBandera = true;
var idMarca = "";

// Función para buscar proveedores por filtro
function buscarMarcaPorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: urlMarca + "busquedaFiltros/" + filtro,
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
    var urlLocal = registrarMarcaBandera ? urlMarca : urlMarca + idMarca;


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
        url: urlMarca,
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
                <i class="fas fa-edit editar" data-id="${result[i]["idMarca"]}" title="Editar Marca"></i>
                <i class="fas fa-toggle-on cambiarEstadoMarca" data-id="${result[i]["idMarca"]}" title="Cambiar Estado de Marca"></i>  <!-- Icono de cambio de estado -->
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
        url: urlMarca + idMarca,
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
$(document).on("click", ".cambiarEstadoMarca", function () {
    var idMarca = $(this).data("id");
    $.ajax({
        url: urlMarca + idMarca,
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
function actualizarlistarMarca() {
    listarMarca();
}

//Apartado Novedades
var idNovedad = "";
function buscarNovedadPorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: urlNovedad + "busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                mostrarTablaNovedades(result);
            },
        });
    } else {
        listarNovedad();
    }
}
function listarNovedad() {
    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlNovedad,
        type: "GET",
        headers: {
            'Authorization': 'Bearer ' + token
        },
        success: function (result) {
            mostrarTablaNovedades(result);
        },
        error: function (xhr, status, error) {
            console.error("Error en la petición:", xhr.responseText);
            alert("Error en la petición: " + error);
        }
    });
}
function mostrarTablaNovedades(result) {
    var cuerpoTabla = document.getElementById("cuerpoTablaNovedades");
    cuerpoTabla.innerHTML = "";

    for (var i = 0; i < result.length; i++) {
        var trRegistro = document.createElement("tr");
        trRegistro.innerHTML = `
            <td class="text-center align-middle">${result[i]["asunto"]}</td>
            <td class="text-center align-middle">${result[i]["fechaNovedad"]}</td>
            <td class="text-center align-middle">${result[i]["estadoNovedad"]}</td>
            <td class="text-center align-middle">
                <i class="fas fa-eye ver" data-id="${result[i]['idNovedad']}" title="Ver la Solicitud del Usuario"></i>
                <i class="fas fa-check confirmar" data-id="${result[i]['idNovedad']}" title="Aceptar Novedad"></i>
                <i class="fas fa-times eliminar" data-id="${result[i]['idNovedad']}" title="Rechazar Novedad"></i>
            </td>
        `;
        cuerpoTabla.appendChild(trRegistro);
    }
}
            // <td class="text-center align-middle">${result[i]["cuerpo"]}</td>
$(document).on("click", ".cambiarEstado", function () {
    var idNovedad = $(this).data("id");
    $.ajax({
        url: urlNovedad + idNovedad,
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
        },
        error: function (xhr, status, error) {
            Swal.fire({
                title: "Error",
                text: "Error al cambiar el estado de la novedad: " + error,
                icon: "error"
            });
        }
    });
});
function actualizarlistarNovedad() {
    listarNovedad();
}

//Apartado Perfil
function buscarUserrFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: urlUsuarios + "busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                mostrarTablaPerfiles(result);
            },
        });
    } else {
        listarUser();
    }
}
function listarUser() {
    // const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlUsuarios,
        type: "GET",
        // headers: {
        //     'Authorization': 'Bearer ' + token
        // },
        success: function (result) {
            mostrarTablaPerfiles(result);
        },
        error: function (xhr, status, error) {
            console.error("Error en la petición:", xhr.responseText);
            alert("Error en la petición: " + error);
        }
    });
}
function mostrarTablaPerfiles(result) {
    var cuerpoTabla = document.getElementById("cuerpoTablaPerfiles");
    cuerpoTabla.innerHTML = "";

    for (var i = 0; i < result.length; i++) {
        var trRegistro = document.createElement("tr");
        trRegistro.innerHTML = `
            <td class="text-center align-middle">${result[i]["documentoIdentidad"]}</td>
            <td class="text-center align-middle">${result[i]["nombres"]}</td>
            <td class="text-center align-middle">${result[i]["apellidos"]}</td>
            <td class="text-center align-middle">${result[i]["celular"]}</td>
            <td class="text-center align-middle">${result[i]["correo"]}</td>
            <td class="text-center align-middle">${result[i]["rol"]}</td>
            <td class="text-center align-middle">${result[i]["estado"]}</td>
            <td class="text-center align-middle">
                <i class="fas fa-edit editar" data-id="${result[i]["idUser"]}" title="Editar Información del Usuario"></i>
                <i class="fas fa-user-shield cambiarRolUser" data-id="${result[i]["idUser"]}" title="Cambiar Usuario de Rol"></i>
                <i class="fas fa-toggle-on cambiarEstadoUser" data-id="${result[i]["idUser"]}" title="Cambiar Estado de Usuario"></i>
            </td>
        `;
        cuerpoTabla.appendChild(trRegistro);
    }
}
function registrarUser() {
    var documentoIdentidad = document.getElementById("documentoIdentidad");
    var nombres = document.getElementById("nombres");
    var apellidos = document.getElementById("apellidos");
    var celular = document.getElementById("celular");
    var correo = document.getElementById("correo");
    var rol = document.getElementById("rol");
    var estado = document.getElementById("estado");

    // Verificar si algún campo obligatorio está vacío
    if (!validarCamposPerfiles()) {
        Swal.fire({
            title: "¡Error!",
            text: "¡Llene todos los campos correctamente!",
            icon: "error"
        });
        return; // Salir si algún campo es inválido
    }

    var forData = {
        "documentoIdentidad": documentoIdentidad.value,
        "nombres": nombres.value,
        "apellidos": apellidos.value,
        "celular": celular.value,
        "correo": correo.value,
        "rol": rol.value,
        "estado": estado.value,
    };

    var metodo = registrarUserBandera ? "POST" : "PUT";
    var urlLocal = registrarUserBandera ? urlUsuarios : urlUsuarios + idUser;

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
                listarUser(); // Refrescar la tabla
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
function validarCamposPerfiles() {
    var documentoIdentidad = document.getElementById("documentoIdentidad");
    var nombres = document.getElementById("nombres");
    var apellidos = document.getElementById("apellidos");
    var celular = document.getElementById("celular");
    var correo = document.getElementById("correo");
    // var rol = document.getElementById("rol");
    // var estado = document.getElementById("estado");

    return validardocumentoIdentidad(documentoIdentidad) && validarnombres(nombres) &&
        validarapellidos(apellidos) && validarcelular(celular) && validarcorreo(correo);
    //  &&
    // validarrol(rol) && validarestado(estado);
}

// Funciones de validación por campo
function validarCampo(cuadroNumero, minLength, maxLength) {
    var valor = cuadroNumero.value.trim();
    var valido = (valor.length >= minLength && valor.length <= maxLength);
    cuadroNumero.className = "form-control " + (valido ? "is-valid" : "is-invalid");
    return valido;
}

function validardocumentoIdentidad(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 41);
}

function validarnombres(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 41);
}

function validarapellidos(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 12);
}

function validarcelular(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 16);
}

function validarcorreo(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 16);
}

// function validarrol(cuadroNumero) {
//     return validarCampo(cuadroNumero, 1, 41);
// }

function validarestado(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 21);
}

function limpiarModalPerfiles() {
    document.querySelectorAll(".form-control").forEach(function (input) {
        input.value = "";
        input.className = "form-control";
    });
}

// Función para editar user
$(document).on("click", ".editar", function () {
    limpiarModalPerfiles();
    idUser = $(this).data("id");
    registrarUserBandera = false; // Cambiar bandera para editar

    $.ajax({
        url: urlUsuarios + idUser,
        type: "GET",
        success: function (user) {
            document.getElementById("documentoIdentidad").value = user.documentoIdentidad;
            document.getElementById("nombres").value = user.nombres;
            document.getElementById("apellidos").value = user.apellidos;
            document.getElementById("celular").value = user.celular;
            document.getElementById("correo").value = user.correo;
            // document.getElementById("rol").value = user.rol;
            document.getElementById("estado").value = user.estado;
            $('#exampleModal').modal('show');
        },
        error: function (error) {
            alert("Error al obtener los datos del proveedor: " + error.statusText);
        }
    });
});

// Función para cambiar estado del user
$(document).on("click", ".cambiarEstadoUser", function () {
    var idUser = $(this).data("id");
    $.ajax({
        url: urlUsuarios + idUser,
        type: "DELETE",
        success: function () {
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Cambio de estado exitoso",
                showConfirmButton: false,
                timer: 1500
            });
            listarUser();
        }
    });
});
$(document).on("click", ".cambiarRolUser", function () {
    var idUser = $(this).data("id");
    $.ajax({
        url: urlUsuarios + idUser,
        type: "DELETE",
        success: function () {
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Cambio de estado exitoso",
                showConfirmButton: false,
                timer: 1500
            });
            listarUser();
        }
    });
});
// Función adicional para actualizar lista de Useres después de registrar/editar
function actualizarlistarUser() {
    listarUser();
}
