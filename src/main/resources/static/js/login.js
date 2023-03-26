// Call the dataTables jQuery plugin
$(document).ready(function() {
    // on ready
});


async function iniciarSesion(){

    let datos = {};

    datos.email = document.getElementById("txtEmail").value;
    datos.password = document.getElementById('txtPassword').value;

    const req = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    const response = await req.text();
    if(response != "FAIL"){
        localStorage.token = response;
        localStorage.email = datos.email;

        window.location.href = 'usuarios.html'
    }else{
        alert("Credenciales incorrectas. Intente nuevamente.");
    }

}
