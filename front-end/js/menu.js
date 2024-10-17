function initMenu() {
    const menuToggle = document.querySelector('.menu-toggle');
    const menu = document.querySelector('.menu');

    if (menuToggle) {
        menuToggle.addEventListener('click', function () {
            menu.classList.toggle('show');
        });
    } else {
        console.error('Element with class "menu-toggle" not found.');
    }
}

// Solo para asegurar que la función se ejecute cuando el DOM esté cargado
document.addEventListener('DOMContentLoaded', initMenu);

// Exporta la función para poder llamarla desde otros scripts
if (typeof window !== 'undefined') {
    window.initMenu = initMenu;
}

function cargarPerfil() {
    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlProfileUser,
        type: "GET",
        headers: {
            "Authorization": "Bearer " + token
        },
        success: function (result) {
            // console.log(result);
            document.getElementById("nombreUsuario").innerText = result["nombres"];
        }
    });
}
$(document).ready(function () {
    cargarPerfil();
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