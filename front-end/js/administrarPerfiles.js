// URL de la API se declara una url por si se modifica solo cambiar donde la declaro y no entodas las demas partes
// var url = "http://10.192.80.134:8080/api/v1/user/";

var url = "http://10.192.80.141:8080/api/v1/user/";
var registrarUserBandera = true;
var idUser = "";

// Función para buscar User por filtro
function buscarUserrFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: url + "busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                mostrarTabla(result);
            },
        });
    } else {
        listarUser();
    }
}

// Función para listar todos los User
function listarUser() {
    // const token = localStorage.getItem('authTokens');
    $.ajax({
        url: url + "profile/",
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

// Función para mostrar los User en la tabla
function mostrarTabla(result) {
    var cuerpoTabla = document.getElementById("cuerpoTabla");
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
                <i class="fas fa-edit editar" data-id="${result[i]["idUser"]}"></i>
                <i class="fa-solid fa-user-slash cambiarEstado" data-id="${result[i]["idUser"]}"></i>
                <i class="fa-solid fa-user-slash cambiarEstado" data-id="${result[i]["idUser"]}"></i>
            </td>
        `;
        cuerpoTabla.appendChild(trRegistro);
    }
}

// Función para blanquear los campos del formulario
function blanquearCampos() {
    document.getElementById('texto').value = "";
}

// Función para registrar o actualizar un user
function registrarUser() {
    var documentoIdentidad = document.getElementById("documentoIdentidad");
    var nombres = document.getElementById("nombres");
    var apellidos = document.getElementById("apellidos");
    var celular = document.getElementById("celular");
    var correo = document.getElementById("correo");
    var rol = document.getElementById("rol");
    var estado = document.getElementById("estado");

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
        "documentoIdentidad": documentoIdentidad.value,
        "nombres": nombres.value,
        "apellidos": apellidos.value,
        "celular": celular.value,
        "correo": correo.value,
        "rol": rol.value,
        "estado": estado.value,
    };

    var metodo = registrarUserBandera ? "POST" : "PUT";
    var urlLocal = registrarUserBandera ? url : url + idUser;

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
function validarCampos() {
    var documentoIdentidad = document.getElementById("documentoIdentidad");
    var nombres = document.getElementById("nombres");
    var apellidos = document.getElementById("apellidos");
    var celular = document.getElementById("celular");
    var correo = document.getElementById("correo");
    var rol = document.getElementById("rol");
    var estado = document.getElementById("estado");

    return validardocumentoIdentidad(documentoIdentidad) && validarnombres(nombres) &&
        validarapellidos(apellidos) && validarcelular(celular) && validarcorreo(correo) &&
        validarrol(rol) && validarestado(estado);
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

function validarrol(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 41);
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
    idUser = $(this).data("id");
    registrarUserBandera = false; // Cambiar bandera para editar

    $.ajax({
        url: url + idUser,
        type: "GET",
        success: function (user) {
            document.getElementById("documentoIdentidad").value = user.documentoIdentidad;
            document.getElementById("nombres").value = user.nombres;
            document.getElementById("apellidos").value = user.apellidos;
            document.getElementById("celular").value = user.celular;
            document.getElementById("correo").value = user.correo;
            document.getElementById("rol").value = user.rol;
            document.getElementById("estado").value = user.estado;
            $('#exampleModal').modal('show');
        },
        error: function (error) {
            alert("Error al obtener los datos del proveedor: " + error.statusText);
        }
    });
});

// Función para cambiar estado del user
$(document).on("click", ".cambiarEstado", function () {
    var idUser = $(this).data("id");
    $.ajax({
        url: url + idUser,
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

// Llamar a la función para listar Useres al cargar la página
$(document).ready(function () {
    listarUser();
});

// Función adicional para actualizar lista de Useres después de registrar/editar
function actualizarlistarUser() {
    listarUser();
}
