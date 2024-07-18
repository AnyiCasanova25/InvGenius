// URL de la API se declara una url por si se modifica solo cambiar donde la declaro y no entodas las demas partes
var url = "http://localhost:8080/api/v1/proveedor/";

// este metodo solo permite letras
const letrasPermitidas = [
    'A', 'Á', 'B', 'C', 'D', 'E', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L', 'M',
    'N', 'Ñ', 'O', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z',
    'a', 'á', 'b', 'c', 'd', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm',
    'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z', ' '
];
const numerosPermitidos = [
    '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
];
const signosPermitidos = [
    '.', ',', '@', '_', '-', '#', ''
];

function soloLetras(event) {
    console.log("Llave presionada: " + event.key);
    console.log("Codigo tecla: " + event.keyCode);

    if (!(letrasPermitidas.includes(event.key))) {
        event.preventDefault();
        return;
    }
}
function soloNumeros(event) {
    console.log("Llave presionada: " + event.key);
    console.log("Codigo tecla: " + event.keyCode);

    if (!(numerosPermitidos.includes(event.key))) {
        event.preventDefault();
        return;
    }
}
function alfaNumericosSignos(event) {
    console.log("Llave presionada: " + event.key);
    console.log("Codigo tecla: " + event.keyCode);

    if (!((numerosPermitidos.includes(event.key)) || (letrasPermitidas.includes(event.key)) || (signosPermitidos.includes(event.key)))) {
        event.preventDefault();
        return;
    }
}

function buscarUsuarioPorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: "http://localhost:8080/api/v1/proveedor/busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                var cuerpoTabla = document.getElementById("cuerpoTabla");
                cuerpoTabla.innerHTML = "";

                for (var i = 0; i < result.length; i++) {
                    var trRegistro = document.createElement("tr");
                    trRegistro.innerHTML = `
                        <td class="align-middle">${result[i]["nombreProveedor"]}</td>
                        <td class="text-center align-middle">${result[i]["apellidoProveedor"]}</td>
                        <td class="text-center align-middle">${result[i]["documentoProveedor"]}</td>
                        <td class="text-center align-middle">${result[i]["numeroProveedor"]}</td>
                        <td class="text-center align-middle">${result[i]["empresaProveedor"]}</td>
                        <td class="text-center align-middle">${result[i]["estadoProveedor"]}</td>
                        <td class="text-center align-middle">
                            <i class="fas fa-edit editar" onclick="registrarProveedorBandera=false;" data-id="${result[i]["idProveedor"]}"></i>
                            <i class="fa-solid fa-user-slash cambiarEstado" data-id="${result[i]["idProveedor"]}"></i>
                        </td>
                    `;
                    cuerpoTabla.appendChild(trRegistro);
                }
            },
            error: function (error) {
                alert("Error en la petición: " + error);
            }
        });
    } else {
        listarProveedor();
    }
}

function listarProveedor() {
    $.ajax({
        url: url,
        type: "GET",
        success: function (result) {
            var cuerpoTabla = document.getElementById("cuerpoTabla");
            cuerpoTabla.innerHTML = "";

            for (var i = 0; i < result.length; i++) {
                var trRegistro = document.createElement("tr");
                trRegistro.innerHTML = `
                    <td class="align-middle">${result[i]["nombreProveedor"]}</td>
                    <td class="text-center align-middle">${result[i]["apellidoProveedor"]}</td>
                    <td class="text-center align-middle">${result[i]["documentoProveedor"]}</td>
                    <td class="text-center align-middle">${result[i]["numeroProveedor"]}</td>
                    <td class="text-center align-middle">${result[i]["empresaProveedor"]}</td>
                    <td class="text-center align-middle">${result[i]["estadoProveedor"]}</td>
                    <td class="text-center align-middle">
                        <i class="fas fa-edit editar" onclick="registrarProveedorBandera=false;" data-id="${result[i]["idProveedor"]}"></i>
                        <i class="fa-solid fa-user-slash cambiarEstado" data-id="${result[i]["idProveedor"]}"></i>
                    </td>
                `;
                cuerpoTabla.appendChild(trRegistro);
            }
        },
        error: function (error) {
            alert("Error en la petición: " + error);
        }
    });
}

function blanquearCampos() {
    document.getElementById('texto').value = "";
}

var registrarProveedorBandera = true;

function registrarProveedor() {
    var nombreProveedor = document.getElementById("nombreProveedor");
    var apellidoProveedor = document.getElementById("apellidoProveedor");
    var documentoProveedor = document.getElementById("documentoProveedor");
    var numeroProveedor = document.getElementById("numeroProveedor");
    var empresaProveedor = document.getElementById("empresaProveedor");
    var tipestadoProveedorUser = document.getElementById("tipestadoProveedorUser");

    // Verificar si algún campo obligatorio está vacío
    if (!validarnombreProveedor(nombreProveedor) ||
        !validarapellidoProveedor(apellidoProveedor) ||
        !validardocumentoProveedor(documentoProveedor) ||
        !validarnumeroProveedor(numeroProveedor) ||
        !validarempresaProveedor(empresaProveedor) ||
        !tipestadoProveedorUser(tipestadoProveedorUser)) {
        // Mostrar una alerta indicando que todos los campos son obligatorios
        Swal.fire({
            title: "¡Error!",
            text: "¡Llene todos los campos correctamente!",
            icon: "error"
        });
        return; // Salir de la función si algún campo está vacío
    }

    var forData = {
        "nombreProveedor": nombreProveedor.value,
        "apellidoProveedor": apellidoProveedor.value,
        "documentoProveedor": documentoProveedor.value,
        "numeroProveedor": numeroProveedor.value,
        "empresaProveedor": empresaProveedor.value,
        "tipestadoProveedorUser": tipestadoProveedorUser.value,
    };

    var metodo = "";
    var urlLocal = "";
    var textoimprimir = "";
    if (registrarUsuarioBandera == true) {
        metodo = "POST";
        urlLocal = url;

    } else {
        metodo = "PUT";
        urlLocal = url + idUsuario;
    }

    if (validarCampos()) {
        $.ajax({
            url: urlLocal,
            type: metodo,
            data: forData,
            success: function (response) {
                limpiar();
                Swal.fire({
                    title: "LISTO",
                    text: "Felicidades, Registro exitoso",
                    icon: "success"
                }).then(function () {
                    // Aquí puedes agregar más acciones después del registro exitoso
                    $('#exampleModal').modal('hide');
                    listarProveedor(); // Aquí se vuelve a listar los productos
                });
            },
        });
    } else {
        Swal.fire({
            title: "Error",
            text: "¡Llene todos los campos correctamente!",
            icon: "error"
        });
    }
};

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

function validarCampo(cuadroNumero, minLength, maxLength) {
    var valor = cuadroNumero.value.trim();
    var valido = true;
    if (valor.length < minLength || valor.length > maxLength) {
        valido = false;
    }
    if (valido) {
        cuadroNumero.className = "form-control is-valid";
    } else {
        cuadroNumero.className = "form-control is-invalid";
    }
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

function tipestadoProveedorUser(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 21);
}

function limpiar() {
    document.getElementById("nombreProveedor").value = "";
    document.getElementById("nombreProveedor").className = "form-control";
    document.getElementById("apellidoProveedor").value = "";
    document.getElementById("apellidoProveedor").className = "form-control";
    document.getElementById("documentoProveedor").value = "";
    document.getElementById("documentoProveedor").className = "form-control";
    document.getElementById("numeroProveedor").value = "";
    document.getElementById("numeroProveedor").className = "form-control";
    document.getElementById("empresaProveedor").value = "";
    document.getElementById("empresaProveedor").className = "form-control";
    document.getElementById("estadoProveedor").value = "";
    document.getElementById("estadoProveedor").className = "form-control";
}

var idProveedor = "";
$(document).on("click", ".editar", function () {
    limpiar();
    idProveedor = $(this).data("id");

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
            listarProveedor ();
        }
    });
});

// Llamar a la función para listar cliente al cargar la página
$(document).ready(function () {
    listarProveedor();
});
function actualizarlistarProveedor() {
    listarProveedor();
}
