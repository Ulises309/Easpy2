$('document').ready(function () {
    wsPath = 'http://localhost:62783';
	controladorGenerico = 'Generico';

    token = localStorage.getItem('access_token');
    auth_token = 'test';
    TOKEN_TYPE = 'bearer';

    nav = '.navbar-nav';
    navlinkC = 'nav-link';
    navlink = '.' + navlinkC;
    formulario = '.formulario';
    divReporte = '#seleccionarReporteDiv';
    cbReporte = '#reporteCBox';
    respuesta = '#respuesta';

    login_path = 'Login.html';
	cambio_password = 'CambiarPassword.html';
	pagina_inicial = 'Index.html';
	
	//SheetJS
	rABS = true;
});