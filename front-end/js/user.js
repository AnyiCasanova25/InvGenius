var url = "http://localhost:8080/api/v1/user/";

function registrarUser(event) {
    event.preventDefault(); // Evitar el comportamiento predeterminado del formulario

    let nombres = document.getElementById("Nombres").value;
    let apellidos = document.getElementById("Apellidos").value;
    let documentoIdentidad = document.getElementById("documentoIdentidad").value;
    let Correo = document.getElementById("Correo").value;
    let Celular = document.getElementById("Celular").value;
    let rol = document.getElementById("rol").value;

    // Datos del formulario
    let formData = {
        "nombres": nombres,
        "apellidos": apellidos,
        "documentoIdentidad": documentoIdentidad,
        "Correo": Correo,
        "Celular": Celular,
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
