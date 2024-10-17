var idLote = "";

//Filtro por codigo del lote
function buscarLotePorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: urlLote + "busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                mostrarTablaBajoStock(result);
            },
        });
    } else {
        listarLotesBajoStock();
    }
}

//Esto es para lo de bajo stock
$(document).ready(function () {
    listarLotesBajoStock();
});

function listarLotesBajoStock() {
    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlLote + "loteBajoStock/",
        type: "GET",
        headers: {
            "Authorization": "Bearer" + token
        },
        success: function (result) {
            mostrarTablaBajoStock(result);
        },
        error: function (xhr, status, error) {
            console.error("Error en la petición:", xhr.responseText);
            alert("Error en la petición: " + error);
        }
    });
}
//parte de lo de productos bajo stock
function mostrarTablaBajoStock(result) {
    var cuerpoTabla = document.getElementById("cuerpoTabla");
    cuerpoTabla.innerHTML = "";

    for (var i = 0; i < result.length; i++) {
        var lote = result[i]; // Asigna cada lote directamente desde result[i]
        var trRegistro = document.createElement("tr");
        trRegistro.innerHTML = `
             <td class="text-center align-middle">${lote.codigoLote}</td>
                <td class="text-center align-middle">${lote.producto.categoria.nombreCategoria}</td>
                <td class="text-center align-middle">${lote.producto.nombreProducto}</td>
                <td class="text-center align-middle">${lote.producto.marca.nombreMarca}</td>
                <td class="text-center align-middle">${lote.producto.stock}</td>
                <td class="text-center align-middle">
                    <button class="btn btn-secondary" onclick="verLote('${lote.numeroLote}')">Ver Lote</button>
                </td>
        `;
        cuerpoTabla.appendChild(trRegistro);
    }
}