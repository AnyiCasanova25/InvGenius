// URL de la API se declara una url por si se modifica solo cambiar donde la declaro y no entodas las demas partes
var url = "http://localhost:8080/api/v1/novedad/";

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

function alfaNumericosSignos(event) {
    console.log("Llave presionada: " + event.key);
    console.log("Codigo tecla: " + event.keyCode);

    if (!((numerosPermitidos.includes(event.key)) || (letrasPermitidas.includes(event.key)) || (signosPermitidos.includes(event.key)))) {
        event.preventDefault();
        return;
    }
}

function mostrarTabla(result) {
    var cuerpoTabla = document.getElementById("cuerpoTabla");
    cuerpoTabla.innerHTML = "";

    for (var i = 0; i < result.length; i++) {
        var trRegistro = document.createElement("tr");
        trRegistro.innerHTML = `
            <td class="align-middle">${result[i]["asunto"]}</td>
            <td class="text-center align-middle">${result[i]["fechaNovedad"]}</td>
            <td class="text-center align-middle">
                <span class="editar" data-id="${result[i]["idNovedad"]}">Ver Detalle</span>
            </td>

        `;
        cuerpoTabla.appendChild(trRegistro);
    }
}

function buscarNovedadUsuarioFiltro(filtro) {
    if (filtro.trim() !== "") {
        var url = tipo === "asunto" ? "http://localhost:8080/api/v1/novedad/busquedafiltro/" : "http://localhost:8080/api/v1/novedad/busquedaFiltrosFecha/";
        var parametro = tipo === "asunto" ? filtro : encodeURIComponent(filtro);
        $.ajax({
            url: url + parametro,
            type: "GET",
            success: function (result) {
                mostrarTabla(result);
            },
        });
    } else {
        listarNovedadUsuario();
    }
}

function listarNovedadUsuario() {
    $.ajax({
        url: url,
        type: "GET",
        success: function (result) {
            mostrarTabla(result);
        },
        error: function (error) {
            alert("Error en la petición: " + error);
        }
    });
}

function blanquearCampos() {
    document.getElementById('texto').value = "";
}


function registrarNovedadUsuario() {
    var asunto = document.getElementById("asunto");
    var para = document.getElementById("para");
    var cuerpo = document.getElementById("cuerpo");
    var fechaNovedad = document.getElementById("fechaNovedad");

    // Verificar si algún campo obligatorio está vacío
    if (!validarasunto(asunto) ||
        !validarpara(para) ||
        !validarcuerpo(cuerpo)) {
        // Mostrar una alerta indicando que todos los campos son obligatorios
        Swal.fire({
            title: "¡Error!",
            text: "¡Llene todos los campos correctamente!",
            icon: "error"
        });
        return; // Salir de la función si algún campo está vacío
    }

    var forData = {
        "asunto": asunto.value,
        "para": para.value,
        "cuerpo": cuerpo.value,
        "fechaNovedad": fechaNovedad.value,
    };

    var metodo = "";
    var urlLocal = "";
    var textoimprimir = "";
        metodo = "POST";
        urlLocal = url;

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
                    listarNovedadUsuario(); // Aquí se vuelve a listar los productos
                });
            },
            error: function (xhr, status, error) {
                Swal.fire({
                    title: "Error",
                    // text: "¡El número de documento ya se encuentra registrado!",
                    icon: "error"
                });
            }
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
    var para = document.getElementById("para");
    var asunto = document.getElementById("asunto");
    var cuerpo = document.getElementById("cuerpo");

    return validarpara(para) && validarasunto(asunto) && validarcuerpo(cuerpo);
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


function validarpara(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 101);
}
function validarasunto(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 81);
}
function validarcuerpo(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 501);
}


function limpiar() {
    document.getElementById("validarpara").value = "";
    document.getElementById("validarpara").className = "form-control";
    document.getElementById("validarasunto").value = "";
    document.getElementById("validarasunto").className = "form-control";
    document.getElementById("validarcuerpo").value = "";
    document.getElementById("validarcuerpo").className = "form-control";
    document.getElementById("fechaNovedad").value = "";
    document.getElementById("fechaNovedad").className = "form-control";
}

var idNovedad = "";
$(document).on("click", ".editar", function () {
    limpiar();
    idNovedad = $(this).data("id");

    $.ajax({
        url: url + idNovedad,
        type: "GET",
        success: function (novedad) {
            document.getElementById("para").value = novedad.para;
            document.getElementById("asunto").value = novedad.asunto;
            document.getElementById("cuerpo").value = novedad.cuerpo;
            $('#exampleModalDetalle').modal('show');
        },
        error: function (error) {
            alert("Error al obtener los datos de la novedad: " + error.statusText);
        }
    });
});

$(document).ready(function () {
    listarNovedadUsuario();
});
function actualizarlistarNovedadUsuario() {
    listarNovedadUsuario();
}

document.addEventListener('DOMContentLoaded', function () {
    var comentario = document.getElementById('comentario');
    var charCount = document.getElementById('charCount');

    comentario.addEventListener('input', function () {
        var count = this.value.length;
        charCount.textContent = count;

        if (count > 500) {
            this.value = this.value.slice(0, 500); // Limitar el valor a 100 caracteres
            charCount.textContent = 500;
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var fechaIngreso = document.getElementById('fechaIngreso');
    var hoy = new Date();
    var fechaActual = hoy.getFullYear() + '-' + ('0' + (hoy.getMonth() + 1)).slice(-2) + '-' + ('0' + hoy.getDate()).slice(-2);

    fechaIngreso.value = fechaActual;
});
