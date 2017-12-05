$(document).ready(function(){
	$("#actualizadatos").click (function() {
		alertify.confirm("<h3>Esta seguro que desea editar los datos ?</h3>", function (e) {
            if (e) {
                  alertify.success("Editado con exito");
            } else { 
                        alertify.error("Cancelado");
            }
      }); 
	});
	
	 });

$('#show').click(function(e) {
	  
	  // Resetear, por si acaso has estado jugando con la otra propiedad
	  $('#element').css('visibility', 'visible');
	  
	  if( $('#element').is(":visible") ) {
	    $('#element').css('display', 'none'); 
	  } else {
	    $('#element').css('display', 'block');
	  }
	});


$('#sltFiltro').change(function(){
	if($(this).val() !== "0"){
		$('#inFiltro').val('');
		//$('#inFiltro').removeAttr('disabled');
		$('#inFiltro').prop('disabled', false);
		$('#btnFiltro').prop('disabled', false);
		
		
	}else{
		$('#inFiltro').val('');
		$('#inFiltro').prop('disabled', true);
		$('#btnFiltro').prop('disabled', true);
		//$('#inFiltro').attr('disabled','disabled');

	}
	
});

