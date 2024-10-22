var registrarProductoBandera = true;
var idProducto = "";

// Función para buscar proveedores por filtro
function buscarProductoPorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: urlProducto + "busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                mostrarTarjetas(result);
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
    var cuerpoTarjetas = document.getElementById("cuerpoTarjetasProductos");
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
                <h3 class="title">${result[i]["nombreProducto"]}</h3>
                <div class="card-actions">
                    <a href="/front-end/html/Roles/Administrador/Categoria/lote.html?id=${result[i]["idProducto"]}" class="action-icon">
                        <i class="fas fa-eye" title="Ver Productos"></i>
                    </a>
                    <a class="action-icon" data-bs-toggle="modal" data-bs-target="#modalProducto">
                        <i class="fas fa-edit editar" data-id="${result[i]["idProducto"]}" title="Editar Categoria"></i>
                    </a>
                </div>
            </div>
        </div>
    `;
        // Inserta la tarjeta en el contenedor de la cuadrícula
        shopGrid.appendChild(divTarjeta);
    }
}

function blanquearCampos() { 
    document.getElementById('texto').value = "";
}

$(document).ready(function () {
    listarProductos();
});

function registrarProducto() {
    var nombreProducto = document.getElementById("nombreProducto");
    var marca = document.getElementById("marca");
    var unidadMedida = document.getElementById("unidadMedida");
    var categoria = document.getElementById("categoria");
    var imagen_base = document.getElementById("imagen_base").files[0]; 
    var descripcionProducto = document.getElementById("descripcionProducto");

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

    // Determinar si es un registro nuevo o una edición
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
        mimeType: "multipart/form-data",
        data: formData,
        success: function (response) {
            limpiar();
            Swal.fire({
                title: "LISTO",
                text: "Felicidades, Registro exitoso",
                icon: "success"
            }).then(function () {
                $('#modalProducto').modal('hide');
                listarProductos(); // Refrescar la lista
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
                    var option = document.createElement("option");
                    option.value = result[i].idCategoria;
                    option.text = result[i].nombreCategoria;
                    categoria.appendChild(option);
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
function limpiar() {
    document.querySelectorAll(".form-control").forEach(function (input) {
        input.value = "";
        input.className = "form-control";
    });
}

// Función para editar categoría
$(document).on("click", ".editar", function () {
    limpiar();
    idProducto = $(this).data("id");
    registrarProductoBandera = false; // Cambiar bandera para editar

    const token = localStorage.getItem('authTokens'); // Obtener token

    $.ajax({
        url: urlProducto + idProducto,
        type: "GET",
        headers: {
            "Authorization": "Bearer " + token
        },
        success: function (producto) {
            document.getElementById("nombreProducto").value = producto.nombreProducto;
            document.getElementById("unidadMedida").value = producto.unidadMedida;
            document.getElementById("descripcionProducto").value = producto.descripcionProducto;

            // Cargar marcas y seleccionar la correspondiente
            cargarMarca(function() {
                document.getElementById("marca").value = producto.marca.idMarca; // Usar el idMarca
            });

            // Cargar categorías y seleccionar la correspondiente
            cargarCategoria(function() {
                document.getElementById("categoria").value = producto.categoria.idCategoria; // Usar el idCategoria
            });

            $('#modalProducto').modal('show');
        },
        error: function (error) {
            alert("Error al obtener los datos del producto: " + error.statusText);
        }
    });
});

