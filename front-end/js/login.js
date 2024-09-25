const container = document.querySelector(".container");
const btnSignIn = document.getElementById("btn-sign-in");
const btnSignUp = document.getElementById("btn-sign-up");

btnSignIn.addEventListener("click", () => {
   container.classList.remove("toggle");
});
btnSignUp.addEventListener("click", () => {
   container.classList.add("toggle");
});

var url = "http://localhost:8080/api/v1/public/user/login";

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
         let tokens = JSON.parse(localStorage.getItem('authTokens')) || [];
         tokens.push(token);
         localStorage.setItem('authTokens', JSON.stringify(tokens));
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


