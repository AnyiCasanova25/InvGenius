var registrarCategoriaBandera = true;
var idCategoria = "";

// Función para buscar proveedores por filtro
function buscarCategoriaPorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: urlCategoria + "busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                mostrarTabla(result);
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
            mostrarTabla(result);
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

    for (var i = 0; i < result.length; i++) {
        var divTarjeta = document.createElement("div");
        divTarjeta.classList.add("shop-card");

        divTarjeta.innerHTML = `
            <div class="card-body">
                <figure>
                    <img src="/front-end/img/Categorias/${result[i]["idCategoria"]}/001.png" alt="${result[i]["asunto"]}">
                </figure>
                <h3 class="title">${result[i]["asunto"]}</h3>
                <div class="card-actions">
                    <a href="../Categoria/categoriaProductos.html" class="action-icon">
                        <i class="fas fa-eye"></i>
                    </a>
                    <a class="action-icon" data-bs-toggle="modal" data-bs-target="#agregarCategoria">
                        <i class="fas fa-edit"></i>
                    </a>
                    <a class="action-icon" data-bs-toggle="modal" data-bs-target="#eliminarCategoria">
                        <i class="fas fa-trash"></i>
                    </a>
                </div>
            </div>
        `;

        cuerpoTarjetas.appendChild(divTarjeta);
    }
}
