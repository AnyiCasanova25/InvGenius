var url = "http://localhost:8080/api/v1/user/";

function registrarUser() {
    let primerNombre = document.getElementById("primerNombre").value;
    let primerApellido = document.getElementById("primerApellido").value;
    let documentoIdentidad = document.getElementById("documentoIdentidad").value;
    let Correo = document.getElementById("Correo").value;
    let Celular = document.getElementById("Celular").value;
    let segundoNombre = document.getElementById("segundoNombre").value;
    let segundoApellido = document.getElementById("segundoApellido").value;
    


    // Datos del formulario
    let formData = {
        "primerNombre": primerNombre,
        "primerApellido": primerApellido,
        "documentoIdentidad": documentoIdentidad,
        "Correo": Correo,
        "Celular": Celular,
        "segundoNombre": segundoNombre,
        "segundoApellido": segundoApellido
    };

    $.ajax({
        url: url,
        type: "POST",
        data: formData,
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
                text: "Error al guardar el usuario",
                icon: "error"
            });
        }
    });
}
