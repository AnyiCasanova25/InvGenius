// var url = "http://10.192.80.134:8080/api/v1/user/";
var url = "http://localhost:8080/api/v1/user/";

function registrarUser() {
    // event.preventDefault(); // Evitar el comportamiento predeterminado del formulario

    // Obtener los valores de los campos del formulario
    let nombres = document.getElementById("Nombres").value;
    let apellidos = document.getElementById("Apellidos").value;
    let documentoIdentidad = document.getElementById("documentoIdentidad").value;
    let correo = document.getElementById("Correo").value;
    let celular = document.getElementById("Celular").value;
    let rol = document.getElementById("rol").value;
    let imagenUser = document.getElementById("imagenUser").value;

    // Datos del formulario
    let formData = {
        "nombres": nombres,
        "apellidos": apellidos,
        "documentoIdentidad": documentoIdentidad,
        "correo": correo,
        "celular": celular,
        "rol": rol,
        "imagenUser": imagenUser
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











// URL de la API se declara una url por si se modifica solo cambiar donde la declaro y no entodas las demas partes
// var url = "http://10.192.80.134:8080/api/v1/user/";

// URL de la API
var url = "http://localhost:8080/api/v1/user/";

function listarUser() {
    $.ajax({
        url: url,
        type: "GET",
        success: function (result) {
            mostrarTabla(result);
        },
        error: function (error) {
            alert("Error en la petición: " + error);
        }
    });
}

function mostrarTabla(result) {
    var cuerpoTabla = document.getElementById("cuerpoTabla");
    cuerpoTabla.innerHTML = "";

    for (var i = 0; i < result.length; i++) {
        var iconClass = result[i]["rol"] === "Administrador" ? "fa-user-tie" : "fa-user-large";

        var trRegistro = document.createElement("tr");
        trRegistro.innerHTML = `
            <td class="align-middle">${result[i]["documentoIdentidad"]}</td>
            <td class="align-middle">${result[i]["documentoIdentidad"]}</td>
            <td class="text-center align-middle">${result[i]["nombres"]}</td>
            <td class="text-center align-middle">${result[i]["apellidos"]}</td>
            <td class="text-center align-middle">${result[i]["correo"]}</td>
            <td class="text-center align-middle">${result[i]["celular"]}</td>
            <td class="text-center align-middle">${result[i]["rol"]}</td>
            <td class="text-center align-middle">${result[i]["estado"]}</td>
            <td class="text-center align-middle">
                <i class="fa-solid ${iconClass} cambiarEstado" data-id="${result[i]["idUser"]}" data-rol="${result[i]["rol"]}"></i>
                <i class="fa-solid fa-pen-to-square abrirModal" data-id="${result[i]["idUser"]}"></i> 
            </td>
        `;
        cuerpoTabla.appendChild(trRegistro);
    }
}

// Al hacer clic en el icono para cambiar el estado
$(document).on("click", ".cambiarEstado", function () {
    var idUser = $(this).data("id");
    var icon = $(this); // Referencia al icono
    var rolActual = $(this).data("rol"); // Rol actual

    // Mostrar una alerta de confirmación antes de cambiar el rol
    Swal.fire({
        title: "¿Estás seguro?",
        text: `¿Quieres cambiar el rol?`,
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, cambiar",
        cancelButtonText: "Cancelar"
    }).then((result) => {
        if (result.isConfirmed) {
            // Aquí se realiza la solicitud al servidor para cambiar el rol
            $.ajax({
                url: url + idUser, // URL del usuario
                type: "DELETE", // Cambia a la petición necesaria (si es DELETE o PUT)
                success: function () {
                    // Si la solicitud es exitosa, cambiar el ícono
                    if (icon.hasClass("fa-user-large")) {
                        icon.removeClass("fa-user-large").addClass("fa-user-tie"); // Cambiar a administrador
                    } else {
                        icon.removeClass("fa-user-tie").addClass("fa-user-large"); // Cambiar a usuario
                    }

                    // Mostrar mensaje de éxito
                    Swal.fire({
                        position: "top-end",
                        icon: "success",
                        title: "Cambio de rol exitoso",
                        showConfirmButton: false,
                        timer: 1500
                    });
                    listarUser(); // Actualizar la tabla después del cambio de estado
                },
                error: function () {
                    // Si hay un error en la solicitud, mostrar mensaje de error
                    Swal.fire({
                        icon: "error",
                        title: "Error al cambiar el rol",
                        text: "No se pudo cambiar el rol. Inténtalo de nuevo.",
                    });
                }
            });
        }
    });
});

// Llamar a la función para listar usuarios al cargar la página
$(document).ready(function () {
    listarUser();
});


// Mostrar el modal y cargar los datos del usuario cuando se hace clic en el ícono de edición
$(document).on("click", ".abrirModal", function () {
    var idUser = $(this).data("id");

    // Obtener los datos del usuario
    $.ajax({
        url: url + idUser,
        type: "GET",
        success: function (user) {
            // Rellenar los campos del modal con los datos del usuario
            $('#userId').val(user.idUser);
            $('#nombre').val(user.nombres);
            $('#apellido').val(user.apellidos);
            $('#correo').val(user.correo);
            $('#documento').val(user.documentoIdentidad);
            $('#telefono').val(user.celular);

            
            // Mostrar el modal
            $('#exampleModal').modal('show');
        },
        error: function () {
            Swal.fire({
                icon: "error",
                title: "Error al cargar los datos",
                text: "No se pudo cargar los datos del usuario. Inténtalo de nuevo.",
            });
        }
    });
});

// Guardar los cambios al hacer clic en el botón de guardar
$('#saveChanges').on('click', function () {
    var userId = $('#userId').val();
    var FormData = {
        nombres: $('#nombre').val(),
        apellidos: $('#apellido').val(),
        documentoIdentidad: $('#documento').val(),
        celular: $('#telefono').val(),
        correo: $('#correo').val()

    };

    // Enviar los datos actualizados al servidor
    $.ajax({
        url: url + userId,
        type: "PUT",
        data: JSON.stringify(FormData),
        contentType: "application/json",
        success: function () {
            $('#exampleModal').modal('hide');
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Datos actualizados exitosamente",
                showConfirmButton: false,
                timer: 1500
            });
            listarUser(); // Actualizar la tabla después de la edición
        },
        error: function () {
            Swal.fire({
                icon: "error",
                title: "Error al actualizar los datos",
                text: "No se pudo actualizar los datos del usuario. Inténtalo de nuevo.",
            });
        }
    });
});
