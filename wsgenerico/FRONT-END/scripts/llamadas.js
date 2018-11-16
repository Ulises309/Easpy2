
function service(controller, accion) {
    var respuesta = service(controller, accion, []);
    return respuesta;
}


function service(controller, accion, param) {
	
	if(token==null){
        window.document.href = login_path;
        window.location.replace(login_path);
		return false;
	}
	
    var respuesta;
    param = JSON.stringify(param);
    var headers = {
        Authorization: TOKEN_TYPE + ' ' + token,
        auth_token: auth_token
    };
    $.ajax({
        url: wsPath + '/api/' + controller + '/' + accion,
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: param,
        headers: headers,
        async: false,
        success: function (resultado) {
            respuesta = resultado;
        },
        error: function (httpObj, resultado) {
            if (httpObj.status == 401) {
                localStorage.clear();
                window.document.href = login_path;
                window.location.replace(login_path);
            }
			if (httpObj.status == 412){
				window.document.href = cambio_password;
                window.location.replace(cambio_password);
			}
        }
    });

    return respuesta;
}