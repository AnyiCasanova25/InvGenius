// URL de la API se declara una url por si se modifica solo cambiar donde la declaro y no entodas las demas partes
// var url = "http://10.192.66.26:8080/api/v1/user/";

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

function buscarUserPorFiltro(filtro) {
    if (filtro.trim() !== "") {
        $.ajax({
            url: "http://localhost:8080/api/v1/user/busquedaFiltros/" + filtro,
            type: "GET",
            success: function (result) {
                mostrarTabla(result);
            },
        });
    } else {
        listarUser();
    }
}
function mostrarTabla(result){
    var cuerpoTabla = document.getElementById("cuerpoTabla");
    cuerpoTabla.innerHTML = "";

    for (var i = 0; i < result.length; i++) {
        var trRegistro = document.createElement("tr");
        trRegistro.innerHTML = `
            <td class="align-middle">${result[i]["documentoIdentidad"]}</td>
            <td class="text-center align-middle">${result[i]["nombres"]}</td>
            <td class="text-center align-middle">${result[i]["apellidos"]}</td>
            <td class="text-center align-middle">${result[i]["correo"]}</td>
            <td class="text-center align-middle">${result[i]["celular"]}</td>
            <td class="text-center align-middle">${result[i]["rol"]}</td>
            <td class="text-center align-middle">
                <i class="fa-solid fa-user-slash cambiarEstado" data-id="${result[i]["idUser"]}"></i>
            </td>
        `;
        cuerpoTabla.appendChild(trRegistro);
    }
}


function blanquearCampos() {
    document.getElementById('texto').value = "";
}

$(document).on("click", ".cambiarEstado", function () {
    var idUser = $(this).data("id");
    $.ajax({
        url: url + idUser,
        type: "DELETE",
        success: function () {
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Cambio de estado exitoso",
                showConfirmButton: false,
                timer: 1500
            });
            listarUser();
        }
    });
});

// Llamar a la función para listar cliente al cargar la página
$(document).ready(function () {
    listarUser();
});
function actualizarlistarUser() {
    listarUser();
}