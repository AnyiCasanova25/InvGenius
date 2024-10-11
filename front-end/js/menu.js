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

function cargarPerfil(){
    const token = localStorage.getItem('authTokens');
    $.ajax({
        url: urlProfileUser,
        type: "GET",
        headers: {
            "Authorization": "Bearer " + token
        },
        success: function (result) {
            // console.log(result);
            document.getElementById("nombreUsuario").innerText=result["nombres"];
        }
    });
}
$(document).ready(function () {
    cargarPerfil();
});