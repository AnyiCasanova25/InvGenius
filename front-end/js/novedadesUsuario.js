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


function soloLetras(event) {
    console.log("Llave presionada: " + event.key);
    console.log("Codigo tecla: " + event.keyCode);

    if (!(letrasPermitidas.includes(event.key))) {
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

function mostrarTabla(result) {
    var cuerpoTabla = document.getElementById("cuerpoTabla");
    cuerpoTabla.innerHTML = "";

    for (var i = 0; i < result.length; i++) {
        var trRegistro = document.createElement("tr");
        trRegistro.innerHTML = `
            <td class="text-center align-middle">${result[i]["para"]}</td>
            <td class="text-center align-middle">${result[i]["asunto"]}</td>
            <td class="text-center align-middle">${result[i]["fechaNovedad"]}</td>
            <td class="text-center align-middle">
                <i class="fas fa-eye editar" onclick="registrarNovedadUsuario=false;" data-id="${result[i]["idNovedad"]}"></i>
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

var registrarNovedadUsuario = true;

function registrarNovedad() {
    var para = document.getElementById("para");
    var asunto = document.getElementById("asunto");
    var cuerpo = document.getElementById("cuerpo");
    var evidencia = document.getElementById("evidencia");

    // Verificar si algún campo obligatorio está vacío
    if (!validarpara(para) ||
        !validarasunto(asunto) ||
        !validarcuerpo(cuerpo) ||
        !validarevidencia(evidencia)) {
        // Mostrar una alerta indicando que todos los campos son obligatorios
        Swal.fire({
            title: "¡Error!",
            text: "¡Llene todos los campos correctamente!",
            icon: "error"
        });
        return; // Salir de la función si algún campo está vacío
    }

    var forData = {
        "para": para.value,
        "asunto": asunto.value,
        "cuerpo": cuerpo.value,
        "evidencia": evidencia.value,
    };

    var metodo = "";
    var urlLocal = "";
    var textoimprimir = "";
    if (registrarNovedadUsuario == true) {
        metodo = "POST";
        urlLocal = url;

    } else {
        metodo = "PUT";
        urlLocal = url + idNovedad;
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
                    listarNovedadUsuario(); // Aquí se vuelve a listar los productos
                });
            },
            error: function (xhr, status, error) {
                Swal.fire({
                    title: "Error",
                    text: "¡El número de documento ya se encuentra registrado!",
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
    var evidencia = document.getElementById("evidencia");

    return validarpara(para) && validarasunto(asunto) && validarcuerpo(cuerpo) && validarevidencia(evidencia);
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

function validarevidencia(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 101);
}

function limpiar() {
    document.getElementById("para").value = "";
    document.getElementById("para").className = "form-control";
    document.getElementById("asunto").value = "";
    document.getElementById("asunto").className = "form-control";
    document.getElementById("cuerpo").value = "";
    document.getElementById("cuerpo").className = "form-control";
    document.getElementById("evidencia").value = "";
    document.getElementById("evidencia").className = "form-control";
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
            document.getElementById("evidencia").value = novedad.evidencia;
            $('#exampleModal').modal('show');
        },
        error: function (error) {
            alert("Error al obtener los datos de la novedad: " + error.statusText);
        }
    });
});


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
            listarNovedadUsuario ();
        }
    });
});

// Llamar a la función para listar cliente al cargar la página
$(document).ready(function () {
    listarNovedadUsuario();
});
function actualizarlistarNovedadUsuario() {
    listarNovedadUsuario();
}

document.addEventListener('DOMContentLoaded', function () {
    var comentario = document.getElementById('cuerpo');
    var charCount = document.getElementById('charCount');

    comentario.addEventListener('input', function () {
        var count = this.value.length;
        charCount.textContent = count;

        if (count > 500) {
            this.value = this.value.slice(0, 500); // Limitar el valor a 500 caracteres
            charCount.textContent = 500;
        }
    });
});