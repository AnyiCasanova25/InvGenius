var url = "http://localhost:8080/api/v1/lote/";

//Intento de hacer el filtro, no se si sirva
function bajoStockFiltro(filtro){
    if(filtro.trim() !== ""){
        $.ajax({
            url: urlLote + "busquedaFiltros/" + filtro,
            type: "GET",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: function (result) {
                mostrarTabla(result);
            },
        });
    } else {
        listarLote();
    }
} 

//Esto es para lo de bajo stock

$(document).ready(function() {
    listarLotesBajoStock(); // Llama a la función cuando la página se cargue
});

function listarLotesBajoStock() {
    $.ajax({
        url: '/api/v1/lote/loteBajoStock/',
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            const cuerpoTabla = document.getElementById('cuerpoTabla');
            cuerpoTabla.innerHTML = ''; // Limpiar la tabla antes de llenarla

            if (response.length > 0) {
                response.forEach(producto => {
                    const fila = document.createElement('tr');
                    fila.innerHTML = `
                        <td class="text-center align-middle">${producto.lote}</td>
                        <td class="text-center align-middle">${producto.categoria}</td>
                        <td class="text-center align-middle">${producto.producto}</td>
                        <td class="text-center align-middle">${producto.marca}</td>
                        <td class="text-center align-middle">${producto.cantidad}</td>
                        <td class="text-center align-middle">
                            <button class="btn btn-secondary">Ver Lote</button>
                        </td>
                    `;
                    cuerpoTabla.appendChild(fila);
                });
            } else {
                const fila = document.createElement('tr');
                fila.innerHTML = `<td colspan="6" class="text-center">No se encontraron productos con bajo stock</td>`;
                cuerpoTabla.appendChild(fila);
            }
        },
        error: function(error) {
            console.error('Error al obtener los productos con bajo stock:', error);
        }
    });
}


//Esto es para lo de producto caducados
$(document).ready(function() {
    listarProductosCaducados(); // Llama a la función cuando la página se cargue
});

function listarProductosCaducados() {
    $.ajax({
        url: '/api/v1/lote/loteVencido/',
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            const cuerpoTabla = document.getElementById('cuerpoTabla');
            cuerpoTabla.innerHTML = ''; // Limpiar la tabla antes de llenarla

            if (response.length > 0) {
                response.forEach(producto => {
                    const fila = document.createElement('tr');
                    fila.innerHTML = `
                        <td class="text-center align-middle">${producto.numeroLote}</td>
                        <td class="text-center align-middle">${producto.nombreCategoria}</td>
                        <td class="text-center align-middle">${producto.nombreProducto}</td>
                        <td class="text-center align-middle">${producto.nombreMarca}</td>
                        <td class="text-center align-middle">${producto.fechaVencimiento}</td>
                        <td class="text-center align-middle">
                            <button class="btn btn-secondary">Ver Lote</button>
                        </td>
                    `;
                    cuerpoTabla.appendChild(fila);
                });
            } else {
                const fila = document.createElement('tr');
                fila.innerHTML = `<td colspan="6" class="text-center">No se encontraron productos caducados</td>`;
                cuerpoTabla.appendChild(fila);
            }
        },
        error: function(error) {
            console.error('Error al obtener los productos caducados:', error);
        }
    });
}


//Esto es para los de proximos a caducar
$(document).ready(function() {
    listarProductosProximosACaducar(); // Llama a la función cuando la página se cargue
});

function listarProductosProximosACaducar() {
    $.ajax({
        url: '/api/v1/lote/loteACaducar/',
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            const cuerpoTabla = document.getElementById('cuerpoTabla');
            cuerpoTabla.innerHTML = ''; // Limpiar la tabla antes de llenarla

            if (response.length > 0) {
                response.forEach(producto => {
                    const fila = document.createElement('tr');
                    fila.innerHTML = `
                        <td class="text-center align-middle">${producto.numeroLote}</td>
                        <td class="text-center align-middle">${producto.nombreCategoria}</td>
                        <td class="text-center align-middle">${producto.nombreProducto}</td>
                        <td class="text-center align-middle">${producto.nombreMarca}</td>
                        <td class="text-center align-middle">${producto.fechaVencimiento}</td>
                        <td class="text-center align-middle">
                            <button class="btn btn-secondary">Ver Lote</button>
                        </td>
                    `;
                    cuerpoTabla.appendChild(fila);
                });
            } else {
                const fila = document.createElement('tr');
                fila.innerHTML = `<td colspan="6" class="text-center">No se encontraron productos próximos a caducar</td>`;
                cuerpoTabla.appendChild(fila);
            }
        },
        error: function(error) {
            console.error('Error al obtener los productos próximos a caducar:', error);
        }
    });
}
