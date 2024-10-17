var registrarProductoBandera = true;
var idProducto = "";


$(document).ready(function () {
    listarProductos();
});


function blanquearCampos() {
    document.getElementById('texto1').value = "";
    document.getElementById('texto2').value = "";
}

// Función para buscar proveedores por filtro
function buscarProductoPorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: urlProducto + "busquedaFiltros/" + filtro,
            type: "GET",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: function (result) {
                mostrarTabla(result);
            },
        });
    } else {
        listarProductos();
    }
}

function listarProductos() {
    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlProducto,
        type: "GET",
        headers: {
            "Authorization": "Bearer" + token
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

function mostrarTabla(result) {
    var cuerpoTabla = document.getElementById("cuerpoTablaEntradaProducto");
    cuerpoTabla.innerHTML = "";

    for (var i = 0; i < result.length; i++) {
        var trRegistro = document.createElement("tr");
        trRegistro.innerHTML = `
            <td class="text-center align-middle">${result[i]["categoria"]["nombreCategoria"]}</td>
            <td class="text-center align-middle">${result[i]["nombreProducto"]}</td>
            <td class="text-center align-middle">${result[i]["marca"]["nombreMarca"]}</td>
            <td class="text-center align-middle">${result[i]["unidadMedida"]}</td>
            <td class="text-center align-middle">
                <i class="fa-solid fa-box editar" data-id="${result[i]["idProducto"]}" title="Entrar Producto"></i>
            </td>
        `;
        cuerpoTabla.appendChild(trRegistro);
    }
}


function registrarProducto() {
    var nombreProducto = document.getElementById("nombreProducto");
    var marca = document.getElementById("marca");
    var unidadMedida = document.getElementById("unidadMedida");
    var categoria = document.getElementById("categoria");
    var imagen_base = document.getElementById("imagen_base").files[0]; // Asegúrate de obtener el archivo de imagen
    var descripcionProducto = document.getElementById("descripcionProducto");

    // Verificar si algún campo obligatorio está vacío
    if (!validarCampos()) {
        Swal.fire({
            title: "¡Error!",
            text: "¡Llene todos los campos correctamente!",
            icon: "error"
        });
        return; // Salir si algún campo es inválido
    }

    // Crear un objeto FormData
    var formData = new FormData();
    formData.append("nombreProducto", nombreProducto.value);
    formData.append("marca", marca.value);
    formData.append("estadoProducto", "Activo");
    formData.append("unidadMedida", unidadMedida.value);
    formData.append("categoria", categoria.value);
    formData.append("descripcionProducto", descripcionProducto.value);

    // Solo agregar la imagen si se selecciona una nueva
    if (imagen_base) {
        formData.append("file", imagen_base);
    }

    var metodo = registrarProductoBandera ? "POST" : "PUT";
    var urlLocal = registrarProductoBandera ? urlProducto : urlProducto + idProducto;

    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlLocal,
        type: metodo,
        headers: {
            'Authorization': 'Bearer ' + token
        },
        processData: false,
        contentType: false,
        data: formData,
        success: function (response) {
            limpiar();
            Swal.fire({
                title: "LISTO",
                text: "Felicidades, Registro exitoso",
                icon: "success"
            }).then(function () {
                $('#modalProducto').modal('hide'); // Cerrar modal si el registro es exitoso
                listarProductos(); // Refrescar la tabla
            });
        },
        error: function (xhr, status, error) {
            console.error("Error en la petición:", xhr.responseText); // Agregar logs para depuración
            Swal.fire({
                title: "Error",
                text: "¡Error al registrar el producto! Corrija los errores y vuelva a intentar.",
                icon: "error"
            });
            // No cerrar el modal aquí para permitir corrección de errores
        }
    });
}


function validarCampos() {
    var nombreProducto = document.getElementById("nombreProducto");
    var marca = document.getElementById("marca");
    var unidadMedida = document.getElementById("unidadMedida");
    var categoria = document.getElementById("categoria");
    var imagen_base = document.getElementById("imagen_base");
    var descripcionProducto = document.getElementById("descripcionProducto");

    return validarnombreProducto(nombreProducto) && validarmarca(marca) 
    && validarunidadMedida(unidadMedida) && validarcategoria(categoria) 
    && validardescripcionProducto(descripcionProducto) 
    && validarimagen_base(imagen_base.files.length);
}

// Función de validación genérica
function validarCampo(cuadroNumero, minLength, maxLength) {
    var valor = cuadroNumero.value.trim();  // `cuadroNumero` es el input, no su valor directamente
    var valido = (valor.length >= minLength && valor.length <= maxLength);
    cuadroNumero.className = "form-control " + (valido ? "is-valid" : "is-invalid");
    return valido;
}

function validarnombreProducto(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 41);  // Aquí pasas el input completo
}

function validarmarca(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 41);  // Aquí pasas el input completo
}

function validarunidadMedida(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 41);  // Aquí pasas el input completo
}

function validarcategoria(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 41);  // Aquí pasas el input completo
}

function validardescripcionProducto(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 101);  // Aquí pasas el input completo
}

function validarimagen_base(imagenLength) {
    // La imagen no tiene `.trim()`, así que no puedes usar `validarCampo`
    return imagenLength > 0;  // Verifica si hay una imagen seleccionada
}


function cargarMarca() {
    var marca = document.getElementById("marca");

    if (marca) {
        // Limpiar las opciones actuales
        marca.innerHTML = "";

        $.ajax({
            url: urlMarca,
            type: "GET",
            success: function (result) {
                for (var i = 0; i < result.length; i++) {
                    // Filtrar solo las marcas cuyo estado sea 'Activo'
                    if (result[i].estado === "Activo") {
                        var option = document.createElement("option");
                        option.value = result[i].idMarca;
                        option.text = result[i].nombreMarca;
                        marca.appendChild(option);
                    }
                }
            },
            error: function (error) {
                console.error("Error al obtener la lista de marcas: " + error);
            }
        });
    } else {
        console.error("Elemento con ID 'marca' no encontrado.");
    }
}


function cargarCategoria() {
    var categoria = document.getElementById("categoria");

    if (categoria) {
        // Limpiar las opciones actuales
        categoria.innerHTML = "";

        $.ajax({
            url: urlCategoria,
            type: "GET",
            success: function (result) {
                for (var i = 0; i < result.length; i++) {
                    // Si existe una propiedad 'estado', filtrar solo las categorías activas
                    if (result[i].estado === "Activo") {
                        var option = document.createElement("option");
                        option.value = result[i].idCategoria;
                        option.text = result[i].nombreCategoria;
                        categoria.appendChild(option);
                    }
                }
            },
            error: function (error) {
                console.error("Error al obtener la lista de categorías: " + error);
            }
        });
    } else {
        console.error("Elemento con ID 'categoria' no encontrado.");
    }
}


function limpiar() {
    document.querySelectorAll(".form-control").forEach(function (input) {
        input.value = "";
        input.className = "form-control";
    });
}


