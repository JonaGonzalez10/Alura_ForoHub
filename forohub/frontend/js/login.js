document.addEventListener('DOMContentLoaded', function() {
    console.log("La página ha sido completamente cargada.");
});

document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Previene el comportamiento por defecto del formulario

    console.log("Formulario de inicio de sesión enviado."); // Paso 2

    // Obtiene los valores del formulario
    var correo = document.getElementById('usuarioInicio').value;
    var contrasena = document.getElementById('contrasenaInicio').value;

    // Crea el objeto que se enviará
    var loginData = {
        correo: correo,
        contrasena: contrasena
    };

    // Envía la solicitud al servidor
    fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Authorization': 'Basic ' + btoa('user:Nastya'),
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginData),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Respuesta del servidor no es OK');
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            alert('Inicio de sesión exitoso.');
            // Aquí puedes redirigir al usuario o hacer algo con la respuesta
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('Error al iniciar sesión. Por favor, intenta de nuevo.');
        });
});