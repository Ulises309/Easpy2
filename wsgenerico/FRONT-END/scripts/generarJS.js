var reportes;
var campos;
var menu;
var r;
var hijo;

function init() {
        reportes = obtenerReportes();
        if (reportes == null) {
			alert('Sin reportes');
        }
        llenarReporteCBox(reportes);
        menu = service(controladorGenerico, 'menuReporte');
        llenarMenu(menu);
		
		$('.navbar-brand').click(function () {
        volver();
		});
		
		$(navlink).click(function () {
			if($(this).attr('href') == '#')
				return;
		});
		
		$('.dropdown-item').click(function () {
			window.location.href = $(this).attr('href');
		});

		$(cbReporte).change(function () {
			r = $(this).val();
			construirFormulario(r);
		});
    }



    function llenarReporteCBox( arreglo ) {
        for (var i = 0; i < arreglo.length; i++) {
            if(arreglo[i].Orden > 0)
                $(cbReporte).append('<option value="' + arreglo[i].Id_Reporte + '">' + arreglo[i].Nombre + '</option>');
        }
    }

    function llenarMenu(arreglo) {
        for (var i = 0; i < arreglo.length; i++) {
			if(arreglo[i].Padre==0){
				if( $(nav + '[menu="'+arreglo[i].IdMenu+'"]').length == 0) {
					$(nav).append(
						'    <li class="nav-item"  menu="' + arreglo[i].IdMenu + '">' +
						'    <a class="'+navlinkC+'" href="'+arreglo[i].URL+'">' + arreglo[i].Menu + '</a>' +
						'    </li >');
				}
			}
			else{
				if($('[menu="'+arreglo[i].Padre+'"] '+navlink).children().length == 0 ){
					$('[menu="'+arreglo[i].Padre+'"]').addClass('dropdown');
					$('[menu="'+arreglo[i].Padre+'"] '+navlink).addClass('dropdown-toggle');
					$('[menu="'+arreglo[i].Padre+'"] '+navlink).attr('id', 'menu'+arreglo[i].Padre);
					$('[menu="'+arreglo[i].Padre+'"] '+navlink).attr('role', 'button');
					$('[menu="'+arreglo[i].Padre+'"] '+navlink).attr('data-toggle', 'dropdown');
					$('[menu="'+arreglo[i].Padre+'"] '+navlink).attr('aria-haspopup', 'true');
					$('[menu="'+arreglo[i].Padre+'"] '+navlink).attr('aria-expanded', 'false');
					$('[menu="'+arreglo[i].Padre+'"] '+navlink).append(
						'<div class="dropdown-menu" aria-labelledby="menu'+ arreglo[i].Padre +'"></div>'
					);
				}
				$('.nav-item[menu="'+arreglo[i].Padre+'"] .dropdown-menu').append(
						'<a class="dropdown-item" href="'+arreglo[i].URL+'">' + arreglo[i].Menu + '</a>');
			}
        }
    }

	

    function construirFormulario(reporte) {
        $(formulario).empty();
        $(formulario).show();
        $(respuesta).empty();
        $(divReporte).hide();
        var nombre;
        for (var i = 0; i < reportes.length; i++) {
            if (reportes[i].Id_Reporte == reporte) {
                nombre = reportes[i].Nombre;
            }
        }
		$(formulario).attr('reporte',reporte);
        $(formulario).append('<h1 class="text-center">' + nombre + '</h1>');
        campos = obtenerCampos({ Reporte_Id: reporte });
        if (campos == null) {
            window.location.href = login_path;
        }
        for (var i = 0; i < campos.length; i++) {
            var grupo, control, label;
            grupo = $('<div>');
            grupo.addClass('form-group');
            label = $('<label for="' + campos[i].Titulo_Pagina + '" class="control-label">' + campos[i].Nombre + '</label>')
            switch (campos[i].Control_Id) {
                case 1:
                    control = $('<input>');
                    control.attr('type', 'text');
                    break;
                case 2:
                    control = $('<select>');
                    if (campos[i].ComboPadre != null) {
                        hijo = campos[i].Titulo_Pagina;
                        var padre = campos[i].ComboPadre;
                        padre = padre.split(',');
                        padre = padre[padre.length - 1];
                        control.attr('combopadre', campos[i].ComboPadre);
                        control.attr('campo', campos[i].ID_Campo);
                        $('#' + padre).attr('hijo', hijo);
                        $('#' + padre).addClass('padre');
                        hijo = null;
                    }
                    else {
                        llenarSelect(control, obtenerDataSet(campos[i].ID_Campo));
                    }
                    break;
                case 3:
                    control = $('<input>');
                    control.attr('type', 'date');
                    break;
                case 4:
                    control = $('<input>');
                    control.attr('type', 'file');
                    break;
                case 5:
                    control = $('<select>');
                    llenarSelect(control, obtenerDataSet(campos[i].ID_Campo));
                    break;
                case 6:
                    control = $('<textarea>');
                    control.attr('rows', '6');
                    break;
                default:
                    control = $('<input>');
                    control.attr('type', 'text');
                    break;
            }
            if(campos[i].Visible == 0) {
                grupo.hide();
            }
            if (campos[i].Valor_Variable.length > 0) {
                control.val(control.val() + campos[i].Valor_Variable);
            }
            switch (campos[i].Variable_Id) {
                case 1:
                    break;
                case 2:
                    control.attr('type', 'number');
                    break;
                case 3:
                    control.val('$' + control.val());
                    break;
                case 4:
                    control.val('$' + control.val());
                    break;
                case 6:
					control.attr('tipo', 'XML');
					control.attr('dataset', campos[i].DataSet);
                    break;
				case 7:
					control.attr('tipo', 'AGD');
					break;
				case 8:
					control.attr('tipo', 'ARC');
					break;
				case 9:
					control.attr('tipo', 'FTX');
					break;
				case 10:
					control.attr('tipo', 'ARF');
					break;
            }
            control.addClass('form-control');

            if (campos[i].Omitir != 1 || !'Omitir' in campos[i])
                control.addClass('parametro');

            control.attr('name', campos[i].Titulo_Pagina);
            grupo.append(label);
            grupo.append(control);
            $(formulario).append(grupo);
        }
        var boton;
        boton = $('<button>');
        boton.addClass('btn btn-primary');
		boton.addClass('btnGenerar');
        boton.html('Generar');
		boton.attr('reporte',reporte);
        $('.padre').change(function () {
            var h = $(this).attr('hijo');
            h = $('#' + h);
            var padres = h.attr('combopadre');
            padres = padres.split(',');
            comboPadre(h, padres);
        });
        $(formulario).append(boton);
		$(respuesta).attr('reporte', reporte);
    }

    function mostrarResultado(data, reporte) {
        $(respuesta + '[reporte="'+reporte+'"]').empty();
        if (data.length == 0) {
            $(respuesta).append('<p>No hay resultados.<p>');
            return;
        }
        var tabla;
        tabla = $('<table>');
        tabla.addClass('table');
        var thead;
        thead = $('<thead>');
        var trh;
        trh = $('<tr>');
        var headers = Object.keys(data[0]);
        for (var i = 0; i < headers.length; i++) {
            trh.append('<th>'+headers[i]+'</th>');
        }
        thead.append(trh);
        tabla.append(thead);
        var tbody;
        tbody = $('<tbody>');
        for (var i = 0; i < data.length; i++) {
            var tr;
            tr = $('<tr>');
            for (var j = 0; j < headers.length; j++) {
                tr.append('<td>' + data[i][headers[j]] + '</td>');
            }
            tbody.append(tr);
        }
        tabla.append(tbody);
        $(respuesta+ '[reporte="'+reporte+'"]').append(tabla);
    }

    function volver() {
		$('#reporteCBox').prop("selectedIndex", 0);
        $(divReporte).show();
        $(formulario).empty();
        $(formulario).hide();
        $(respuesta).hide();
        $(respuesta).empty();
    }

    function llenarSelect(select, data) {
        select.empty();
        select.append('<option selected disabled>--- Seleccione uno ---</opcion>');
        for (var i = 0; i < data.length; i++) {
            var keys = Object.keys(data[i]);
            select.append('<option value="' + data[i][keys[0]] +'">'+data[i][keys[1]]+'</option>')
        }
    }

    function comboPadre(hijo, arreglo) {
        var campo = hijo.attr('campo');
        var param = { ID_Campo: campo };;
        var valores = [];
        for (var i = 0; i < arreglo.length; i++) {
            var parametro = arreglo[i];
            var valor = $('#' + parametro).val();
            if (valor == '' || valor == null)
                return;
            var p = {};
            p[parametro] = valor;
            valores.push(p);
        }
        param.valores = valores;
        var lista = service(controladorGenerico, 'comboPadre', param);
        llenarSelect(hijo, lista);
    }

    function obtenerReportes(){
        var respuesta = service(controladorGenerico, 'listaReportes');

        return respuesta;
    }

    function obtenerCampos(param) {
        var respuesta = service(controladorGenerico, 'camposReporte', param);
        return respuesta;
    }

    function ejecutar(reporte, param) {
        param = { sp: reporte, parametros: param };

        var respuesta = service(controladorGenerico, 'Exec', param);

        return respuesta;
    }

    function obtenerDataSet(campo) {
        
        param = { ID_Campo: campo };

        var respuesta = service(controladorGenerico, 'obtenerDataSet', param);
        
        return respuesta;
    }


$('document').ready(function () {

    

    init();

    

});