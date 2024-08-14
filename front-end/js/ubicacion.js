url: "http://10.192.66.26:8080/api/v1/ubicacion"

function registrarUbicacion(){
    let asignar_ubicacion = document.getElementById("asignarUbicacion").value;
    let bloques = document.getElementById("bloques").value;

    //Datos del formulario de ubicacion
    let formData = {
        "asignar_ubicacion": asignar_ubicacion,
        "bloques": bloques
    };

    $.ajax({
        url: url,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(formData),
        success: function (result){
            Swal.fire({
                title: "¡Excelente!",
                text: "Se guardó correctamente",
                icon: "success"
            });
            limpiarFormulario();
        },
        error:function (error){
            Swal.fire({
                title: "Error",
                text: "Error al guardar el libro",
                icon: "error"
            });
        }
    });
}