


// function Iniciar() {
//    let userName = document.getElementById("userName").value;
//    let password = document.getElementById("password").value;

//    let formData = {
//       "userName": userName,
//       "password": password
//    };

//    $.ajax({
//       url: urlLogin,
//       type: "POST",
//       data: JSON.stringify(formData),
//       contentType: "application/json",
//       success: function (result) {
//          const token = result.token; 
//          localStorage.setItem('authTokens', token); // Almacenar el token
//          if(result.rol == "Admin"){
//             window.location.href = "/front-end/html/Roles/Administrador/vistaPrevia.html/productosCaducados.html";
//          }else{
//             window.location.href = "/front-end/html/Roles/Usuario/vistaPrevia.html/productosCaducados.html";
//          }
         
//       },
//       error: function (error) {
//          Swal.fire({
//             title: "Error de Validación",
//             text: "Los datos ingresados son incorrectos. Por favor, verifica la información y vuelve a intentarlo.",
//             icon: "error",
//             confirmButtonText: "Aceptar"
//          });
//       }
//    });
// }
// function Iniciar() {
//    let userName = document.getElementById("userName").value;
//    let password = document.getElementById("password").value;

//    let formData = {
//       "userName": userName,
//       "password": password
//    };

//    $.ajax({
//       url: urlLogin,
//       type: "POST",
//       data: JSON.stringify(formData),
//       contentType: "application/json",
//       success: function (result) {
//          console.log(result); // Agregamos un log para ver la respuesta completa
         
//          const token = result.token;
//          localStorage.setItem('authTokens', token); // Almacenar el token
         
//          const rol = result.rol; 
//          const estado = result.estado; 
//          console.log('Rol recibido: ', rol); 
//          console.log('Estado recibido: ', estado); 

//          if (rol === "Admin") {
//             window.location.href = "/front-end/html/Roles/Administrador/vistaPrevia.html/productosCaducados.html";
//          } else if (rol === "User") {
//             window.location.href = "/front-end/html/Roles/Usuario/vistaPrevia.html/productosCaducados.html";
//          } else {
//             Swal.fire({
//                title: "Error",
//                text: "Rol desconocido. Contacta con el administrador.",
//                icon: "error",
//                confirmButtonText: "Aceptar"
//             });
//          }
//       },
//       error: function (error) {
//          Swal.fire({
//             title: "Error de Validación",
//             text: "Los datos ingresados son incorrectos. Por favor, verifica la información y vuelve a intentarlo.",
//             icon: "error",
//             confirmButtonText: "Aceptar"
//          });
//       }
//    });
// }


function Iniciar() {
   let userName = document.getElementById("userName").value;
   let password = document.getElementById("password").value;

   let formData = {
       "userName": userName,
       "password": password
   };

   $.ajax({
       url: urlLogin,
       type: "POST",
       data: JSON.stringify(formData),
       contentType: "application/json",
       success: function (result) {
           const token = result.token;
           localStorage.setItem('authTokens', token); // Almacenar el token
           localStorage.setItem('userRol', result.rol);
           localStorage.setItem('userEstado', result.estado);

           const rol = result.rol;
           const estado = result.estado;

           if (estado === "Activo") {
               // Llamar a la función para cargar los detalles del usuario
               $.ajax({
                   url: urlProfileUser,
                   type: "GET",
                   headers: {
                       "Authorization": "Bearer " + token
                   },
                   success: function (profileResult) {
                       const cambiarPassword = profileResult.cambiarPassword; // Suponiendo que aquí obtienes el valor

                       if (cambiarPassword) { // Si cambiarPassword es true
                           window.location.href = "/front-end/html/Login/actualizarContrasena.html";
                       } else {
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
                       }
                   },
                   error: function (error) {
                       console.error("Error al cargar la información del usuario", error);
                       Swal.fire({
                           title: "Error",
                           text: "No se pudo cargar la información del perfil. Inténtalo de nuevo.",
                           icon: "error",
                           confirmButtonText: "Aceptar"
                       });
                   }
               });
           } else {
               Swal.fire({
                   title: "Cuenta Inactiva",
                   text: "Tu cuenta está inactiva. Por favor, contacta al administrador.",
                   icon: "warning",
                   confirmButtonText: "Aceptar"
               });
           }
       },
       error: function (error) {
           const errorMsg = error.responseJSON ? error.responseJSON.message : "Los datos ingresados son incorrectos.";
           Swal.fire({
               title: "Error de Validación",
               text: "Acceso denegado",
               icon: "error",
               confirmButtonText: "Aceptar"
           });
       }
   });
}
