function salirDeCambioContraseña() {
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

function validarPassword() {
    const password = document.getElementById('new-password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    const errorMessage = document.getElementById('password-error');
    const mismatchMessage = document.getElementById('password-mismatch-error');

    const letrasPermitidas = "abcdefghijklmnopqrstuvwxyzñÑABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    const numerosPermitidos = "0123456789".split("");
    const signosPermitidos = ". , @ _ - # ( )".split("");

    const tieneLetra = letrasPermitidas.some(letra => password.includes(letra));
    const tieneNumero = numerosPermitidos.some(numero => password.includes(numero));
    const tieneSigno = signosPermitidos.some(signo => password.includes(signo));

    if (password.length >= 8 && tieneLetra && tieneNumero && tieneSigno) {
        errorMessage.style.display = 'none'; // Ocultar error de validación si la contraseña es válida

        if (password === confirmPassword) {
            mismatchMessage.style.display = 'none'; // Ocultar error si las contraseñas coinciden
        } else {
            mismatchMessage.style.display = 'block'; // Mostrar error si no coinciden
        }
    } else {
        errorMessage.style.display = 'block'; // Mostrar error si la contraseña no cumple las reglas
        mismatchMessage.style.display = 'none'; // Ocultar error de coincidencia
    }
}

function actualizarPassword() {
    const token = localStorage.getItem('authTokens');
    const contrasenaAntigua = document.getElementById('contrasenaAntigua').value;
    const nuevaPassword = document.getElementById('new-password').value;
    const confirmarPassword = document.getElementById('confirm-password').value;

    // Validar si la nueva contraseña es válida
    if (nuevaPassword !== confirmarPassword) {
        Swal.fire({
            title: "Error",
            text: "Las contraseñas no coinciden.",
            icon: "error",
            confirmButtonText: "Aceptar"
        });
        return;
    }

    const datosActualizar = {
        actualPassword: contrasenaAntigua,
        newPassword: nuevaPassword,
        confirmPassword: confirmarPassword
    };

    fetch(urlCambiarContraseña, {
        method: 'PUT',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datosActualizar)
    })
    .then(response => {
        console.log(response); // Ver la respuesta del servidor
        if (response.ok) {
            return response.json();
        } else {
            return response.text().then(text => { throw new Error(text); });
        }
    })
    .then(data => {
        console.log("Contraseña actualizada:", data); // Verificar el resultado de la actualización
        Swal.fire({
            title: "Éxito",
            text: "Contraseña actualizada correctamente.",
            icon: "success",
            confirmButtonText: "Aceptar"
        }).then(() => {
            cerrarSesion(); // Cerrar sesión
        });
    })
    .catch(error => {
        console.error(error); // Ver el error en la consola
        Swal.fire({
            title: "Error",
            text: "Hubo un problema al actualizar la contraseña. " + error.message,
            icon: "error",
            confirmButtonText: "Aceptar"
        });
    });
}
