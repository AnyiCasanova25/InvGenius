var registrarCategoriaBandera = true;
var idCategoria = "";

function redireccionCategoriaProducto() {
    // Obtener los valores almacenados en localStorage
    const token = localStorage.getItem('authTokens');
    const rol = localStorage.getItem('userRol');
    const estado = localStorage.getItem('userEstado');

    // Verificar que el token exista
    if (token) {
        // Verificar el estado y redirigir según el rol
        if (estado === "Activo") {
            if (rol === "Admin") {
                window.location.href = "/front-end/html/Roles/Administrador/Categoria/categoriaProductos.html";
            } else if (rol === "User") {
                window.location.href = "/front-end/html/Roles/Usuario/Categoria/categoriaProductos.html";
            } else {
                Swal.fire({
                    title: "Error",
                    text: "Rol desconocido. Contacta con el administrador.",
                    icon: "error",
                    confirmButtonText: "Aceptar"
                });
            }
        } else {
            Swal.fire({
                title: "Cuenta Inactiva",
                text: "Tu cuenta está inactiva. Por favor, contacta al administrador.",
                icon: "warning",
                confirmButtonText: "Aceptar"
            });
        }
    } else {
        Swal.fire({
            title: "Error",
            text: "Token no encontrado. Inicia sesión nuevamente.",
            icon: "error",
            confirmButtonText: "Aceptar"
        });
    }
}
// Función para buscar proveedores por filtro
function buscarCategoriaPorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: urlCategoria + "busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                mostrarTarjetas(result);
            },
        });
    } else {
        listarCategoria();
    }
}

function listarCategoria() {
    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlCategoria,
        type: "GET",
        headers: {
            "Authorization": "Bearer " + token
        },
        success: function (result) {
            mostrarTarjetas(result);
        },
        error: function (xhr, status, error) {
            console.error("Error en la petición:", xhr.responseText);
            alert("Error en la petición: " + error);
        }
    });
}

// Función para mostrar los proveedores en la tabla
function mostrarTarjetas(result) {
    var cuerpoTarjetas = document.getElementById("cuerpoTarjetas");
    cuerpoTarjetas.innerHTML = "";

    // Selecciona el contenedor de la cuadrícula
    var shopGrid = document.querySelector('.shop-grid');

    // Itera sobre los resultados y crea las tarjetas
    for (var i = 0; i < result.length; i++) {
        var divTarjeta = document.createElement("div");
        divTarjeta.innerHTML = `
        <div class="shop-card">
            <div class="card-body">
            <figure>
                <img src="data:image/png;base64,${result[i]["imagen_base"]}">
            </figure>
                <h3 class="title">${result[i]["nombreCategoria"]}</h3>
                <div class="card-actions">
                <a class="action-icon" onclick="redireccionCategoriaProducto()">
                    <i class="fas fa-eye" title="Ver Productos"></i>
                </a>

                    <a class="action-icon" data-bs-toggle="modal" data-bs-target="#agregarCategoria">
                        <i class="fas fa-edit editar" data-id="${result[i]["idCategoria"]}" title="Editar Categoria"></i>
                    </a>
                </div>
            </div>
        </div>
    `;
        // Inserta la tarjeta en el contenedor de la cuadrícula
        shopGrid.appendChild(divTarjeta);
    }
}
//<a class="action-icon">
//    <i class="fas fa-trash eliminar" data-id="${result[i]["idCategoria"]}" title="Eliminar Categoria"></i>
//</a>


function blanquearCampos() {
    document.getElementById('texto').value = "";
}

function registrarCategoria() {
    var nombreCategoria = document.getElementById("nombreCategoria");
    var ubicacion = document.getElementById("ubicacion");
    var imagen_base = document.getElementById("imagen_base").files[0]; // Obtener el archivo de imagen solo si se selecciona

    // Verificar si los campos son válidos
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
    formData.append("nombreCategoria", nombreCategoria.value);
    formData.append("estado", "Activo");
    formData.append("ubicacion", ubicacion.value);

    // Solo agregar la imagen si se selecciona una nueva
    if (imagen_base) {
        formData.append("file", imagen_base);
    }

    // Determinar si es un registro nuevo o una edición
    var metodo = registrarCategoriaBandera ? "POST" : "PUT";
    var urlLocal = registrarCategoriaBandera ? urlCategoria : urlCategoria + idCategoria;

    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlLocal,
        type: metodo,
        headers: {
            'Authorization': 'Bearer ' + token
        },
        processData: false,
        contentType: false,
        mimeType: "multipart/form-data",
        data: formData,
        success: function (response) {
            limpiar();
            Swal.fire({
                title: "LISTO",
                text: "Felicidades, Registro exitoso",
                icon: "success"
            }).then(function () {
                $('#agregarCategoria').modal('hide');
                listarCategoria(); // Refrescar la lista
            });
        },
        error: function (xhr, status, error) {
            console.error("Error en la petición:", xhr.responseText);

            // Parsear la respuesta JSON si es posible
            let errorMessage = "¡Error al registrar esta categoría!";

            try {
                const response = JSON.parse(xhr.responseText);
                if (response.message) {
                    errorMessage = response.message;
                }
            } catch (e) {
                console.error("No se pudo parsear la respuesta JSON:", e);
            }

            Swal.fire({
                title: "Error",
                text: errorMessage,
                icon: "error"
            });
        }

    });
}



function validarCampos() {
    var nombreCategoria = document.getElementById("nombreCategoria");
    var ubicacion = document.getElementById("ubicacion");
    var imagen_base = document.getElementById("imagen_base");

    // Validar solo si es un registro nuevo, no durante la edición
    if (registrarCategoriaBandera) {
        return validarnombreCategoria(nombreCategoria) &&
            validarubicacion(ubicacion) &&
            validarimagen_base(imagen_base.files.length);
    } else {
        // Solo validar nombre y ubicación durante la edición
        return validarnombreCategoria(nombreCategoria) && validarubicacion(ubicacion);
    }
}


// Función de validación genérica
function validarCampo(cuadroNumero, minLength, maxLength) {
    var valor = cuadroNumero.value.trim();  // `cuadroNumero` es el input, no su valor directamente
    var valido = (valor.length >= minLength && valor.length <= maxLength);
    cuadroNumero.className = "form-control " + (valido ? "is-valid" : "is-invalid");
    return valido;
}

function validarnombreCategoria(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 41);  // Aquí pasas el input completo
}

function validarubicacion(cuadroNumero) {
    return validarCampo(cuadroNumero, 1, 21);
}

function validarimagen_base(imagenLength) {
    // La imagen no tiene `.trim()`, así que no puedes usar `validarCampo`
    return imagenLength > 0;  // Verifica si hay una imagen seleccionada
}

// Función para limpiar el formulario
function limpiar() {
    document.querySelectorAll(".form-control").forEach(function (input) {
        input.value = "";
        input.className = "form-control";
    });
}

// Función para editar categoría
$(document).on("click", ".editar", function () {
    limpiar();
    idCategoria = $(this).data("id");
    registrarCategoriaBandera = false; // Cambiar bandera para editar

    const token = localStorage.getItem('authTokens'); // Obtener token

    $.ajax({
        url: urlCategoria + idCategoria,
        type: "GET",
        headers: {
            "Authorization": "Bearer " + token
        },
        success: function (categoria) {
            document.getElementById("nombreCategoria").value = categoria.nombreCategoria;
            document.getElementById("ubicacion").value = categoria.ubicacion;
            // El campo de imagen no se puede prellenar
            $('#agregarCategoria').modal('show');
        },
        error: function (error) {
            alert("Error al obtener los datos de la categoría: " + error.statusText);
        }
    });
});



// Llamar a la función para listar proveedores al cargar la página
$(document).ready(function () {
    listarCategoria();
});