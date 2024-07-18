url: "http://localhost:8080/api/v1/user"

function registrarUser() {
    let primer_nombre = document.getElementById("primerNombre").value;
    let primer_apellido = document.getElementById("primerApellido").value;
    let documento_identidad = document.getElementById("documentoIdentidad").value;
    let correo = document.getElementById("Correo").value;


    // Datos del formulario
    let formData = {
        "primer_nombre": primer_nombre,
        "primer_apellido": primer_apellido,
        "documento_identidad": documento_identidad,
        "correo": correo
    };

    $.ajax({
        url: url,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(formData),
        success: function (result) {
            Swal.fire({
                title: "¡Excelente!",
                text: "Se guardó correctamente",
                icon: "success"
            });
            limpiarFormulario();
        },
        error: function (error) {
            Swal.fire({
                title: "Error",
                text: "Error al guardar el libro",
                icon: "error"
            });
        }
    });
}
