const container = document.querySelector(".container");
const btnSignIn = document.getElementById("btn-sign-in");
const btnSignUp = document.getElementById("btn-sign-up");

btnSignIn.addEventListener("click", () => {
   container.classList.remove("toggle");
});
btnSignUp.addEventListener("click", () => {
   container.classList.add("toggle");
});


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
         const token = result.token; // Asegúrate de que esto sea correcto
         localStorage.setItem('authTokens', token); // Almacenar el token
         if(result.rol == "Admin"){
            window.location.href = "/front-end/html/Roles/Administrador/vistaPrevia.html/productosCaducados.html";
         }else{
            window.location.href = "/front-end/html/Roles/Usuario/vistaPrevia.html/productosCaducados.html";
         }
         
      },
      error: function (error) {
         Swal.fire({
            title: "Error de Validación",
            text: "Los datos ingresados son incorrectos. Por favor, verifica la información y vuelve a intentarlo.",
            icon: "error",
            confirmButtonText: "Aceptar"
         });
      }
   });
}



