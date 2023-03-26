// Call the dataTables jQuery plugin
$(document).ready(function() {
   // on ready
});


async function registrarUsuario(){

    let datos = {};

    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellidos = document.getElementById('txtApellidos').value;
    datos.telefono = document.getElementById('txtTelefono').value;
    datos.email = document.getElementById("txtEmail").value;
    datos.telefono = document.getElementById("txtTelefono").value;
    datos.password = document.getElementById('txtPassword').value;

    let repetirPass = document.getElementById('txtRepetirPassword').value;

    if(repetirPass != datos.password){
        alert("Las contraseñas no coinciden");
        return;
    }

    const req = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    alert("La cuenta fue creada con exito.")
    window.location.href = 'login.html'

}
