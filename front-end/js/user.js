var url = "http://localhost:8080/api/v1/user/";

function registrarUser(event) {
    event.preventDefault(); // Evitar el comportamiento predeterminado del formulario

    let primerNombre = document.getElementById("primerNombre").value;
    let primerApellido = document.getElementById("primerApellido").value;
    let documentoIdentidad = document.getElementById("documentoIdentidad").value;
    let Correo = document.getElementById("Correo").value;
    let Celular = document.getElementById("Celular").value;
    let segundoNombre = document.getElementById("segundoNombre").value;
    let segundoApellido = document.getElementById("segundoApellido").value;
    let rol = document.getElementById("rol").value;

    // Datos del formulario
    let formData = {
        "primerNombre": primerNombre,
        "primerApellido": primerApellido,
        "documentoIdentidad": documentoIdentidad,
        "Correo": Correo,
        "Celular": Celular,
        "segundoNombre": segundoNombre,
        "segundoApellido": segundoApellido,
        "rol": rol
    };

    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        success: function (result) {
            Swal.fire({
                title: "Registro Exitoso",
                text: "Su registro fue exitoso, por favor revise su correo.",
                icon: "success",
                confirmButtonText: "Aceptar"
            }).then((result) => {
                if (result.isConfirmed) {
                    limpiarFormulario();
                    refrescarPagina();
                }
            });
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

function limpiarFormulario() {
    document.getElementById("register-form").reset();
}
function refrescarPagina() {
    location.reload();
}
