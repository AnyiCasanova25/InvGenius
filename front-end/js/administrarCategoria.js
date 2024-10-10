var registrarCategoriaBandera = true;
var idCategoria = "";

// var url = "http://localhost:8080/api/v1/categoria/";


$(document).ready(function () {
    listarCategoria();
});


function blanquearCampos() {
    document.getElementById('texto').value = "";
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

// Función para listar todos los proveedores
function listarCategoria() {
    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlCategoria,
        type: "GET",
        headers: {
            "Authorization": "Bearer" + token
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
                    <a href="../Categoria/categoriaProductos.html?id=${result[i]["idCategoria"]}" class="action-icon">
                        <i class="fas fa-eye"></i>
                    </a>
                    <a class="action-icon" data-bs-toggle="modal" data-bs-target="#agregarCategoria" data-id="${result[i]["idCategoria"]}">
                        <i class="fas fa-edit"></i>
                    </a>
                    <a class="action-icon" data-bs-toggle="modal" data-bs-target="#eliminarCategoria" data-id="${result[i]["idCategoria"]}">
                        <i class="fas fa-trash"></i>
                    </a>
                </div>
            </div>
        </div>
    `;
        // Inserta la tarjeta en el contenedor de la cuadrícula
        shopGrid.appendChild(divTarjeta);
    }
}


// <h3 class="title">Ubicación: ${result[i]["ubicacion"]}</h3>

// <p class="estado">Estado: ${result[i]["estado"]}</p>
function registrarCategoria() {
    var nombreCategoria = document.getElementById("nombreCategoria");
    var ubicacion = document.getElementById("ubicacion");
    var imagen_base = document.getElementById("imagen_base").files[0]; // Asegúrate de obtener el archivo de imagen

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
    formData.append("nombreCategoria", nombreCategoria.value);
    formData.append("estado", "activo"); // Asignar el estado automáticamente a "activo"
    formData.append("ubicacion", ubicacion.value);
    formData.append("file", imagen_base); // Agregar el archivo de imagen

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
                listarCategoria(); // Refrescar la tabla
            });
        },
        error: function (xhr, status, error) {
            console.error("Error en la petición:", xhr.responseText); // Agregar logs para depuración
            Swal.fire({
                title: "Error",
                text: "¡Error al registrar esta categoría!",
                icon: "error"
            });
        }
    });

    function validarCampos() {
        var nombreCategoria = document.getElementById("nombreCategoria").value.trim();
        var ubicacion = document.getElementById("ubicacion").value.trim();
        var imagen_base = document.getElementById("imagen_base").files.length;

        return nombreCategoria !== "" && ubicacion !== "" && imagen_base > 0;
    }

    // Función para limpiar el formulario
function limpiar() {
    document.querySelectorAll(".form-control").forEach(function (input) {
        input.value = "";
        input.className = "form-control";
    });
}

// Función para editar categoria
$(document).on("click", ".editar", function () {
    limpiar();
    idCategoria = $(this).data("id");
    registrarCategoriaBandera = false; // Cambiar bandera para editar

    $.ajax({
        url: urlCategoria + idCategoria,
        type: "GET",
        success: function (categoria) {
            document.getElementById("nombreCategoria").value = categoria.nombreCategoria;
            document.getElementById("ubicacion").value = categoria.ubicacion;
            document.getElementById("file").value = categoria.imagen_base;
            $('#agregarCategoria').modal('show');
        },
        error: function (error) {
            alert("Error al obtener los datos de categoria: " + error.statusText);
        }
    });
});
   
// Llamar a la función para listar proveedores al cargar la página
$(document).ready(function () {
    listarCategoria();
});
}