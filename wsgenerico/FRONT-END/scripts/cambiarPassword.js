$('document').ready(function(){

	$('#enviar').click(function(){

		var password =  $('#password').val();
		var passwordConfirma =  $('#passwordConfirma').val();

		if(password.lenght == 0 || passwordConfirma.lenght == 0)
		{
			alert('¡Debe llenar los campos!');
			return false;
		}

		if(password != passwordConfirma)
		{
			alert('¡Las contraseñas no coinciden!');
			return false;
		}

		var params =[];
		params.push(
		{
			password: password
		});
		params.push(
		{
			passwordConfirma: passwordConfirma
		});
		params = {parametros: params};
		service(controladorGenerico, 'cambiarPassword', params);
		
		localStorage.clear();

		window.document.href = login_path;
        window.location.replace(login_path);

	});

});