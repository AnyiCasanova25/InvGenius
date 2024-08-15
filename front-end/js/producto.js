var url = "http://localhost:8080/v1/movimientos/";


function registrarProducto() {
    let categoria = document.getElementById("categoria").value;
    let nombreProducto = document.getElementById("nombreProducto").value;
    let proveedor = document.getElementById("proveedor").value;
    let cantidadProducto = document.getElementById("cantidadProducto").value;
    let precioEntrada = document.getElementById("precioEntrada").value;
    let precioUnitario = document.getElementById("precioUnitario").value;
    let fechaIngreso = document.getElementById("fechaIngreso").value;
    let fechaCaducidad = document.getElementById("fechaCaducidad").value;


    // Crear el objeto producto
    let formData = {
        categoria: categoria,
        nombreProducto: nombreProducto,
        proveedor: proveedor,
        precioEntrada: precioEntrada,
        cantidadProducto: cantidadProducto,
        precioUnitario:precioUnitario,
        fechaIngreso:fechaIngreso,
        fechaCaducidad:fechaCaducidad
    };

    // Enviar el producto al servidor
    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        success: function (result) {
            Swal.fire({
                title: "Registro Exitoso",
                text: "El producto se registró exitosamente.",
                icon: "success",
                confirmButtonText: "Aceptar"
            }).then((result) => {
                if (result.isConfirmed) {
                    limpiarFormulario();
                    listarProductos(); // Actualizar la lista de productos
                }
            });
        },
        error: function (error) {
            Swal.fire({
                title: "Error",
                text: "Error al registrar el producto.",
                icon: "error"
            });
        }
    });
}


