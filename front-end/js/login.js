const container = document.querySelector(".container");
const btnSignIn = document.getElementById("btn-sign-in");
const btnSignUp = document.getElementById("btn-sign-up");

btnSignIn.addEventListener("click", () => {
   container.classList.remove("toggle");
});
btnSignUp.addEventListener("click", () => {
   container.classList.add("toggle");
});

// var url = "http://10.192.80.141:8080/api/v1/public/user/login";
var url = "http://10.192.80.141:8080/api/v1/public/user/login";

function Iniciar() {
   let userName = document.getElementById("userName").value;
   let password = document.getElementById("password").value;

   let formData = {
      "userName": userName,
      "password": password
   };

   $.ajax({
      url: url,
      type: "POST",
      data: JSON.stringify(formData),
      contentType: "application/json",
      success: function (result) {
         const token = result.token;
         //let tokens = localStorage.getItem('authTokens');
      
         localStorage.setItem('authTokens',token);
         window.location.href = "/front-end/html/Roles/Administrador/vistaPrevia.html/productosCaducados.html";
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


