// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#usuariosTable').DataTable();
});


async function cargarUsuarios(){
    const req = await fetch('api/usuarios', {
      method: 'GET',
      headers: getHeaders()
    });
    const users = await req.json();


    let listadoHTML = ''
    for(let user of users){
        let botonEliminar = '<a href="#" onclick ="deleteUser('+user.id+')" class="btn btn-danger btn-circle btn-sm">\n'+
            '        <i class="fas fa-trash"></i>\n' +
            '   </a>\n';
        let usuarioshtml= '<tr>\n' +
            ' <td>'+user.id+'</td>\n' +
            ' <td>'+user.nombre+'</td>\n' +
            ' <td>'+user.apellidos+'</td>\n' +
            ' <td>'+user.email+'</td>\n' +
            ' <td>'+user.telefono+'</td>\n' +
            ' <td>\n' +
            botonEliminar+
            ' </td>\n' +
            ' </tr>';
        listadoHTML += usuarioshtml;
    }

    document.querySelector('#usuariosTable tbody').outerHTML = listadoHTML;



function getHeaders(){
    return {
        'Accept': 'application/json',
        'Content-type': 'application/json',
        'Auth': localStorage.token
    }
}

async function deleteUser(id) {
    if (!confirm('¿Desea eliminar este usuario?')) {
        return;
    }
    const req = await fetch('api/usuarios/' + id, {
        method: 'DELETE',
        headers: getHeaders(),
    });
    location.reload();
    }
}