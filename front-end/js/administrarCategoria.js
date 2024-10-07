var registrarCategoriaBandera = true;
var idCategoria = "";


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
            'Authorization': 'Bearer ' + token
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
    var estado = document.getElementById("estado");
    var ubicacion = document.getElementById("ubicacion");
    var imagen_base = document.getElementById("imagen_base");

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
        "nombreCategoria": nombreCategoria.value,
        "estado": estado.value,
        "ubicacion": ubicacion.value,
        "imagen_base": imagen_base.value,
    };

    var metodo = registrarCategoriaBandera ? "POST" : "PUT";
    var urlLocal = registrarCategoriaBandera ? urlCategoria : urlCategoria + idCategoria;

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
                listarProveedor(); // Refrescar la tabla
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