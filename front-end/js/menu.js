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
            // Actualizar el nombre de usuario
            document.getElementById("nombreUsuario").innerText = result["nombres"];

            // Obtener el elemento de imagen (asegúrate de que el ID coincida con el HTML)
            const imgPerfil = document.getElementById("imgPerfil"); // Cambié "genero" por "imgPerfil"

            if (result["tipoDocumento"] === "Otro") {
                imgPerfil.src = "/front-end/img/ImgPerfilPredeterminados/compania.png"; 
            } else {
                if (result["rol"] === "Otro") {

            }



            // Cambiar la imagen según el género
            if (result["genero"] === "Femenino") {
                imgPerfil.src = "/front-end/img/ImgPerfilPredeterminados/mujer1.png"; 
            } else if (result["genero"] === "Masculino") {
                imgPerfil.src = "/front-end/img/ImgPerfilPredeterminados/hombre1.png"; 
            } else {
                imgPerfil.src = "/front-end/img/ImgPerfilPredeterminados/otro3.png"; 
            }
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