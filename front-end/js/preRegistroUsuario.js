function registrarUser() {
    // Obtener los valores de los campos del formulario
    const nombres = document.getElementById("Nombres").value.trim();
    const apellidos = document.getElementById("Apellidos").value.trim();
    const documentoIdentidad = document.getElementById("documentoIdentidad").value.trim();
    const tipoDocumento = document.getElementById("tipoDocumento").value.trim();
    const estado = document.getElementById("estado").value.trim();
    const genero = document.getElementById("genero").value.trim();
    const userName = document.getElementById("userName").value.trim();
    const celular = document.getElementById("Celular").value.trim();
    const rol = document.getElementById("rol").value.trim();
    const imagenUser = document.getElementById("imagenUser").value.trim();

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
    const formData = {
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
        url: urlPreRegistro + "/",
        type: "POST",
        data: JSON.stringify(formData),
        contentType: "application/json", // Agregar el encabezado Content-Type
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
                    window.location.href = "/front-end/html/";
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
