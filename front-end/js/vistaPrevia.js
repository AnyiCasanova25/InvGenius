var idLote = "";

$(document).ready(function () {
    listarLotes();
});

//Intento de hacer el filtro, no se si sirva
function vistaPreviaFiltro(filtro) {
    if(filtro.trim() !== "") {
        $.ajax({
            url: urlLote + "busquedaFiltros/" + filtro, // Asegúrate de pasar 'filtro' aquí
            type: "GET",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: function (result) {
                mostrarTabla(result);
            },
            error: function (xhr, status, error) {
                console.error("Error en la petición:", xhr.responseText);
                alert("Error en la petición: " + error);
            }
        });
    } else {
        listarLotes(); // Si no hay filtro, llamas al listado normal
    }
}

//Esto es para lo de bajo stock
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


function mostrarTablaBajoStock(result) {
    var cuerpoTabla = document.getElementById("cuerpoTabla");
    cuerpoTabla.innerHTML = "";

    for (var i = 0; i < result.length; i++) {
        var lote = result[i]; // Asigna cada lote directamente desde result[i]
        var trRegistro = document.createElement("tr");
        trRegistro.innerHTML = `
             <td class="text-center align-middle">${lote.numeroLote}</td>
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


// //Esto es para lo de producto caducados


// //Esto es para los de proximos a caducar