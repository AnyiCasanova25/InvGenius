function registrarUser() {
    // Obtener el token del localStorage
    const token = localStorage.getItem('authTokens'); // Recuperar el token

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

    // Validar si todos los campos están llenos
    if (!nombres || !apellidos || !documentoIdentidad || !tipoDocumento || !estado || !genero || !userName || !celular || !rol || !imagenUser) {
        Swal.fire({
            title: "Campos incompletos",
            text: "Por favor complete todos los campos del formulario antes de enviar.",
            icon: "warning",
            confirmButtonText: "Aceptar"
        });
        return; // Detener la ejecución si hay campos vacíos
    }

    // Mostrar alerta de carga
    Swal.fire({
        title: "Registrando...",
        text: "Por favor espere mientras procesamos su registro.",
        allowOutsideClick: false,
        didOpen: () => {
            Swal.showLoading(); // Mostrar el indicador de carga
        }
    });

    // Datos del formulario
    let formData = {
        "nombres": nombres,
        "apellidos": apellidos,
        "tipoDocumento": tipoDocumento,
        "documentoIdentidad": documentoIdentidad,
        "genero": genero,
        "estado": estado,
        "userName": userName,
        "celular": celular,
        "rol": rol,
        "imagenUser": imagenUser
    };

    // Enviar los datos mediante AJAX
    $.ajax({
        url: urlUsuariosRegister + "register/",
        type: "POST",
        headers: {
            "Authorization": "Bearer " + token, // Usar el token obtenido
            "Content-Type": "application/json"
        },
        data: JSON.stringify(formData),
        success: function (result) {
            Swal.fire({
                title: "Registro Exitoso",
                text: "Su registro fue exitoso, por favor revise su correo.",
                icon: "success",
                confirmButtonText: "Aceptar"
            }).then((result) => {
                if (result.isConfirmed) {
                    limpiarFormulario();
                    // Redirigir a otra página después del registro exitoso
                    window.location.href = "/front-end/html/Roles/Administrador/administrarPerfiles.html";
                }
            });
        },
        error: function (xhr) {
            // Extraer el mensaje de error desde el JSON retornado
            let errorMessage = xhr.responseJSON ? xhr.responseJSON.message : "Error al guardar el usuario.";
            Swal.fire({
                title: "Error",
                text: errorMessage,
                icon: "error"
            });
        }
    });
}

function limpiarFormulario() {
    document.getElementById("register-form").reset(); // Limpiar todos los campos del formulario
}

function verificarSesion() {
    const token = localStorage.getItem('authTokens');

    // Si no hay token, redirigir a la página de inicio
    if (!token) {
        window.location.href = "/front-end/html/index.html";
    }
}

// Llamar a la función de verificación cuando cargue la página
window.onload = verificarSesion;

function cerrarSesion() {
    // Limpiar el localStorage
    localStorage.removeItem('authTokens');
    localStorage.removeItem('userRol');
    localStorage.removeItem('userEstado');

    // También puedes usar clear() para limpiar todo el localStorage
    // localStorage.clear();

    // Redirigir al usuario a la página de inicio (opcional si el href ya lo hace)
    window.location.href = "/front-end/html/index.html";
}
