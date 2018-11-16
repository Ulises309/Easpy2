$('document').ready(function () {

    $('#submit').click(function () {

        var username = $('#username').val();
        var password = $('#password').val();

        var sesion = login(username, password);

        if ('error' in sesion) {
            alert('Hubo un error al ingresar, compruebe sus datos');
            return;
        }

        localStorage.setItem('idUsuario', sesion.idUsuario);
        localStorage.setItem('access_token', sesion.access_token);

		if(sesion.cambiarPassword == '1')
		{
			window.document.href = cambio_password;
			window.location.replace(cambio_password);
		}
		else{
			window.location.href = pagina_inicial;
			window.location.replace(pagina_inicial);
		}
    });

    function login(username, password) {
        var respuesta;
        param = { username: username, password: password, grant_type: 'password' };
        
        $.ajax({
            url: wsPath + '/login',
            type: 'POST',
            data: param,
            contentType: "application/x-www-form-urlencoded",
            async: false,
            complete: function (resultado) {
                respuesta = resultado.responseJSON;				
            }
        });

        return respuesta;
    }



});