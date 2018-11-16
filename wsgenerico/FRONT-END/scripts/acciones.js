var cola = 0;
var params = [];

function obtenerParametrosUrl(){
	if(window.location.search.length == 0)
		return "";
	var url = window.location.search.substr(1);

	var parametros = { };
	
	var p = url.split('&');
	
	for(var i = 0; i < p.length; i++){
		var parametro = p[i].split('=');
		parametros[ parametro[0] ] = parametro[1];
	}
	
	return parametros;
	
}

function llenarInputs(r, parametros){
	var inputs = Object.keys(parametros);
	
	var as = false;
	for(var i = 0; i < inputs.length; i++){
		var sensible = inputs[i].toUpperCase();
		switch(sensible){
			case 'REPORTE':
				break;
			case 'AS':
				if(parametros[inputs[i]] == 1 || parametros[inputs[i]] == '1')
					as = true;
				break;
			case 'NA':
				break;
			case 'RO':
				break;
			case '':
				break;
			default:
				$('.formulario[reporte="'+r+'"]').find('[name="' + inputs[i] + '"]').val(parametros[inputs[i]]);
				break;
		}
		
	}
	if(as){
			$('.btnGenerar[reporte="'+r+'"]').click();
			$('.formulario[reporte="'+r+'"]').hide();
		}
}

function crearModal(p){
	var parametros = { };
	
	var pAux = p.split('&');
	
	for(var i = 0; i < pAux.length; i++){
		var parametro = pAux[i].split('=');
		parametros[ parametro[0] ] = parametro[1];
	}
	
	var r = parametros.Reporte;
	
	var codigo = 
	'<div class="modal fade" reporte="'+r+'" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">' +
	'  <div class="modal-dialog modal-dialog-centered" role="document">' +
	'	<div class="modal-content">' +
	'	  <div class="modal-header">' +
	'		<h5 class="modal-title" id="exampleModalLongTitle"></h5>' +
	'		<button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
	'		  <span aria-hidden="true">&times;</span>' + 
	'		</button>' +
	'	  </div>' +
	'	  <div class="modal-body">' +
	'	  	<div class="formulario" reporte="'+r+'">' +
				obtenerFormulario(r) +
	'	  	</div>' +
	'	  	<div class="respuesta" reporte="'+r+'">' +
	'	  	</div>' +
	'     </div>' +
	'	  <div class="modal-footer">' +
	'		<button type="button" class="btn btn-primary btnGenerar" reporte="'+r+'">Generar</button>' +
	'	  </div>' +
	'	</div>' +
	'  </div>' +
	'</div>';
	
	$('body').append(codigo);
	
	llenarInputs(r, parametros);
	
	$('.modal[reporte="'+r+'"]').modal('show');
}

function obtenerFormulario(reporte) {
		var formulario = $('<div>');
        var nombre;
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
            formulario.append(grupo);
        }
        $('.padre').change(function () {
            var h = $(this).attr('hijo');
            h = $('#' + h);
            var padres = h.attr('combopadre');
            padres = padres.split(',');
            comboPadre(h, padres);
        });
		return formulario.html();
    }

function wait(ms) {
    var start = Date.now(),
        now = start;
    while (now - start < ms) {
      now = Date.now();
    }
}
function mostrarResultado(data, reporte) {
    $('.respuesta[reporte="'+reporte+'"]').empty();
    $('.respuesta[reporte="'+reporte+'"]').show();
    if (data.length == 0) {
        $(respuesta).append('<p>No hay resultados.<p>');
        return;
    }
    var tabla;
    tabla = $('<table>');
    tabla.addClass('table');
    tabla.addClass('table-striped');
    tabla.addClass('table-bordered');
    var thead;
    thead = $('<thead>');
    thead.addClass('thead-dark');
    var trh;
    trh = $('<tr>');
    var headers = Object.keys(data[0]);
    for (var i = 0; i < headers.length; i++) {
        trh.append('<th>' + headers[i] + '</th>');
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
    tabla.DataTable(
        {
            dom: 'Bfrtip',
            buttons: [
                'excel', 'pdf'
            ],
            "language": {
                "url": "https://cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json"
            }
        }
    );
    $('.respuesta[reporte="'+reporte+'"]').append(tabla);
}

function restaurarFormulario(reporte) {
    $('.formulario[reporte="' + reporte + '"] .parametro').each(function (i, obj) {
        if(obj.value[0]!='$')
            obj.value = '';
    });
}

function comparar(a,a2){
	if(a.length!=a2.length)
		return false;
	
	for(var i = 0; i < a.length; i++){
		if(a[i]!=a2[i])
			return false;
	}
	return true;
}

function volver() {
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
        select.append('<option value="' + data[i][keys[0]] + '">' + data[i][keys[1]] + '</option>')
    }
}

function obtenerReportes() {
    var respuesta = service(controladorGenerico, 'listaReportes');

    return respuesta;
}

function obtenerCampos(param) {
    var respuesta = service(controladorGenerico, 'camposReporte', param);
    return respuesta;
}
function obtenerDataSet(campo) {
        
        param = { ID_Campo: campo };

        var respuesta = service(controladorGenerico, 'obtenerDataSet', param);
        
        return respuesta;
    }

function ejecutar(reporte, param) {
    param = { sp: reporte, parametros: param };
	params = [];
	if(cola>0)
		return;
    var respuesta = service(controladorGenerico, 'Exec', param);

    return respuesta;
}

function btnGenerar(boton) {
    var reporte = $(boton).attr('reporte');
    $('.formulario[reporte="' + reporte + '"] .parametro').each(function (i, obj) {
		
		var p = {};
        if (obj.value == '' || obj.value == null)
            return;
		if (!obj.hasAttribute('tipo')) {
		 p[$(obj).attr('name')] = obj.value;
		}
		if( $(obj).attr('tipo') == 'XML'){
			cola++;
			var dataset = $(obj).attr('dataset');
			obtenerXML(reporte, obj.id, obj.files[0], dataset);
			return;
		}
		if( $(obj).attr('tipo') == 'AGD'){
			
		}
		if( $(obj).attr('tipo') == 'ARC'){
			
		}
		if( $(obj).attr('tipo') == 'FTX'){
			
		}
		if( $(obj).attr('tipo') == 'ARF'){
			
		}
		if( $(obj).attr('tipo') == 'AGD'){
			p[$(obj).attr('name')] = 'No implementado';
		}
        
        params.push(p);
    });
    ejecutarAsync(reporte);
}

function ejecutarAsync(reporte){
	var tabla = ejecutar(reporte, params);
	if(tabla == null){
	return;
	}
	params = [];
	mostrarResultado(tabla, reporte);
	restaurarFormulario(reporte);
}

function obtenerBase64(file) {
   var reader = new FileReader();
   reader.readAsDataURL(file);
   reader.onload = function () {
     console.log(reader.result);
	 return reader.result;
   };
   reader.onerror = function (error) {
     console.log('Error: ', error);
   };
}

function obtenerXML(reporte, p, file, dataset) {
	  var f = file;
	  var reader = new FileReader();
	  var XML;
	  reader.onload = function(e) {
		var data = e.target.result;
		if(!rABS) data = new Uint8Array(data);
		var workbook = XLSX.read(data, {type: rABS ? 'binary' : 'array'});

		var JSON = XLSX.utils.sheet_to_json(workbook.Sheets[workbook.SheetNames[0]]);
		
		dataset = dataset.split(',');
		var encabezados = Object.keys(JSON[0]);
		if(!comparar(encabezados, dataset)){
			alert('El archivo no es valido');
			return false;
		}
		
		JSON = { REG: JSON };
		
		JSON = { ARCH: JSON };
		
		XML = json2xml(JSON);
		
		
		
		var parametro = {};
		parametro[p] = XML;
		params.push(parametro);
		cola--;
		ejecutarAsync(reporte);
	  };
	  if(rABS) reader.readAsBinaryString(f); else reader.readAsArrayBuffer(f);
	  
	  
	  return XML;
	}
	
	function json2xml(o, tab) {
	   var toXml = function(v, name, ind) {
		  var xml = "";
		  if (v instanceof Array) {
			 for (var i=0, n=v.length; i<n; i++)
				xml += ind + toXml(v[i], name, ind+"\t") + "\n";
		  }
		  else if (typeof(v) == "object") {
			 var hasChild = false;
			 xml += ind + "<" + name;
			 for (var m in v) {
				if (m.charAt(0) == "@")
				   xml += " " + m.substr(1) + "=\"" + v[m].toString() + "\"";
				else
				   hasChild = true;
			 }
			 xml += hasChild ? ">" : "/>";
			 if (hasChild) {
				for (var m in v) {
				   if (m == "#text")
					  xml += v[m];
				   else if (m == "#cdata")
					  xml += "<![CDATA[" + v[m] + "]]>";
				   else if (m.charAt(0) != "@")
					  xml += toXml(v[m], m, ind+"\t");
				}
				xml += (xml.charAt(xml.length-1)=="\n"?ind:"") + "</" + name + ">";
			 }
		  }
		  else {
			 xml += ind + "<" + name + ">" + v.toString() +  "</" + name + ">";
		  }
		  return xml;
	   }, xml="";
	   for (var m in o)
		  xml += toXml(o[m], m, "");
	   return tab ? xml.replace(/\t/g, tab) : xml.replace(/\t|\n/g, "");
	}

$('document').ready(function () {
	
	if(token == null){
		window.document.href = login_path;
        window.location.replace(login_path);
	}
		
	service(controladorGenerico, 'checarLogin');
	
	var parametros = obtenerParametrosUrl();
	
    init();
    function init() {
		if(parametros.length!=""){
			try{
					r = parametros.Reporte;
					construirFormulario(r);
					llenarInputs(r, parametros);
				}
			catch{}
			
		}
		else{
			$('#seleccionarReporteDiv').show();
		}
		
		
        $('select').each(function (index) {
            var attr = $(this).attr('combopadre');
            if (typeof attr !== typeof undefined && attr !== false) {
                var hijo = $(this).attr('id');
                var padre = attr;
                padre = padre.split(',');
                padre = padre[padre.length - 1];
                $('#' + padre).attr('hijo', hijo);
                $('#' + padre).addClass('padre');
            }
            else {
                attr = $(this).attr('campo');
                if (typeof attr !== typeof undefined && attr !== false) {
                    llenarSelect($(this), obtenerDataSet(attr));
                }
            }
        });

        $('.padre').change(function () {
            var h = $(this).attr('hijo');
            h = $('#' + h);
            var padres = h.attr('combopadre');
            padres = padres.split(',');
            var campo = h.attr('campo');
            var param = { ID_Campo: campo };
            var valores = [];
            for (var i = 0; i < padres.length; i++) {
                var parametro = $('#' + padres[i]).attr('parametro');
                var valor = $('#' + padres[i]).val();
                if (valor == '' || valor == null)
                    return;
                var p = {};
                p[parametro] = valor;
                valores.push(p);
            }
            param.valores = valores;
            var lista = service(controladorGenerico, 'comboPadre', param);
            llenarSelect(h, lista);
        });
    }

    $('body').on('click','.btnGenerar',function () {
        btnGenerar($(this));
    });
	
	$('body').on('click','.reporte-modal', function(){
		crearModal($(this).attr('data'));
	});
	
	$('body').on('hide.bs.modal', '.modal' , function(){
		$(this).remove();
	});
	
	$('body').on('show.bs.modal', '.modal' , function(){
       $(this).find('.modal-body').css({
              width:'auto', //probably not needed
              height:'auto', //probably not needed 
              'max-height':'100%'
       });
});
	
});


