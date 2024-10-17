function salirDeMiPerfil() {
    // Obtener los valores almacenados en localStorage
    const token = localStorage.getItem('authTokens');
    const rol = localStorage.getItem('userRol');
    const estado = localStorage.getItem('userEstado');

    // Verificar que el token exista
    if (token) {
        // Verificar el estado y redirigir según el rol
        if (estado === "Activo") {
            if (rol === "Admin") {
                window.location.href = "/front-end/html/Roles/Administrador/vistaPrevia.html/productosCaducados.html";
            } else if (rol === "User") {
                window.location.href = "/front-end/html/Roles/Usuario/vistaPrevia.html/productosCaducados.html";
            } else {
                Swal.fire({
                    title: "Error",
                    text: "Rol desconocido. Contacta con el administrador.",
                    icon: "error",
                    confirmButtonText: "Aceptar"
                });
            }
        } else {
            Swal.fire({
                title: "Cuenta Inactiva",
                text: "Tu cuenta está inactiva. Por favor, contacta al administrador.",
                icon: "warning",
                confirmButtonText: "Aceptar"
            });
        }
    } else {
        Swal.fire({
            title: "Error",
            text: "Token no encontrado. Inicia sesión nuevamente.",
            icon: "error",
            confirmButtonText: "Aceptar"
        });
    }
}
function cargarRolyNombresApellidos(){
    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlProfileUser,
        type: "GET",
        headers: {
            "Authorization": "Bearer " + token
        },
        success: function (result) {
            // console.log(result);
            document.getElementById("nombreUsuario").innerText = result["nombres"] + " " + result["apellidos"];
            document.getElementById("rolUsuario").innerText = result["rol"];
            document.getElementById("nombre").value = result["nombres"];
            document.getElementById("apellido").value = result["apellidos"];
            document.getElementById("tipodocu").value = result["tipoDocumento"];
            document.getElementById("numerodocu").value = result["documentoIdentidad"];
            document.getElementById("correo").value = result["correo"];
            document.getElementById("Celular").value = result["celular"];


        }
    });
}
$(document).ready(function () {
    cargarRolyNombresApellidos();
});


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

