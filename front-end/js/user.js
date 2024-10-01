var url = "http://localhost:8080/api/v1/public/user/";
// var url = "http://10.192.80.141:8080/api/v1/public/user/";

function registrarUser() {
    // event.preventDefault(); // Evitar el comportamiento predeterminado del formulario

    // Obtener los valores de los campos del formulario
    let nombres = document.getElementById("Nombres").value;
    let apellidos = document.getElementById("Apellidos").value;
    let documentoIdentidad = document.getElementById("documentoIdentidad").value;
    let tipoDocumento = document.getElementById("tipoDocumento").value;
    let estado = document.getElementById("estado").value;
    let genero = document.getElementById("genero").value;
    let userName = document.getElementById("userName").value;
    let celular = document.getElementById("Celular").value;
    let rol = document.getElementById("rol").value;
    let imagenUser = document.getElementById("imagenUser").value;

    // Datos del formulario
    let formData = {
        "nombres": nombres,
        "apellidos": apellidos,
        "tipoDocumento": tipoDocumento,
        "documentoIdentidad": documentoIdentidad,
        "genero": genero,
        "estado":estado ,
        "userName": userName,
        "celular": celular,
        "rol": rol,
        "imagenUser": imagenUser
    };

    // Enviar los datos mediante AJAX
    $.ajax({
        url: url + "register/",
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
                    // Redirigir a otra página
                    window.location.href = "/front-end/html/Roles/Administrador/administrarPerfiles.html"; // Reemplaza 'url_de_destino' por la URL a la que quieras redirigir
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

