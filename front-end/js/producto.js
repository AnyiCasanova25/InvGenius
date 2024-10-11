var registrarProductoBandera = true;
var idProducto = "";

var urlMarca 
var urlCategoria


// Función para buscar proveedores por filtro
function buscarProductoPorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: urlProveedor + "busquedaFiltros/" + filtro,
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
            mostrarTarjetas(result);
        },
        error: function (xhr, status, error) {
            console.error("Error en la petición:", xhr.responseText);
            alert("Error en la petición: " + error);
        }
    });
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
    formData.append("unidadMedida", unidadMedida.value);
    formData.append("categoria", categoria.value);
    formData.append("file", imagen_base); // Agregar el archivo de imagen
    formData.append("descripcionProducto", descripcionProducto.value);


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
                $('#exampleModalLabel').modal('hide');
                listarProductos(); // Refrescar la tabla
            });
        },
        error: function (xhr, status, error) {
            console.error("Error en la petición:", xhr.responseText); // Agregar logs para depuración
            Swal.fire({
                title: "Error",
                text: "¡Error al registrar el producto!",
                icon: "error"
            });
        }
    });  
}

function validarCampos() {
    var nombreProducto = document.getElementById("nombreProducto").value.trim();
    var marca = document.getElementById("marca").value;
    var unidadMedida = document.getElementById("unidadMedida").value;
    var categoria = document.getElementById("categoria").value;
    var imagen_base = document.getElementById("imagen_base").files.length;
    var descripcionProducto = document.getElementById("descripcionProducto").value.trim();

    return nombreProducto && marca && unidadMedida && categoria && imagen_base > 0 && descripcionProducto.length >= 50;
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
                    var option = document.createElement("option");
                    option.value = result[i].idMarca;
                    option.text = result[i].nombreMarca;
                    marca.appendChild(option);
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



