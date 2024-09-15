// var url = "http://10.192.80.134:8080/api/v1/user/";
var url = "http://localhost:8080/api/v1/user/";

function registrarUser(event) {
    event.preventDefault(); // Evitar el comportamiento predeterminado del formulario

    // Obtener los valores de los campos del formulario
    let nombres = document.getElementById("Nombres").value;
    let apellidos = document.getElementById("Apellidos").value;
    let documentoIdentidad = document.getElementById("documentoIdentidad").value;
    let correo = document.getElementById("Correo").value;
    let celular = document.getElementById("Celular").value;
    let rol = document.getElementById("rol").value;

    // Datos del formulario
    let formData = {
        "nombres": nombres,
        "apellidos": apellidos,
        "documentoIdentidad": documentoIdentidad,
        "correo": correo,
        "celular": celular,
        "rol": rol
    };

    // Enviar los datos mediante AJAX
    $.ajax({
        url: url,
        type: "POST",
        data: JSON.stringify(formData), // Convertir los datos a JSON
        contentType: "application/json", // Establecer el tipo de contenido
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
    document.getElementById("register-form").reset(); // Limpiar todos los campos del formulario
}

function refrescarPagina() {
    location.reload(); // Recargar la página
}
