var idLote = "";

//Filtro por codigo del lote
function buscarLotePorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: urlLote + "busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                mostrarTablaLoteVencidos(result);
            },
        });
    } else {
        listarLotesVencido();
    }
}


// //Esto es para lo de producto caducados
$(document).ready(function () {
    listarLotesVencido();
});

function listarLotesVencido() {
    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlLote + "loteVencido/",
        type: "GET",
        headers: {
            "Authorization": "Bearer " + token
        },
        success: function (result) {
            mostrarTablaLoteVencidos(result);
        },
        error: function (xhr, status, error) {
            console.error("Error en la petición:", xhr.responseText);
            alert("Error en la petición: " + error);
        }
    });
}
//parte para lo de lotes caducados
function mostrarTablaLoteVencidos(result) {
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
                <td class="text-center align-middle">${lote.fechaVencimiento}</td>
                <td class="text-center align-middle">
                    <button class="btn btn-secondary" onclick="verLote('${lote.numeroLote}')">Ver Lote</button>
                </td>
        `;
        cuerpoTabla.appendChild(trRegistro);
    }
}